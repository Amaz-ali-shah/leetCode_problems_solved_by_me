import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        if (n1 > n2) return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Count characters in s1 and the first window of s2
        for (int i = 0; i < n1; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(count1, count2)) return true;

        // Slide the window over s2
        for (int i = n1; i < n2; i++) {
            count2[s2.charAt(i) - 'a']++;          // add new character
            count2[s2.charAt(i - n1) - 'a']--;     // remove old character

            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }

        return false;
    }
}
