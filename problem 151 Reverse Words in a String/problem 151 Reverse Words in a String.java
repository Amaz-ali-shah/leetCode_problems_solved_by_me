class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        // 1. Reverse whole string
        reverse(arr, 0, n - 1);
        
        // 2. Reverse each word
        int start = 0;
        for (int end = 0; end < n; end++) {
            if (arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }
        reverse(arr, start, n - 1); // last word
        
        // 3. Clean extra spaces in-place
        int i = 0;      // write position
        int j = 0;      // read position
        boolean firstWord = true;
        
        while (j < n) {
            // skip spaces
            while (j < n && arr[j] == ' ') j++;
            if (j >= n) break;
            
            // add space before this word (except first)
            if (!firstWord) {
                arr[i++] = ' ';
            }
            firstWord = false;
            
            // copy the word
            while (j < n && arr[j] != ' ') {
                arr[i++] = arr[j++];
            }
        }
        
        return new String(arr, 0, i);
    }
    
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char tmp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tmp;
        }
    }
}