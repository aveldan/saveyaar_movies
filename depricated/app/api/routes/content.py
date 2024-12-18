from typing import Literal
from fastapi import APIRouter, Query
from beanie import PydanticObjectId

from app.core.content import movies

router = APIRouter()

@router.get("/test")
async def tester(ss: str | None = None):
    return {"Got it": ss}

@router.get("/movies")
async def getContentMovies(release_date: Literal['This Week', 'This Month', 'Upcoming']):
    """
    Get the latest content available to watch
    """
    
    res = await movies(relase_date=release_date)
    return res


@router.get("/{contentID}")
async def getContent(contentID: PydanticObjectId):
    """
    Get the content with contentID
    """
    pass