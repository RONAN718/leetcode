/*
106. 从中序与后序遍历序列构造二叉树

思路
后序可以确定根节点  左右根
中序可以用来区分左右子树的范围 左根右

先通过后序找到根节点 然后在中序中区分左右子树再分别做递归构成树
*/

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