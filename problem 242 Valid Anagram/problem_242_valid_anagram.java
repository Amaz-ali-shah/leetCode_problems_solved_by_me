class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, they can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        // Array to store count of each letter (26 letters a-z)
        int[] counts = new int[26];
        
        // Count characters in string s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;  // 'a' -> index 0, 'b' -> index 1, etc.
        }
        
        // Subtract counts using string t
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            counts[c - 'a']--;
            
            // If any count becomes negative, t has more of this letter than s
            if (counts[c - 'a'] < 0) {
                return false;
            }
        }
        
        // All counts should be zero
        return true;
    }
}