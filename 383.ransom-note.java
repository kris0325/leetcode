/*
 * @lc app=leetcode id=383 lang=java
 *
 * [383] Ransom Note
 *
 * https://leetcode.com/problems/ransom-note/description/
 *
 * algorithms
 * Easy (61.30%)
 * Likes:    4905
 * Dislikes: 496
 * Total Accepted:    1.1M
 * Total Submissions: 1.8M
 * Testcase Example:  '"a"\n"b"'
 *
 * Given two strings ransomNote and magazine, return true if ransomNote can be
 * constructed by using the letters from magazine and false otherwise.
 * 
 * Each letter in magazine can only be used once in ransomNote.
 * 
 * 
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 * 
 * 
 */

 /*
  * 思路：哈希表
   Solution1：
         分别使用hashMap收集ransomNote, magazine的letter数量， key-> letter, value->nums,
         再遍歷hashmap，當Each letter in magazine對應的value必須大於等於ransomNote對應的hashmap，
          return true, 否則false

  Solution2：
    本题与 242. Valid Anagram 242.有效的字母异位词很像, 可以使用哈希数组  
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> ransomNoteMap = new HashMap<>();
        HashMap<Character, Integer> magazineMap = new HashMap<>();
        for(int i = 0; i< ransomNote.length(); i++){
            Character letter = ransomNote.charAt(i);
            ransomNoteMap.put(letter, ransomNoteMap.getOrDefault(letter,0)+1);
        }
        for(int i = 0; i< magazine.length(); i++ ){
            Character letter = magazine.charAt(i);
            magazineMap.put(letter, magazineMap.getOrDefault(letter,0)+1);
        }
        for(Map.Entry<Character, Integer> entry : ransomNoteMap.entrySet()){
            if(!magazineMap.containsKey(entry.getKey())
             || (magazineMap.containsKey(entry.getKey()) &&  magazineMap.get(entry.getKey()) < entry.getValue())){
                return false;
            }
        }
        return true;
    }
}

class Solution2 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // shortcut
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for(char c : magazine.toCharArray()){
            record[c - 'a'] += 1;
        }

        for(char c : ransomNote.toCharArray()){
            record[c - 'a'] -= 1;
        }
        
        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }

        return true;
    }
}

/*
*
Solution1的时间复杂度为O(m + n)，其中m为ransomNote字符串的长度，n为magazine字符串的长度。在该解决方案中，首先需要遍历ransomNote和magazine字符串各一次，然后需要遍历ransomNoteMap中的键值对，因此总的时间复杂度为O(m + n)。

Solution2的时间复杂度也为O(m + n)，其中m为ransomNote字符串的长度，n为magazine字符串的长度。在该解决方案中，首先需要遍历magazine字符串和ransomNote字符串各一次，然后遍历record数组，因此总的时间复杂度为O(m + n)。

对于空间复杂度，Solution1使用了两个HashMap来存储ransomNote和magazine字符串中字符出现的次数，因此空间复杂度为O(m + n)，其中m和n分别为ransomNote和magazine字符串的长度。

而Solution2使用了一个长度为26的数组record来记录magazine字符串中各个字符出现的次数，因此空间复杂度为O(1)，与字符串的长度无关。

在面试中，Solution2更好一些。因为它不需要额外的HashMap数据结构，只需要一个固定大小的数组即可完成任务，节省了空间，并且时间复杂度也相同。此外，Solution2的实现更加简洁明了，易于理解和维护。
*
*
*/

// @lc code=end

