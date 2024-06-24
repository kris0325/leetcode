/*
 * @lc app=leetcode id=455 lang=java
 *
 * [455] Assign Cookies
 *
 * https://leetcode.com/problems/assign-cookies/description/
 *
 * algorithms
 * Easy (52.66%)
 * Likes:    3857
 * Dislikes: 368
 * Total Accepted:    459.6K
 * Total Submissions: 872.8K
 * Testcase Example:  '[1,2,3]\n[1,1]'
 *
 * Assume you are an awesome parent and want to give your children some
 * cookies. But, you should give each child at most one cookie.
 * 
 * Each child i has a greed factor g[i], which is the minimum size of a cookie
 * that the child will be content with; and each cookie j has a size s[j]. If
 * s[j] >= g[i], we can assign the cookie j to the child i, and the child i
 * will be content. Your goal is to maximize the number of your content
 * children and output the maximum number.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3
 * children are 1, 2, 3. 
 * And even though you have 2 cookies, since their size is both 1, you could
 * only make the child whose greed factor is 1 content.
 * You need to output 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2
 * children are 1, 2. 
 * You have 3 cookies and their sizes are big enough to gratify all of the
 * children, 
 * You need to output 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        //cookies num count
        int count = 0;
        int i = 0;
        int j = 0;
        //travelse g ,s, to use smallest cookie to satisfy smallest greedy child
        //小胃口优先分配小饼干，（大胃口优先分配大饼干也可以），即先找局部最优解，再推导全局最优解
        //使用双指针遍历孩子数组，饼干数组
        while (i < g.length && j<s.length) {
            if(s[j]>=g[i]){
                count++;
                //找到满足当前孩子的最小饼干后，指针i继续遍历下一个孩子
                i++;
            }
            //未找到 / 找到，均移动指针j继续遍历下一饼干
            j++;
        }
        return count;
    }
}
// @lc code=end

