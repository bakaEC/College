from bs4 import BeautifulSoup
import urllib.request
import csv
import requests
import os
import re
pos=123
i=0
for i in range(0,5):
    data = {"re":pos}
    url='http://recruit.x5tar.com:60002/'
    headers = {
                "User-Agent":"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0",
                "Cookie":"a=1234; PHPSESSID=b79dc6bd4ef6d6b7e601aa7afb2352c1; delicious_cookie=Y25zc3syZjg4NTg2NC1hMDYxLTRjY2ItOTMxMC00MjMxNTY5OGRhOGF9",
                "Upgrade-Insecure-Requests":"1"
            }
    resp = requests.post(url=url,headers=headers,data=data)
    html=resp.text
    bs = BeautifulSoup(resp.text,"html.parser")
    total=bs.find_all('p')
    res=total[1].contents[0]
    result=res.string
    pattern = re.compile(r'\d{8}')
    addons=pattern.findall(result)
    print(resp.text)
    pos=int(addons[0])+int(addons[1])
    print(pos)
    i=i+1



# pattern = re.compile(r'(?<=calibration=)\d+\.?\d*')
# pattern.findall(string)
# for item in bs.find_all("p"): 
#     print(re.findall("\d+",item))