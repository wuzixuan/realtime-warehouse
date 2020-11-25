package com.zixuan.leetcode;

import java.util.Timer;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length-1; i++){
            for (int j = 0; i+j+1 <=nums.length-1-j ; j++) {
                int leftPointTmp = i+j+1;
                int rigthPointTmp = nums.length-1-j;
                if (leftPointTmp<nums.length && nums[i]+nums[leftPointTmp]==target){
                    return new int[] {i,leftPointTmp};
                }
                if (rigthPointTmp>i && nums[i]+nums[rigthPointTmp]==target){
                    return new int[] {i,rigthPointTmp};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] ints = {3,2,4};
        int target = 6;
        int[] twoSum = twoSum(ints, target);
        System.out.println(twoSum[0]+" "+twoSum[1]);
    }
}
