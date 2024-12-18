from dotenv import dotenv_values
from httpx import AsyncClient

from app.logger import log

config = dotenv_values("./.env")
async def movie(id: int):
    params = {
        'append_to_response': 'release_dates,translations,watch/providers',
    }

    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    ret = {}
    async with AsyncClient() as client:
        try:
            res = await client.get(f'{config["MOVIE_URL"]}/{id}', params=params, headers=headers)
            if res.status_code == 200:
                ret = res.json()
        except Exception:
            log.error(f'EXCEPTION')
    return ret