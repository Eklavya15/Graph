class Solution 
{
    public static Node cloneGraph(Node startnode)
    {
        if(startnode==null)
        {
            return null;
        }
        Map<Node,Node>adjlist=new HashMap<>();
        Queue<Node>que=new LinkedList<>();
        que.offer(startnode);
        adjlist.put(startnode,new Node(startnode.val));
        while(!que.isEmpty())
        {
            Node cur=que.poll();
            for(Node neighbor:cur.neighbors)
            {
                if(!adjlist.containsKey(neighbor))
                {
                     adjlist.put(neighbor,new Node(neighbor.val));
                     que.offer(neighbor);
                }
                adjlist.get(cur).neighbors.add(adjlist.get(neighbor));
            }
        }
        return adjlist.get(startnode);
    }
}
