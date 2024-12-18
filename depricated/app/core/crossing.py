from bs4 import BeautifulSoup
import requests

wiki_base_url = "https://en.wikipedia.org"

def get_all_pages():
    res = requests.get(wiki_base_url+"/wiki/Lists_of_Telugu-language_films")

    soup = BeautifulSoup(res.text, 'html.parser').select('body')[0]

    ret_map = {}

    for tag in soup.find_all('a'):
        if "title" in tag.attrs:
            title = tag["title"]
            if title.startswith("List of Telugu films of") and "href" in tag.attrs:
                ret_map[title] = tag["href"]
            elif title.startswith("List of Telugu films of") and "href" not in tag.attrs and title not in ret_map.keys():
                ret_map[title] = None

    return ret_map


def list_movies(url: str):
    res = requests.get(wiki_base_url+url)

    soup = BeautifulSoup(res.text, 'html.parser').select('body')[0]

    ret_map = {}

    for tag in soup.find_all('a'):
        if "title" in tag.attrs:
            pass