from fastapi import APIRouter

from app.core.movie import movie

router = APIRouter()

@router.get("/{movie_id}")
async def getMovie(movie_id: int):
    """
    Get the movie with movie_id
    """
    ret = await movie(movie_id)
    return ret