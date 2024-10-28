import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen
 * apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * <p>
 * <p>
 * Related Topics Array Hash Table String Dynamic Programming Trie Memoization 👍
 * 17186 👎 788
 */
       
/*
 2024-07-23 22:29:52
 Word Break
Category	Difficulty	Likes	Dislikes
algorithms	Medium (46.76%)	17186	788
Tags
dynamic-programming

Companies
amazon | bloomberg | facebook | google | pocketgems | uber | yahoo
*/

class WordBreak {
    public static void main(String[] args) {
        Solution solution = new WordBreak().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 背包問題
        //單詞就是物品，字符串就是背包，單詞能否組成字符串s，就是物品能否裝滿背包。單詞可重複利用，說明是完全背包問題
        //字符串有順序，所以是求排列，遍歷順序應該是先遍歷背包再遍歷單詞
        public boolean wordBreak(String s, List<String> wordDict) {
            //定義dp數組：字符串長度為i,dp[i]為true,表示可以拆分為一個或多個在字典中出現的單詞
            boolean[] dp = new boolean[s.length() + 1];
            //初始化：从递推公式中可以看出，dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递推的根基，dp[0]一定要为true，否则递推下去后面都都是false了。
            dp[0] = true;
            //遍歷順序
            //遍歷背包 字符串
            for (int i = 1; i <= s.length(); i++) {
                //遍歷物品 單詞
                for (String word : wordDict) {
                    //遞推公式：如果確定dp[j]為true，且[j,i]這個區間的字串出現在字典中，那麼可推導出dp[i]一定為true
                    if (word.length() <= i && dp[i - word.length()] && word.equals(s.substring(i - word.length(), i))) {
                        dp[i] = true;
                        //跳出內層循環，繼續遍歷外層循環，即遍歷背包
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}