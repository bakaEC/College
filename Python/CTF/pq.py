
page = urllib2.urlopen("http://summer.x5tar.com:50002/")   
contents = page.read()   
bs = BeautifulSoup(contents,"html.parser") 

print(bs.prettify())

for item in bs.find_all("a"): 
    print(item.get("href")) 

for item in bs.find_all("a"): 
    print(item.get_text())