import java.util.Stack;

public class StacknQueue {

    public static void main(String[] args) {
        
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab#cc", "ad#c"));
        System.out.println(backspaceCompare("bxj##tw", "bxo#j##tw"));

    }

    /** 
     * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

        Note that after backspacing an empty text, the text will continue empty.

        

        Example 1:

        Input: s = "ab#c", t = "ad#c"
        Output: true
        Explanation: Both s and t become "ac".
        Example 2:

        Input: s = "ab##", t = "c#d#"
        Output: true
        Explanation: Both s and t become "".
        Example 3:

        Input: s = "a#c", t = "b"
        Output: false
        Explanation: s becomes "c" while t becomes "b".
        

        Constraints:

        1 <= s.length, t.length <= 200
        s and t only contain lowercase letters and '#' characters.
        

        Follow up: Can you solve it in O(n) time and O(1) space?
     */
    public static boolean backspaceCompare(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;

        return editWord(s).equals(editWord(t));

    }

    private static Stack<Character> editWord(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(char c : s.toCharArray()) {
            if(c != '#') stack.push(c);
            else {
                if(!stack.isEmpty()) stack.pop();
            }
        } 
        return stack;
    }

}

