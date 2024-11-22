from pydantic import Field, BaseModel
from beanie import Document
from typing import Any

class TMDB_Discover(Document):
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


class TMDB_TV(Document):
    adult: Any
    backdrop_path: Any
    created_by: Any
    genres: Any
    homepage: Any
    id: Any
    in_production: Any
    languages: Any
    name: Any
    networks: Any
    origin_country: Any
    original_language: Any
    original_name: Any
    overview: Any
    popularity: Any
    poster_path: Any
    production_companies: Any
    production_countries: Any
    seasons: Any
    spoken_languages: Any
    status: Any
    tagline: Any
    type: Any
    translations: Any

    class Settings:
        name = "TMDB_TV"
        keep_nulls = True


class TMDB_Season(Document):
    air_date: Any
    episodes: Any
    name: Any
    overview: Any
    id: Any
    poster_path: Any
    season_number: Any
    vote_average: Any
    translations: Any
    watch_providers: Any = Field(alias="watch/providers")

    class Settings:
        name = "TMDB_Seasons"
        keep_nulls = True

class TMDB_Episode(BaseModel):
    air_date: Any
    crew: Any
    episode_number: Any
    guest_stars: Any
    name: Any
    overview: Any
    id: Any
    production_code: Any
    runtime: Any
    still_path: Any
    translations: Any