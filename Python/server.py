import socket
s = socket.socket()
host = socket.gethostname()
port = 8000
s.bind((host, port))

s.listen(5)
while True:
    print("="*100)
    print('waiting……')
    c, addr = s.accept()
    print('Connected! address:{}\n{}\nsever:May I help?'.format(addr,"-"*50))
    c.send(bytes("pls input ur message!(quit 2 quit)",encoding="utf-8"))
    while True:
        mes = str(c.recv(1024),encoding = "utf-8")
        print("client：{}".format(mes))
        if mes == "quit":
            c.send(bytes("bye",encoding="utf-8"))
            break
        mes = 1
        c.send(bytes(mes,encoding="utf-8"))
    c.close()