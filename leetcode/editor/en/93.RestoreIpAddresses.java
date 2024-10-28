import java.util.ArrayList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * <p>
 * <p>
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.2
 * 55.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * <p>
 * <p>
 * Given a string s containing only digits, return all possible valid IP
 * addresses that can be formed by inserting dots into s. You are not allowed to reorder or
 * remove any digits in s. You may return the valid IP addresses in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 20
 * s consists of digits only.
 * <p>
 * <p>
 * Related Topics String Backtracking 👍 5288 👎 795
 */
       
/*
 2024-10-15 12:00:46
*/

class RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            //backtrack to solve it
            // collect valid ip
            List<String> validIps = new ArrayList<>();
            //1. not valid string
            if (s.length() < 4 || s.length() > 12) {
                return validIps;
            }

            int segament = 0;
            String currentIPString = "";
            int startIndex = 0;
            backtrack(s, validIps, 0, currentIPString, segament);
            return validIps;
        }

        // backtrack
        public void backtrack(String s, List<String> validIps, int startIndex,
                              String currentIPString, int segament) {
            // return condition         // 如果 IP 地址的四个部分已经完整，且已使用的字符正好等于输入字符串的长度
            if (segament == 4 && startIndex == s.length()) {
                validIps.add(currentIPString);
                return;
            }
            //trm         // 如果当前部分数已经达到 4 但字符串仍未用完，或者已用字符超出限制，则返回
            if (segament == 4 || startIndex == s.length()) {
                return;
            }
            // iterative current string from 1 to 3
            // 尝试每个部分可能的长度（1 到 3）
            for (int len = 1; len <= 3; len++) {
                // 计算当前部分的结束索引，    // 如果结束索引超出字符串长度，则跳出循环
                if (startIndex + len > s.length()) {
                    break;
                }
                // 取出当前部分
                String segamentIP = s.substring(startIndex, startIndex + len);
                if (validSegamentIP(segamentIP, len)) {
                    // 如果有效，则继续进行回溯，增加当前部分到结果字符串
                    String newcurrentIPString = segament == 0 ?
                            segamentIP : currentIPString + "." + segamentIP;
                    backtrack(s, validIps, startIndex + len, newcurrentIPString, segament + 1);
                }
            }

        }

        //validSegamentIP
        public boolean validSegamentIP(String segamentIP, int len) {
            // invalid SegamentIP 01 || 256 etc.
            if ((segamentIP.startsWith("0") && len > 1)
                    || (!segamentIP.startsWith("0") && Integer.valueOf(segamentIP) > 255)) {
                return false;
            }
            return true;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}