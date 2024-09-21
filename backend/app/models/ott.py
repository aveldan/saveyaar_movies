from pydantic import Field
from beanie import Document

class OTT(Document):
    name: str = Field(
        max_length=15,
        regex="^[a-zA-Z0-9]+$"
    )
    homePage: str | None = None # Validate this to only contain urls
    logoUrl: str | None = None # Validate this to only contain urls

    class Settings:
        name = "OTTs"
        keep_nulls = False
