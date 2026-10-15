/*
 * Book.java
 * Author: Oscar Zhu
 * Date: October 12, 2026
 * Represents a book that can be borrowed from the library
 */
public class Book extends LibraryItem implements Borrowable {
    private final String author;
    private final int pages;
    private static final int LOAN_DAYS = 21;
    private static final double DAILY_FEE = 0.25;

    /**
     * Creates a book with an author and page count
     * Precondition: ID, title, year, and pages must be valid
     *
     * @param id     the catalogue ID
     * @param title  the book title
     * @param year   the publication year
     * @param author the book author
     * @param pages  the number of pages
     * @throws IllegalArgumentException if any required value is invalid
     */
    public Book(String id, String title, int year, String author, int pages) {
        super(id, title, year);
        if (pages <= 0) {
            throw new IllegalArgumentException("pages must be > 0, got " + pages);
        }
        this.author = author;
        this.pages = pages;
    }

    /**
     * Returns the type of this library item.
     * Precondition: None
     *
     * @return the item type
     */
    @Override
    public String kind() {
        return "book";
    }

    /**
     * Returns the loan period for a book
     * Precondition: None
     *
     * @return the number of days a book can be borrowed
     */
    @Override
    public int loanPeriodDays() {
        return LOAN_DAYS;
    }

    /**
     * Returns the daily late fee for a book.
     * Precondition: None.
     *
     * @return the late fee per day in dollars
     */
    @Override
    public double dailyLateFee() {
        return DAILY_FEE;
    }

    /**
     * Returns the formatted description of the book
     * Precondition: None.
     *
     * @return the book information including author and pages
     */
    @Override
    public String toString() {
        return super.toString() + " | by " + author + ", " + pages + " pages";
    }
}
