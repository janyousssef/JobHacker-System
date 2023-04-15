package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LongestPrefix {
    public static void main(String[] args) {
        System.out.println(solve(new String[]{"flower","flow","flight"}));
        System.out.println(solve(new String[]{"dog","racecar","car"}));
    }

    private static String solve(String[] strings) {
        StringBuilder pref = new StringBuilder();
        int shortest = Arrays.stream(strings).min(Comparator.comparing(String::length)).get().length();
        for (int i = 0; i < shortest; i++) {
            if (allHaveSameLetter(strings, i))
                pref.append(strings[0].charAt(i));
        }
        return pref.toString();
    }

    private static boolean allHaveSameLetter(String[] strings, int i) {
        for (int j = 1; j < strings.length; j++) {
            if (strings[j-1].charAt(i)!=strings[j].charAt(i)) return false;
        }
        return true;
    }
}
