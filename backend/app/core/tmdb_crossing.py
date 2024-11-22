from dotenv import dotenv_values
from httpx import Response, ReadTimeout
from time import sleep

from app.logger import log
from app.models.tmdb import TMDB_Season

urls = {
    "movie": "https://api.themoviedb.org/3/movie/",
    "tv": "https://api.themoviedb.org/3/tv/",
    "tv_season": "https://api.themoviedb.org/3/tv/",
    "tv_episode": "https://api.themoviedb.org/3/tv/",
    "disc": "https://api.themoviedb.org/3/discover/"
}

config = dotenv_values("./.env")

def generate_params(date_gte: str, date_lte: str, language: str = "", country: str = "", page: int = 1, content: str = "movie") -> dict:
    params = {}
    params["page"] = page

    if content == "movie":
        params["release_date.gte"] = date_gte
        params["release_date.lte"] = date_lte
    else:
        params["first_air_date.gte"] = date_gte
        params["first_air_date.lte"] = date_lte
    
    if country != "":
        params["with_origin_country"] = country
    if language != "":
        params["with_original_language"] = language

    return params

async def discover(client, date_gte: str = "", date_lte: str = "", language: str = "", country: str = "", page: int = 1, content: str = "movie") -> Response:

    params = generate_params(date_gte=date_gte, date_lte=date_lte, language=language, country=country, page=page, content=content)
                
    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    res = None
    while(res == None):
        try:
            res = await client.get(f'{urls["disc"]}{content}', params=params, headers=headers)
        except ReadTimeout:
            log.error('READ TIMEOUT HAPPENED IN DISCOVER')
            sleep(1)
             
    return res


async def content(client, content_id: int, season_num: str = 0, episode_num: str = 0, content: str = "movie") -> Response:
    params = {}
    if content == "movie":
        params["append_to_response"] = "release_dates,translations,watch/providers"
    elif content == "tv":
        params["append_to_response"] = "translations"
    elif content == "tv_season":
        params["append_to_response"] = "translations,watch/providers"
    else:
        params["append_to_response"] = "translations"
    
    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    res = None
    url = f'{urls[content]}{content_id}'
    if season_num > 0 and episode_num > 0:
        url = url + f'/season/{season_num}/episode/{episode_num}'
    elif season_num > 0:
        url = url + f'/season/{season_num}'

    while(res == None):
        try:
            res = await client.get(url, params=params, headers=headers)
        except ReadTimeout:
            log.error('READ TIMEOUT HAPPENED IN CONTENT')
            sleep(1)
            
    return res

async def loadSeason(client, tv_id: int, total_seasons: int = 1) -> dict:
    season_count = 0

    for i in range(0, total_seasons):
        res = await content(client=client, content_id=tv_id, season_num=i+1, content="tv_season")

        if res.status_code == 200:
            data = res.json()
            data.pop("_id")
            tmdb_season = TMDB_Season(**data)

            episodes = []
            for j in range(0, len(tmdb_season.episodes)):
                res = await content(client=client, content_id=tv_id, season_num=i+1, episode_num=j+1, content="tv_episode")

                if res.status_code == 200:
                    data = res.json()
                    episodes.append(data)
                elif res.status_code == 429:
                    log.error('Received 429 for a Episode call, sleeping one second')
                    sleep(1) # Just to wait if the rate limit is reached
                    j -= 1
                else:
                    log.error(f'In Episode Call: {res}')
                    episodes.append(tmdb_season.episodes[j])
            
            tmdb_season.episodes = episodes
            await tmdb_season.save()
            season_count += 1
        elif res.status_code == 429:
            log.error('Received 429 for a Season call, sleeping one second')
            sleep(1) # Just to wait if the rate limit is reached
            i -= 1
        else:
            log.error(f'In Season Call: {res}')
            i -= 1

    return season_count