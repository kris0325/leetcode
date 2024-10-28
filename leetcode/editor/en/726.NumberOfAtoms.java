import java.util.*;

/**
 * Given a string formula representing a chemical formula, return the count of
 * each atom.
 * <p>
 * The atomic element always starts with an uppercase character, then zero or
 * more lowercase letters, representing the name.
 * <p>
 * One or more digits representing that element's count may follow if the count
 * is greater than 1. If the count is 1, no digits will follow.
 * <p>
 * <p>
 * For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
 * <p>
 * <p>
 * Two formulas are concatenated together to produce another formula.
 * <p>
 * <p>
 * For example, "H2O2He3Mg4" is also a formula.
 * <p>
 * <p>
 * A formula placed in parentheses, and a count (optionally added) is also a
 * formula.
 * <p>
 * <p>
 * For example, "(H2O2)" and "(H2O2)3" are formulas.
 * <p>
 * <p>
 * Return the count of all elements as a string in the following form: the first
 * name (in sorted order), followed by its count (if that count is more than 1),
 * followed by the second name (in sorted order), followed by its count (if that
 * count is more than 1), and so on.
 * <p>
 * The test cases are generated so that all the values in the output fit in a 32-
 * bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: formula = "H2O"
 * Output: "H2O"
 * Explanation: The count of elements are {'H': 2, 'O': 1}.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= formula.length <= 1000
 * formula consists of English letters, digits, '(', and ')'.
 * formula is always valid.
 * <p>
 * <p>
 * Related Topics Hash Table String Stack Sorting 👍 1889 👎 408
 */
       
/*
 2024-10-17 17:32:32
*/

class NumberOfAtoms {
    public static void main(String[] args) {
        Solution solution = new NumberOfAtoms().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //與括號序列相關的題目，通用解法是使用棧或遞歸，本地使用棧實現

        /**
         * 在处理完一对括号后，我们已经将括号内的原子计数合并到了上一层的哈希表中,當前括號內原子已pop出去。
         * 所以栈顶始终保持着当前层级的原子计数，topMap = stack.peek()
         * */
        int i, n;
        String formula;
        public String countOfAtoms(String formula) {
            this.formula = formula;
            this.n = formula.length();
            this.i = 0;
            //棧中存放元素為 哈希表，即 字母：字母數量
            Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
            stack.push(new HashMap<String, Integer>());
            while (i < n) {
                char c = formula.charAt(i);
                //c是左括號
                if (c == '(') {
                    i++;
                    //放空的哈希表
                    stack.push(new HashMap<String, Integer>());
                }//c是右括號
                else if (c == ')') {
                    i++;
                    //括號右側的數字
                    int num = parseNum();
                    //彈出跨號內的原子數量,即 popMap 為當前括號的原子哈希表
                    Map<String, Integer> popMap = stack.pop();
                    //彈出popMap當前層哈希表後, 棧頂stack.peek()即為之前所有的原子哈希表topMap，然後合併當前popMap到 topMap
                    Map<String, Integer> topMap = stack.peek();
                    for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                        String atom = entry.getKey();
                        int val = entry.getValue();
                        topMap.put(atom, topMap.getOrDefault(atom, 0) + val * num);
                    }
                }
                //c是字符
                else {
                    String atom = parseAtom();
                    int num = parseNum();
                    Map<String, Integer> topMap = stack.peek();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
                }
            }

            //取出棧頂元素，即所有原子的哈希表
            Map<String, Integer> topMap = stack.peek();
            //對key （原子）按照字典序進行排序
            TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(topMap);

            //拼接結果
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                String atom = entry.getKey();
                int count = entry.getValue();
                sb.append(atom);
                if (count > 1) {
                    sb.append(count);
                }
            }
            return sb.toString();
        }

        //當前index = i 字符解析 提取數字
        public int parseNum() {
            if (i == n || !Character.isDigit(formula.charAt(i))) {
                //非數字， 則計為1
                return 1;
            }
            int num = 0;
            while (i < n && Character.isDigit(formula.charAt(i))) {
                //掃描數字，（數字有可能大於>10,所以逐位掃描
                num = num * 10 + formula.charAt(i++) - '0';
            }
            return num;
        }

        //當前index = i 字符解析 提取字母
        public String parseAtom() {
            StringBuffer sb = new StringBuffer();
            //掃描字母， append 後 i++更新下標
            sb.append(formula.charAt(i++));
            //掃描大寫字母後的小寫字母，組合為一個原子atom的情況
            while (i<n && Character.isLowerCase(formula.charAt(i))){
                //append 後 i++更新下標
                sb.append(formula.charAt(i++));
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}