from fastapi import APIRouter
from httpx import AsyncClient
from time import sleep
from pydantic import BaseModel
from json import dumps

from app.core.tmdb_crossing import discover, content, loadSeason
from app.models.tmdb import TMDB_Discover, TMDB_Movie, TMDB_TV, TMDB_Season
from app.logger import log

router = APIRouter()

class Respond(BaseModel):
    release_date_gte: str = ""
    release_date_lte: str = ""
    total_disc_pages: int = 1
    total_records: int = 0
    total_bytes_stored: float = 0.0

async def imdb_rating(mv: TMDB_Movie) -> float:
    rating = 0
    from imdb import Cinemagoer

    imdb_instance = Cinemagoer()
    try:
        movie = imdb_instance.get_movie(mv.imdb_id[2:])
        if movie != None and 'rating' in movie.keys():
            rating = movie['rating']
    except Exception as e:
        rating = 0
        log.error(e)
    
    return rating



@router.post("/load/movies")
async def loadMvs(gte: str, lte: str, country: str = "", language:str = "") -> Respond:
    
    pg = 1
    mvs_count = 0
    total_bytes_stored = 0
    log.info(f'loadMvs called with gte, {gte}, and lte, {lte}. Country: {country}. Language: {language}')
    async with AsyncClient() as client:
        max_pages = 1
        
        while pg <= max_pages:
            res = await discover(client=client, date_gte=gte, date_lte=lte, language=language, country=country, page=pg, content="movie")
            pg += 1
            if res.status_code == 200:
                log.info(f'Page {pg-1} response 200, max_pages: {max_pages}')
                data = res.json()
                dis_mvs = TMDB_Discover(**data)

                if max_pages < dis_mvs.total_pages:
                    max_pages = dis_mvs.total_pages
                
                for mv in dis_mvs.results:
                    res = await content(client=client, content_id=mv["id"], content="movie")
                    
                    if res.status_code == 200:
                        data = res.json()
                        tmdb_movie = TMDB_Movie(**data)
                        if language == "en" and tmdb_movie.imdb_id != None:
                            rating = await imdb_rating(tmdb_movie)
                            if rating >= 5.5:
                                await tmdb_movie.save()
                                mvs_count += 1
                                total_bytes_stored += len(dumps(data).encode("utf-8"))
                        else:
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
    resp.release_date_gte = gte
    resp.release_date_lte = lte
    resp.total_disc_pages = pg-1
    resp.total_records = mvs_count
    resp.total_bytes_stored = total_bytes_stored

    return resp


@router.post("/load/tvs")
async def loadTvs(gte: str, lte: str, country: str = "", language:str = "") -> Respond:
    
    pg = 1
    tvs_count = 0
    total_bytes_stored = 0
    log.info(f'loadTvs called with gte, {gte}, and lte, {lte}. Country: {country}. Language: {language}')
    async with AsyncClient() as client:
        max_pages = 1
        
        while pg <= max_pages:
            res = await discover(client=client, date_gte=gte, date_lte=lte, language=language, country=country, page=pg, content="tv")
            pg += 1
            if res.status_code == 200:
                log.info(f'Page {pg-1} response 200, max_pages: {max_pages}')
                data = res.json()
                dis_tvs = TMDB_Discover(**data)

                if max_pages < dis_tvs.total_pages:
                    max_pages = dis_tvs.total_pages
                
                for tv in dis_tvs.results:
                    res = await content(client=client, content_id=tv["id"], content="tv")
                    
                    if res.status_code == 200:
                        data = res.json()
                        tmdb_tv = TMDB_TV(**data)
                        season_count = await loadSeason(client=client, tv_id=tv["id"], total_seasons=len(tmdb_tv.seasons))
                        await tmdb_tv.save()
                        tvs_count += 1
                        total_bytes_stored += len(dumps(data).encode("utf-8"))
                    elif res.status_code == 429:
                        log.error('Received 429 for a tv call, sleeping one second')
                        sleep(1) # Just to wait if the rate limit is reached
                    else:
                        log.error(f'In Tv Call: {res}')
                        return res
            elif res.status_code == 429:
                log.error('Received 429 for a discover call, waiting one second')
                sleep(1) # Just to wait if the rate limit is reached
            else:
                log.error(f'In Discover Call: {res}')
                return res


        log.info(f'Load Complete total_disc_pages: {pg-1}, total_records: {tvs_count}, total_bytes_stored:{total_bytes_stored}.')
    
    resp = Respond()
    resp.release_date_gte = gte
    resp.release_date_lte = lte
    resp.total_disc_pages = pg-1
    resp.total_records = tvs_count
    resp.total_bytes_stored = total_bytes_stored

    return resp