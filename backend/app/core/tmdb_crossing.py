from dotenv import dotenv_values
from httpx import Response, ReadTimeout
from time import sleep

from app.logger import log

urls = {
    "mv": "https://api.themoviedb.org/3/movie/",
    "disc_mv": "https://api.themoviedb.org/3/discover/movie",
}

config = dotenv_values("./.env")

async def discover(client, rd_gte, rd_lte, page = 1) -> Response:

    params = {
        "with_origin_country": "IN",
        "release_date.gte": str(rd_gte),
        "release_date.lte": str(rd_lte),
        "page": page
    }
                
    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    res = None
    while(res == None):
        try:
            res = await client.get(urls['disc_mv'], params=params, headers=headers)
        except ReadTimeout:
            log.error('READ TIMEOUT HAPPENED IN DISCOVER')
            sleep(1)
             
    return res

async def movie(client, mv_id) -> Response:
    
    params = {
        "append_to_response": "release_dates,translations,watch/providers"
    }

    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    res = None
    while(res == None):
        try:
            res = await client.get(urls['mv']+str(mv_id), params=params, headers=headers)
        except ReadTimeout:
            log.error('READ TIMEOUT HAPPENED IN MOVIES')
            sleep(1)
            
    return res
# discover("2024-01-01", "2024-10-09")