/*
* LibraryDemo.java (given: copy it into a2-library/ unchanged, then add
section 11)
* Runs the library through checkouts, refusals, returns, fines, search and
bad input.
*/
public class LibraryDemo {
    private static Library lib = new Library();

    private static void borrow(int member, String id, int day) {
        try {
            int due = lib.checkOut(member, id, day);
            System.out.println("Day " + day + ": member " + member + " borrows " + id + ", due day " + due);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Day " + day + ": refused: " + e.getMessage());
        }
    }

    private static void giveBack(String id, int day) {
        try {
            double fee = lib.returnItem(id, day);
            System.out.printf("Day %d: %s returned, fine $%.2f%n", day, id, fee);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Day " + day + ": refused: " + e.getMessage());
        }
    }

    private static void list(String label, LibraryItem[] found) {
        System.out.println(label + " (" + found.length + ")");
        for (int i = 0; i < found.length; i++) {
            System.out.println("   " + found[i]);
        }
    }

    public static void main(String[] args) {
        lib.addItem(new Book("B-100", "Rivers of Ontario", 2019, "M. Clarke",

                288));

        lib.addItem(new Book("B-101", "Birds of Ontario", 2015, "J. Park",

                312));

        lib.addItem(new Book("B-102", "Lake Effect", 2022, "S. Okafor",

                240));

        lib.addItem(new Book("B-103", "The Long Portage", 2008, "R. Martin",

                410));

        lib.addItem(new Book("B-104", "Winter Roads", 2024, "A. Singh",

                196));

        lib.addItem(new DVD("D-200", "Northern Lights", 2021, 94));
        lib.addItem(new Magazine("M-300", "Code Monthly", 2026, 42));
        lib.addMember(new Member(7, "Amira Haddad"));
        lib.addMember(new Member(12, "Ben Oyelaran"));
        list("1. Catalogue", lib.catalogue());
        System.out.println("2. Checkouts");
        borrow(7, "B-100", 0);
        borrow(7, "D-200", 3);
        System.out.println("3. Refusals");
        borrow(12, "B-100", 4);
        borrow(12, "M-300", 4);
        borrow(99, "B-101", 4);
        borrow(12, "X-999", 4);
        list("4. Overdue on day 12", lib.overdue(12));
        System.out.println("5. Returns");
        giveBack("D-200", 13);
        giveBack("D-200", 14);
        giveBack("B-100", 15);
        System.out.printf("Member 7 owes $%.2f%n", lib.finesOwed(7));
        System.out.println("6. The five-item limit");
        borrow(7, "B-100", 16);
        borrow(7, "B-101", 16);
        borrow(7, "B-102", 16);
        borrow(7, "B-103", 16);
        borrow(7, "B-104", 16);
        borrow(7, "D-200", 16);
        System.out.println("7. The fine limit");
        giveBack("B-104", 18);
        borrow(12, "D-200", 20);
        giveBack("D-200", 40);
        System.out.printf("Member 12 owes $%.2f%n", lib.finesOwed(12));
        borrow(12, "B-104", 41);
        list("8. Search \"ontario\"", lib.search("ontario"));
        list("9. Search \"zzz\"", lib.search("zzz"));
        System.out.println("10. Bad input");
        try {
            lib.addItem(new DVD("B-100", "Copy", 2020, 90));
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            lib.addMember(new Member(7, "Someone Else"));
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new Book("B-105", " ", 2000, "X", 10);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new DVD("D-201", "Future Film", 2031, 100);
            System.out.println("FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("11. My item");
        AppleLaptop laptop = new AppleLaptop("A-400", "MacBook Pro", 2025, 16);
        lib.addItem(laptop);
        System.out.println(laptop);
        borrow(7, "A-400", 42);
        giveBack("A-400", 52);
    }
}