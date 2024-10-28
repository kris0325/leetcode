import java.util.*;

/**
 * You are given a string s. We want to partition the string into as many parts as
 * possible so that each letter appears in at most one part.
 * <p>
 * Note that the partition is done so that after concatenating all the parts in
 * order, the resultant string should be s.
 * <p>
 * Return a list of integers representing the size of these parts.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits
 * s into less parts.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: s = "eccbbbbdec"
 * Output: [10]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * <p>
 * <p>
 * Related Topics Hash Table Two Pointers String Greedy 👍 10287 👎 399
 */

/*
 2024-07-30 17:33:08
 Partition Labels
Category	Difficulty	Likes	Dislikes
algorithms	Medium (79.83%)	10287	399
Tags
string | recursion

Companies
Unknown
*/

class PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new PartitionLabels().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //greedy question: 遍歷的過程相當於是找每個字母的最遠邊界，
        // 每小端局部遍歷，如果找到之前遍歷過的所有字母的最遠邊界，說明此時index就是分割點，為局部最優解
        //然後繼續迭代，實現全局最優
        //TC:O(N)
        //SC:O(1), HASHMAP數組大小為固定大小
        public List<Integer> partitionLabels(String s) {
            Map<Character, Integer> char2IndexMap = new HashMap<>();
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                char2IndexMap.put(s.charAt(i), i);
            }
            int preIndex = 0;
            int currentIndex = char2IndexMap.get(s.charAt(0));
            for (int i = 0; i < s.length(); i++) {
                //每遍歷一個新字符 都更新當前最遠右邊界
                currentIndex = Math.max(currentIndex, char2IndexMap.get(s.charAt(i)));
                //局部最優：遍歷到最遠邊界時，即找到當前切割點
                if (currentIndex == i) {
                    res.add(currentIndex - preIndex + 1);
                    //更新左邊界
                    preIndex = currentIndex + 1;
                }
            }
            return res;
        }
    }

    class Solution {
        //greedy question: 思路與452.用最少数量的箭引爆气球，435.无重叠区间 相同的思路。
        // 统计字符串中所有字符的起始和结束位置，记录这些区间(实际上也就是435.无重叠区间 题目里的输入)，
        // 将区间按左边界从小到大排序，找到边界将区间划分成组，互不重叠。找到的边界就是答案。
        public List<Integer> partitionLabels(String s) {
            //1.構建區間
            int[][] partitions = findPartitions(s);
            List<Integer> res = new ArrayList<>();
            //2.partition 切割區間
            //按第一個坐標（左邊界）升序排序
            Arrays.sort(partitions, (a, b) -> Integer.compare(a[0], b[0]));
            //紀錄上一區間right
            int preRight = partitions[0][1];
            int preLeft = 0;
            for (int i = 0; i < partitions.length; i++) {
                //1.無重疊區間：當前區間左邊界大於上一區間右邊界，即可實現一次分割
                if (preRight < partitions[i][0]) {
                    res.add(preRight - preLeft + 1);
                    //更新左邊界
                    preLeft = partitions[i][0];
                    //更新右邊界
                    preRight = partitions[i][1];
                } else {
                    // 2.有重疊區間 只需更新最遠右邊界
                    preRight = Math.max(preRight, partitions[i][1]);
                }

            }
            //最右端 需要額外處理下
            res.add(preRight - preLeft + 1);
            return res;
        }

        //1.構建區間
        public int[][] findPartitions(String s) {
            //統計每個字母對應的區間
            //數組2個元素分別表示最左邊界，最右邊界
            int[][] hash = new int[26][2];
            //左，右邊界都初始化為-1
            for (int i = 0; i < 26; i++) {
                hash[i][0] = -1;
                hash[i][1] = -1;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (hash[c - 'a'][0] == -1) {
                    //第一次出現 所以是最左邊界
                    hash[c - 'a'][0] = i;
                }
                //每次更新出現更新index，最後可得到最大值，即最右邊界
                hash[c - 'a'][1] = i;
            }
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                if (hash[i][0] != -1) {
                    list.add(new int[]{hash[i][0], hash[i][1]});
                }
            }
            return list.toArray(new int[list.size()][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}