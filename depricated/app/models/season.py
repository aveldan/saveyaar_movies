from pydantic import BaseModel
from beanie import Document

from app.models.ott import OTT_Availability
from app.models.miscellaneous import t_title, t_overview, cast_crew


class episode(BaseModel):
    episode_name: t_title | None = None
    overview: t_overview | None = None
    episode_number: int | None = None
    cast_n_crew: list[cast_crew] | None = None

    class Config:
        arbitrary_types_allowed = True

class Season(Document):
    season_name: t_title | None = None
    season_num: int | None = None
    overview: t_overview | None = None
    ott_availability: list[OTT_Availability] | None = None
    episodes: list[episode] | None = None

    class Settings:
        name = "Seasons"
        keep_nulls = False
    
    class Config:
        arbitrary_types_allowed = True