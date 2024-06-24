/*
 * @lc app=leetcode id=93 lang=java
 *
 * [93] Restore IP Addresses
 *
 * https://leetcode.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (49.82%)
 * Likes:    5190
 * Dislikes: 789
 * Total Accepted:    464.3K
 * Total Submissions: 923.5K
 * Testcase Example:  '"25525511135"'
 *
 * A valid IP address consists of exactly four integers separated by single
 * dots. Each integer is between 0 and 255 (inclusive) and cannot have leading
 * zeros.
 * 
 * 
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but
 * "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP
 * addresses.
 * 
 * 
 * Given a string s containing only digits, return all possible valid IP
 * addresses that can be formed by inserting dots into s. You are not allowed
 * to reorder or remove any digits in s. You may return the valid IP addresses
 * in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 20
 * s consists of digits only.
 * 
 * 
 */

// @lc code=start

import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        //backtrack to solve it
               // collect valid ip
               List<String> validIps = new ArrayList<>();
        //1. not valid string 
        if( s.length()< 4 || s.length()> 12 ){

            return validIps;
        }
 
        int segament = 0;
        String currentIPString = "";
        int startIndex = 0;
        backtrack(s, validIps, 0, currentIPString, segament);
        return validIps;
    }
    // backtrack 
    public void backtrack(String s,List<String> validIps, int startIndex,
      String currentIPString, int segament ){
        // return condition
        if(segament == 4 && startIndex == s.length()){
            validIps.add(currentIPString);
            return;
        }
        //trm
        if(segament ==4 ||  startIndex == s.length()){
            return;
        }
        // iterative current string from 1 to 3
        for(int len = 1; len<=3; len++){
            if(startIndex + len >s.length()){
                break;
            }
            String segamentIP = s.substring(startIndex, startIndex+len);
            if(validSegamentIP(segamentIP, len)){
                String newcurrentIPString = segament == 0 ?
                segamentIP : currentIPString + "." + segamentIP;
                backtrack(s,validIps,startIndex+len, newcurrentIPString, segament+1);
            }
        }

    }

    //validSegamentIP
    public boolean validSegamentIP(String segamentIP, int len){
        // invalid SegamentIP 01 || 256 etc.
        if((segamentIP.startsWith("0") && len > 1)
        || (!segamentIP.startsWith("0") && Integer.valueOf(segamentIP) > 255)){
            return false;
        }
        return true;
    }

}
// @lc code=end

