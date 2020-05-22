def my_abs (x):
    if x>=0 : 
        return x
    else:
        return -x
def power(x,n):
    if n == 0:
        return 1
    elif n == 1:
        return x
    elif n > 1:
        s=1
        while (n>0):
            n=n-1
            s=s*x
    return s

print(power(my_abs(-5),2))

