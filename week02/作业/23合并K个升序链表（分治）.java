class Solution {   
    /*
    思路
    分治思想 二分法处理后合并 每次都对半拆分为两个数组
    */
    public ListNode mergeKLists(ListNode[] lists) {
      return merge(lists,0,lists.length-1);
    }

     public ListNode merge(ListNode[] lists, int left, int right) {
        //左右相等时 说明只有一个元素返回该元素即可
        if (left == right) {
            return lists[left];
        }
        //左大于右 不合法了返回空
        if (left > right) {
            return null;
        }
        //计算中位数进行二分
        int mid = (left + right)/2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    //合并两个链表的函数
     public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null&&l2!=null) return l2; 
        if(l1!=null&&l2==null) return l1; 
        ListNode protect=new ListNode(0,null);
        ListNode last=protect;
        ListNode head=null;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                head=l1;
                l1=l1.next;
                last.next=head;
                last=head;
            }else{
                head=l2;
                l2=l2.next;
                last.next=head;
                last=head;
            }
        }
        if(l1!=null){
                head.next=l1;
        }
        if(l2!=null){
                head.next=l2;
        }
        return protect.next;
    }
}
