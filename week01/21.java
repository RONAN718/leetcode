class Solution {
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
