package algorithm.practice.leetcode;

/**
 * 6. Zigzag Conversion:
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * @author TomAndersen
 */
public class LeetCode6 {
}

class LeetCode6_1 {
    public String convert(String s, int numRows) {
        // boundary condition
        if (s == null || s.length() == 0 || numRows < 0) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }

        // handle
        int len = s.length(), num = numRows;
        int radix = num * 2 - 2;
        StringBuilder[] stringBuilders = new StringBuilder[num];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        for (int i = 0; i < len; i++) {
            int idx = i % radix;
            int left = idx, right = radix - idx;
            int line = Math.min(left, right);
            stringBuilders[line].append(s.charAt(i));
        }

        // output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringBuilders.length; i++) {
            sb.append(stringBuilders[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("PAHNAPLSIIGYIR".equals(new LeetCode6_1().convert("PAYPALISHIRING", 3)));
        System.out.println("PINALSIGYAHRPI".equals(new LeetCode6_1().convert("PAYPALISHIRING", 4)));
        System.out.println("A".equals(new LeetCode6_1().convert("A", 1)));
    }
}

class LeetCode6_2 {
    public String convert(String s, int numRows) {
        // boundary condition
        if (s == null || s.length() == 0 || numRows == 0) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }

        // handle
        StringBuilder sb = new StringBuilder();
        int len = s.length(), radix = numRows * 2 - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < len; j++) {
                int idx = j % radix;
                int lineNum = Math.min(idx, radix - idx);
                if (lineNum == i) {
                    sb.append(s.charAt(j));
                }
            }
        }

        // output
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("PAHNAPLSIIGYIR".equals(new LeetCode6_2().convert("PAYPALISHIRING", 3)));
        System.out.println("PINALSIGYAHRPI".equals(new LeetCode6_2().convert("PAYPALISHIRING", 4)));
        System.out.println("A".equals(new LeetCode6_2().convert("A", 1)));
    }

}

class LeetCode6_3 {
    public String convert(String s, int numRows) {
        // boundary
        if (s == null || s.length() == 0 || numRows == 0) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }

        // handle
        StringBuilder sb = new StringBuilder();
        int cycle = numRows * 2 - 2, len = s.length();
        // first row
        for (int i = 0; i < len; i += cycle) {
            sb.append(s.charAt(i));
        }

        // middle row
        for (int row = 1; row < numRows - 1; row++) {
            int distance = 2 * (numRows - row - 1);

            for (int i = row; i < len; i += cycle) {
                int left = i;
                sb.append(s.charAt(left));

                int right = i + distance;
                if (right < len) {
                    sb.append(s.charAt(right));
                }
            }
        }

        // last row
        for (int i = numRows - 1; i < len; i += cycle) {
            sb.append(s.charAt(i));
        }

        // output
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode6_3().convert("PAYPALISHIRING", 4));
        System.out.println("PAHNAPLSIIGYIR".equals(new LeetCode6_3().convert("PAYPALISHIRING", 3)));
        System.out.println("PINALSIGYAHRPI".equals(new LeetCode6_3().convert("PAYPALISHIRING", 4)));
        System.out.println("A".equals(new LeetCode6_3().convert("A", 1)));
    }
}

/**
 * Line by line, step by step.
 */
class LeetCode6_4 {
    public String convert(String s, int numRows) {
        // exclude boundary condition
        if (s == null || s.length() == 0 || numRows == 0) {
            return null;
        }
        if (numRows == 1) {
            return s;
        }

        // handler
        StringBuilder sb = new StringBuilder();
        int len = s.length();

        // the first line
        int step = numRows * 2 - 2;
        for (int i = 0; i < len; i += step) {
            sb.append(s.charAt(i));
        }

        // the middle lines
        for (int n = 1; n < numRows - 1; n += 1) {
            // each middle line consist of multi cycles
            for (int i = n; i < len; i += step) {
                // each cycle may consist of two characters
                sb.append(s.charAt(i));

                int interval = (numRows - n - 1) * 2;
                if (i + interval < len) {
                    sb.append(s.charAt(i + interval));
                }
            }
        }

        // the last line
        for (int i = numRows - 1; i < len; i += step) {
            sb.append(s.charAt(i));
        }

        // return
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("PAHNAPLSIIGYIR".equals(new LeetCode6_4().convert("PAYPALISHIRING", 3)));
        System.out.println("PINALSIGYAHRPI".equals(new LeetCode6_4().convert("PAYPALISHIRING", 4)));
        System.out.println("A".equals(new LeetCode6_4().convert("A", 1)));
    }
}
