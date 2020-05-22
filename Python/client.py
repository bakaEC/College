import socket
s = socket.socket()
host = socket.gethostname()
port = 8000
s.connect((host, port))
while True:
    mes = str(s.recv(1024),encoding = "utf-8")
    print("服务器：{}".format(mes))
    if mes == "再见":
        break
    mes = input("客户端：")
    s.send(bytes(mes,encoding="utf-8"))
s.close()
