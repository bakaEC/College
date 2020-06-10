from bs4 import BeautifulSoup
import urllib.request
import csv
import requests
import os
import time

import re

headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36'
}
main = 'http://www.lyckapp.com/index.php?p=3&type=0&mg_code=862484'
while (True):
    i = 1
    response = requests.get(main, headers=headers)
    html = response.text
    urls = re.findall('<img src="(.*?)"  />', html)
    dir_name = re.findall('<title>(.*?)</title>', html)
    ddir = ''.join(dir_name)
    if not os.path.exists("E:/GHS_research/PIC/" + ddir):
        os.mkdir("E:/GHS_research/PIC/" + ddir)
    print(ddir)

    for url in urls:
        time.sleep(0.01)
        print(i)
        i += 1
        file_name = url.split('/')[-1]
        response = requests.get(url, headers=headers)
        with open("E:/GHS_research/PIC/" + ddir + "/" + file_name, 'wb') as f:
            f.write(response.content)
    mainoff = re.findall('<a href="(.*?)" class="am_img_bg">', html)[1]
    print(mainoff)
    main = 'http://www.lyckapp.com/'+mainoff
