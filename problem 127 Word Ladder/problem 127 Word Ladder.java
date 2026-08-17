class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to HashSet for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If endWord is not in wordList, no transformation possible
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // BFS queue
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Visited set to avoid revisiting words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        int level = 1; // Start with level 1 (beginWord itself)
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all words at current level
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();
                
                // Try changing each character
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    // Try all 26 lowercase letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        
                        // If we found the endWord, return level + 1
                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }
                        
                        // If newWord is valid and not visited
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                    
                    // Restore original character
                    wordChars[j] = originalChar;
                }
            }
            
            level++;
        }
        
        // No transformation sequence found
        return 0;
    }
}
