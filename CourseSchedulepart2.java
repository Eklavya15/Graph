class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        int indeg[] = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++) graph[i] = new ArrayList<>();
        for(int[] depd : prerequisites){
            int course = depd[0];
            int dependent = depd[1];
            graph[dependent].add(course);
            indeg[course]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i =0 ;i< numCourses; i++){
            if(indeg[i] == 0) q.offer(i);
        }
        int cnt = 0;
        while(!q.isEmpty()){
            int course = q.poll();
            order[cnt++] = course;
            for(int depd: graph[course]){
                if(--indeg[depd] == 0) q.offer(depd);
            }
        }
        System.gc();
        if(cnt != numCourses) return new int[0];
        return order;
    }
}
