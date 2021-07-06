class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans=0;
        int l=0;
        int[] s=new int[nums.length+1];
        s[0]=0;
        for(int i=1;i<s.length;i++){
            s[i]=s[i-1]+nums[i-1];
        }

        for(int j=s.length-1;j>=0;j--){
            while(l<j){
                if(s[j]-s[l]==k) ans++;
                l++;
            }
            l=0;
        }
        return ans;
    }
}