from pydantic import BaseModel, Field
from beanie import Document

class name(BaseModel):
    firstName: str | None = Field(
        default=None,
        max_length=15,
        regex="^[a-zA-Z]+$"
    )
    lastName: str | None = Field(
        default=None,
        max_length=15,
        regex="^[a-zA-Z]+$"
    )
    otherName: str | None = Field(
        default=None,
        max_length=25,
        regex="^[a-zA-Z]+$"
    )
    displayName: str | None = Field(
        default=None,
        max_length=10,
        regex="^[a-zA-Z]+$"
    )


class People(Document):
    name: name
    gender: str | None = None # Validate this to only certain values
    imageUrl: str | None = None # Validate this to only contain urls
    summary: str | None = Field(
        default=None,
        max_length=100
    )
    roles_played: list[str] | None = None # Can remove this, this will create infinite increase of document size

    class Settings:
        name="People"
        keep_nulls=False