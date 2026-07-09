class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0, maxLen = 1; // track the longest palindrome's start and length

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                // Calculate the starting index of the palindrome
                // For odd length: start = i - len/2
                // For even length: start = i - (len/2 - 1) = i - len/2 + 1
                // The formula (i - (len - 1) / 2) works for both cases
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length = right - left - 1 because after loop left and right are one step outside
        return right - left - 1;
    }
}