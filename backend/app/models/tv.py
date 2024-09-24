from pydantic import BaseModel, Field
from beanie import Document, PydanticObjectId

from app.models.movie import certification, language, cast

class season(BaseModel):
    seasonID: PydanticObjectId
    seasonName: str | None = None
    seasonNum: int | None = None

class ppl(BaseModel):
    peopleID: PydanticObjectId
    displayName: str | None = Field(
        default=None,
        max_length=10,
        pattern="^[a-zA-Z]+$"
    )
    imageUrl: str | None = None # Validate this to only contain urls
    castRoles: list[cast] | cast | None = None

class TV(Document):
    enTitle: str | None = None
    plot: str | None = Field(
        default=None,
        max_length=500
    )
    certifications: list[certification]
    genre: list[str] | None = None
    languages: list[language] # Using same language class as in movie, even though this model does not have ott in it. But will leave it null here
    seasons: list[season]
    cast: list[ppl]
    createdBy: list[ppl]

    class Settings:
        name = "TVs"
        keep_nulls = False