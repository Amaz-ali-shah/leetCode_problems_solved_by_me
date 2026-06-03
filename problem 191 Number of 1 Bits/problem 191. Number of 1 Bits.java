class Solution {
    public int hammingWeight(int n) {
          LinkedList<Integer> list = new LinkedList<>();

          while (n>0 || n> 1){
            int  result=n%2;
            if (result ==1){
                list.push(result);
            }
            
            n=n/2;
          }
          return list.size();
        
    }
}