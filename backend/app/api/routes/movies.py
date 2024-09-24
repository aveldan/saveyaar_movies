from fastapi import APIRouter
from beanie import PydanticObjectId


router = APIRouter()

@router.get("/{movieID}")
async def getMovie(movieID: PydanticObjectId):
    """
    Get the movie with movie_id
    """
    pass