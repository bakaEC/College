import math
import re

input_list = []
platformFlag = False

sum = 0
count = 0
input_num = 0

pre_LiveAvg = 0
OUT_count = 0
# a = np.array(a)
# a.flatten()


def platformTest(input_list, LiveAvg):
    global OUT_count
    for i in input_list:
        compareAvg = (i-LiveAvg)/LiveAvg
        print(compareAvg*100, "%")
        if abs(compareAvg) <= 0.03:
            return True
        else:
            OUT_count += 1
            print("偏移平台", OUT_count, "个数据")


def getLiveAvg(sum, count):
    return sum/count


def regInput(num):
    matched = re.match(r'[0-9+-.]', num)
    if num.isdigit() == True or matched:
        return True
    else:
        print("输入有误！")
        return False


LiveAvg = 0
while True:
    input_num = input("下一个数据: ")
    if input_num == "ok":
        break
    if regInput(input_num) == True:
        input_num = float(input_num)
        sum += input_num
        count += 1
        input_list.append(input_num)
        LiveAvg = getLiveAvg(sum, count)
        if count == 50:
            ptFlag = platformTest(input_list, LiveAvg)
            if ptFlag == True:
                print("是平台")
            else:
                print("不是平台")
                break
        print(
            "位置", count,
            "当前输入", input_num,
            "总数", sum,
            "平均数为", LiveAvg,
        )

    else:
        print("输入格式错误，请重新输入！")

print("Exit")
