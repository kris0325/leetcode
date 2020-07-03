//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法
// 思路: 使用哈希表 存储元素与出现的次数， 遍历数组跟新哈希表并判断



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0];
        HashMap<Integer, Integer> numTime = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numTime.containsKey(nums[i])) {
                numTime.put(nums[i], numTime.get(nums[i]) + 1);
                if (numTime.get(nums[i]) > nums.length / 2) {
                    res = nums[i];
                    break;
                }
            } else {
                numTime.put(nums[i], 1);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
