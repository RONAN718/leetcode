class Solution {
    /*
    思路
    维护一个哈希表用来存储障碍物的坐标位置 用来替换数组的遍历查询
    使用单位向量的概念模拟机器人的单步移动
    每次移动都要和当前最大距离比较

    涉及到了两种存储障碍结点的方法
    1.使用映射 本题x，y取值是[-3 * 10e4,3 * 10e4]
    所以映射为(x+30000)*60000+y+30000
    公式就是x，y取值是[-o,o]
    (x+o)*2*o+y+o

    2.将x，y拼成字符串类似"x,y"进行存储 但是运行速度低于第一种
    */

    public int robotSim(int[] commands, int[][] obstacles) {
        //模拟了北东南西四个方向的单位向量
        //          北 东 南 西
        int[] depx={0,1,0,-1};
        int[] depy={1,0,-1,0};
        //记录机器人的当前位置
        int x=0;
        int y=0;
        //记录最远距离
        int res=0;
        HashSet<Integer> s=new HashSet();
        int face=0;
        for(int[] obstacle:obstacles){
            //使用映射来做哈希处理
            s.add((obstacle[0]+30000)*60000+obstacle[1]+30000);
        }

        for(int c:commands){
            if(c==-2){
                //向左转90度，需要注意face=0的时候 所以补4
                face=(face-1+4)%4;
            }
            if(c==-1){
                //向右转90度
                face=(face+1)%4;
            }
            while(c>0){
                 //先判断下一步是否遇到障碍物再前进，遇到障碍物本次指令结束
                if(s.contains((x+depx[face]+30000)*60000+y+depy[face]+30000))
                break;
                x+=depx[face];
                y+=depy[face];
                c--;
            }
            res=Math.max(res,x*x+y*y);
        }
        return res;

    }
}
