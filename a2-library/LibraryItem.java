/*
 * LibraryItem.java
 * Author: Oscar Zhu
 * Date: October 11, 2026
 * Stores common information for all library items.
 */
public abstract class LibraryItem {
    public static final int EARLIEST_YEAR = 1450;
    public static final int CURRENT_YEAR = 2026;
    private final String id;
    private final String title;
    private final int year;

    /**
     * Creates a library item with an ID, title, and year
     * Precondition: ID and title are not blank, and year is valid
     *
     * @param id    the catalogue ID
     * @param title the item title
     * @param year  the publication year
     * @throws IllegalArgumentException if any value is invalid
     */
    public LibraryItem(String id, String title, int year) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        if (year < EARLIEST_YEAR || year > CURRENT_YEAR) {
            throw new IllegalArgumentException("year must be between " + EARLIEST_YEAR
                    + " and " + CURRENT_YEAR + ", got " + year);
        }
        this.id = id;
        this.title = title;
        this.year = year;
    }

    /**
     * Returns the catalogue ID of this item.
     * Precondition: None
     *
     * @return the item's ID
     */

    public String getId() {
        return id;
    }

    /**
     * Returns the title of this item
     * Precondition: None
     *
     * @return the item's title
     */

    public String getTitle() {
        return title;
    }

    /**
     * Returns the publication year of this item
     * Precondition: None
     *
     * @return the publication year
     */

    public int getYear() {
        return year;
    }

    /**
     * Returns the type of this library item
     * Precondition: None
     *
     * @return the item type
     */
    public abstract String kind();

    /**
     * Returns the formatted description of this library item
     * Precondition: None
     *
     * @return the formatted item information
     */
    @Override
    public String toString() {
        return String.format("%s | %-8s | %s (%d)", id, kind(), title, year);
    }
}
