# Assignment 2 - Answers
## Question 1
In my checkOut() method, IllegalArgumentException is thrown when the requested member or item does not exist, such as "no member 99". IllegalStateException is thrown when the member and item exist but the current state of the library does not allow borrowing, such as "B-100 is already checked out". In this case, the book exists, but it cannot be borrowed again until it is returned.
## Question 2
One change from my first UML to the final version was adding the private removeLoan() method to the Library class. My teacher's feedback pointed out that findLoan() returns a Loan object, but removing a loan from an array requires finding its position. I added a separate helper so returnItem() could remove a completed loan correctly.
## Question 3
I store loans in a Loan[] array inside the Library class. This made findLoan() straightforward because all borrowing records are stored in one place. However, overdue() was more complicated because the loans array can change order when a loan is removed, so I need to check items in catalogue order to return the correct results.
## Question 4
In returnItem(), I find the existing loan and calculate how many days late the item is. I cast the item from LibraryItem to Borrowable so I can call lateFee(daysLate) and add the fine to the member's balance. This cast is safe because checkOut() only creates loans for items that implement Borrowable.
## Question 5
I first loop through the items to count how many titles contain the search text, ignoring uppercase and lowercase differences. Then I create a LibraryItem[] array of exactly that size and use a second loop to fill it with the matching items. Using an ArrayList would make this easier because I could add matches directly without counting them first, but this assignment requires arrays.
 