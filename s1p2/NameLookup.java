// Name: Oscar Zhu
//Date: September 17, 2026
// Purpose: Looks for a name in a list.

package s1p2;

import java.util.Scanner;

public class NameLookup {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] names = {"Oscar", "Richard", "Jerry", "Zac", "Sam", "Issac", "Frank", "Daniel"};
        System.out.println("Enter a name");
        String target = input.nextLine();
        int position = findName(names, target);
        if (position != -1) {
            System.out.println("Found at position" + position);
        } else {
            System.out.println("Name cannot be found");
        }
    }

    public static int findName(String[] names, String target) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}