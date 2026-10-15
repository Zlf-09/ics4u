/*
 * Library.java
 * Author: Oscar Zhu
 * Date: October 2026
 * Manages library items, members, and borrowing records.
 */

public class Library {
    public static final int MAX_ITEMS = 100;
    public static final int MAX_MEMBERS = 50;
    public static final int MAX_ITEMS_OUT = 5;
    public static final double FINE_LIMIT = 10.00;
    private final LibraryItem[] items = new LibraryItem[MAX_ITEMS];
    private final Member[] members = new Member[MAX_MEMBERS];
    private int itemCount = 0;
    private int memberCount = 0;
    private final Loan[] loans = new Loan[MAX_ITEMS];
    private int loanCount = 0;

    /**
     * Adds a library item to the catalogue
     * Precondition: item is not null
     *
     * @param item the library item to add
     * @throws IllegalArgumentException if the item ID already exists
     * @throws IllegalStateException    if the catalogue is full
     */

    public void addItem(LibraryItem item) {
        if (findItem(item.getId()) != null) {
            throw new IllegalArgumentException("duplicate item ID: " + item.getId());
        }
        if (itemCount >= MAX_ITEMS) {
            throw new IllegalStateException("the catalogue is full");
        }
        items[itemCount] = item;
        itemCount++;
    }

    private LibraryItem findItem(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                return items[i];
            }
        }
        return null;
    }

    private Member findMember(int number) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getNumber() == number) {
                return members[i];
            }
        }
        return null;
    }

    /**
     * Adds a new member to the library
     * Precondition: m is not null
     *
     * @param m the member to add
     * @throws IllegalArgumentException if the member number already exists
     * @throws IllegalStateException    if the member list is full
     */

    public void addMember(Member m) {
        if (findMember(m.getNumber()) != null) {
            throw new IllegalArgumentException("duplicate member number: " + m.getNumber());
        }
        if (memberCount >= MAX_MEMBERS) {
            throw new IllegalStateException("the member list is full");
        }
        members[memberCount] = m;
        memberCount++;
    }

    /**
     * Returns a copy of the library catalogue in insertion order
     * Precondition: None
     *
     * @return an array containing all current library items
     */

    public LibraryItem[] catalogue() {
        LibraryItem[] result = new LibraryItem[itemCount];
        for (int i = 0; i < itemCount; i++) {
            result[i] = items[i];
        }
        return result;
    }

    private Loan findLoan(String itemId) {
        for (int i = 0; i < loanCount; i++) {
            if (loans[i].getItem().getId().equals(itemId)) {
                return loans[i];
            }
        }
        return null;
    }

    private int countLoansForMember(int memberNumber) {
        int count = 0;
        for (int i = 0; i < loanCount; i++) {
            if (loans[i].getMember().getNumber() == memberNumber) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks out an item to a library member
     * Precondition: day is a valid day
     *
     * @param memberNumber the borrowing member number
     * @param itemId       the item ID
     * @param day          the checkout day
     * @return the due day
     * @throws IllegalArgumentException if the member or item is not found
     * @throws IllegalStateException    if borrowing is not allowed
     */
    public int checkOut(int memberNumber, String itemId, int day) {
        Member member = findMember(memberNumber);
        LibraryItem item = findItem(itemId);
        if (member == null) {
            throw new IllegalArgumentException("no member " + memberNumber);
        }
        if (item == null) {
            throw new IllegalArgumentException("no item " + itemId);
        }
        if (!(item instanceof Borrowable b)) {
            throw new IllegalStateException(itemId + " can't be borrowed");
        }
        if (findLoan(itemId) != null) {
            throw new IllegalStateException(itemId + " is already checked out");
        }
        if (countLoansForMember(memberNumber) >= MAX_ITEMS_OUT) {
            throw new IllegalStateException("member " + memberNumber + " already has 5 items out");
        }
        if (member.getFines() > FINE_LIMIT) {
            throw new IllegalStateException(String.format("member %d owes $%.2f in fines",
                    memberNumber, member.getFines()));
        }
        int dueDay = day + b.loanPeriodDays();
        loans[loanCount] = new Loan(item, member, dueDay);
        loanCount++;
        return dueDay;
    }

    /**
     * Returns a borrowed item and calculates its late fee
     * Precondition: day is a valid day
     *
     * @param itemId the item ID
     * @param day    the return day
     * @return the late fee charged
     * @throws IllegalArgumentException if the item does not exist
     * @throws IllegalStateException    if the item is not checked out
     */
    public double returnItem(String itemId, int day) {
        LibraryItem item = findItem(itemId);
        if (item == null) {
            throw new IllegalArgumentException("no item " + itemId);
        }
        Loan loan = findLoan(itemId);
        if (loan == null) {
            throw new IllegalStateException(itemId + " is not checked out");
        }
        int daysLate = day - loan.getDueDay();
        Borrowable borrowable = (Borrowable) item;
        double fee = borrowable.lateFee(daysLate);
        loan.getMember().addFine(fee);
        removeLoan(itemId);
        return fee;
    }

    /**
     * Removes an active loan from the library's loan records
     * Precondition: The item ID belongs to an active loan
     *
     * @param itemId the ID of the borrowed item
     */
    private void removeLoan(String itemId) {
        for (int i = 0; i < loanCount; i++) {
            if (loans[i].getItem().getId().equals(itemId)) {
                loans[i] = loans[loanCount - 1];
                loans[loanCount - 1] = null;
                loanCount--;
                break;
            }
        }
    }

    /**
     * Searches for library items whose titles contain the given text
     * Precondition: text is not null
     *
     * @param text the text to search for
     * @return matching items in catalogue order
     */
    public LibraryItem[] search(String text) {
        String keyword = text.toLowerCase();
        int count = 0;
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getTitle().toLowerCase().contains(keyword)) {
                count++;
            }
        }
        LibraryItem[] result = new LibraryItem[count];
        int index = 0;
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getTitle().toLowerCase().contains(keyword)) {
                result[index] = items[i];
                index++;
            }
        }
        return result;
    }

    /**
     * Returns all overdue library items
     * Precondition: day is a valid day
     *
     * @param day the current day
     * @return overdue items in catalogue order
     */
    public LibraryItem[] overdue(int day) {
        int count = 0;
        for (int i = 0; i < itemCount; i++) {
            Loan loan = findLoan(items[i].getId());
            if (loan != null && loan.getDueDay() < day) {
                count++;
            }
        }
        LibraryItem[] result = new LibraryItem[count];
        int index = 0;
        for (int i = 0; i < itemCount; i++) {
            Loan loan = findLoan(items[i].getId());
            if (loan != null && loan.getDueDay() < day) {
                result[index] = items[i];
                index++;
            }
        }
        return result;
    }

    /**
     * Returns the total unpaid fines for member
     * Precondition: None
     *
     * @param memberNumber the member number
     * @return the total unpaid fines
     * @throws IllegalArgumentException if the member does not exist
     */
    public double finesOwed(int memberNumber) {
        Member member = findMember(memberNumber);
        if (member == null) {
            throw new IllegalArgumentException("no member " + memberNumber);
        }
        return member.getFines();
    }
}
