class Solution {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0;
        int n = s.length();

        // 1. Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n) return 0;

        // 2. Determine sign
        int sign = 1;
        char first = s.charAt(i);
        if (first == '+' || first == '-') {
            sign = (first == '-') ? -1 : 1;
            i++;
        }

        // 3. Convert digits and clamp to 32-bit signed integer range
        long result = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            result = result * 10 + (s.charAt(i) - '0');

            // Early overflow detection
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (result * sign);
    }
}