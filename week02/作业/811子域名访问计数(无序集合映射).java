class Solution {
       /*
    思路
    维护一个哈希表 key存域名 value存次数
    使用stringbuffer来拼接字符串
    */
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String,Integer> map=new HashMap();
        List<String> res=new ArrayList();
        for(String cpdomain:cpdomains){
            //先按空格进行分割
           String[] newS=cpdomain.split(" ");
            //再按.进行分割
           String[] secondS=newS[1].split("\\.");
           for(int i=0;i<secondS.length;i++){
            //使用stringbuffer拼接字符串
               StringBuffer s=new StringBuffer();
               int j=i;
               while(j<secondS.length){
                   s.append(secondS[j++]);
                   //未到末尾就需要补.
                   if(j!=secondS.length)
                   s.append(".");
               }
                //更新到哈希表内 如果存在就加值 不存在就插入新的键值对
               map.put(s.toString(),map.containsKey(s.toString())?map.get(s.toString ())+Integer.valueOf(newS[0]):Integer.valueOf(newS[0]));
            }
           }
     for(Map.Entry e:map.entrySet()){
         res.add(e.getValue()+" "+e.getKey());
     }
     return res;
    }
}