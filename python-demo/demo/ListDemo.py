import time
num_list = [6,2,7,4,1,3,5]
print(sorted(num_list))
print(sorted(num_list, reverse=True))

# List comprehension
a = []
for i in range(1,11):
    a.append(i)

print(a)

b = [i for i in range(1,11)]
print(b)


a = []
t0 = time.clock()
for i in range(1,20000):
    a.append(i)
print(time.clock() - t0, "seconds process time")

t0 = time.clock()
b = [i for i in range(1,20000)]
print(time.clock() - t0, "seconds process time")


a = [i**2 for i in range(1,10)]
c = [j+1 for j in range(1,10)]
k = [n for n in range(1,10) if n % 2 ==0]
z = [letter.lower() for letter in 'ABCDEFGHIGKLMN']
print(a)
print(c)
print(k)
print(z)

d = {i:i+1 for i in range(4)}
g = {i:j for i,j in zip(range(1,6),'abcde')}
h = {i:j.upper() for i,j in zip(range(1,6),'abcde')}
print(d)
print(g)
print(h)


letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
for num,letter in enumerate(letters):
    print(letter,'is',num + 1)













