package com.example.zlxpractice;

/**
 * 动态规划问题
 */
public class DP {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        int min = nums[0];
        int max = nums[0];
        int res = nums[0];
        for(int i=1; i<nums.length; i++){
            int x = nums[i];
            int curMax = Math.max(x, Math.max(x * min, x * max));
            int curMin = Math.min(x, Math.min(x * min, x * max));

            min = Math.min(min, curMin);
            max = Math.max(max, curMax);

            res = Math.max(res, max);
        }
        System.out.println(res);
    }
}
