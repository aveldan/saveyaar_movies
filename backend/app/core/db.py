from motor.motor_asyncio import AsyncIOMotorClient, AsyncIOMotorDatabase
from dotenv import dotenv_values
from beanie import init_beanie

class Database:
    __client: AsyncIOMotorClient | None = None
    __db: AsyncIOMotorDatabase | None = None
    
    @staticmethod
    async def connect() -> None:
        config = dotenv_values("./.env")
        Database.__client = AsyncIOMotorClient(config["MONGO_URI"])
        Database.__db = Database.__client[config["DB"]]
        await init_beanie(database=Database.db(), document_models=[
            "app.models.people.People",
            "app.models.ott.OTT",
            "app.models.movie.Movie",
            "app.models.tv.TV",
            "app.models.season.Season"
        ])

    @staticmethod
    def close() -> None:
        if Database.__client == None:
            raise ConnectionError("Cannot close database client. Database client is not connected")
        
        Database.__client.close()

    @staticmethod
    def client() -> AsyncIOMotorClient:
        if Database.__client == None:
            raise ConnectionError("Database client is None, database client is not connected")
        
        return Database.__client
    
    @staticmethod
    def db() -> AsyncIOMotorDatabase:
        if Database.__db == None:
            raise ConnectionError("Database is None, database is not connected")
        
        return Database.__db    