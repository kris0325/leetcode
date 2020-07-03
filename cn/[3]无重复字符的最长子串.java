//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)

// 思路：
// 方法一：滑动窗口+HashMap+常规解法
//这道题字符出现的位置很重要，所以可以使用 HashMap 来建立字符和其出现位置之间的映射。主要思路如下：
//
//维护了一个滑动窗口，窗口内的都是没有重复的字符，需要尽可能的扩大窗口的大小
//
//由于窗口在不停向右滑动，所以只关心每个字符最后出现的位置，并建立映射
//
//窗口的右边界就是当前遍历到的字符的位置，为了求出窗口的大小，需要一个变量 left 来指向滑动窗口的左边界
//
//如果当前遍历到的字符从未出现过，那么直接扩大右边界
//
//如果之前出现过，那么就分两种情况，在或不在滑动窗口内
//
//如果不在，那么就没事，当前字符可以加进来
//如果在，需要先在滑动窗口内去掉这个已经出现过的字符了，去掉的方法并不需要将左边界 left 一位一位向右遍历查找，由于 HashMap 已经保存了该重复字符最后出现的位置，所以直接移动 left 指针就可以了。
//维护一个结果 res，每次用出现过的窗口大小来更新结果 res，就可以得到最终结果啦。
//
//注意将 left 初始化为 -1，在 i - left 时，不必判断单个字符的情况了。
//————————————————
//版权声明：本文为CSDN博主「Y_puyu」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/yl_puyu/article/details/104717915/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }

}


//leetcode submit region end(Prohibit modification and deletion)
