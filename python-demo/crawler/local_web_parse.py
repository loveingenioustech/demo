from bs4 import BeautifulSoup

data = []
path = '../web/blah/new_index.html'

with open(path, 'r') as f:
    Soup = BeautifulSoup(f.read(), 'lxml')
    print(Soup)

    # images = Soup.select('body > div.main-content > ul > li > img')
    # print(images)

    titles = Soup.select('ul > li > div.article-info > h3 > a')
    pics = Soup.select('ul > li > img')
    descs = Soup.select('ul > li > div.article-info > p.description')
    rates = Soup.select('ul > li > div.rate > span')
    cates = Soup.select('ul > li > div.article-info > p.meta-info')

for title, pic, desc, rate, cate in zip(titles, pics, descs, rates, cates):
    info = {
        'title': title.get_text(),
        'pic': pic.get('src'),
        'descs': desc.get_text(),
        'rate': rate.get_text(),
        'cate': list(cate.stripped_strings)
    }
    print(info)
    data.append(info)

for i in data:
    if float(i['rate']) > 3:
        print(i['title'], i['cate'])
