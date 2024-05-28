/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 *
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (62.76%)
 * Likes:    17113
 * Dislikes: 647
 * Total Accepted:    2.1M
 * Total Submissions: 3.4M
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * 
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * 
 * 
 * 
 * Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 * 
 */

 /*
 * 思路：最小堆（小顶堆）
 *     1.haspmap 存每个元素的值与对应频率；
 *     2.PriorityQueue实现构建一个最小堆，然后遍历hashmap，将所有频率都推入到堆中，
 *     维护堆的大小为k,即如果堆大小超过k时，就将堆顶元素弹出，
 *     3.遍历完后，PriorityQueue的元素即为所求
 * 
 * */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> num2Frequent = new HashMap<>();
        for(int num : nums){
            num2Frequent.put(num, num2Frequent.getOrDefault(num, 0)+1);
        }
        //构建最小堆的优先级队列
        //优先队列的存储的元素为一个int[2], 即一个pair<num, cnt> num为数字，cnt为对应频率
        PriorityQueue<int[]> priorityQueue = new java.util.PriorityQueue<>((pair1,pair2) -> pair1[1]-pair2[1]);
        for(Map.Entry<Integer, Integer> entry : num2Frequent.entrySet() ){
            if(priorityQueue.size()<k){
                priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
            } else {
                //当前元素出现频率大于最小堆堆顶元素对应的频率
                if(entry.getValue() > priorityQueue.peek()[1]){
                    //弹出最小堆堆顶元素
                    priorityQueue.poll();
                    //将当前元素推入队列
                    priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }  
        }
        int[] result = new int[k];
        for(int i = 0; i<k ; i++){
            result[i] = priorityQueue.poll()[0];
        }
        return result;
    }
}
// @lc code=end

