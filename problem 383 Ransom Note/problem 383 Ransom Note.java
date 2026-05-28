class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26]; // only lowercase English letters
        
        // Count characters in magazine
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Use characters for ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false; // not enough of this character
            }
            count[c - 'a']--;
        }
        
        return true;
    }
}