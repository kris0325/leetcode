  /**
You are climbing a staircase. It takes n steps to reach the top. 

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you 
climb to the top? 

 
 Example 1: 

 
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
 

 Example 2: 

 
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

 
 Constraints: 

 
 1 <= n <= 45 
 

 Related Topics Math Dynamic Programming Memoization 👍 21911 👎 841

*/
       
/*
 2024-07-15 17:58:28
*/

class ClimbingStairs {
      public static void main(String[] args) {
           Solution solution = new ClimbingStairs().new Solution();
      }
        
      //leetcode submit region begin(Prohibit modification and deletion)
      class Solution {
          public int climbStairs(int n) {
              if(n<=2){
                  return n;
              }
              int []dp = new int[n +1];
              dp[1]= 1;
              dp[2]= 2;
              //注意下标index开始于i=3
              for(int i =3; i<=n; i++){
                  dp[i] = dp[i-2] +dp[i-1];
                  // System.out.println("dp[i]"+ "i:"+ i+":"+dp[i]);
              }
              return dp[n];
          }
      }
//leetcode submit region end(Prohibit modification and deletion)

}