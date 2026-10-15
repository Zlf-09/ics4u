/*
 * DVD.java
 * Author: Oscar Zhu
 * Date: October 2026
 * Represents a DVD that can be borrowed from the library.
 */
public class DVD extends LibraryItem implements Borrowable {
    private final int minutes;
    private static final int LOAN_DAYS = 7;
    private static final double DAILY_FEE = 1.00;

    /**
     * Creates a DVD with a runtime in minutes.
     * Precondition: ID, title, year, and minutes must be valid.
     *
     * @param id      the catalogue ID
     * @param title   the DVD title
     * @param year    the publication year
     * @param minutes the DVD runtime
     * @throws IllegalArgumentException if any value is invalid
     */
    public DVD(String id, String title, int year, int minutes) {
        super(id, title, year);
        if (minutes <= 0) {
            throw new IllegalArgumentException("runtime must be > 0, got " + minutes);
        }
        this.minutes = minutes;
    }

    /**
     * Returns the type of this library item.
     * Precondition: None
     *
     * @return the item type
     */
    @Override
    public String kind() {
        return "DVD";
    }

    /**
     * Returns the loan period for a DVD
     * Precondition: None
     *
     * @return the number of loan days
     */
    @Override
    public int loanPeriodDays() {
        return LOAN_DAYS;
    }

    /**
     * Returns the daily late fee for a DVD
     * Precondition: None
     *
     * @return the daily fee in dollars
     */
    @Override
    public double dailyLateFee() {
        return DAILY_FEE;
    }

    /**
     * Returns the formatted description of the DVD.
     * Precondition: None
     *
     * @return the DVD information including runtime
     */
    @Override
    public String toString() {
        return super.toString() + " | " + minutes + " min";
    }

}
