import java.util.*;

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // Graph: airport -> min-heap of destinations
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }

        LinkedList<String> itinerary = new LinkedList<>();

        dfs("JFK", graph, itinerary);

        return itinerary;
    }

    private void dfs(String airport,
                     Map<String, PriorityQueue<String>> graph,
                     LinkedList<String> itinerary) {

        PriorityQueue<String> destinations = graph.get(airport);

        // Visit all outgoing destinations in lexical order
        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll();
            dfs(next, graph, itinerary);
        }

        // Add airport after all outgoing edges are used — post-order
        itinerary.addFirst(airport);
    }
}
