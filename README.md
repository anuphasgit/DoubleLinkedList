****************
* Project name: DoubleLinkedList
**************** 

OVERVIEW:

 The DoubleLinkedList project creates a special type of list in java called a doubly linked list. The list follows the ruled outlined in the IndexedUnsortedList interface. With this you can perform different operations for adding, removing, and changing elements in the list.


INCLUDED FILES:

 * ListTester.java - source file
 * Node.java - source file
 * IUDoubleLinkedList.java - source file
 * README - this file


COMPILING AND RUNNING:

 To complile the program, navigate to the directory containing all the source files and excute the following command:

 $ javac IUDoubleLinkedList.java ListTester.java Node.java

 To run the compiled program, use the following command:

 $ java IUDoubleLinkedList

 The console output will display results after the program finishes.



PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 The IUDoubleLinkedList is designed to implement a doubly linked list with the followng key concepts:

 - Double Linked Structure:
   This allows efficient movement in both directions(forward and backward) through the list which enhances the speed of various operations by easily accessing the elements in both directions.

 - Node Encapsulation:
   Elements are encapsulated within Nodes, each linked to the next and previous elements.

 - ModCount Tracking:
   The ModCount variabble keeps track of modifications made during iteration which help detect changes and ensure accurate iteration over the list. 

 The primary responsibilities of the IUDounumber/bleLinkedList class include:

 - Adding elements:
    Adding elements to the front, rear, or at specific indices.

 - Removing elements: 
    Removing elements from the front, rear, or at specific indices.

 - Element Access:
    Providing methods for accessing elements.

 - List Status:
    Methods to check the status of the list, such as it is empty or not or its current size.
   

TESTING:

 To make sure the IUDoubleLinkedList works correctly, I used a testing tool called the "List Tester". This tool helped me check if the list was doing what it was supposed to do or not. The list tester helped detect bugs and mistakes so it could be fixed instantly. 

 One time the ListTestor really came to rescue was when dealing with the add(int index, T element) method. The original code tried to add an element using a method that is still commented out in the code but was not working as expected. This caused issues and even failed most of the concurrency tests at the end.
 To be honest, I wasn't exactly sure what was wrong with my initial approach. But, thanks to listTestor, I was able to replace that part of code with a different method using ListIterator logic which fixed the issue passing all the tests for the doubleLinkedList.


DISCUSSION:
 
 This project was really educational for me. It wasn't just about the doubly linked list itself, but also about how this project was constructed based on the previous assignment. Doing this project from scratch would have been tough, but the way this project was structured, especially after covering the ArrayLists and SingleLinkedList made it easier to understand. I also learned a lot about test-driven development as I worked through this project. 
