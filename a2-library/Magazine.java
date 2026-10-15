/*
 * Magazine.java
 * Author: Oscar Zhu
 * Date: October 12, 2026
 * Represents a magazine that can only be read in the library.
 */
public class Magazine extends LibraryItem {
    private final int issue;

    /**
     * Creates a magazine with an issue number
     * Precondition: ID, title, year, and issue must be valid
     *
     * @param id    the catalogue ID
     * @param title the magazine title
     * @param year  the publication year
     * @param issue the magazine issue number
     * @throws IllegalArgumentException if any value is invalid
     */
    public Magazine(String id, String title, int year, int issue) {
        super(id, title, year);
        if (issue <= 0) {
            throw new IllegalArgumentException("issue must be > 0, got " + issue);
        }
        this.issue = issue;
    }

    /**
     * Returns the type of this library item.
     * Precondition: None
     *
     * @return the item type
     */
    @Override
    public String kind() {
        return "magazine";
    }

    /**
     * Returns the formatted description of the magazine
     * Precondition: None
     *
     * @return the magazine information including the issue number
     */
    @Override
    public String toString() {
        return super.toString() + " | issue " + issue;
    }
}
