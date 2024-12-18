from fastapi import APIRouter
from beanie import PydanticObjectId


router = APIRouter()

@router.get("/{peopleID}")
async def getPeople(peopleID: PydanticObjectId):
    """
    Get the movie with peopleID
    """
    pass