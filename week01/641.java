class MyCircularDeque {

    public class Node{
        public Node pre;
        public Node next;
        public int val;

        Node(int _val){
            val=_val;
        }
    }

    public Node head=null;
    public Node tail=null;
    public int size=0;
    public int length=0;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        size=k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(length>=size)return false;
        if(head==null){
        head=new Node(value);
        tail=head;
        }else{
            Node n=new Node(value);
            n.next=head;
            head.pre=n;
            head=n;
        }
        length++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(length>=size)return false;
        if(tail==null){
        tail=new Node(value);
        head=tail;
        }else{
            Node n=new Node(value);
            n.pre=tail;
            tail.next=n;
            tail=n;
        }
        length++;
        return true;

    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(length==0) return false;
        if(length==1){
            head=null;
            tail=null;
        }else{
            head.next.pre=null;
            Node newHead=head.next;
            head.next=null;
            head=newHead;
        }
        length--;
        return true;

    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(length==0)return false;
        if(length==1){
            head=null;
            tail=null;
        }else{
            tail.pre.next=null;
            Node newPre=tail.pre;
            tail.pre=null;
            tail=newPre;
        }
        length--;
        return true;

    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if(length==0) return -1;
        return head.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if(length==0) return -1;
        return tail.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return length==0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return length==size;
    }
}