import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's
 * anagrams in s. You may return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length, p.length <= 3 * 10⁴
 * s and p consist of lowercase English letters.
 * <p>
 * <p>
 * Related Topics Hash Table String Sliding Window 👍 12341 👎 341
 */
       
/*
 2024-08-06 17:34:10
 Find All Anagrams in a String
Category	Difficulty	Likes	Dislikes
algorithms	Medium (50.88%)	12341	341
Tags
hash-table

Companies
amazon
*/

class FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // sliding window +  hashtable array
        //维护一个数组，表示窗口中字符出现次数
        //用长度为p.length的滑动窗口遍历字符s， 比较滑动窗口与p 二者对应的每个字符数是否相等
        //相等，收集index,向右平移滑动窗口
        //不想等，收缩左边界，移除最左元素，右边界向右移动一步
        public List<Integer> findAnagrams(String s, String p) {
            if (s.length() < p.length()) return new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            //
            int[] sCunrrentWindowMap = new int[26];
            int[] pmap = new int[26];
            char[] schar = s.toCharArray();
            char[] pchar = p.toCharArray();
            //初始化长度为p.length()的滑动窗口
            for (int i = 0; i < p.length(); i++) {
                sCunrrentWindowMap[schar[i] - 'a']++;
                pmap[pchar[i] - 'a']++;
            }
            //比较初始化后的2个字符串是异位词,即index =0时
            if (Arrays.equals(sCunrrentWindowMap, pmap)) {
                result.add(0);
            }

            //移动滑动窗口遍历s
            for (int i = 0; i < s.length() - p.length(); i++) {
                //滑动窗口每次向右移动一步，进行比较
                //移除左边界第一个元素，更新sCunrrentWindowMap
                sCunrrentWindowMap[s.charAt(i) - 'a']--;
                //在右边界新增一个元素，更新sCunrrentWindowMap
                sCunrrentWindowMap[s.charAt(i + p.length()) - 'a']++;
                if (Arrays.equals(sCunrrentWindowMap, pmap)) {
                    //滑动窗口每次移动一步，所以index = i+1
                    result.add(i + 1);
                }
            }
            return result;
        }
    }

    class Solution2 {
        // hashmap
        //Time Limit Exceeded
        public List<Integer> findAnagrams(String s, String p) {
            if (s.length() < p.length()) return new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            HashMap<Character, Integer> pmap = new HashMap<>();
            for (char c : p.toCharArray()) {
                pmap.put(c, pmap.getOrDefault(c, 0) + 1);
            }
            for (int start = 0; start <= s.length() - p.length(); start++) {
                if (isAnagram(s.substring(start, start + p.length()), pmap)) {
                    result.add(start);
                }
            }
            return result;
        }

        public boolean isAnagram(String s, HashMap<Character, Integer> pmap) {
            HashMap<Character, Integer> smap = new HashMap<>();
            for (char c : s.toCharArray()) {
                smap.put(c, smap.getOrDefault(c, 0) + 1);
            }
            return pmap.equals(smap);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}