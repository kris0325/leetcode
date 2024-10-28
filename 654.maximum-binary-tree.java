/*
 * @lc app=leetcode id=654 lang=java
 *
 * [654] Maximum Binary Tree
 *
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * algorithms
 * Medium (85.31%)
 * Likes:    5194
 * Dislikes: 340
 * Total Accepted:    295.8K
 * Total Submissions: 346.3K
 * Testcase Example:  '[3,2,1,6,0,5]'
 *
 * You are given an integer array nums with no duplicates. A maximum binary
 * tree can be built recursively from nums using the following algorithm:
 * 
 * 
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the
 * maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of
 * the maximum value.
 * 
 * 
 * Return the maximum binary tree built from nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right
 * suffix is [0,5].
 * ⁠   - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix
 * is [2,1].
 * ⁠       - Empty array, so no child.
 * ⁠       - The largest value in [2,1] is 2. Left prefix is [] and right
 * suffix is [1].
 * ⁠           - Empty array, so no child.
 * ⁠           - Only one element, so child is a node with value 1.
 * ⁠   - The largest value in [0,5] is 5. Left prefix is [0] and right suffix
 * is [].
 * ⁠       - Only one element, so child is a node with value 0.
 * ⁠       - Empty array, so no child.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,1]
 * Output: [3,null,2,null,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * All integers in nums are unique.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution1 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(null == nums){
            return null;
        }
        return constructMaximumBinaryTreeHelp(nums);
        
    }

    public TreeNode constructMaximumBinaryTreeHelp(int[] nums){
        //edge case
        if(null == nums || nums.length == 0){
            return null;
        }
        //1.root
        TreeMap<Integer,Integer> val2index = new TreeMap<>();
        for(int i = 0; i< nums.length; i++){
            val2index.put(nums[i],i);
        }
        int rootVal = val2index.lastKey();
        int splitIndex = val2index.lastEntry().getValue();
        TreeNode root = new TreeNode(rootVal);

        // get numsLeft by splitIndex
        int[] numsLeft = IntStream.range(0, splitIndex)
        .map(i -> nums[i])
        .toArray();

        // get numsRight by splitIndex
        int[] numsRight = IntStream.range(splitIndex+1, nums.length)
        .map(i -> nums[i])
        .toArray();

        // recursive to get root.left | root.right
        root.left = constructMaximumBinaryTreeHelp(numsLeft);
        root.right = constructMaximumBinaryTreeHelp(numsRight);
        return root;
    }
}

//TODO 空间优化,基于index,直接在原数组上操作
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree1(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {// 没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {// 只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;// 最大值所在位置
        int maxVal = nums[maxIndex];// 最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxVal){
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree1(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree1(nums, maxIndex + 1, rightIndex);
        return root;
    }
}


// @lc code=end

