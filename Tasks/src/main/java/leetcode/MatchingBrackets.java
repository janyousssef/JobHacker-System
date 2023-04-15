package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

public class MatchingBrackets {
    static TreeSet<Character> openers = new TreeSet<>();

    public static void main(String[] args) {
        initialize();
        System.out.println(solve("()"));
        System.out.println(solve("()[]{}"));
        System.out.println(solve("(]"));
        System.out.println(solve("]"));
        System.out.println(solve("((("));
    }

    private static void initialize() {
        openers.add('[');
        openers.add('{');
        openers.add('(');
    }

    private static boolean solve(String str) {
        Deque<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if (isOpeningBracket(str.charAt(i)))
                queue.add(str.charAt(i));
            else if (!queue.isEmpty() && isCorrectClosingBracket(queue.peekLast(), str.charAt(i)))
                queue.removeLast();
            else
                return false;
        }
        return queue.isEmpty();
    }

    private static boolean isOpeningBracket(char c) {
        return openers.contains(c);
    }

    private static boolean isCorrectClosingBracket(Character last, char current) {
        if (last == '[' && current == ']') return true;
        if (last == '{' && current == '}') return true;
        if (last == '(' && current == ')') return true;

        return false;
    }

}
