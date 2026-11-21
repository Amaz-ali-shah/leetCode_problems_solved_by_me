class Solution {
    int[] parent;
    int[] rank;
    int count;

    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(e[0], e[1]);
        }
        return count;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return; // already in same component

        // union by rank
        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
        count--; // merged two components
    }
}
