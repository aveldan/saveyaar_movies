from fastapi import FastAPI

from dotenv import dotenv_values
from contextlib import asynccontextmanager
from enum import Enum

from app.core.db import Database
from app.logger import log
from app.api.router import router

class tmp(str, Enum):
    a = "option_a"
    b = "option_b"

@asynccontextmanager
async def lifespan(app: FastAPI):
    config = dotenv_values("./.env")
    
    await Database.connect(config["MONGO_URI"], config["DB"])
    log.info("Mongo client connected and beanie initialised successfully")

    app.include_router(router, prefix=config["API_PREFIX"])

    yield
    Database.close()
    log.info("Mongo client closed successfully")

app = FastAPI(lifespan=lifespan)

@app.get("/{item}")
async def root(item: tmp):
    if item is tmp.a:
        return {"message": "Hello World, this is saveyaar_movies",
            "item": 'A'}
    else:
        return {"message": "Hello World, this is saveyaar_movies",
            "item": 'B'}