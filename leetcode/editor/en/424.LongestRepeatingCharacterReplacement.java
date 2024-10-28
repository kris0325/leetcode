/**
 * You are given a string s and an integer k. You can choose any character of the
 * string and change it to any other uppercase English character. You can perform
 * this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can
 * get after performing the above operations.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 10⁵
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 * <p>
 * <p>
 * Related Topics Hash Table String Sliding Window 👍 10713 👎 540
 */
       
/*
 2024-08-06 00:34:04
 Longest Repeating Character Replacement
Category	Difficulty	Likes	Dislikes
algorithms	Medium (53.80%)	10716	540
Tags
two-pointers | sliding-window

Companies
pocketgems
*/

class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
//        Solution solution = new LongestRepeatingCharacterReplacement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //sliding window + hashtable
        //這裡是變種的sliding window （https://leetcode.cn/problems/longest-repeating-character-replacement/solutions/78520/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/）
        //先考慮問題退化為，當k=0，即同3.LongestSubstringWithoutRepeatingCharacters 3.無重複字符的最長子串，滿足條件窗口擴張，否則窗口左邊界收縮，即窗口從左到右擴張/滑動，紀錄最大值，直到結尾，运算结束，窗口的宽度为最终结果。
        //而當k>0時，子串的條件變為，允許變化子串的k個字符變成一個連續子串
        //那麼問題的關鍵點就是如何判斷一個字符串改變k個字符，能夠變為一個連續字串
        //如果當前字串（即窗口內）中出現次數最多的字母個數+k大於等於字串長度，就是滿足條件的
        //因為題目是求最大值，那麼我們只需在滑動窗口的過程中，更新出現次數最多的字母個數的 歷史 最大值+k，進行比較運算，直到結尾，运算结束，窗口的宽度为最终结果，即為所求的最大窗口，即最大字串

        //维护历史最大值historyCharMax的原因：窗口从根本来说是只增不减的：
        //
        //如果k够用来替换的话，right右移1步，窗口变大（当前维护的最大值变大，不消耗k；当前维护的最大值不变，将消耗k）；
        //
        //如果k不够用来替换的话，left和right都右移一步，窗口不变（我们已经不关心比当前小的情况了）。
        //
        //从这个角度来说，也不难理解最后答案是right-left（最后窗口的大小，可扩充的最大窗口）。


        // //（注意：因為我們只是紀錄來最大窗口長度，所有最後窗口內的元素不一定是所求的字串元素）
        //維護一個數組int[26]來存儲當前窗口中的各個字母出現的次數，left表示窗口左邊界，right表示右邊界
        //滿足條件時，窗口擴張：left不變，right++
        //不滿條件時，窗口滑動：left++, right++
        //historyCharMax ：表示滑動窗口中相同字母出現次數的歷史最大值（注意是“滑動窗口中”，且是“歷史”，不是求整個數組中，因為要求連續子串的最大長度），
        //通過 if(right-left+1 > historyCharMax +k) 來決定窗口是需要滑動，否則窗口就擴張
        public int characterReplacement(String s, int k) {
            char[] chars = s.toCharArray();
            //A-Z 對應0-25
            int[] charMap = new int[26];
            int left = 0;
            int historyCharMax = 0;
            for (int right = 0; right < chars.length; right++) {
                //chars[right] - 'A'表示 對應字符的index，即 A-Z 對應0-25
                int charIndex = chars[right] - 'A';
                //相同字符累加次數
                charMap[charIndex]++;
                //滑動窗口中相同字母出現次數的歷史最大值（注意是“滑動窗口中”，且是“歷史”）
                historyCharMax = Math.max(historyCharMax, charMap[charIndex]);
                //注意：这里左邊界只移動一步（因為我們不關心比historyCharMax + k小的窗口，所以只需左，右邊界同時移動，即向右平移一步，繼續尋找可能擴大的窗口）
                if (right - left + 1 > historyCharMax + k) {
                    //不滿足條件時，滑動窗口，即左邊界收縮，同時最左邊元素在統計字符次數charMap中對應的次數減一
                    charMap[chars[left] - 'A']--;
                    left++;
                }
            }
            //最終窗口的長度即為最大長度：right -left +1 （注意：最終窗口的長度為最大長度，但是最終的窗口中的元素不一定是所求字串，因為historyCharMax只紀錄來長度，沒有變量維護最大子串的元素，
            // 所有可以理解到達最大historyCharMax後，窗口長度不會收縮，只會維持historyCharMax的值）
            //right 會移動到結尾chars.length-1
            return chars.length - left;
        }
    }

    class Solution2 {
        public int characterReplacement(String s, int k) {
            int[] charCount = new int[26]; // 数组存储字符计数
            int maxCount = 0; // 【历史】窗口中任何字符的最大频率，基于最大频率的字符来寻找最长子串
            int maxLength = 0; // 最长有效子字符串的长度
            int start = 0; // 当前窗口的起始索引
            for (int end = 0; end < s.length(); end++) {
                // 更新end索引处字符的计数
                charCount[s.charAt(end) - 'A']++;
                // 用当前窗口中任何字符的最大频率更新历史窗口maxCount
                maxCount = Math.max(maxCount, charCount[s.charAt(end) - 'A']);
                // 从start一侧缩小窗口 ，注意Solution2与Solution1的区别，Solution2是while一直缩小
                //Solution1只左边界移动一步
                while (end - start + 1 - maxCount > k) {
                    // 递减start索引处字符的计数
                    charCount[s.charAt(start) - 'A']--;
                    start++;
                }
                // 用当前窗口有效子字符串的最大长度更新maxLength
                maxLength = Math.max(maxLength, end - start + 1);
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}