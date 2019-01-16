# Polybius-Encryption
##### Encrypts text documents using a Polybius Square, a project for a course on algorithms

The goal of this project was to encrypt and decrypt the novel "War and Peace" by Leo Tolstoy using a [Polybius square](https://en.wikipedia.org/wiki/Polybius_square) as fast as possible.
The project's purpose was to demonstrate the effects of different data types and algorithms on memory space and running times when dealing with large data sets.

Students were required to use a Polybius Square to encrypt and/or decrypt the 1225 page novel War and Peace (conveniently in the public domain).

Encryption consisted mainly of:
* Reading the text file (War and Peace)
* Convert each character of said file into two more characters
* Build a table using a keyword
* Filling the table with the converted characters
* Shuffling the keyword (and the table collumns)
* Finally, print it all to a file

Decrpytion consisted of:
* Reading the file (which at this point is at least twice the size of the original text)
* Outline the table using the shuffled keyword
* Fill the table with the converted text
* Unshuffle the keyword to get the correct character pairs
* Convert the character pairs into their original characters
* Print the text to a file (hopefully matching the original text)

This has the unfortunate and intended effect of highlighting Java's most often criticized weakness, memory overhead. If students resorted to using too many Strings and Java Collections, the JVM would in turn spend too much time managing its memory space.

This project was done in my second year of university at a time when uploading projects to Github for grading was starting to become mandatory. 
