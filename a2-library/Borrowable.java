/*
 * Borrowable.java
 * Author: Oscar Zhu
 * Date: October 11, 2026
 * Defines borrowing rules and late fees for library items
 */
public interface Borrowable {
    /**
     * Returns the number of days an item can be borrowed
     * Preconditions: None
     *
     * @return the loan period in days
     */
    int loanPeriodDays();

    /**
     * Returns the daily late fee for this item.
     * Precondition: None
     *
     * @return the late fee per day in dollars
     */
    double dailyLateFee();

    /**
     * Calculates the total late fee based on overdue days
     * Precondition: None
     *
     * @param daysLate the number of days overdue
     * @return the total late fee in dollars
     */
    default double lateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }
        return daysLate * dailyLateFee();
    }
}
