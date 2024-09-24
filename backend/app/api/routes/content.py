from fastapi import APIRouter
from beanie import PydanticObjectId


router = APIRouter()

@router.get("/test")
async def tester(ss: str | None = None):
    return {"Got it": ss}

@router.get("/latest")
async def getLatestContent(movies: bool | None = None, tv: bool | None = None):
    """
    Get the latest content available to watch

    - If `movies` is set to true return only movies
    - If `tv` is set to true return only tv shows
    - If both `movies` and  `tv` is `None` return combination
    """
    pass


@router.get("/{contentID}")
async def getContent(contentID: PydanticObjectId):
    """
    Get the content with contentID
    """
    pass