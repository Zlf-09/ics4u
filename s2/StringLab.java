package s2;
//Name: Oscar Zhu
//Date: September 21, 2026
//Purpose: Practice strings, StringBuilder, split, and formatted output

public class StringLab {

    // TODO 1: return the initials of a "First Last" name.
    //         "Amira Khan" -> "A.K."
    //         Hint: indexOf(" ") finds the gap; charAt and substring do the rest.
    public static String initials(String fullName) {
        char firstInitial = fullName.charAt(0);
        int space = fullName.indexOf(" ");
        char lastInitial = fullName.charAt(1 + space);
        return firstInitial + "." + lastInitial + ".";
    }

    // TODO 2: reverse a string using StringBuilder.
    //         "hello" -> "olleh"
    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return sb.toString();
    }

    // TODO 3: count the words in a sentence. Words are separated by single spaces.
    //         "one two three" -> 3
    //         Hint: split, then look at the array.
    public static int wordCount(String sentence) {
        String[] parts = sentence.split(" ");
        return parts.length;
    }

    public static void main(String[] args) {
        System.out.println(initials("Amira Khan"));       // A.K.
        System.out.println(reverse("hello"));             // olleh
        System.out.println(wordCount("one two three"));   // 3

        // One record, exactly as it will appear in employees.txt
        String record = "Amira Khan,1001,22.50,38";

        // TODO 4: split the record on commas, convert each piece to the right type
        //         (String, int, double, double), and print it with printf so it reads:
        String[] parts = record.split(",");
        String name = parts[0];
        int id = Integer.parseInt(parts[1]);
        double wages = Double.parseDouble(parts[2]);
        double hours = Double.parseDouble(parts[3]);
        System.out.printf("%s (#%d) earns $%.2f/hr, worked %.1f hrs%n", name, id, wages, hours);
    }
}