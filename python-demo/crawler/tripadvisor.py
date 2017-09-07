from bs4 import BeautifulSoup
import requests

url = 'https://www.tripadvisor.cn/Tourism-g60763-New_York_City_New_York-Vacations.html'
wb_data = requests.get(url)
soup = BeautifulSoup(wb_data.text, 'lxml')
# print(soup)
hotel_names = soup.select('#HTL_FAVS > li > div > a.hotel.name')
hotel_imgs = soup.select('#HTL_FAVS > li > a.photo.posRel > div > div > div > img')
hotel_captions = soup.select('#HTL_FAVS > li > a.photo.posRel > div > span')
# print(hotel_names, hotel_imgs, hotel_captions)

for name, caption, img in zip(hotel_names, hotel_captions, hotel_imgs):
    data = {
        'name':name.get_text(),
        'img':img.get('src'),
        'caption':caption.get_text(),
    }
    print(data)

urls = ['https://cn.tripadvisor.com/Attractions-g60763-Activities-oa{}-New_York_City_New_York.html#ATTRACTION_LIST'.format(str(i)) for i in range(30,930,30)]
print(urls)

# mobile site normally easy to be fetched
headers = {
    'User-Agent':'Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1',
}
mb_url = 'https://www.tripadvisor.cn/Hotels-g60763-New_York_City_New_York-Hotels.html'
mb_data = requests.get(mb_url, headers = headers)
mb_soup = BeautifulSoup(mb_data.text, 'lxml')
imgs = mb_soup.select('#taplc_mhl_list_hsx_0 > div > div > div > div > a > div.prw_rup.prw_common_lazy_image_lite_hsx.thumb_box > img')
# print(imgs)
for i in imgs:
    print(i.get('data-lazyurl'))

