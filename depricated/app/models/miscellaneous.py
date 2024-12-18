from pydantic import BaseModel
from datetime import date

class t_path(str):
    def validate(cls, value: str) -> str:
        pass # Write validation for paths after base url

class t_url(str):
    def validate(cls, value: str) -> str:
        pass # Write validation for url

class t_country(str):
    def validate(cls, value: str) -> str:
        """
        Field( # Add a better validator to check if it is in ISO 3166-1
            default=None,
            min_length=2,
            max_length=2,
            pattern="^[A-Z]+$"
        )
        """
        pass

class t_lang(str):
    def validate(cls, value: str) -> str:
        """
        Field( # Add a better validator to check if it is in ISO 639-1
            default=None,
            min_length=2,
            max_length=2,
            pattern="^[a-z]+$"
        )
        """
        pass

class t_title(str):
    def validate(cls, value: str) -> str:
        # Write a validator for content title
        pass

class t_cert(str):
    def validate(cls, value: str) -> str:
        # Write a validator for cert
        pass

class t_name(str):
    def validate(cls, value: str) -> str:
        # Write a validator for name
        """
        Field(
            default=None,
            max_length=10,
            pattern="^[a-zA-Z]+$"
        )  
        """
        pass

class t_crew(str):
    def validate(cls, value: str) -> str:
        #Validate to limit the lenght
        pass

class t_overview(str):
    def validate(cls, value: str) -> str:
        """
        Field(
            default=None,
            max_length=500
        )
        """
        pass

class language(BaseModel):
    language: t_lang
    local_title: t_title | None = None
    is_original_language: bool | None = None

    class Config:
        arbitrary_types_allowed = True

class release_date(BaseModel):
    country: t_country
    certification: t_cert | None = None
    release_date: date | None = None
    note: str | None = None # Write validator to limit the length

    class Config:
        arbitrary_types_allowed = True


class cast(BaseModel):
    role: str | None = None
    character_name: t_name | None = None

    class Config:
        arbitrary_types_allowed = True

class cast_crew(BaseModel):
    display_name: t_name
    image_path: t_path | None = None
    crew_roles: list[t_crew] | t_crew | None = None
    cast_roles: list[cast] | cast | None = None

    class Config:
        arbitrary_types_allowed = True