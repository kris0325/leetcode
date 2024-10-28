/**
 * There are n gas stations along a circular route, where the amount of gas at the
 * iᵗʰ station is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from the iᵗʰ station to its next (i + 1)ᵗʰ station. You begin the journey with
 * an empty tank at one of the gas stations.
 * <p>
 * Given two integer arrays gas and cost, return the starting gas station's index
 * if you can travel around the circuit once in the clockwise direction, otherwise
 * return -1. If there exists a solution, it is guaranteed to be unique
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 =
 * 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to
 * station 3.
 * Therefore, return 3 as the starting index.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the
 * next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only
 * have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * n == gas.length == cost.length
 * 1 <= n <= 10⁵
 * 0 <= gas[i], cost[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics Array Greedy 👍 11955 👎 1155
 */
       
/*
 2024-07-19 22:56:08
 Gas Station
Category	Difficulty	Likes	Dislikes
algorithms	Medium (45.53%)	11955	1155
Tags
greedy

Companies
Unknown
*/

class GasStation1 {
    public static void main(String[] args) {
        Solution solution = new GasStation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //greedy : 局部最優解： 當前[0,1] rest[i]的累加和：curGasSum += gas[i]- cost[i] <0, 則[0,i]不能設為起點，起點至少是從i+1開始計算，同時更新curGasSum
        //全局最優解，找到可以跑完一圈的起點
        //全局rest[i]的累加和 totalGasSum += gas[i]- cost[i] <0, 則沒有gas可以跑完一圈，return-1
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int start = 0;
            int curGasSum = 0;
            int totalGasSum = 0;
            for (int i = 0; i < gas.length; i++) {
                //紀錄[0,1]當前剩餘gas的累加和
                curGasSum += gas[i] - cost[i];
                //紀錄[0,gas.length-1]全局剩餘gas的累加和
                totalGasSum += gas[i] - cost[i];
                if (curGasSum < 0) {
                    //更新起始位置
                    start = i + 1;
                    //重置curGasSum
                    curGasSum = 0;
                }
            }
            //全局rest[i]的累加和totalGasSum < 0, 說明沒有gas跑完一圈
            if (totalGasSum < 0) {
                return -1;
            }
            return start;
        }
    }

    /**
     * 2024-10-08
     */
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int start = 0;
            int curGasSum = 0;
            int totalGasSum = 0;
            for (int i = 0; i < gas.length; i++) {
                curGasSum += gas[i] - cost[i];
                totalGasSum += gas[i] - cost[i];
                //當前gas station 不能選為起點，假設起點start後移動一站
                if (curGasSum < 0) {
                    start = i + 1;
                    //重置curGasSum
                    curGasSum = 0;
                }
            }
            //判斷能否走一圈
            if (totalGasSum < 0) {
                return -1;
            }
            return start;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}