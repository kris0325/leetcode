import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all
 * possible unique permutations in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * <p>
 * <p>
 * Related Topics Array Backtracking 👍 8496 👎 144
 */
       
/*
 2024-07-04 23:18:06

 Permutations II
Category	Difficulty	Likes	Dislikes
algorithms	Medium (59.17%)	8496	144
Tags
backtracking

Companies
linkedin | microsoft
*/

class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
        int[] nums = {1, 1, 2};
        System.out.println(solution.permuteUnique(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * sep 8, 2024
     */
    class Solution {
        //permutation: 排列问题， backtracking +去重：重复元素跳过
        List<List<Integer>> permutations = new ArrayList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            //permutations: firstly sort nums
            Arrays.sort(nums);
            boolean[] vistedNum = new boolean[nums.length];
            backtracking(new ArrayList<>(), nums, vistedNum);
            return permutations;
        }

        public void backtracking(List<Integer> path, int[] nums, boolean[] vistedNum) {
            if (path.size() == nums.length) {
                permutations.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                //同層遍歷，橫向遍歷，去重
                if (i > 0 && !vistedNum[i - 1] && nums[i] == nums[i - 1]) {
                    continue;
                }
                //同樹枝遍歷，縱向遍歷，去重
                if (!vistedNum[i]) {
                    path.add(nums[i]);
                    vistedNum[i] = true;
                    backtracking(path, nums, vistedNum);
                    path.removeLast();
                    vistedNum[i] = false;
                }
            }
        }
    }


    class Solution0 {
        //permutation: 排列问题，+去重：重复元素跳过
        List<List<Integer>> permutations = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            //標記當前元素是否已使用過
            boolean[] used = new boolean[nums.length];
            backtracking(new ArrayList<>(), nums, used);
            return permutations;
        }

        public void backtracking(List<Integer> path, int[] nums, boolean[] used) {
            if (path.size() == nums.length) {
                permutations.add(new ArrayList<>(path));
            }
            for (int i = 0; i < nums.length; i++) {
                //想像樹形path結構，縱向遍歷時，即同一樹枝的同一個元素已使用過
                //即當前元素已使用過
                if (used[i]) {
                    continue;
                }
                //橫向遍歷，即同一層，元素duplicate時，即前後兩個元素相同，
                // 當前面元素已做過一次dfs,會回溯，所以此時used[i - 1] = false
                // 那麼後面一個元素則無須再做dfs,因為二者得到的path會一樣
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                    continue;
                }

                path.add(nums[i]);
                used[i] = true;
                backtracking(path, nums, used);
                path.removeLast();
                used[i] = false;
            }

        }
    }


    class Solution1 {
        //permutation: 排列问题，+去重：重复元素跳过
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            //还要强调的是去重一定要对元素进行排序，这样我们才方便通过相邻的节点来判断是否重复使用了。
            Arrays.sort(nums);
            boolean[] used = new boolean[nums.length];
            permuteUniqueBacktracking(nums, used);
            return permutations;
        }

        public void permuteUniqueBacktracking(int[] nums, boolean[] used) {
            if (path.size() == nums.length) {
                //能去重，但效率比较低
//                if (!permutations.contains(path)) {
//                    permutations.add(new ArrayList<>(path));
//                }
                permutations.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过, 树枝指的是纵向遍历dfs
                // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过, 树层指的是横向遍历bfs ( 回溯时会重置为false),
                // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
                //for loop 是横向遍历，
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
                //递归调用是纵向遍历，深度调用

//
                //if (i > 0 && nums[i] == nums[i - 1] ) continue;这样其实并不行，
                // 一定要加上 used[i - 1] == false或者used[i - 1] == true，
                // 因为 used[i - 1] 要一直是 true 或者一直是false 才可以，而不是 一会是true 一会又是false。 因为递归调用时，used[i - 1]会变化 所以这个条件要写上。
                //是不是豁然开朗了！！

                //单前元素没有已使用过
                if (!used[i]) {
                    used[i] = true;
                    path.add(nums[i]);
                    permuteUniqueBacktracking(nums, used);
                    path.removeLast();
                    used[i] = false;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}