import java.util.*;

class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] freq = new int[26];

        // Count frequencies
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // If a character appears too many times, it's impossible
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }
        if (maxFreq > (n + 1) / 2) {
            return "";
        }

        // Max-heap by frequency
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[1] != a[1] ? b[1] - a[1] : a[0] - b[0]
        );

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(new int[]{i, freq[i]});
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            char c = (char) (first[0] + 'a');

            // If c is different from last placed character, use it
            if (sb.length() == 0 || c != sb.charAt(sb.length() - 1)) {
                sb.append(c);
                first[1]--;
                if (first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                // Otherwise, use the second most frequent character
                if (pq.isEmpty()) return "";

                int[] second = pq.poll();
                char d = (char) (second[0] + 'a');

                sb.append(d);
                second[1]--;
                if (second[1] > 0) {
                    pq.offer(second);
                }

                // Put the first character back
                pq.offer(first);
            }
        }

        return sb.toString();
    }
}
