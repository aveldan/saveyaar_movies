from httpx import AsyncClient, ReadTimeout
import asyncio
import json
import argparse
from time import sleep

async def loadMvs():

    dts = ['01-31', '02-28','03-31','04-30', '05-31','06-30','07-31', '08-31', '09-30','10-31', '11-30','12-31']
    start_year = 2022
    end_year = 2023

    responses = []
    async with AsyncClient() as client:
        for i in range(start_year, end_year+1):
            p = 0
            q = 0
            while q < len(dts):
                date_gte = f'{i}-{dts[q]}'
                date_lte = f'{i}-{dts[q]}'
                date_gte = date_gte[0:8] + "01"
                params = {
                    "gte": date_gte,
                    "lte": date_lte,
                    "language": "en"
                }
                res = await client.post("http://127.0.0.1:8000/api/v1/tmdbCrossing/load/movies", params=params, timeout=7200.0)

                if res.status_code == 200:
                    data = res.json()
                    responses.append(data)
                    print(f'Complete resquest date_gte:{date_gte}, date_lte:{date_lte}')
                else:
                    print(f'date_gte:{date_gte}, date_lte:{date_lte}, response: {res}')
                    continue
                p += 1
                q += 1


async def loadLang():

    dts = ['01-01', '04-30', '05-01', '08-31', '09-01', '12-31']
    start_year = 1920
    end_year = 2024
    with open('tmdb_lang_metadata.json', 'r') as jFile:
        data = json.load(jFile)['results']

    responses = []
    async with AsyncClient() as client:
        for lang in data:
            for i in range(start_year, end_year+1):
                p = 0
                q = 1
                while q < len(dts):
                    date_gte = f'{i}-{dts[p]}'
                    date_lte = f'{i}-{dts[q]}'
                    params = {
                        "gte": date_gte,
                        "lte": date_lte,
                        "language": lang["_id"]
                    }
                    res = await client.post("http://127.0.0.1:8000/api/v1/tmdbCrossing/load/movies", params=params, timeout=90.0)

                    if res.status_code == 200:
                        data = res.json()
                        responses.append(data)
                        print(f'Complete resquest date_gte:{date_gte}, date_lte:{date_lte}')
                    else:
                        print(f'date_gte:{date_gte}, date_lte:{date_lte}, response: {res}')
                        continue
                    p += 1
                    q += 1


async def loadTvs():

    start_year = 2008
    end_year = 2012

    responses = []
    month_days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    async with AsyncClient() as client:
        for year in range(start_year, end_year+1):
            for month in range(0, 12):
                for day in range(0, month_days[month]):
                    if month < 9:
                        m = f'0{month+1}'
                    else:
                        m = f'{month+1}'
                    
                    if day < 9:
                        d = f'0{day+1}'
                    else:
                        d = f'{day+1}'

                    date_gte = f'{year}-{m}-{d}'
                    date_lte = date_gte
                    
                    params = {
                        "gte": date_gte,
                        "lte": date_lte,
                        "country": "IN"
                    }
                    try:
                        res = await client.post("http://127.0.0.1:8000/api/v1/tmdbCrossing/load/tvs", params=params, timeout=90.0)
                        
                        if res.status_code == 200:
                            data = res.json()
                            responses.append(data)
                            print(f'Complete resquest date_gte:{date_gte}, date_lte:{date_lte}')
                        else:
                            print(f'date_gte:{date_gte}, date_lte:{date_lte}, response: {res}')
                            continue
                    except ReadTimeout:
                        print('READ TIMEOUT HAPPENED')
                        sleep(60)


parser = argparse.ArgumentParser()
parser.add_argument('--L', default=None, type=str)
parser.add_argument('--TV', default=False, type=bool)

args = parser.parse_args()

if args.TV:
    asyncio.run(loadTvs())
else:
    if args.L == None:
        asyncio.run(loadMvs())
    else:
        asyncio.run(loadLang())