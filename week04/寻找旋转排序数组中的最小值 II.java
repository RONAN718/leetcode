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
