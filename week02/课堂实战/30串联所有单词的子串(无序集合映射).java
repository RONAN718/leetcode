class Solution {
    /*
    思路
    将提供的words映射进一个hashmap里面存储每个单词的出现次数
    计算偏移量
    然后从头开始遍历 拆分字符串为多个单词 然后维护一个hashmap
    和之前统计的hashmap比较 如果相同就塞进答案里
    */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res=new ArrayList();
        HashMap<String,Integer> wordsCount=new HashMap();
        //计算出总长度也就是后面的偏移量
        int totallen=words.length*words[0].length();
        //遍历字符串数组维护hashmap
        for(String word:words){
            if(wordsCount.containsKey(word)){
                wordsCount.put(word,wordsCount.get(word)+1);
            }else{
                wordsCount.put(word,1);
            }
        }

        //遍历字符串 注意这里是<= 因为substring取头不取尾所以可以相等
        for(int i=0;i+totallen<=s.length();i++){
            int index=i;
            HashMap<String,Integer> wordsNum=new HashMap();
            while(index<i+totallen){
                if(wordsNum.containsKey(s.substring(index,index+words[0].length()))){
                    wordsNum.put(s.substring(index,index+words[0].length()), wordsNum.get(s.substring(index,index+words[0].length()))+1);
                }else{
                    wordsNum.put(s.substring(index,index+words[0].length()),1);
                }
                //向后偏移一个单词的长度
                index+=words[0].length();
            }
            //相等就说明包含相同的单词元素就是一个答案了
            if(wordsNum.equals(wordsCount)){
                    res.add(i);
            }
        }
        return res;
    }
}