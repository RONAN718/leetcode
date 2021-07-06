
106. 从中序与后序遍历序列构造二叉树  

思路  

后序可以确定根节点  左右根  

中序可以用来区分左右子树的范围 左根右  

先通过后序找到根节点 然后在中序中区分左右子树再分别做递归构成树

```java
class Solution {
    //声明两个用来存储后序和中序的数组
    private int[] postorder_;
    private int[] inorder_;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorder_=postorder;
        inorder_=inorder;
        return build(0,postorder_.length-1,0,inorder_.length-1);
    }

     //递归的主函数 l1 r1代表后序的范围 l2r2代表中序的范围
    public TreeNode build(int l1,int r1,int l2,int r2){
        //终止条件
        if(l1>r1||l2>r2) return null;
        //因为后序的最后一个结点就是根节点
        TreeNode root=new TreeNode(postorder_[r1]);
        //遍历中序数组找到根节点的位置
        int mid= l2;
        while(postorder_[r1]!=inorder_[mid])mid++;
        //用递归来构建左右子树 注意范围mid-l2-1左子树的偏移量 
        root.left=build(l1,l1+mid-l2-1,l2,mid-1);
        //中序要当心mid需要去掉因为已经生成为根节点了
        root.right=build(l1+mid-l2,r1-1,mid+1,r2);
        return root;
    }
}
```


210. 课程表 II
思路  

使用BFS +拓扑排序来找合适的课程表  

拆分步骤  

1.生成邻接表和入度数组  

2.使用拓扑排序维护一个双端队列  

3.如果完成学习的课程数等于总课程数 返回结果否则返回空数组

```java
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
```