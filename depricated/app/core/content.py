from dotenv import dotenv_values
from datetime import datetime, timedelta
from httpx  import AsyncClient

from app.logger import log
from app.models.tmdb import TMDB_Discover

config = dotenv_values("./.env") 
async def movies(relase_date: str):
    headers = {
        "Authorization": f'Bearer {config["TMDB_TOKEN"]}'
    }

    print(relase_date)
    params = {}
    params["with_origin_country"] = "IN"
    params["sort_by"] = "popularity.desc"

    if relase_date == "This Week":
        today = datetime.now()
        week = today-timedelta(days=7)

        format_date = week.strftime('%Y-%m-%d')
        params["primary_release_date.gte"] = format_date

        format_date = today.strftime('%Y-%m-%d')
        params["primary_release_date.lte"] = format_date
    elif relase_date == "This Month":
        today = datetime.now()
        week = today-timedelta(days=30)

        format_date = week.strftime('%Y-%m-%d')
        params["primary_release_date.gte"] = format_date

        format_date = today.strftime('%Y-%m-%d')
        params["primary_release_date.lte"] = format_date
    elif relase_date == "Upcoming":
        today = datetime.now()
        format_date = today.strftime('%Y-%m-%d')
        params["primary_release_date.gte"] = format_date
    

    ret = {
        'featured': None,
        'movies': []
    }
    
    print(params)
    async with AsyncClient() as client:
        try:
            params["with_original_language"] = 'te'
            res = await client.get(f'{config["CONTENT_URL"]}/movie', params=params, headers=headers)
            if res.status_code == 200:
                data = res.json()
                mvs = TMDB_Discover(**data)
                results = mvs.results
                items = []
                for r in results:
                    item = {
                        'id': r['id'],
                        'poster_path': r['poster_path'],
                        'backdrop_path': r['backdrop_path'],
                        'title': r['title']
                    }

                    items.append(item)
                ret['featured'] = items[0]
                lst = {
                    "rowTitle": "Top Telugu for you",
                    "movies": items
                }

                ret['movies'].append(lst)
        except Exception:
            log.error(f'EXCEPTION')

        try:
            params["with_original_language"] = 'hi'
            res = await client.get(f'{config["CONTENT_URL"]}/movie', params=params, headers=headers)
            if res.status_code == 200:
                data = res.json()
                mvs = TMDB_Discover(**data)
                results = mvs.results
                items = []
                for r in results:
                    item = {
                        'id': r['id'],
                        'poster_path': r['poster_path'],
                        'backdrop_path': r['backdrop_path'],
                        'title': r['title']
                    }

                    items.append(item)
                lst = {
                    "rowTitle": "Top Hindi for you",
                    "movies": items
                }
                if ret['featured'] == None:
                    ret['featured'] = items[0]
                ret['movies'].append(lst)
        except Exception:
            log.error(f'EXCEPTION')


    return ret