import math

sum = 0
input_num = input("下一个数据")
while(input != "ok"):
    while True:
        try:
            input_num = float(input_num)
            break
        except:
            input_num = input("输入错误，n只能为数字，请输入n：")
    sum += input


def getLiveAvg(num, list):
