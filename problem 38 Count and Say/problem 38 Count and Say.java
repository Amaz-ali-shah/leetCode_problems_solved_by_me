class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        
        String result = "1";
        
        for (int i = 2; i <= n; i++) {
            result = generateNext(result);
        }
        
        return result;
    }
    
    private String generateNext(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Count consecutive same characters
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }
            
            // Append count and the character
            sb.append(count);
            sb.append(s.charAt(i));
            
            // Reset count for next character group
            count = 1;
        }
        
        return sb.toString();
    }
}
