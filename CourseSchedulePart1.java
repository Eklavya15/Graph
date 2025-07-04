class Solution 
{
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
        graph.add(new ArrayList<>());
    }

    for (int[] pre : prerequisites) {
        graph.get(pre[1]).add(pre[0]); // b → a
    }

    boolean[] visited = new boolean[numCourses];
    boolean[] onPath = new boolean[numCourses];

    for (int i = 0; i < numCourses; i++) {
        if (!visited[i]) {
            if (hasCycle(i, graph, visited, onPath)) {
                return false;
            }
        }
    }

    return true;
}

private boolean hasCycle(int node, List<List<Integer>> graph, boolean[] visited, boolean[] onPath) {
    visited[node] = true;
    onPath[node] = true;

    for (int neighbor : graph.get(node)) {
        if (!visited[neighbor]) {
            if (hasCycle(neighbor, graph, visited, onPath)) {
                return true;
            }
        } else if (onPath[neighbor]) {
            return true; // Cycle detected
        }
    }

    onPath[node] = false;
    return false;
}


}
