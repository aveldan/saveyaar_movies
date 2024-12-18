from beanie import Document

from app.models.genre import Genre
from app.models.ott import OTT_Availability
from app.models.miscellaneous import language, release_date, t_title, t_path, cast_crew, t_overview


class Movie(Document):
    title: t_title
    backdrop_path: t_path | None = None
    poster_path: t_path | None = None
    overview: t_overview | None = None 
    genre: list[Genre] | None = None
    release_dates: list[release_date] | None = None
    languages: list[language] | None = None
    ott_availability: list[OTT_Availability] | None = None
    cast_n_crew: list[cast_crew] | None = None

    class Config:
        arbitrary_types_allowed = True
        
    class Settings:
        name = "Movies"
        keep_nulls = False