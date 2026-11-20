class Solution {
    public String alienOrder(String[] words) {
        // Graph: char -> set of chars that must come AFTER it
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // 1. Register every character
        for (String w : words) {
            for (char c : w.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // 2. Derive edges from adjacent word pairs
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i], b = words[i + 1];
            int minLen = Math.min(a.length(), b.length());
            boolean foundDiff = false;

            for (int j = 0; j < minLen; j++) {
                char ca = a.charAt(j), cb = b.charAt(j);
                if (ca != cb) {
                    if (graph.get(ca).add(cb)) {       // avoid duplicate edges
                        indegree.put(cb, indegree.get(cb) + 1);
                    }
                    foundDiff = true;
                    break;
                }
            }
            // Invalid case: "abc" before "ab"
            if (!foundDiff && a.length() > b.length()) return "";
        }

        // 3. Kahn's BFS topological sort
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) queue.offer(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char next : graph.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) queue.offer(next);
            }
        }

        // 4. If not all characters ordered, a cycle exists
        return sb.length() == indegree.size() ? sb.toString() : "";
    }
}
