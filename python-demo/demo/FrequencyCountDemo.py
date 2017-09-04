


path = 'Walden.txt'
# with open(path,'r',encoding="utf8") as text:
#     words = text.read().split()
#     print(words)
#     for word in words:
#         print('{}-{} times'.format(word,words.count(word)))

# Improvement
import string
with open(path,'r',encoding="utf-8") as text:
    words = [raw_word.strip(string.punctuation).lower() for raw_word in text.read().split()]
    words_index = set(words)
    counts_dict = {index:words.count(index) for index in words_index}
    for word in sorted(counts_dict,key=lambda x: counts_dict[x],reverse=True):
        print('{} -- {} times'.format(word,counts_dict[word]))





