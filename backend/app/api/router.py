from fastapi import APIRouter

from app.api.routes import content, movies, tv, people, tmdb_crossing

router = APIRouter()

router.include_router(content.router, prefix="/content")
router.include_router(movies.router, prefix="/movie")
router.include_router(tv.router, prefix="/tv")
router.include_router(people.router, prefix="/people")
router.include_router(tmdb_crossing.router, prefix="/tmdbCrossing")