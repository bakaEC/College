
page = urllib2.urlopen("http://recruit.x5tar.com:60002/")   
contents = page.read()   
bs = BeautifulSoup(contents,"html.parser") 

print(bs.prettify())

for item in bs.find_all("a"): 
    print(item.get("href")) 

for item in bs.find_all("a"): 
    print(item.get_text())