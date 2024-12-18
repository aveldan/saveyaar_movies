from pydantic import BaseModel, Field
from beanie import Document

from app.models.miscellaneous import t_name, t_path

class name(BaseModel):
    firstName: t_name | None = None
    lastName: t_name | None = None
    otherName: t_name | None = None
    displayName: t_name | None = None

    class Config:
        arbitrary_types_allowed = True


class t_gender(str):
    def validate(cls, value: str) -> str:
        pass  # Validate this to only certain values

class People(Document):
    name: name
    gender: t_gender | None = None
    image_path: t_path | None = None # Validate this to only contain urls
    summary: str | None = Field(
        default=None,
        max_length=100
    )
    roles_played: list[str] | None = None # Can remove this, this will create infinite increase of document size

    class Settings:
        name="People"
        keep_nulls=False

    class Config:
        arbitrary_types_allowed = True