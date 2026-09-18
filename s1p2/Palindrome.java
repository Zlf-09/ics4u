//Name: Oscar Zhu
//Date: September 17, 2026
//Purpose: To check whether a word is a palindrome.
package s1p2;
import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a word:");
        String word = input.nextLine();
        if (isPalindrome(word)) {
            System.out.println(word + "is a palindrome");
        }else {
            System.out.println(word + "is not a palindrome");
        }
        }
    public static boolean isPalindrome(String word) {
        word = word.toLowerCase();
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}



