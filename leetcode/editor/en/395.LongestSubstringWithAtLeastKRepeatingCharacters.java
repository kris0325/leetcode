import java.util.HashMap;

/**
 * Given a string s and an integer k, return the length of the longest substring
 * of s such that the frequency of each character in this substring is greater than
 * or equal to k.
 * <p>
 * if no such substring exists, return 0.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and
 * 'b' is repeated 3 times.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 10⁴
 * s consists of only lowercase English letters.
 * 1 <= k <= 10⁵
 * <p>
 * <p>
 * Related Topics Hash Table String Divide and Conquer Sliding Window 👍 6230 👎 5
 * 16
 */
       
/*
 2024-08-07 12:30:43
 Longest Substring with At Least K Repeating Characters
Category	Difficulty	Likes	Dislikes
algorithms	Medium (44.97%)	6231
*/

class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //sliding window + hashmap
        //注意：無法直接使用sliding window實現，因為這樣不能確定當下right指針是否需要expand往前走，還是shrink，必須在固定窗口大小條件下，才能判斷right指針下一步是否需要往前走
        //固定滑动窗口中唯一字符的数量unique，从1-26（所有可能的字符），即設置滑動窗口map的 key size分別為1-26，簡化問題再分類討論
        // 并在每个窗口中检查是否满足题意：所有字符出现的次数至少为k,
        //然后遍历所有1-26的情况，更新求最大值
        //TC : O(26*N)
        public int longestSubstring(String s, int k) {
            char[] schars = s.toCharArray();
            int longestLength = 0;
            //unique表示当前固定滑动窗口的大小
            for (int unique = 1; unique <= 26; unique++) {
                //每轮固定的窗口，所有每次都需初始化当前变量
                HashMap<Character, Integer> map = new HashMap<>();
                //窗口map中的有效字符個數，即每個字符的frequency大於等於k的字符個數
                int validCount = 0;
                int left = 0;
                for (int i = 0; i < schars.length; i++) {
                    //step1. 進窗口：当前滑动窗口中字符出现次数计数
                    map.put(schars[i], map.getOrDefault(schars[i], 0) + 1);
                    // 更新validCount 窗口内满足条件的元素个数，
                    if (map.get(schars[i]) == k) {
                        validCount++;
                    }
                    //step2 出：窗口
                    //窗口大小超了固定size, shrink移除左边界元素，重新计算
                    while (map.keySet().size() > unique) {
                        char leftChar = schars[left];
                        //判断是否需要更新validCount
                        if (map.getOrDefault(leftChar,0) == k) {
                            validCount--;
                        }
                        map.put(leftChar, map.getOrDefault(leftChar,0) - 1);
                        if (map.get(leftChar) == 0){
                            map.remove(leftChar);
                        }
                        left++;
                    }
                    //step3.計算：满足条件, 即currentUnique和限定的固定窗口大小unique相等时， 且所有字符出现的次数至少为k,
                    int currentUnique = map.keySet().size();
                    if ( currentUnique == unique && currentUnique == validCount) {
                        //更新longestLength
                        longestLength = Math.max(longestLength, i - left + 1);
                    }
                }
            }
            return longestLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}