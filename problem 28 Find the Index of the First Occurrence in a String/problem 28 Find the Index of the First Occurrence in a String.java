class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        
        if (needleLen > haystackLen) {
            return -1;
        }
        
        // Build the LPS (Longest Proper Prefix which is also Suffix) array
        int[] lps = new int[needleLen];
        computeLPS(needle, lps);
        
        int i = 0; // index for haystack
        int j = 0; // index for needle
        
        while (i < haystackLen) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                
                if (j == needleLen) {
                    return i - j;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1;
    }
    
    private void computeLPS(String pattern, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}