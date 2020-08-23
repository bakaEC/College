flag = int(b"cnss".hex(), 16)
flag = "cnss{%s}" % (hex(pow(flag, 0x10001, 19260817)))
print(flag)