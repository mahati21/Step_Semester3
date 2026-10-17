package categoryB.Class_problemsW9;

public class W9TargetSumBruteForce {
    public static boolean hasPairWithSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {3, 4, 6};
        System.out.println(hasPairWithSum(nums, 20));
    }
}
