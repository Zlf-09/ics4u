/*
 * Oscar Zhu
 * October 29, 2026
 * Checks whether brackets are balanced using LinkedStack
 */
public class Brackets {
    /**
     * Checks whether brackets are balanced, ignoring other characters
     * Precondition: s is not null
     * Big-O: O(n), where n is the length of s
     *
     * @param s the string to check
     * @return true if all brackets match in the correct order
     * @throws IllegalArgumentException if s is null
     */
    public static boolean isBalanced(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s must not be null");
        }
        LinkedStack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}'){
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if ((ch == ')' && open != '(')
                        || (ch == ']' && open != '[')
                        || (ch == '}' && open != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
