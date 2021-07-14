
295.数据流的中位数 

思路求中位数可以把一个数组拆分成两个堆来看  

1 2 3 4 5 6 7 8 可以拆成   

1 2 3 4 最大堆==>堆顶为左半边的最大值  

5 6 7 8 最小堆==>堆顶为右半边的最小值  

元素个数为偶数时，两个堆的堆顶就是两个中位数  

元素个数为奇数时，前一个堆的堆顶就是中位数  


```java
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
```


154. 寻找旋转排序数组中的最小值 II
思路  

二分法求解 注意重复值需要分三种情况考虑 等于的时候只需right向前移动
```java
class Solution {
/**
     * 思路求二分法求解 注意重复值需要分三种情况考虑 等于的时候只需right向前移动必然包含最小值
     */
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1,mid;
        //合法条件 左小于右
        while(left<right){
            //求中位数
            mid=(left+right)>>1;
             //小于说明 答案包含在mid和mid之前
            if(nums[mid]<nums[right]){
                right=mid;
            //大于说明 答案包含在mid之后
            }else if(nums[mid]>nums[right]){
                left=mid+1;
            }
            //等于的时候right点的值就无用了需要更新前移找一个更小的目标值
           else
            right--;
        }
        return nums[left];
    }
}

```