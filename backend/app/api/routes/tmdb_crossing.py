from fastapi import APIRouter
from httpx import AsyncClient
from time import sleep
from pydantic import BaseModel
from json import dumps

from app.core.tmdb_crossing import discover, movie
from app.models.tmdb import TMDB_Discover_Movie, TMDB_Movie
from app.logger import log

router = APIRouter()

class Respond(BaseModel):
    total_disc_pages: int = 1
    total_records: int = 0
    total_bytes_stored: float = 0.0
    
@router.post("/load/movies")
async def loadMvs(gte: str, lte: str) -> Respond:
    
    pg = 1
    mvs_count = 0
    total_bytes_stored = 0
    log.info(f'loadMvs called with gte, {gte}, and lte, {lte}.')
    async with AsyncClient() as client:
        max_pages = 1
        
        while pg <= max_pages:
            res = await discover(client=client, rd_gte=gte, rd_lte=lte, page=pg)
            pg += 1
            if res.status_code == 200:
                log.info(f'Page {pg-1} response 200, max_pages: {max_pages}')
                data = res.json()
                dis_mvs = TMDB_Discover_Movie(**data)

                if max_pages < dis_mvs.total_pages:
                    max_pages = dis_mvs.total_pages
                
                for mv in dis_mvs.results:
                    res = await movie(client=client, mv_id=mv["id"])
                    
                    if res.status_code == 200:
                        data = res.json()
                        tmdb_movie = TMDB_Movie(**data)
                        await tmdb_movie.save()
                        mvs_count += 1
                        total_bytes_stored += len(dumps(data).encode("utf-8"))
                    elif res.status_code == 429:
                        log.error('Received 429 for a movie call, sleeping one second')
                        sleep(1) # Just to wait if the rate limit is reached
                    else:
                        log.error(f'In Movies Call: {res}')
                        return res
            elif res.status_code == 429:
                log.error('Received 429 for a discover call, waiting one second')
                sleep(1) # Just to wait if the rate limit is reached
            else:
                log.error(f'In Discover Call: {res}')
                return res


        log.info(f'Load Complete total_disc_pages: {pg-1}, total_records: {mvs_count}, total_bytes_stored:{total_bytes_stored}.')
    
    resp = Respond()
    resp.total_disc_pages = pg-1
    resp.total_records = mvs_count
    resp.total_bytes_stored = total_bytes_stored

    return resp