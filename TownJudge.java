class Solution 
{
    public int findJudge(int n, int[][] trust) 
    {
        if(trust.length<n-1)
        {
            return -1;
        }
        if(trust.length==0&&n==1)
        {
            return 1;
        }
        int degree[]=new int[n+1];
        for (int[] pair : trust) {
    degree[pair[0]]--;
    degree[pair[1]]++;
}

        for(int i=1;i<=n;i++)
        {
            if(degree[i]==n-1)
            {
                return i;
            }
        }
        return -1;
    } 
}
