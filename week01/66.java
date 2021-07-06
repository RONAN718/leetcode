class Solution {
    public int[] plusOne(int[] digits) {
       boolean flag=false;
       if(digits[digits.length-1]==9)
       flag=true;
       digits[digits.length-1]=(digits[digits.length-1]+1)%10;
       for(int i=digits.length-2;i>=0;i--){
           if(flag==true){
               digits[i]=(digits[i]+1)%10;
               if(digits[i]!=0)
               return digits;
           }
       }
       if(flag==true)
       {digits=new int[digits.length+1];
       digits[0]=1;
       }
       return digits;
    }
}