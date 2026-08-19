class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] state = new int[n]; // 0 = unvisited, 1 = visiting, 2 = safe
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, state)) {
                result.add(i);
            }
        }
        return result;
    }
    
    private boolean dfs(int node, int[][] graph, int[] state) {
        if (state[node] > 0) {
            return state[node] == 2;
        }
        
        state[node] = 1; // visiting
        
        for (int neighbor : graph[node]) {
            if (!dfs(neighbor, graph, state)) {
                return false;
            }
        }
        
        state[node] = 2; // safe
        return true;
    }
}
