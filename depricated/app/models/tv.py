from pydantic import BaseModel
from beanie import Document

from app.models.miscellaneous import t_title, t_overview, release_date, language, cast_crew
from app.models.genre import Genre
from app.models.people import People

class season(BaseModel):
    # seasonID: PydanticObjectId
    season_name: t_title | None = None

    class Config:
        arbitrary_types_allowed = True

class TV(Document):
    title: t_title | None = None
    overview: t_overview | None = None
    release_dates: list[release_date] | None = None
    genre: list[Genre] | None = None
    languages: list[language]
    seasons: list[season] | None = None
    cast_n_crew: list[cast_crew] | None = None
    createdBy: list[People] | None = None

    class Config:
        arbitrary_types_allowed = True
        
    class Settings:
        name = "TVs"
        keep_nulls = False