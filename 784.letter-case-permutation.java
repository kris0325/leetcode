/*
 * @lc app=leetcode id=784 lang=java
 *
 * [784] Letter Case Permutation
 *
 * https://leetcode.com/problems/letter-case-permutation/description/
 *
 * algorithms
 * Medium (74.08%)
 * Likes:    4588
 * Dislikes: 154
 * Total Accepted:    294.9K
 * Total Submissions: 397.8K
 * Testcase Example:  '"a1b2"'
 *
 * Given a string s, you can transform every letter individually to be
 * lowercase or uppercase to create another string.
 * 
 * Return a list of all possible strings we could create. Return the output in
 * any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and
 * digits.
 * 
 * 
 * >思路分析：
这一类搜索问题是在一个隐式的树上进行的搜索问题，即「树形问题」。解决这一类问题， 先画出递归树是十分重要的，可以帮助打开思路 ，然后看着图形把代码写出来；
这个问题所求的解，是这棵树的叶子结点上的值。因此，可以使用深度优先遍历，收集 所有 叶子结点的值，深度优先遍历用于搜索也叫回溯算法；
回溯算法因为有回头的过程，因此其显著特征是 状态重置。回溯算法的入门问题是「力扣」第 46 题：全排列）。

由于集合元素字母只有大写，小写之分，那么结果树为二叉树，所以不需要用for循环去遍历集合元素

大小写转换：
我们发现大写字符与其对应的小写字符的 ASCII 的差为 32，32 这个值如果敏感的话，它是 2^5 
，在编程语言中，可以表示为 1 << 5。而
变换大小写这件事等价于：
如果字符是小写字符，减去 32 得到大写字符；
如果字符是大写字符，加上 32 得到小写字符。
而这两者合并起来，就是给这个字符做一次不进位的加法，即异或上 1 << 5。
 */


// @lc code=start

import java.util.ArrayList;

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

