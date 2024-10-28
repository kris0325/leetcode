import java.util.Arrays;
import java.util.LinkedList;

/**
 * You are given an array of people, people, which are the attributes of some
 * people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents
 * the iᵗʰ person of height hi with exactly ki other people in front who have a
 * height greater than or equal to hi.
 * <p>
 * Reconstruct and return the queue that is represented by the input array people.
 * The returned queue should be formatted as an array queue, where queue[j] = [hj,
 * kj] is the attributes of the jᵗʰ person in the queue (queue[0] is the person
 * at the front of the queue).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * Explanation:
 * Person 0 has height 5 with no other people taller or the same height in front.
 * Person 1 has height 7 with no other people taller or the same height in front.
 * Person 2 has height 5 with two persons taller or the same height in front,
 * which is person 0 and 1.
 * Person 3 has height 6 with one person taller or the same height in front, which
 * is person 1.
 * Person 4 has height 4 with four people taller or the same height in front,
 * which are people 0, 1, 2, and 3.
 * Person 5 has height 7 with one person taller or the same height in front, which
 * is person 1.
 * Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= people.length <= 2000
 * 0 <= hi <= 10⁶
 * 0 <= ki < people.length
 * It is guaranteed that the queue can be reconstructed.
 * <p>
 * <p>
 * Related Topics Array Binary Indexed Tree Segment Tree Sorting 👍 7071 👎 716
 */
       
/*
 2024-07-22 20:16:10
 Queue Reconstruction by Height
Category	Difficulty	Likes	Dislikes
algorithms	Medium (73.42%)	7071	716
Tags
greedy

Companies
google
*/

class QueueReconstructionByHeight {
    public static void main(String[] args) {
        Solution solution = new QueueReconstructionByHeight().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy question: 本題與135.Candy类似， 遇到2個緯度的權衡排列時，先考慮其中一個維度，再考慮另一個維度
        //具體先考慮hi,還是ki，可以都嘗試下，
        // 如果選擇ki，那麼根據題意，必然從小到大排序，發現k排列不符合條件，身高hi排列也不符合條件，兩個緯度都沒確定下來；
        // 如果先按照身高hi排序， 那麼根據題意，必然從大到小排序（身高相同的k小的排前面），即讓個子高的站前面。
        // 此時可以確定一個緯度，就是身高，這樣前面節點一定比本節點高。然後以k為下標，從小到大插入隊列即可。
        //因為，按照身高排序之后，优先按身高高的people的k来插入，后序插入节点也不会影响前面已经插入的节点，最终按照k的规则完成了队列。
        //局部最優：優先按照身高高的people的k來插入。插入操作後的people都滿足題意的隊列屬性
        //全局最優：最後做完插入操 作，整個隊列滿足題目要求的隊列屬性
        public int[][] reconstructQueue(int[][] people) {
            //先按照維度身高hi排序（從大到小）
            Arrays.sort(people, (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return b[0] - a[0];
            });
            //再按照維度ki（從小到大）插入
            LinkedList<int[]> queue = new LinkedList<>();
            for (int[] person : people) {
                queue.add(person[1],person);
            }
            return queue.toArray(new int[people.length][2]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}