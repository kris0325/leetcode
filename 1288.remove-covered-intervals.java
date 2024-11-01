/*
 * @lc app=leetcode id=1288 lang=java
 *
 * [1288] Remove Covered Intervals
 *
 * https://leetcode.com/problems/remove-covered-intervals/description/
 *
 * algorithms
 * Medium (56.41%)
 * Likes:    2229
 * Dislikes: 60
 * Total Accepted:    122.4K
 * Total Submissions: 217.3K
 * Testcase Example:  '[[1,4],[3,6],[2,8]]'
 *
 * Given an array intervals where intervals[i] = [li, ri] represent the
 * interval [li, ri), remove all intervals that are covered by another interval
 * in the list.
 * 
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a
 * and b <= d.
 * 
 * Return the number of remaining intervals.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= li < ri <= 10^5
 * All the given intervals are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        
    }
}
// @lc code=end

