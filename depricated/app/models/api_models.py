from pydantic import BaseModel
from typing import Literal

class LatestContent(BaseModel):
    content: Literal['movies', 'tvs']
    release_date: Literal['this_week', 'this_month', 'upcoming']