import java.util.Arrays;

/**
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid
 * values of i (i.e., 0 <= i < n).
 * <p>
 * In one operation, you can select two indices x and y where 0 <= x, y < n and
 * subtract 1 from arr[x] and add 1 to arr[y] (i.e., perform arr[x] -=1 and arr[y] +=
 * 1). The goal is to make all the elements of the array equal. It is guaranteed
 * that all the elements of the array can be made equal using some operations.
 * <p>
 * Given an integer n, the length of the array, return the minimum number of
 * operations needed to make all the elements of arr equal.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: arr = [1, 3, 5]
 * First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
 * In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: n = 6
 * Output: 9
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= n <= 10⁴
 * <p>
 * <p>
 * Related Topics Math 👍 1440 👎 182
 */
       
/*
 2024-08-02 23:08:15
 Minimum Operations to Make Array Equal
Category	Difficulty	Likes	Dislikes
algorithms	Medium (82.19%)	1440	182
Tags
Unknown

Companies
TikTok
*/

class MinimumOperationsToMakeArrayEqual {
    public static void main(String[] args) {
//        Solution solution = new MinimumOperationsToMakeArrayEqual().new Solution();
//        int[] arr = {6, 4, 5, 1, 7};
//        int k = 5;
//        System.out.println("maxShownSegments: " + solution.maxShownSegments(arr, k));  // 輸出一個可能的最大觀看數量

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        public int minOperations(int n) {
            //數學（Mathematical Problem）/模擬（Simulation）：因為數組是有序奇數列，所以將所有數變為平均值n= (1+(2*n+1))/2的操作數最小
            //模擬，相向雙指針法，讓所有元素變為平均值
            int start = 0;
            int end = n - 1;
            int count = 0;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = 2 * i + 1;
            }
            while (start < end) {
                while (arr[start] < n && arr[end] > n) {
                    count++;
                    arr[start] += 1;
                    arr[end] -= 1;
                }
                start++;
                end--;
            }
            return count;
        }
    }

    class Solution2 {
        /**
         * //數學（Mathematical Problem）/模擬（Simulation）：因為數組是有序奇數列，所以將所有數變為平均值n= (1+(2*n+1))/2的操作數最小
         * 注意到每次我们进行操作时都同时进行了「加」操作和「减」操作，这样我们只需要记录「减」操作的数量即可知道我们操作了多少次。
         * <p>
         * 对于每一个大于 n 的数，其与 n 的差值即为该元素需要进行的「减」操作的数量。我们只要统计所有大于 n 的数与 n 的差值，就能计算出我们操作了多少次。
         */
        public int minOperations(int n) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                int x = 2 * i + 1;
                //大於平均值，則執行減操作,每次減1，那麼總共次數為x - n
                if (x > n) {
                    count += x - n;
                }
            }
            return count;
        }
    }

    /**
     * TikTok
     * Minimum Cycles
     * 🔥 FULLTIME🤘 INTERN
     * Given an array of n integers, arr, make the values equal using the minimum number of operations.
     * <p>
     * - Either choose an element and apply the operation:
     * <p>
     * If the operation number is odd (e.g., first, third, fifth,...), then increase the element by 1.
     * If the operation number is even (e.g., second, fourth, sixth,...), then increase the element by 2.
     * - or do nothing.
     * Only one element can change in an operation.
     * <p>
     * Calculate the minimum number of operations required to make all elements equal.
     * <p>
     * Function Description
     * <p>
     * Complete the function minimumCycles in the editor.
     * <p>
     * minimumCycles has the following parameter:
     * <p>
     * int arr[n]: an array of integers
     * Returns
     * <p>
     * int: the minimum number of operations required
     * <p>
     * Example 1:
     * <p>
     * Input:  arr = [1, 2, 4]
     * Output: 4
     * Explanation:
     * <p>
     * [1,2,4]
     * <p>
     * 1 -> 4  = 1+2 ( 2 operations)
     * 2 -> 4 = (do nothing operation) + 2 (2 operations)
     * 4 operations total
     * <p>
     * As doing nothing is a considered an operation as well.
     */
    public class Solution3 {
        public int minimumCycles(int[] arr) {
            if (arr.length == 1) {
                return 0;
            }
            //至少一次操作
            int minCount = 0;
            int start = 0;
            int end = arr.length - 1;
            //升序排序，將所有數通過+1/+2/不操組，變為最大的一個數
            Arrays.sort(arr);
            //從前往後遍歷，從第一個元素，操作到第arr.length - 2個元素
            while (start < end) {
                while (arr[start] < arr[end]) {
                    minCount++;
                    if (minCount % 2 == 1) {
                        arr[start] += 1;
                    } else {
                        //arr[start] + 2 < arr[end]才操作+2，否則arr[start] do nothing
                        if (arr[start] + 2 < arr[end]) {
                            arr[start] += 2;
                        }
                    }
                }
                start++;
            }
            return minCount;
        }
    }

    public class Solutio4 {
        //無須排序， 只需求處最大值，遍歷數組，每個元素與最大值的差值，按奇數/偶數操作，統計總操作數
        public int minimumCycles(int[] arr) {
            if (arr.length == 1) {
                return 0;
            }
            //至少一次操作
            int minTotalOperations = 0;
            //find最大值
            //    int maxNum = Arrays.stream(arr).max().orElse(0);
            int maxNum = 0;
            for (int num : arr) {
                maxNum = Math.max(maxNum, num);
            }
            for (int num : arr) {
                //計算當前元素變為最大值所需操作次數
                int diff = maxNum - num;
                while (diff > 0) {
                    minTotalOperations++;
                    if (minTotalOperations % 2 == 1) {
                        //operations為奇數操作，加1
                        diff -= 1;
                    } else {
                        //operations為偶數操作，加2（包含了最後一次 需要do nothing的情況, 即偶當前為偶數操作數&&diff=1，此時do nothing，但仍然需要使用一次操作數 ）
                        diff -= 2;
                    }
                }
            }
            return minTotalOperations;
        }
    }


    //    public class TikTokVideoAnalyzer {
    public class Solution5 {
//        Maximum Shown Segments
//    https://www.fastprep.io/problems/tiktok-max-shown-segments

    }




//leetcode submit region end(Prohibit modification and deletion)
}