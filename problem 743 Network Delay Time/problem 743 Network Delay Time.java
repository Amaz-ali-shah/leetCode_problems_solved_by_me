class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.get(u).add(new int[]{v, w});
        }
        
        // Distance array to store shortest distance from source k
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        
        // Min-heap priority queue: [node, distance]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currDist = current[1];
            
            // Skip if we've already found a better path
            if (currDist > dist[node]) continue;
            
            // Explore neighbors
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDist = currDist + weight;
                
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }
        
        // Find the maximum distance among all nodes
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // Some node is unreachable
            }
            maxTime = Math.max(maxTime, dist[i]);
        }
        
        return maxTime;
    }
}
