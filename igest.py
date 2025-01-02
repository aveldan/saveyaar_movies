import requests
from tqdm import tqdm

def discover_movies(date_gte: str, date_lte: str, lang: str, page: int = 1, country: str = "IN"):
    url: str = "http://localhost:8080/tmdb/ingest/discover/movies"
    
    params = {
        "primary_release_date.gte": date_gte,
        "primary_release_date.lte": date_lte,
        "with_original_language": lang,
        "with_origin_country": country,
        "page": page
    }

    response = requests.get(url=url, params=params)

    if response.status_code == 200:
        data = response.json()
        return data
    else:
        print(f"Request failed with status code: {response.status_code}")
        print("Error:", response.text)


def ingest_movie(id: int):
    
    url: str = f'http://localhost:8080/tmdb/ingest/movie/{id}'

    response = requests.post(url=url)

    if response.status_code == 200:
        data = response.json()
        return data
    else:
        print(f"Request failed with status code: {response.status_code}")
        print("Error:", response.text)

def main():
    mvs = discover_movies("2024-01-01", "2024-12-31", "hi")
    max_pages: int = 1
    max_pages = max(max_pages, mvs["total_pages"])

    
    for page in tqdm(range(1, max_pages+1)):
        mvs = discover_movies("2024-01-01", "2024-12-31", "hi", page)

        results = mvs["results"]
        for i in tqdm(range(0, len(results)), leave= False):
            movie = ingest_movie(results[i]["id"])
    
    return 0


main()