from app.logger import log
from app.models.tmdb import TMDB_Movie, TMDB_TV

async def count_lanaguages_tmdb(content: str):
    pipeline = [
        {
            "$group": {
                "_id": "$original_language",
                "count": {"$sum": 1}
            }
        },
        {
            "$sort": {"count": -1}
        }
    ]

    if content == "movie":
        results = await TMDB_Movie.get_motor_collection().aggregate(pipeline=pipeline).to_list(length=None)
    else:
        results = await TMDB_TV.get_motor_collection().aggregate(pipeline=pipeline).to_list(length=None)
    return results