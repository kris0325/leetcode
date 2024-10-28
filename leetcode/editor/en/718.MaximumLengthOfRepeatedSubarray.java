/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a
 * subarray that appears in both arrays.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 * <p>
 * <p>
 * Related Topics Array Binary Search Dynamic Programming Sliding Window Rolling
 * Hash Hash Function 👍 6788 👎 168
 */
       
/*
 2024-07-24 18:27:07
 Maximum Length of Repeated Subarray
Category	Difficulty	Likes	Dislikes
algorithms	Medium (50.95%)	6788	168
Tags
array | hash-table | binary-search | dynamic-programming

Companies
Unknown
*/

class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question:
        public int findLength(int[] nums1, int[] nums2) {
            //定義dp數組 dp[i][j]表示以i-1，j-1結尾的的nums1 与nums2的最長重複子數組
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            int longestLength = 0;
            //初始化  根据定义，dp[0][0]没有实际意义，只是为满足递推公式
            dp[0][0] = 0;
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        //递推公式 根据dp[i][j]定義，dp[i][j]的狀態只能由dp[i-1][j-1]推導出來
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    longestLength = Math.max(longestLength, dp[i][j]);
                }
            }
            return longestLength;
        }
    }

    class Solution2 {
        //dp question: 一維滾動數組，根据dp[i][j]定義，dp[i][j]的狀態只能由dp[i-1][j-1]推導出來，
        //那麼壓縮為一維數組，即 dp[j]都由dp[j-1]推導出來
        public int findLength(int[] nums1, int[] nums2) {
            //定義dp數組 dp[i][j]表示以i-1，j-1結尾的的nums1 与nums2的最長重複子數組
            int[] dp = new int[nums2.length + 1];
            int longestLength = 0;
            //初始化  根据定义，dp[0][0]没有实际意义，只是为满足递推公式
            dp[0] = 0;
            for (int i = 1; i <= nums1.length; i++) {
                //遍歷nums2數組時，需要從後往前，避免重複覆蓋
                for (int j = nums2.length; j >0; j--) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        //递推公式 根据dp[i][j]定義，dp[i][j]的狀態只能由dp[i-1][j-1]推導出來
                        dp[j] = dp[j - 1] + 1;
                    } else {
                        //不相等時，需要重置dp[j]=0
                        dp[j] = 0;
                    }
                    longestLength = Math.max(longestLength, dp[j]);
                }
            }
            return longestLength;
        }
    }

    class Solution3 {
        //暴力解法
        public int findLength(int[] nums1, int[] nums2) {
            int longestLength = 0;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    //定義當前臨時指針pointer1，pointer1，currentLength，進行遍歷
                    int currentLength = 0;
                    int pointer1 = i;
                    int pointer2 = j;
                    while (pointer1 <nums1.length && pointer2 < nums2.length &&  nums1[pointer1] == nums2[pointer2]) {
                        currentLength++;
                        pointer1++;
                        pointer2++;
                    }
                    longestLength = Math.max(longestLength, currentLength);
                }
            }
            return longestLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}