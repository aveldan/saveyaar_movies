from fastapi import APIRouter
from beanie import PydanticObjectId


router = APIRouter()

@router.get("/{tvID}")
async def getTV(tvID: PydanticObjectId):
    """
    Get the movie with tvID
    """
    pass