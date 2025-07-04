class Solution {
    static{
        int[][] a = new int[][]{{1}};
        for(int i = 0 ; i < 500 ; i++)
            findMinHeightTrees(1, a);
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(0);

        int[] deg = new int[n];
        for(int[] edge : edges){
            deg[edge[0]]++;
            deg[edge[1]]++;
        }

        int curNbrsCount;
        int[][] nbrs = new int[n][];
        for(int i = 0 ; i < n ; i++){
            curNbrsCount = deg[i];
            nbrs[i] = new int[curNbrsCount]; 
        }

        int[] nextNbrPos = new int[n];
        for(int[] edge : edges){
            int u = edge[0], v = edge[1];
            nbrs[u][nextNbrPos[u]++] = v;
            nbrs[v][nextNbrPos[v]++] = u;
        }

        int[] newLeaves = new int[n];
        int newLeavesCount = 0;
        for(int i = 0 ; i < n ; i++)
            if(deg[i] == 1)
                newLeaves[newLeavesCount++] = i;
        
        boolean[] been = new boolean[n];
        int[] curLeaves = new int[n], temp;
        int remains = n, leaveNeig, leave, curLeavesCount;
        
        Arrays.fill(nextNbrPos, 0);
        while(remains > 2){
            temp = curLeaves;
            curLeaves = newLeaves;
            newLeaves = temp;

            curLeavesCount = newLeavesCount;
            newLeavesCount = 0;
            remains -= curLeavesCount;

            for(int i = 0 ; i < curLeavesCount ; i++){
                leave = curLeaves[i];
                int[] curLeaveNbrs = nbrs[leave];
                while(been[curLeaveNbrs[nextNbrPos[leave]]])
                    nextNbrPos[leave]++;
                leaveNeig = nbrs[leave][nextNbrPos[leave]];

                deg[leave]--;
                deg[leaveNeig]--;
                been[leave] = true;
                if(deg[leaveNeig] == 1)
                    newLeaves[newLeavesCount++] = leaveNeig;
            }
        }
        if(remains == 1)
            return Arrays.asList(newLeaves[0]);
        return Arrays.asList(newLeaves[0], newLeaves[1]);
    }
}
