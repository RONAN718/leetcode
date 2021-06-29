class Solution {
      /*
    思路
    连续的空间求元素和 想到前缀和 但是矩阵是二维的
    降维 去掉一维 卡死一维 动另一维
    */
    public int numSubmatrixSumTarget(int[][] mat, int target) {
        int n = mat.length, m = mat[0].length;
        //列的前缀和
        int[][] s = new int[n+1][m];
        for(int j = 0; j < m; j++){
            for(int i = 1; i <= n; i++){
                s[i][j] = s[i-1][j] + mat[i-1][j];
            }
        }

        int res = 0;
        //控制上边界的移动
        for(int i = 1; i <= n; i++){
            //控制下边界的移动
            for(int j = i; j <= n; j++){
                //使用hashmap来记忆面积，因为是降维问题所以该hashmap非全局变量 只记录降为一维时的各个矩阵的面积和
                Map<Integer,Integer> map = new HashMap<>();
                //添加一个辅助键值对用于计算面积差
                map.put(0, 1);
                int sum = 0;
                for(int k = 0; k < m; k++){
                    //卡主了上下边界降维 成为一维问题的前缀和问题
                    sum += s[j][k] - s[i-1][k];
                    res += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return res;
    }
}