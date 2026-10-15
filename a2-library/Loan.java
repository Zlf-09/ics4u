/*
 * Loan.java
 * Author: Oscar Zhu
 * Date: October 13, 2026
 * Represents an active library loan
 */
public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final int dueDay;

    /**
     * Creates a loan record for a borrowed library item
     * Precondition: item and member are not null and dueDay is a valid day
     *
     * @param item   the borrowed library item
     * @param member the borrowing member
     * @param dueDay the day the item must be returned
     */
    public Loan(LibraryItem item, Member member, int dueDay) {
        this.item = item;
        this.member = member;
        this.dueDay = dueDay;
    }

    /**
     * Returns the borrowed library item
     * Precondition: None
     *
     * @return the borrowed item
     */
    public LibraryItem getItem() {
        return item;
    }

    /**
     * Returns the member who borrowed the item
     * Precondition: None
     *
     * @return the borrowing member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Returns the due date of this loan
     * Precondition: None
     *
     * @return the due day
     */
    public int getDueDay() {
        return dueDay;
    }
}
