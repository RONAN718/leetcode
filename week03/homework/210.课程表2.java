/*
210. 课程表 II

思路
使用BFS +拓扑排序来找合适的课程表

拆分步骤
1.生成邻接表和入度数组
2.使用拓扑排序维护一个双端队列
3.如果完成学习的课程数等于总课程数 返回结果否则返回空数组

*/

class Solution {
    //声明邻接表和入度数组和总课程数
    private List<List<Integer>> edges;
    private int[] indegrees;
    int n;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //邻接表和入度数组和总课程数赋初始值
        n=numCourses;
        edges=new ArrayList<List<Integer>>();
        indegrees=new int[n];
        for(int i=0;i<n;i++){
            edges.add(new ArrayList<Integer>());
            indegrees[i]=0;
        }
        for(int[] prerequisite:prerequisites){
            int u=prerequisite[0];
            int v=prerequisite[1];
            addEdge(u,v);
        }
        return topSort();
    }
    //拓扑排序
    public int[] topSort(){
        int learned=0;
        int[] res=new int[n];
        //使用双端队列来表示当前解锁课程情况
        Deque<Integer> q=new LinkedList<>();
        //寻找那些一开始就可以学不用预修的课程入队
        for(int i=0;i<n;i++)
        if(indegrees[i]==0) q.offer(i);

        //队列不空就持续
            while(q.size()!=0){
            //解锁第一门课
                int first=q.poll();
            //记下课程号
                res[learned]=first;
            //已修课程加一
                learned++;
                //解锁该课后需要维护其相关的后续课程
                for(int y:edges.get(first)){
                    //后续课程的入度减一
                    indegrees[y]--;
                    //如果解锁了就加入队列的尾部
                    if(indegrees[y]==0)
                    q.offer(y);
                }
            }

            //运行结束后查看是否学了所有课，如果学了就返回记录的结果集
            if(learned==n)
            return res;
            //如果没有就说明课程内有环结构 返回空组数
            return new int[0];
    }
    //维护邻接表和入度数组
    public void addEdge(int x,int y){
        edges.get(y).add(x);
        indegrees[x]++;
    }
}