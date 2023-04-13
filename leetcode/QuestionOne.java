package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class QuestionOne {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(new int[]{3, 3}, 6)));
    }

    private static int[] solve(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                if (!map.get(target - nums[i]) .equals(i) )
                    return new int[]{i, map.get(target - nums[i])};
        }

        return null;
    }
}
