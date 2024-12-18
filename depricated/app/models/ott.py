from pydantic import BaseModel
from beanie import Document
from datetime import datetime

from app.models.miscellaneous import t_path, t_country, t_url

class t_ott_name(str):
    def validate(cls, value: str) -> str:
        """
        Write a validator
        Field(
            max_length=15,
            pattern="^[a-zA-Z0-9]+$"
        )
        """
        pass

class OTT(Document):
    name: t_ott_name
    home_page: t_url | None = None
    logo_path: t_path | None = None

    class Config:
        arbitrary_types_allowed = True

    class Settings:
        name = "OTTs"
        keep_nulls = False

class OTT_Record(BaseModel):
    name: t_ott_name
    start_date: datetime
    end_date: datetime | None = None

    class Config:
        arbitrary_types_allowed = True

class OTT_Availability(BaseModel):
    country: t_country
    ott_records: list[OTT_Record] | None = None

    class Config:
        arbitrary_types_allowed = True