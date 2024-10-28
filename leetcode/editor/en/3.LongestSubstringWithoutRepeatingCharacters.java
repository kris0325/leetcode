import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a
 * substring.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= s.length <= 5 * 10⁴
 * s consists of English letters, digits, symbols and spaces.
 * <p>
 * <p>
 * Related Topics Hash Table String Sliding Window 👍 39856 👎 1908
 */
       
/*
 2024-08-06 00:00:28
 Longest Substring Without Repeating Characters
 無重複字符的最長子串
Category	Difficulty	Likes	Dislikes
algorithms	Medium (34.74%)	39225	1850
Tags
hash-table | two-pointers | string | sliding-window

Companies
adobe | amazon | bloomberg | yelp

Given a string s, find the length of the longest substring without repeating characters.


*/

class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //3.LongestSubstringWithoutRepeatingCharacters
        //3.無重複字符的最長子串
        //sliding window + hashset
        public int lengthOfLongestSubstring(String s) {
            char[] chars = s.toCharArray();
            HashSet<Character> subStringSet = new HashSet<>(chars.length);
            int left = 0;
            int longestLength = 0;
            for (int right = 0; right < chars.length; right++) {
                //case1.repeating characters
                while (subStringSet.contains(chars[right])) {
                    subStringSet.remove(chars[left]);
                    left++;
                }
                //case2. no repeating characters
                subStringSet.add(chars[right]);
                longestLength = Math.max(longestLength, right - left + 1);

            }
            return longestLength;
        }
    }

    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> dic = new HashMap<>();
            int i = -1, res = 0, len = s.length();
            for (int j = 0; j < len; j++) {
                if (dic.containsKey(s.charAt(j)))
                    i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
                dic.put(s.charAt(j), j); // 哈希表记录
                res = Math.max(res, j - i); // 更新结果
            }
            return res;
        }
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> dic = new HashMap<>();
            int left = 0, res = 0, len = s.length();
            for (int j = 0; j < len; j++) {
                if (dic.containsKey(s.charAt(j))) {
                    // 更新左指针 left
                    left = Math.max(left, dic.get(s.charAt(j)));
                }
                //sets.charAt(j)的是当前字符的下一个字符的位置，+1表示跳过当前重复字符，
                // 所以在更新左指针时，left = Math.max(left, dic.get(s.charAt(j)));时，left跳过了第一个重复字符，指向的下一个位置，所以计算长度时（无重复字符的个数）需要+1，即right -left +1
                dic.put(s.charAt(j), j + 1);
                res = Math.max(res, j - left + 1); // 更新结果
            }
            return res;
        }
    }


    class Solution159 {
        //159. Longest Substring with At Most Two Distinct Characters
        // 159. 最多包含2個不同字符的最長子串
        //sliding window +hashmap
        public int lengthOfLongestSubstringWithTwoDistinctCharacters(String s) {
            //用char2map存储对应字符的在当前窗口中的出现次数
            HashMap<Character, Integer> char2map = new HashMap<>();
            int longestLength = 0;
            int left = 0;
            char[] chars = s.toCharArray();
            for (int right = 0; right < chars.length; right++) {
                //更新char2map,统计key,即更新DistinctCharacters的值
                char2map.put(chars[right], char2map.getOrDefault(chars[right], 0) + 1);
                //case1.DistinctCharacters 不满足条件时，每次从左边移除字符，更新 chars[left]左边界对应chars[left]-1，滑动窗口chars[left]左边界右移动，
                while (char2map.size() > 2) {
                    char charToRemove = chars[left];
                    char2map.put(charToRemove, char2map.get(charToRemove) - 1);
                    left++;
                    //char2map.get(chars[left]) ==0时
                    if (char2map.get(charToRemove) == 0) {
                        char2map.remove(chars[left]);
                    }
                }
                //case2.满足条件时，更新longestLength，准备下一次迭代 right++，扩大滑动窗口
                longestLength = Math.max(longestLength, right - left);

            }
            return longestLength;


        }

    }


    public class Solution340 {
        /**
         * 340.Longest Substring with At Most K Distinct Characters
         * 340.最多包含k個不同字符的最長字串
         * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
         * <p>
         * Example 1:
         * <p>
         * <p>
         * Copy
         * Input: s = "eceba", k = 2
         * Output: 3
         * Explanation: The substring is "ece" with length 3.
         * Example 2:
         * <p>
         * <p>
         * Copy
         * Input: s = "aa", k = 1
         * Output: 2
         * Explanation: The entire string is a substring with k distinct characters.
         * Constraints:
         * <p>
         * 1 <= s.length <= 5 * 10^4
         * 0 <= k <= 50
         */

        //sliding window + hashmap
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            HashMap<Character, Integer> char2map = new HashMap<>();
            int longestLength = 0;
            int left = 0;
            for (int right = 0; right < s.length(); right++) {
                //1.進入：满足条件，expanding，加入右边界元素
                char2map.put(s.charAt(right), char2map.getOrDefault(s.charAt(right), 0) + 1);
                //2.出來：不滿足添加 shrink，移除左邊界元素
                while (char2map.keySet().size() > k) {
                    char2map.put(s.charAt(left), char2map.get(s.charAt(left)) - 1);
                    if (char2map.get(s.charAt(left)) == 0) {
                        char2map.remove(s.charAt(left));
                    }
                    left++;
                }
                //3.計算：更新 longestLength
                longestLength = Math.max(longestLength, right - left + 1);
            }
            return longestLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}