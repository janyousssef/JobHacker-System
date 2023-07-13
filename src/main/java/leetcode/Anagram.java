package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Anagram {
    public static void main(String[] args) {
        System.out.println(solve("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(solve("leetcode"));
    }

    private static boolean solve(String str) {
        Set<Integer> map = new HashSet<>();
        str.chars().forEach(map::add);
        return map.size() == 26;
    }
}
