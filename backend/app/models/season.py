from pydantic import BaseModel, Field
from beanie import Document, PydanticObjectId
from datetime import datetime

class ott_history(BaseModel):
    ottID: PydanticObjectId
    ottName: str = Field(
        max_length=15,
        pattern="^[a-zA-Z0-9]+$"
    )
    language: str | None = Field( # Add a better validator to check if it is in ISO 639-1
        default=None,
        min_length=2,
        max_length=2,
        pattern="^[a-z]+$"
    ) 
    country: str | None = Field( # Add a better validator to check if it is in ISO 3166-1
        default=None,
        min_length=2,
        max_length=2,
        pattern="^[A-Z]+$"
    )
    start_date: datetime
    end_date: datetime | None = None 

class ppl(BaseModel):
    peopleID: PydanticObjectId
    displayName: str | None = Field(
        default=None,
        max_length=10,
        pattern="^[a-zA-Z]+$"
    )
    imageUrl: str | None = None # Validate this to only contain urls
    crewRoles: list[str] | str | None = None

class episode(BaseModel):
    episodeName: str | None = None
    plot: str | None = Field(
        default=None,
        max_length=500
    )
    episodeNum: int | None = None
    crew: list[ppl] | None = None

class Season(Document):
    seasonName: str | None = None
    seasonNum: int | None = None
    ott: list[ott_history] | None = None
    episodes: list[episode] | None = None

    class Settings:
        name = "Seasons"
        keep_nulls = False