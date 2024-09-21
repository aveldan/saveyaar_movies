from fastapi import FastAPI

from contextlib import asynccontextmanager
from enum import Enum

from app.core.db import Database
from app.logger import log


class tmp(str, Enum):
    a = "option_a"
    b = "option_b"

@asynccontextmanager
async def lifespan(app: FastAPI):
    await Database.connect()
    log.info("Mongo client connected and beanie initialised successfully")

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