from pydantic import Field
from beanie import Document
from typing import Any

class TMDB_Discover_Movie(Document):
    page: Any
    results: Any
    total_pages: Any
    total_results: Any

    class Settings:
        name = "TMDB_DIS_MV"
        keep_nulls = True

class TMDB_Movie(Document):
    adult: Any
    backdrop_path: Any
    belongs_to_collection: Any
    budget: Any
    genres: Any
    homepage: Any
    id: Any
    imdb_id: Any
    origin_country: Any
    original_language: Any
    original_title: Any
    overview: Any
    popularity: Any
    poster_path: Any
    production_companies: Any
    production_countries: Any
    release_date: Any
    revenue: Any
    runtime: Any
    spoken_languages: Any
    status: Any
    tagline: Any
    title: Any
    video: Any
    vote_average: Any
    vote_count: Any
    release_date: Any
    translations: Any
    watch_providers: Any = Field(alias="watch/providers")

    class Settings:
        name = "TMDB_MV"
        keep_nulls = True