package algorithm.practice.leetcode;

import java.util.Arrays;

/**
 * 16. 3Sum Closest: https://leetcode.com/problems/3sum-closest/
 * <p>
 * tags: medium, array, two pointers
 *
 * @author TomAndersen
 */
public class LeetCode16 {

}

/**
 * Sort + two pointers
 * TC: O(n), SC: O(1)
 */
class LeetCode16_1 {
    public int threeSumClosest(int[] nums, int target) {
        // exclude boundary condition
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // sort
        Arrays.sort(nums);
        // must initialize the closest sum with any three element of array
        int closestSum = nums[0] + nums[1] + nums[2];

        // iterate all alternative elements in first position
        // and divide into multiple two pointers sub-problem
        for (int i = 0; i < nums.length - 2; i++) {

            // two pointers
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(curSum - target) < Math.abs(closestSum - target)) {
                    closestSum = curSum;
                }

                if (curSum > target) {
                    right -= 1;
                }
                else if (curSum < target) {
                    left += 1;
                }
                else {
                    return target;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{0, 0, 0}, 1));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{0, 1, 2}, 0));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{1, 1, 1, 1}, 0));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5}, -2));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{0, 3, 97, 102, 200}, 300));
        System.out.println(new LeetCode16_1().threeSumClosest(new int[]{13, 252, -87, -431, -148, 387, -290, 572, -311, -721, 222, 673, 538, 919, 483, -128, -518, 7, -36, -840, 233, -184, -541, 522, -162, 127, -935, -397, 761, 903, -217, 543, 906, -503, -826, -342, 599, -726, 960, -235, 436, -91, -511, -793, -658, -143, -524, -609, -728, -734, 273, -19, -10, 630, -294, -453, 149, -581, -405, 984, 154, -968, 623, -631, 384, -825, 308, 779, -7, 617, 221, 394, 151, -282, 472, 332, -5, -509, 611, -116, 113, 672, -497, -182, 307, -592, 925, 766, -62, 237, -8, 789, 318, -314, -792, -632, -781, 375, 939, -304, -149, 544, -742, 663, 484, 802, 616, 501, -269, -458, -763, -950, -390, -816, 683, -219, 381, 478, -129, 602, -931, 128, 502, 508, -565, -243, -695, -943, -987, -692, 346, -13, -225, -740, -441, -112, 658, 855, -531, 542, 839, 795, -664, 404, -844, -164, -709, 167, 953, -941, -848, 211, -75, 792, -208, 569, -647, -714, -76, -603, -852, -665, -897, -627, 123, -177, -35, -519, -241, -711, -74, 420, -2, -101, 715, 708, 256, -307, 466, -602, -636, 990, 857, 70, 590, -4, 610, -151, 196, -981, 385, -689, -617, 827, 360, -959, -289, 620, 933, -522, 597, -667, -882, 524, 181, -854, 275, -600, 453, -942, 134}, -2805));
    }
}

/**
 * Sort + HashMap
 * TC: O(n), SC: O(n)
 */
class LeetCode16_2 {

}
