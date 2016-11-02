"""
Name:       Isaac Smith
Class:      Artificial Intelligence
Assignment: Naive Bayes
Professor:  John Licato
Date:       11/1/2016
"""
from collections import Counter
from itertools import chain
from sklearn.naive_bayes import GaussianNB
import csv


def convert_to_vector(message, word_list=[]):
    """A transformation matrix T in the form of a function.
       Converts the word_counts vector in terms of the
       original message to a word_counts vector in terms of
       the words_list"""
    # Count the number of times the words in a sentence
    # are repeated and store that in a dictionary.
    # This is the input, or vector A.
    word_counts = dict(Counter(message.lower().split(' ')))
    # Used to keep track of the number of times the words
    # are repeated in terms of the larger twitter database
    # of words.
    # This is the storage for the output, or vector B
    word_vector = [0] * len(word_list)
    # This is the transformation of the input to the
    # output, or vector T
    for word, count in word_counts.items():
        if word in word_list:
            word_vector[word_list.index(word)] = count
    # TA = B
    return word_vector


def generate_classifier(data, target):
    fit_object = GaussianNB()
    classifier_object = fit_object.fit(data, target)

    def classifier(s):
        return classifier_object.predict(s)

    return classifier


def load_all_csvs():
    with open('words.csv') as csvfile:
        reader = csv.reader(csvfile, quoting=csv.QUOTE_NONE)
        # Lowercase all words and flatten the list so items
        # are easier to find.
        words = [word.lower() for word in chain.from_iterable(reader)]

    with open('vectors.csv') as csvfile:
        reader = csv.reader(csvfile, quoting=csv.QUOTE_NONE)
        # Remove the empty string from the end of the list
        # and convert all the string-numbers to integers.
        vectors = [list(map(int, word[:len(word)-1])) for word in reader]

    with open('tweets.csv') as csvfile:
        reader = csv.reader(csvfile, quoting=csv.QUOTE_NONE)
        # Transpose the matrix so it's
        # easier to get the classification.
        sentiments = list(zip(*reader))

    return words, vectors, sentiments


def main():
    words, vectors, sentiments = load_all_csvs()
    predict = generate_classifier(vectors, list(map(int, sentiments[0])))
    phrases = ["America is great!", "NOOOO NOT AGAIN", "I love homework!",
               "...I love homework", "Why? Why does this happen to me?",
               "Professor John Licato, Ph.D."]

    for phrase in phrases:
        vector_form = convert_to_vector(phrase, words)
        sentiment = "+" if predict([vector_form])[0] else "-"
        print("({}) : {}".format(sentiment, phrase))

if __name__ == "__main__":
    main()
