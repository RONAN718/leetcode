public class MedianFinder {

    /**
     * 思路求中位数可以把一个数组拆分成两个堆来看
     * 1 2 3 4 5 6 7 8 可以拆成 
     * 1 2 3 4 最大堆==>堆顶为左半边的最大值
     * 5 6 7 8 最小堆==>堆顶为右半边的最小值
     * 元素个数为偶数时，两个堆的堆顶就是两个中位数
     * 元素个数为奇数时，前一个堆的堆顶就是中位数
     */
    private int count;
    private PriorityQueue<Integer> leftheap;
    private PriorityQueue<Integer> rightheap;

    public MedianFinder() {
        count = 0;
        //java默认使用优先队列实现最小堆 所以最大堆需要修改比较函数
        leftheap = new PriorityQueue<>((x, y) -> y - x);
        rightheap = new PriorityQueue<>();
    }
     //一开始不能确定加入的值落在哪里 所以先加左边 再弹出左边的最大值加入右边 
     //如果此时是奇数为了保证中位数出现在左边的堆顶 将右边的最小值弹回给左边
    public void addNum(int num) {
        count += 1;
        leftheap.offer(num);
        rightheap.add(leftheap.poll());
        if ((count & 1) != 0) {
            leftheap.add(rightheap.poll());
        }
    }
    //总个数为奇数的情况下直接弹出左边的堆顶，为偶数求平均
    public double findMedian() {
        if ((count & 1) == 0) {
            return (double) (leftheap.peek() + rightheap.peek()) / 2;
        } else {
            return (double) leftheap.peek();
        }
    }
}