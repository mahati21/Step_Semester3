package categoryB.Class_problemsW9;

import java.util.HashSet;
import java.util.Set;

public class W9TargetSumHashing {
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(hasPairWithSum(nums, 9));
    }
}