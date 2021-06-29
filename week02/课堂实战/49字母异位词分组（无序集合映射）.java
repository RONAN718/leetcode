class Solution {
    /*
    思路
    维护一个哈希表 key用来存储排好序的字符串 value用来存储包含相同字母的字符串的集合
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> h=new HashMap<>();
        for(String s:strs){
            //将字符串变为字符数组
            char[] c=s.toCharArray();
            //对字符数组进行排序
            Arrays.sort(c);
            //生成新的字符串
            String k=new String(c);
            //将字符串塞进一个list里
            List<String> value=new ArrayList();
            value.add(s);
            //如果存在只需向内添加新的元素即可
            if(h.containsKey(k)){
               h.get(k).add(s);
            }else{
            //如果不存在需要添加一对新的键值对
            h.put(k,value);
            }
        }
        //返回所有的vlaues生成的ArraryList
    return new ArrayList<List<String>>(h.values());
}
}