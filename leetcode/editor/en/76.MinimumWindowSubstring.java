import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum
 * window substring of s such that every character in t (including duplicates) is
 * included in the window. If there is no such substring, return the empty string "".
 * <p>
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C'
 * from string t.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10⁵
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * <p>
 * Related Topics Hash Table String Sliding Window 👍 17941 👎 739
 */
       
/*
 2024-08-08 16:43:48
 Minimum Window Substring
Category	Difficulty	Likes	Dislikes
algorithms	Hard (42.97%)	17941	739
Tags
hash-table | two-pointers | string | sliding-window

Companies
facebook | linkedin | snapchat | uber
*/

class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //sliding window + hashmap
        public String minWindow(String s, String t) {
            HashMap<Character, Integer> tmap = new HashMap<>();
            HashMap<Character, Integer> smap = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
            }
            int left = 0;
            String minwindow = "";
            int count = 0;
            int minwindowLength = Integer.MAX_VALUE;
            for (int right = 0; right < s.length(); right++) {
                //1.進：添加右元素到窗口
                char rightChar = s.charAt(right);
                smap.put(rightChar, smap.getOrDefault(rightChar, 0) + 1);
                //更新smap中滿足tmap字符出現次數的字符個數
                if (tmap.containsKey(rightChar) && tmap.get(rightChar).intValue() == smap.get(rightChar).intValue()) {
                    count++;
                }

                //滿足條件時
                while (count == tmap.size()) {
                    char leftChar = s.charAt(left);
                    //3.計算: 更新最小窗口
                    if (minwindowLength > right - left + 1) {
                        minwindowLength = right - left + 1;
                        minwindow = s.substring(left, right + 1);
                    }
                    //2.出：移動左邊界元素
                    // shrink 縮小窗口移動左邊界
                    smap.put(leftChar, smap.get(leftChar) - 1);
                    left++;
                    //更新count
                    if (tmap.containsKey(leftChar) && smap.get(leftChar).intValue() < tmap.get(leftChar).intValue()) {
                        count--;
                    }

                }
            }
            return minwindow;

        }
    }

    //TC: O(n)
    public class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
            int left = 0, minStart = 0, minLen = Integer.MAX_VALUE;
            //但前窗口的字符串的有效長度
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                //step1.進：加入字符
                if (map.containsKey(c)) {
                    //valid count,  表示”當前窗口有效長度“，即s中選擇加入窗口的字符 對構成t字符串是否有效，
                    // 我們希望它趨向接近t.length()，滿足條件時就是count == t.length()
                    // 比如,中t "A"只有一個，s有3個"A"， 那麼第一個加入的“"A"”算有效的所以有效長度count++, 第二，三個，多餘的，所以是無效
                   //當前加入的字符是有效時，count++;
                    if (map.get(c) > 0) count++;
                    //加入字符，map可以理解為：構成target字符對應的remain字符，所以map.get(c)-1
                    //注意：因為s中同一個字符的個數可能超過t中的字符，所以c字符對應的次數可能會減為負數，
                    // 下面 step2.出：移除字符時需要+1，加回來
                    map.put(c, map.get(c) - 1);
                }
                //滿足條件，即包含了所有k的字符，即有效長度等於t.length()
                while (count == t.length()) {
                    //step3.計算： 發現較小值，只需紀錄 minStart， minLen，最後substring即可
                    if (i - left + 1 < minLen) { //发现更短的，长度可以更新
                        minLen = i - left + 1;
                        minStart = left;
                    }
                    //step2.出 : 移除字符 shrink
                    char leftChar = s.charAt(left);
                    if (map.containsKey(leftChar)) {
                        //step1中減掉的加回來，
                        map.put(leftChar, map.get(leftChar) + 1);
                        //當變為正數1時，表示正在移除了一個有效字符，所以當前窗口有效長度count--
                        if (map.get(leftChar) > 0) count--;
                    }
                    left++;
                }
            }
            if (minLen == Integer.MAX_VALUE) return "";
            return s.substring(minStart, minStart + minLen);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}