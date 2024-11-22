from fastapi import APIRouter
from pydantic import BaseModel
from typing import Any

from app.core.metadata import count_lanaguages_tmdb

router = APIRouter()

class Respond(BaseModel):
    total: int = 0
    results: list[dict] | None = None

@router.get("/tmdb/lang")
async def count_tmdb_languages(content: str = "movies"):

    res = await count_lanaguages_tmdb(content)
    response = Respond()
    response.results = res
    for lang in res:
        response.total += lang['count']
    return response