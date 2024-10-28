/**
 * Given a string s, return true if the s can be palindrome after deleting at most
 * one character from it.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "aba"
 * Output: true
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "abc"
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 10⁵
 * s consists of lowercase English letters.
 * <p>
 * <p>
 * Related Topics Two Pointers String Greedy 👍 8313 👎 454
 */
       
/*
 2024-10-08 17:24:34
*/

class ValidPalindromeIi {
    public static void main(String[] args) {
        Solution solution = new ValidPalindromeIi().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //two pointer
        //tc: o(n)
        public boolean validPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    // 最多删除1个字符，如何判斷 （刪除左字符 ｜｜ 刪除右字符）是否是回文字符
                    return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
                }
            }
            return true;
        }

        public boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                } else {
                    left++;
                    right--;
                }
            }
            return true;
        }
    }

    /**
     * 408.valid Word Abbreviation
     * */
    class Solution_408 {
        public boolean validWordAbbreviation(String word, String abbr) {
            int i = 0,j=0;
            while (i<word.length() && j<abbr.length()){
                if(word.charAt(i) == abbr.charAt(j)){
                    i++;
                    j++;
                    continue;
                }
                //2个字符不相等，且abbr.charAt(j)字符不是有效數字（不是1-9 ｜或者是0開頭）
                if(abbr.charAt(j)<='0' && abbr.charAt(j)>'9'){
                    return false;
                }
                //2个字符不相等, abbr.charAt(j)字符是數字
                int start = j;
                while (j<abbr.length() && abbr.charAt(j)>='0' && abbr.charAt(j)<='9' ){
                    j++;
                }
                //獲取中間的數字
                int num = Integer.valueOf(abbr.substring(start,j));
                i+=num;
            }
            return i == word.length() && j == abbr.length();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}