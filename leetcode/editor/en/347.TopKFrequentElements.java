import java.util.Map;
import java.util.PriorityQueue;

/**
Given an integer array nums and an integer k, return the k most frequent 
elements. You may return the answer in any order. 

 
 Example 1: 
 Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
 
 Example 2: 
 Input: nums = [1], k = 1
Output: [1]
 
 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 
 k is in the range [1, the number of unique elements in the array]. 
 It is guaranteed that the answer is unique. 
 

 
 Follow up: Your algorithm's time complexity must be better than O(n log n), 
where n is the array's size. 

 Related Topics Array Hash Table Divide and Conquer Sorting Heap (Priority 
Queue) Bucket Sort Counting Quickselect 👍 17650 👎 680

*/
       
/*
 2024-10-22 20:57:33
*/

class TopKFrequentElements {
      public static void main(String[] args) {
           Solution solution = new TopKFrequentElements().new Solution();
      }

       
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
          //1.hashmap 統計元素的頻率，
          // 2. pq, 維護一個k大小的小頂堆，即得到出現次數中前k大的元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //int[2]的數組，分別存放num值，與頻率
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] -b[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
         if(pq.size() == k) {
                if(pq.peek()[1] < entry.getValue()){
                    pq.poll();
                    pq.offer(new int[]{entry.getKey(), entry.getValue()});
                }
            } else {
                pq.offer(new int[]{entry.getKey(), entry.getValue()});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll()[0];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}