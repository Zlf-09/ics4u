# ICS4U Session 1 Part 2

## P1

Prediction: I think the first line prints the maximum integer value.
I think the second line prints a value that is one greater than the maximum integer values.

Actual output:
2147483647
-2147483648

Explanation: The second line overflowed because an int cannot store a value greater than Integer.MAX_VALUE, so it wrapped around to Integer.MIN_VALUE.

## P2

Prediction: I think the first line prints 0.3.
I think the second line prints true.

Actual output: 0.30000000000000004 & False

Explanation: I was wrong because floating-point numbers like 0.1 and 0.2 cannot always be represented exactly in binary, so their sum is slightly different from 0.3.

## P3

Prediction: I think the outputs will be 2.5, 2, 5, -5

Actual output:
2
2.5
5
-5
Explanation: I was wrong about the first two lines because dividing two integers uses integer division, while using 2.0 makes the division use a double.

## P4

Prediction:
I think the first line prints 66.
I think the second line prints B.


Actual output:
66
B

Explanation: My prediction was correct. The character 'A' has the numeric value 65, so c + 1 becomes 66, and casting 66 back to char gives 'B'.

## P5

Prediction:
true
false
true

Actual output:
true
false
true

Explanation:
My prediction was correct. The == operator compares whether the references point to the same String object, while equals() compares the String contents.

## P6

Prediction: 
I think the outputs will be 0 and null

Actual output:
0
null

Explanation: I got it right. Java puts 0 in a new int, while a new String array starts with null.

## P7

Prediction: I think the outputs will be x+1, x and 1+x

Actual output: 5，6，7

Explanation: I learned that x++ uses the current value first and then adds 1, while ++x adds 1 first and then uses the new value.

## P8

Prediction: I think the outputs will be: 33 and 15

Actual output: 33 amd 123

Explanation: I was wrong about the second line because Java evaluates from left to right. 1 is already a string, it joins 2 and 3 as text, which giving 123 instead of 15.

## Most Surprising

The one that surprised me most was P8__ because: I thought "1"+2+3 would print 15, but is actually printed 123. I learned that Java goes from ;eft to right when using + with Strings.


