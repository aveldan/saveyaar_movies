from beanie import Document

class Genre(Document):
    genre: str # Validate to limit length

    class Settings:
        name = "Genres"
        keep_nulls = False