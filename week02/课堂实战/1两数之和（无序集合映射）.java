class Solution {
    /*
    思路
    维护一个哈希表用来存储每个数组元素的值和索引值
    先查后插避免查到自身元素
    因为有且只有一个解查到就返回答案
    */
    public int[] twoSum(int[] nums, int target) {
        int []res=new int[2];
        HashMap<Integer,Integer> h=new HashMap();
        //遍历数组，先判断是否包含target-nums[i]再插入，避免查到自身的情况
        for(int i=0;i<nums.length;i++){
            if(h.containsKey(target-nums[i]))
            {
                //返回查到的唯一解
                res[0]=h.get(target-nums[i]);
                res[1]=i;
                return res;
            }
            h.put(nums[i],i); 
        }
        return res;
    }
}