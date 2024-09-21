from pydantic import BaseModel, Field
from beanie import Document, PydanticObjectId
from datetime import date, datetime

class certification(BaseModel):
    country: str | None = Field( # Add a better validator to check if it is in ISO 3166-1
        default=None,
        min_length=2,
        max_length=2,
        regex="^[A-Z]+$"
    )
    certification: str | None = None
    releaseDate: date | None = None


class ott_history(BaseModel):
    ottID: PydanticObjectId
    ottName: str = Field(
        max_length=15,
        regex="^[a-zA-Z0-9]+$"
    )
    country: str | None = Field( # Add a better validator to check if it is in ISO 3166-1
        default=None,
        min_length=2,
        max_length=2,
        regex="^[A-Z]+$"
    )
    start_date: datetime
    end_date: datetime | None = None


class language(BaseModel):
    language: str | None = Field( # Add a better validator to check if it is in ISO 639-1
        default=None,
        min_length=2,
        max_length=2,
        regex="^[a-z]+$"
    )
    localTitle: str | None = None
    isOriginalLang: bool | None = None
    posterURL: bool | None = None
    backdropURL: bool | None = None
    ott: list[ott_history] | None = None

class cast(BaseModel):
    role: str | None = None
    characterName: str | None = None

class cast_crew(BaseModel):
    peopleId: PydanticObjectId
    displayName: str | None = Field(
        default=None,
        max_length=10,
        regex="^[a-zA-Z]+$"
    )  
    imageURL: str | None = None # Validate this to only contain urls
    crewRoles: list[str] | str | None = None
    castRoles: list[str] | str | None = None


class Movie(Document):
    enTitle: str | None = None
    plot: str | None = Field(
        default=None,
        max_length=500
    )
    certifications: list[certification]
    genre: list[str] | None = None
    languages: list[language] | None = None
    castCrew: list[cast_crew] | None = None

    class Settings:
        name = "Movies"
        keep_nulls = False