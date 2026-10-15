/*
 * Member.java
 * Author: Oscar Zhu
 * Date: October 12, 2026
 * Represents a registered library member
 */
public class Member {
    private final int number;
    private final String name;
    private double fines;

    /**
     * Creates a library member with a number and name
     * Precondition: number must be positive and name must not be blank.
     *
     * @param number the member number
     * @param name   the member name
     * @throws IllegalArgumentException if either value is invalid
     */
    public Member(int number, String name) {
        if (number <= 0) {
            throw new IllegalArgumentException("member number must be > 0, got " + number);
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.number = number;
        this.name = name;
        this.fines = 0.0;
    }

    /**
     * Returns the member's number
     * Precondition: None
     *
     * @return the member number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the member's name
     * Precondition: None
     *
     * @return the member name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a late fee to the member's total unpaid fines
     * Precondition: amount must be non-negative
     *
     * @param amount the fine to add
     * @throws IllegalArgumentException if amount is negative
     */
    public void addFine(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        fines += amount;
    }

    /**
     * Returns the member's total unpaid fines
     * Precondition: None
     *
     * @return the total unpaid fines in dollars
     */
    public double getFines() {
        return fines;
    }
}
