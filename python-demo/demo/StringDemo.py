what_he_does = ' plays '
his_instrument = 'guitar'
his_name = 'Robert Johnson'
artist_intro = his_name + what_he_does + his_instrument
print(artist_intro)
print(type(his_name))

num = 1
string = '1'
num2 = int(string)
print(num + num2)

words = 'words' * 3
print(words)

word = 'a loooooong word'
num = 12
string = 'bang!'
total = string * (len(word) - num)
print(total)

# 原来Go很多特性也是借鉴于python
name = 'My name is Mike'
print(name[0])
print(name[-4])
print(name[11:14]) # from 11th to 14th, 14th one is excluded
print(name[11:15]) # from 11th to 15th, 15th one is excluded
print(name[5:])
print(name[:5])


word = 'friends'
# long code could use \ to write in another line
find_the_evil_in_your_friends = \
word[0]+word[2:4]+word[-3:-1]
print(find_the_evil_in_your_friends)


phone_number = '1386-666-0006'
hiding_number = phone_number.replace(phone_number[:9],'*' * 9)
print(hiding_number)

city = input("write down the name of city:")
url = "http://apistore.baidu.com/microservice/weather?citypinyin={}".format(city)
print(url)
