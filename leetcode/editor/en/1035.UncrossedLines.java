/**
 * You are given two integer arrays nums1 and nums2. We write the integers of nums1
 * and nums2 (in the order they are given) on two separate horizontal lines.
 * <p>
 * We may draw connecting lines: a straight line connecting two numbers nums1[i]
 * and nums2[j] such that:
 * <p>
 * <p>
 * nums1[i] == nums2[j], and
 * the line we draw does not intersect any other connecting (non-horizontal) line.
 * <p>
 * <p>
 * <p>
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each
 * number can only belong to one connecting line).
 * <p>
 * Return the maximum number of connecting lines we can draw in this way.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums1 = [1,4,2], nums2 = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2]
 * = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 * <p>
 * <p>
 * Related Topics Array Dynamic Programming 👍 3814 👎 57
 */
       
/*
 2024-07-25 15:15:54
 Uncrossed Lines
Category	Difficulty	Likes	Dislikes
algorithms	Medium (62.96%)	3814	57
Tags
tree | breadth-first-search

Companies
Unknown
*/

class UncrossedLines {
    public static void main(String[] args) {
        Solution solution = new UncrossedLines().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp question: 連接線不相交，只需保證相等元素的相對位置不變，即變為求最長公共子序列
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            //定義dp數組： dp[i][j]表示長度為[0,i-1]的字符串text1，與長度為[0,j-1]的字符串text2的最長公共子序列
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            //初始化
            //字符串與空字符串的最長公共子序列肯定為0,所以統一初始化為0
//        dp[i][0] = 0;
//        dp[0][j] = 0;
            //遍歷順序
            for (int i = 1; i <= nums1.length; i++) {
                for (int j = 1; j <= nums2.length; j++) {
                    //遞推公式
                    //nums1[i-1]與nums2[j-1元素相等
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        //nums1[i-1]與nums2[j-1元素不相等，nums1與nums2分別往前回退一個元素,即比較if(nums1[i-2] = nums2[j-1]) 或 if(nums1[i-1] = nums2[j-2])
                        // 求最長公共子序列，然後求二者較大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[nums1.length][nums2.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}