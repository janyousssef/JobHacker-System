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
            if (openers.contains(str.charAt(i))) queue.add(str.charAt(i));
            else if (!queue.isEmpty() && isCorrectCloser(str.charAt(i), queue.peekLast())) {
                queue.removeLast();
            } else return false;
        }
        return queue.size() == 0;
    }

    private static boolean isCorrectCloser(char current, Character last) {
        if (last == '[' && current == ']') return true;
        if (last == '{' && current == '}') return true;
        if (last == '(' && current == ')') return true;
        return false;
    }

}
