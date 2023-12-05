/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * DFS 深度优先搜素算法
*/

// class Solution {
//     public List<String> letterCombinations(String digits) {
//         // List<String> phoneNum2String =  new ArrayList<String>(Arrays.asList("","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"));
//         //使用hashMap 可应对异常边界条件，比如输入0,1或者非数字
//         Map<Character,String> phoneNum2String = new HashMap<>(){{
//             put('0', "");
//             put('1', "");
//             put('2', "abc");
//             put('3', "def");
//             put('4', "ghi");
//             put('5', "jkl");
//             put('6', "mno");
//             put('7', "pqrs");
//             put('8', "tuv");
//             put('9', "wxyz");
//         }};
//         List<String> combinations = new ArrayList<>();
//         int index = 0;
//         //存当前字符串
//         StringBuffer current = new StringBuffer();
//         //算法：DFS深度优先搜索 / 回溯 实现搜索，
//         findCombinationsBacktrack(phoneNum2String,combinations,0,digits,current);
//         return combinations;        
//     }

//     public void findCombinationsBacktrack(Map<Character,String> phoneNum2String, List<String> combinations ,int index, String digits,StringBuffer current){
//              if(index == digits.length()){
//                  if(index == 0){
//                      return;
//                  }
//                   combinations.add(current.toString());
//                   return;
//              }
//              String alphaString = phoneNum2String.get(digits.charAt(index));
//              for(int i = 0; i<alphaString.length();i++){
//                  current.append(alphaString.charAt(i));
//                  findCombinationsBacktrack(phoneNum2String,combinations,index+1,digits,current);
//                  current.deleteCharAt(index);
//              }
//         }
// }

/*
 * BFS 广度优先搜素算法
*/
class Solution {
    public List<String> letterCombinationsBSF(String digits) {
      if (digits.length() == 0) return new ArrayList<String>();
      
      String[] d = new String[]{" ", 
                                "", 
                                "abc", 
                                "def",
                                "ghi",
                                "jkl",
                                "mno",
                                "pqrs",
                                "tuv",
                                "wxyz"};       
      List<String> ans = new ArrayList<>();
      ans.add("");
      for (char digit : digits.toCharArray()) {
        List<String> tmp = new ArrayList<>();
        for (String t : ans) {
          String s = d[Character.getNumericValue(digit)];
          for (int i = 0; i < s.length(); ++i)
            tmp.add(t + s.charAt(i));
        }
        ans = tmp;
      }
      
      return ans;
    }
 
}
/*
 * debug
*/

class Main{
public static void main(String[] args) {
        // Create a new Solution instance
        Solution solution = new Solution();
        // Create a test case
        String testCase = "23";
        // Get the answer
        List<String>  result = solution.letterCombinationsBSF(testCase);
        // Print the answer
        System.out.println(result);
    }
}

// @lc code=end

