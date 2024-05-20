/*
 * @lc app=leetcode id=242 lang=java
 *
 * [242] Valid Anagram
 *
 * https://leetcode.com/problems/valid-anagram/description/
 *
 * algorithms
 * Easy (64.48%)
 * Likes:    11937
 * Dislikes: 394
 * Total Accepted:    3.5M
 * Total Submissions: 5.4M
 * Testcase Example:  '"anagram"\n"nagaram"'
 *
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * 
 * 
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 * 
 * 
 * 
 * Follow up: What if the inputs contain Unicode characters? How would you
 * adapt your solution to such a case?
 * 
 * 思路：哈希表
 *      异位词，即两个单词/短语s1, s2互为重排，其充要条件是
 *     1字符串的字符总数量，
 *     2以及每个字符对应的数量相同。
 *     因此可以使用哈希表分别统计字符串中各个字符数量 key:字符，value:数量，
 *     统计s1时，value+1， 统计s2时value-1，
 *    当最终哈希表中所有字符对应的统计数值为0，则互为重排。
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public boolean isAnagram(String s, String t) {

        HashMap<Character, Integer> dic = new HashMap<>();
        for(int i = 0; i <s.length(); i++){
            dic.put(s.charAt(i), dic.getOrDefault(s.charAt(i), 0)+1);  
        }
        for(int i = 0; i < t.length(); i++){
            dic.put(t.charAt(i), dic.getOrDefault(t.charAt(i), 0)-1);
        }
        for(int val : dic.values()){
            if(val !=0){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

