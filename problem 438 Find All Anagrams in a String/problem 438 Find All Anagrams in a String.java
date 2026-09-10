class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int ns = s.length(), np = p.length();
        if (ns < np) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // First window
        for (int i = 0; i < np; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount)) result.add(0);

        // Slide the window
        for (int i = np; i < ns; i++) {
            sCount[s.charAt(i) - 'a']++;          // add new right char
            sCount[s.charAt(i - np) - 'a']--;     // remove old left char
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - np + 1);
            }
        }
        return result;
    }
}
