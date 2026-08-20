class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        int completeComponents = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Find all vertices in this component using BFS
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    component.add(curr);
                    
                    for (int neighbor : graph[curr]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
                
                // Check if this component is complete
                if (isComplete(graph, component)) {
                    completeComponents++;
                }
            }
        }
        
        return completeComponents;
    }
    
    private boolean isComplete(List<Integer>[] graph, List<Integer> component) {
        int size = component.size();
        
        // A component with 0 or 1 vertex is complete
        if (size <= 1) {
            return true;
        }
        
        // Check each vertex in component has edges to all other vertices in component
        for (int i = 0; i < size; i++) {
            int vertex = component.get(i);
            
            // Convert component to set for O(1) lookup
            Set<Integer> componentSet = new HashSet<>(component);
            
            // Check if vertex connects to all other vertices in the component
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                int other = component.get(j);
                if (!graph[vertex].contains(other)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
