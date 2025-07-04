class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }
        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", graph, itinerary);
        return itinerary;
    }

    public void dfs(String aiport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = graph.get(aiport);
        while (destinations != null && !destinations.isEmpty()) {
            String nextDest = destinations.poll();
            dfs(nextDest, graph, itinerary);
        }
        itinerary.addFirst(aiport);
    }
}
