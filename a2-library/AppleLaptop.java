/*
 * AppleLaptop.java
 * Author: Oscar Zhu
 * Date: October 15, 2026
 * Represents a laptop that can be borrowed from the library
 */

public class AppleLaptop extends LibraryItem implements Borrowable {
    private final int ramGb;
    private static final int LOAN_DAYS = 7;
    private static final double DAILY_FEE = 3.00;

    /**
     * Creates an Apple laptop with a specified RAM capacity
     * Precondition: ID, title, year, and RAM capacity must be valid
     *
     * @param id the catalogue ID
     * @param title the laptop title
     * @param year the publication year
     * @param ramGb the RAM capacity in gigabytes
     * @throws IllegalArgumentException if any value is invalid
     */

    public AppleLaptop(String id, String title, int year, int ramGb) {
        super(id, title, year);
        if (ramGb <= 0) {
            throw new IllegalArgumentException("ramGb must be > 0, got " + ramGb);
        }
        this.ramGb = ramGb;
    }

    /**
     * Returns the type of this library item
     * Precondition: None
     *
     * @return the item type
     */
    @Override
    public String kind() {
        return "laptop";
    }

    /**
     * Returns the loan period for an Apple laptop
     * Precondition: None
     *
     * @return the loan period in days
     */
    @Override
    public int loanPeriodDays() {
        return LOAN_DAYS;
    }

    /**
     * Returns the daily late fee for an Apple laptop
     * Precondition: None
     *
     * @return the daily late fee in dollars
     */
    @Override
    public double dailyLateFee() {
        return DAILY_FEE;
    }

    /**
     * Returns the formatted description of the Apple laptop
     * Precondition: None
     *
     * @return the laptop information including RAM capacity
     */
    @Override
    public String toString() {
        return super.toString() + " | " + ramGb + " GB RAM";
    }
}
