/*
 * @lc app=leetcode.cn id=784 lang=java
 *
 * [784] 字母大小写全排列
 *
 * https://leetcode.cn/problems/letter-case-permutation/description/
 *
 * algorithms
 * Medium (72.70%)
 * Likes:    571
 * Dislikes: 0
 * Total Accepted:    112.6K
 * Total Submissions: 154.9K
 * Testcase Example:  '"a1b2"'
 *
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 * 
 * 
 */

// @lc code=start
class Solution {
    
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        char[]charArray = s.toCharArray();
        backTrack(charArray,0,result);
        return result;
        
    }

    public void backTrack(char[]charArray, Integer index,List<String> result ){
        if(charArray.length == index){
            result.add(new String(charArray));
            return;
        }
        backTrack(charArray, index+1, result);
        if(Character.isLetter(charArray[index])){
            /* 大小寫轉換 
               注意：char[]charArray 是基本数据类型，所以是值传递，即传递的是副本
            ，所以数组中的某个元素进行大小写转换后，并不会影响原始数组的值，
             从而每一层其实是由系统调用栈保存的，那么就不用在写额外的常规的“状态重置”操作
            **/
            charArray[index] ^= 1<<5;
            backTrack(charArray, index+1,result);
        }

    }
}
// @lc code=end

