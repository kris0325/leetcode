/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
 * bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which returns whether version
 * is bad. Implement a function to find the first bad version. You should minimize
 * the number of calls to the API.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 1, bad = 1
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= bad <= n <= 2³¹ - 1
 * <p>
 * <p>
 * Related Topics Binary Search Interactive 👍 8419 👎 3319
 */
       
/*
 2024-09-13 22:13:12
*/

class FirstBadVersion {
    public static void main(String[] args) {
//        Solution solution = new FirstBadVersion().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

//    public class Solution extends VersionControl {
//        int min = Integer.MAX_VALUE;
//
//        public int firstBadVersion(int n) {
//            int left = 1, right = n;
//            while (left <= right) {
//                int mid = left + (right - left) / 2;
//                if (!isBadVersion(mid)) {
//                    left = mid + 1;
//                } else {
//                    min = Math.min(mid, min);
//                    right = mid - 1;
//                }
//            }
//            return min;
//        }
//    }
//leetcode submit region end(Prohibit modification and deletion)

}