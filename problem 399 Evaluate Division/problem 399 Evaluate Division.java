import java.util.*;

class Solution {
    // Graph: variable -> (neighbor variable -> weight)
    Map<String, Map<String, Double>> graph = new HashMap<>();
    // Visited set for DFS
    Set<String> visited = new HashSet<>();

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        // Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            // a / b = val  →  edge a→b with weight val
            graph.get(a).put(b, val);
            // b / a = 1/val →  edge b→a with weight 1/val
            graph.get(b).put(a, 1.0 / val);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dst = queries.get(i).get(1);

            // If either variable is not in the graph, the answer is -1.0
            if (!graph.containsKey(src) || !graph.containsKey(dst)) {
                result[i] = -1.0;
            } else {
                visited.clear();                 // reset visited for each query
                result[i] = dfs(src, dst, 1.0);  // start with product = 1.0
            }
        }
        return result;
    }

    private double dfs(String current, String target, double product) {
        // If we reached the target, return the accumulated product
        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
            String next = neighbor.getKey();
            double weight = neighbor.getValue();

            if (!visited.contains(next)) {
                double res = dfs(next, target, product * weight);
                if (res != -1.0) {   // valid path found
                    return res;
                }
            }
        }
        return -1.0;   // no path found
    }
}
