class LRUCache {
    /*
    思路
    使用双向链表+哈希表维护
    */
    private HashMap<Integer,Node> h;
    private int size;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        size=capacity;
        h=new HashMap();
        //使用首尾保护结点 简化边界问题
        head=new Node();
        tail=new Node();
        head.next=tail;
        tail.pre=head;
    }
    //如果没有就返回-1 有的话需要更新链表 就是删掉该节点再重新插入头部
    public int get(int key) {
        if(!h.containsKey(key)) return -1;
        Node n=h.get(key);
        removeFromList(n);
        addHead(n);
        return n.value;
    }
    //put 如果没有这个key且容量足够就直接插入头部 否则就移除尾部的前一个结点再插入头部
    //如果有这个key需要则更新至头部 注意这里可能存在value的变化所以不能用之前存的node而需要使用新的节点
    public void put(int key, int value) {
         Node n=new Node(key,value);
        if(!h.containsKey(key)){
            if(h.size()<this.size){
            addHead(n);
            }else{
                removeFromList(tail.pre);
                addHead(n);
            }
        }else{
            Node m=h.get(key);
            removeFromList(m);
            addHead(n);
            }
        }
    //移除节点只需更新该节点的前节点的next 和该节点的后节点的pre
    private void removeFromList(Node n){
        n.pre.next=n.next;
        n.next.pre=n.pre;
        h.remove(n.key,n);
    }
    //添加头部需要维护和head节点和head.next节点的关系 
    //先改后节点再改前节点
    public void addHead(Node n){
        n.next=head.next;
        head.next.pre=n;
        n.pre=head;
        head.next=n;
        h.put(n.key,n);
    }

    public class Node{
        private Node next;
        private Node pre;
        private int value;
        private int key;
        Node(){};
        Node(int _key,int _value){
            key=_key;
            value=_value;
        }
    }
}