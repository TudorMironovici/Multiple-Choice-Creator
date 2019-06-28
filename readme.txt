********************************************************************************************************************
                                                   WHAT I LEARNED
********************************************************************************************************************

- How to work with files, and having them be readble wherever the program is placed.
- How to work with formatting (both creating a format system and reading a formatted file).
- Creating two different ways to do the same thing, each having their ups and downs.
- Storing data in the program to give feedback on how the user is doing (more specifically, which questions the user
gets right, wrong, or chooses to save for later).





********************************************************************************************************************
                                      Welcome to the Multiple Choice Creator!
********************************************************************************************************************

This suite of programs allows you to set up a multiple choice exam and lets others take your exam. This suite 
includes two sets of programs: 
                                              MCExamSelfContained.java
                                                        and
                                               MCExamFileReader.java

which do the same job ultimately, but they each have their use cases. The Multiple Choice Creator program takes in
four things: the introduction of the exam, the question text of the exam, the multiple choice options for those 
question, and the reasons behind why the incorrect multiple choice options are incorrect. The program allows the
user to take a the multiple choice exam and get immediate feedback. They can also skip questions and come back to
them later. After all questions have been answered, the program gives the user their score, and shows them which
questions they got incorrect and why.

Below is the documentation for the programs.




                                              MCExamSelfContained.java
                                              ~~~~~~~~~~~~~~~~~~~~~~~~
This file is the "self-contained" version of the multiple choice exam program. It's recommended that you're somewhat
knowledgable of java to use this version, as it does require quite a bit of changing to make it work with your exam.

Variables:
All the question text is stored in a String Array names "questionText". The multiple choice answers, and the reasons
behind the incorrect answer choices being incorrect are all stored in Arrays of String Arrays named "answerText" and
"reasonText" (respectively). The answer key is stored as a char Array named "answerKey". All these are set to mirror 
the NJIT CS113 Fall 2018 Midterm 2 exam by default.
- When changing the defualt text, you need to make sure you have continuity. Make sure that "answerText" and
"reasonText" have inner Arrays which have the same amount of Strings inside them. Also make sure that the outer
Arrays of "answerText" and "reasonText" have the same amount of Arrays as "questionText" has Strings and "answerKey"
has chars.





                                               MCExamFileReader.java
                                               ~~~~~~~~~~~~~~~~~~~~~
This file is the "interpreter" version of the multiple choice exam program. When you run the program, it will ask
for the name of your test file, and then it will handle everything from there on out. If you wish for the program to
automatically read a file, put its name inside the quotes of the "testFileName" variable. Make sure that the exam
file is in the same folder as the program before running it.
 

Variables:
The only variables you should worry about changing are the "fileExtention", "questionEnd", and "reasonKey" Strings.
- The "fileExtention" is simply the extention of file you want the program to accept. Please keep in mind that the 
program uses the java File and Scanner objects, meaning the file type should be readble by them.
- The "questionEnd" and "reasonKey" Strings are both keywords that the program looks for to stop reading the text
in the given file as a question and to start reading the text as multiple choice answers (for "questionEnd") and to
switch from reading the text as a multiple choice answer to reading it as the reason to why it's wrong (for 
"reasonKey").

Formatting:
There are three major sections to the .txt file (or whatever you choose to set it to): 
		- the introduction
		- the question
		- the answer / reasons

- The first section of the .txt should be the introduction of your exam. Feel free to skip this and get to the
questions right away by just starting with the question. No formatting is needed for the introduction.
- The question section can be repeated as many times as needed. However, make sure you always follow the proper
formatting, or else the program will get mad at you and won't work. The only thing you have to make sure to do for
the question section is to have the first character of the first line of your question's text be an integer,
prerably to represent the question's number. Feel free to stylistically format the question's number however, such
"1.)" or "1:". Then, put whatever the "questionEnd" String is set to at the end of the last line of your question
text, seperated by a blank space.
- The answer / reason section can also be repeated as many times as needed, but always has to follow the question
section. So after a question, put the multiple choice options with their reasons, and then another question (if 
applicable). Note that you CANNOT start the multiple choice answer text line with an integer. This will make the
program think you're starting another question. You need to start the line with the letter of the multiple choice
option. Feel free to stylistically format the multiple choice option's letter however, such as "A." or "b)". 
Moreover, you can only use one line for the multiple choice answers. To seperate the incorrect multiple choice
option text from the reason as to why it's wrong text, just add whatever the "reasonKey" String is set to right
after the last word of the option's text, with a space before and after.

The suite should come with "example.txt" which is a properly formatted version of the NJIT CS113 Fall 2018 Midterm 2
exam. Feel free to refer to it if the instructions above weren't clear, and feel free to run it with the program to
see how the program works.






















