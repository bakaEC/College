from bs4 import BeautifulSoup
import urllib.request
import csv
import requests
import os
import time

import re

headers = {
    'user-agent': 'adapt'
}
main = 'http://www.lyckapp.com/index.php?p=3&type=0&mg_code=154207'
r = 3
while (True):
    response = requests.get(main, headers=headers)
    html = response.text
    urls = re.findall('<img src="(.*?)"  />', html)
    dir_name = re.findall('<title>(.*?)</title>', html)
    ddir = ''.join(dir_name)

    os.mkdir("E:/GHS/" + ddir + str(r))
    print(ddir+str(r))

    print(urls)
    for url in urls:
        time.sleep(0.1)
        file_name = url.split('/')[-1]
        response = requests.get(url, headers=headers)
        with open("E:/GHS/" + ddir + str(r) + "/" + file_name, 'wb') as f:
            f.write(response.content)
    mainoff = re.findall('<a href="(.*?)" class="am_img_bg">', html)[1]
    print(mainoff)
    main = 'http://www.lyckapp.com/'+mainoff
    print(main)
    r += 1
