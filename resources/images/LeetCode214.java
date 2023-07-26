package algorithm.practice.leetcode;

/**
 * 214. Shortest Palindrome: https://leetcode.com/problems/shortest-palindrome/
 * <p>
 * tags: hard, string
 *
 * @author TomAndersen
 */
public class LeetCode214 {
}

/**
 * Brute Force: expanding from center
 * <p>
 * Time Limit Exceeded, 120 / 123 testcases passed
 */
class LeetCode214_1 {
    public String shortestPalindrome(String s) {
        // exclude boundary condition
        if (s == null || s.length() <= 1) {
            return s;
        }

        // expanding from center
        // find the right half of palindromic string, from the middle to the left endpoint
        int length = s.length();
        int append = length - 1;
        for (int i = 0; i <= (length - 1) / 2; i += 1) {
            // middle is one element
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left -= 1;
                right += 1;
            }

            if (left == -1) {
                append = Math.min(append, length - 1 - i - i);
            }

            // middle is two elements
            left = i;
            right = i + 1;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left -= 1;
                right += 1;
            }

            if (left == -1) {
                append = Math.min(append, length - 2 - i - i);
            }
        }

        // expand the string to palindromic and return
        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= length - append; i -= 1) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode214_1().shortestPalindrome("aacecaaa").equals("aaacecaaa"));
        System.out.println(new LeetCode214_1().shortestPalindrome("abcd").equals("dcbabcd"));
        System.out.println(new LeetCode214_1().shortestPalindrome("a").equals("a"));
        System.out.println(new LeetCode214_1().shortestPalindrome("aabba").equals("abbaabba"));
    }
}

/**
 * find the longest palindromic substring in the header
 * 120 / 123 testcases passed
 * TC: O(n*n), SC: O(1)
 */
class LeetCode214_2 {
    public String shortestPalindrome(String s) {
        // exclude boundary condition
        if (s == null || s.length() <= 1) {
            return s;
        }

        // find the longest palindromic substring in the header
        int len = s.length();
        int maxPalindromicHeaderLen = 1;

        for (int i = 0; i < len; i++) {
            int left = 0, right = i;

            // whether it is a palindromic string
            while (left < right && s.charAt(left) == s.charAt(right)) {
                left += 1;
                right -= 1;
            }
            if (left >= right) {
                maxPalindromicHeaderLen = i + 1;
            }
        }

        // expand the string to palindromic and return
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i > maxPalindromicHeaderLen - 1; i -= 1) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode214_2().shortestPalindrome("aacecaaa").equals("aaacecaaa"));
        System.out.println(new LeetCode214_2().shortestPalindrome("abcd").equals("dcbabcd"));
        System.out.println(new LeetCode214_2().shortestPalindrome("a").equals("a"));
        System.out.println(new LeetCode214_2().shortestPalindrome("aabba").equals("abbaabba"));
    }

}
