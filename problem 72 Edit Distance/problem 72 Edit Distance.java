class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] = minimum operations to convert word1[0..i-1] to word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases
        // If word1 is empty, we need to insert all characters of word2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // If word2 is empty, we need to delete all characters of word1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Three possible operations:
                    // 1. Insert: dp[i][j-1] + 1
                    // 2. Delete: dp[i-1][j] + 1
                    // 3. Replace: dp[i-1][j-1] + 1
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1], // Replace
                        Math.min(dp[i][j - 1], // Insert
                                 dp[i - 1][j]) // Delete
                    );
                }
            }
        }
        
        return dp[m][n];
    }
}
