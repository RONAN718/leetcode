class Solution {
    /*
    思路
    统计每个元素出现的次数 找出最大值并记录下来
    使用hashmap维护一个出现元素的位置的数组
    最后遍历数组找出最短的距离
    */
    public int findShortestSubArray(int[] nums) {
        //通过题意可知其长度最多为50001
        int[] res=new int[50001];
        HashMap<Integer,List<Integer>> map=new HashMap();
        int max=Integer.MIN_VALUE;
        int range=Integer.MAX_VALUE;

        for(int i=0;i<nums.length;i++){
            //统计出现的次数
            res[nums[i]]++;
            //找出最大的出现次数
            max=Math.max(res[nums[i]],max);
            List<Integer> l=new ArrayList();
            //记录每个元素的位置
            if(map.containsKey(nums[i])){
                l=map.get(nums[i]);
            }
            l.add(i);
            map.put(nums[i],l);
        }

        for(int i=0;i<=50000;i++){
            //寻找出现最多次的元素 比较他们的子数组的长度
            if(res[i]==max){
                range=Math.min(map.get(i).get(map.get(i).size()-1)-map.get(i).get(0),range);
            }
        }
        return range+1;
        
    }
}