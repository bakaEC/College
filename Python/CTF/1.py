from bs4 import BeautifulSoup
import urllib.request
import csv
import requests
import os
import re
import sys
result = []
with open('stocks.txt', 'r') as f:
    for line in f:
        result.append(list(line.strip('\n').split(',')))
print(result)
pos = "SH603698"

data = {"symbols": pos, "category": "1"}
url = 'http://stock.xueqiu.com/v5/stock/portfolio/stock/add.json'
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0",

    "Upgrade-Insecure-Requests": "1"
}
resp = requests.post(url=url, headers=headers, data=data)
html = resp.text
bs = BeautifulSoup(resp.text, "html.parser")

print(resp.text)
print(bs)


# POST / v5/stock/portfolio/stock/add.json HTTP/1.1
# Host: stock.xueqiu.com
# User-Agent: Mozilla/5.0 (Windows NT 10.0
#                          Win64
#                          x64
#                          rv: 84.0) Gecko/20100101 Firefox/84.0
# Accept: application/json, text/plain, */*
# Accept-Language: zh-CN, zh
# q = 0.8, zh-TW
# q = 0.7, zh-HK
# q = 0.5, en-US
# q = 0.3, en
# q = 0.2

# Accept-Encoding: gzip, deflate
# Referer: https: // xueqiu.com/
# Content-Type: application/x-www-form-urlencoded
# Content-Length: 27
# Origin: https: // xueqiu.com
# Connection: close
# Cookie: xq_a_token = bf4e63a6c373c29ed0eb1766d026a26db3e8b317
# xqat = bf4e63a6c373c29ed0eb1766d026a26db3e8b317
# xq_r_token = f419cdd2cdf5e9d1b2dbf4e7de76176ea18b6dbb
# xq_id_token = eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjExNzcyODM2NjcsImlzcyI6InVjIiwiZXhwIjoxNjEzNTQ5MjkyLCJjdG0iOjE2MTA5NTgwNTExMTgsImNpZCI6ImQ5ZDBuNEFadXAifQ.kX-6xywpYfa61IG677g3u8kJ4gOfJnNbM1f-9vFpHudA1dRvKUlvcoWUIISZDBgQ-MPH0YYTBTxdeH3GBn9daWmMnzmoVQNZv_WWS7o5Zvuo4egH5EXQydvj7TRHvHZbgnd7ULes3ov3Dy8r9XaEMhvLU7KCKY7dwmJi7IyUy-zX19LY2e6l46YFLlXntEvljo6DFuOspcHmkz2sdcaL6dE9maTbN2GVua5QJ-E9DIxf5rWt7VMN166dQHQgivL0Jibwl4xxqPLvMwjkb0ZP8LmCtrnb7PRtJnbxO88IotSfkr2CP_W3uagCXWf3oycjKHwogQVwTXie3bUl8DZlMw
# u = 1177283667
# Hm_lvt_1db88642e346389874251b5a1eded6e3 = 1610957940
# Hm_lpvt_1db88642e346389874251b5a1eded6e3 = 1610958744
# device_id = 24700f9f1986800ab4fcc880530dd0ed
# remember = 1
# xq_is_login = 1

# symbols = SZ002067 & category = 1
