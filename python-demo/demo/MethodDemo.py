def fahrenheit_converter(C):
    fahrenheit = C * 9 / 5 + 32
    return str(fahrenheit) + '˚F'

C2F = fahrenheit_converter(35)
print(C2F)

print(' *',' * *',' * * *',' | ',sep = '\n')

file = open('D:/tmp/text.txt','w')
file.write('Hello World')

def text_create(name, msg):
   desktop_path = 'D:/tmp/'
   full_path = desktop_path + name + '.txt'
   file = open(full_path,'w')
   file.write(msg)
   file.close()
   print('Done')


text_create('hello','hello world')

def text_filter(word,censored_word = 'lame',changed_word = 'Awesome'):
    return word.replace(censored_word, changed_word)

print(text_filter('Python is lame!'))

def censored_text_create(name, msg):
    clean_msg = text_filter(msg)
    text_create(name,clean_msg)

censored_text_create('Try','lame!lame!lame!')

password_list = ['*#*#','12345']
def account_login():
    password = input('Password:')
    password_correct = password == password_list[-1]
    password_reset = password == password_list[0]
    if password_correct:
        print('Login success!')
    elif password_reset:
        new_password = input('Enter a new password:')
        password_list.append(new_password)
        print('Your password has changed successfully!')
        account_login()
    else:
        print('Wrong password or invalid input!')
        account_login()

account_login()


