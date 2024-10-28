import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a
 * dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to
 * be in wordList.
 * sk == endWord
 * <p>
 * <p>
 * Given two words, beginWord and endWord, and a dictionary wordList, return the
 * number of words in the shortest transformation sequence from beginWord to
 * endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
 * "log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" ->
 * "dog" -> cog", which is 5 words long.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
 * "log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid
 * transformation sequence.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * <p>
 * <p>
 * Related Topics Hash Table String Breadth-First Search 👍 12131 👎 1881
 */
       
/*
 2024-08-17 23:58:39
 Word Ladder
Category	Difficulty	Likes	Dislikes
algorithms	Hard (39.13%)	12131	1881
Tags
breadth-first-search

Companies
amazon | facebook | linkedin | snapchat | yelp
*/

class WordLadder {
    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
        List<String> wordlist = List.of("hot", "dot", "dog", "lot", "log", "cog");

        solution.ladderLength("hit", "cog", wordlist);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //BSF

        //單向BFS

        //則使用queue維護當前需要遍歷的字符
        //遍歷beginWord的每一位，用a-z字符替換，
        //每次替換都判斷是否找到與endWord相同，相同則返回
        // 不相同，則判斷在字典wordList中是否存在，存在，則使用queue維護當前需要遍歷的字符，
        // 同時從字典中移除當前單詞（每個單詞只能用一次，需要跳出循環）
        //不存在，則繼續替換字符進行判斷
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            Queue<String> queue = new LinkedList<>();
            int length = 1;
            int n = beginWord.length();
            queue.offer(beginWord);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String curWord = queue.poll();
                    if (Objects.equals(endWord, curWord)) {
                        return length;
                    }
                    //循環當前單詞try每一位字符進行替換
                    for (int j = 0; j < n; j++) {
                        //每一位字符都替換為25分其他字母的情況
                        for (char c = 'a'; c <= 'z'; c++) {
                            StringBuilder next = new StringBuilder(curWord);
                            //替換當前第 j 位的字符為c,然後進行判斷替換後的單詞nextWord是否存在字典中
                            next.setCharAt(j, c);
                            String nextWord = next.toString();
                            if (wordSet.contains(nextWord)) {
                                //判斷是否找到endWord
                                if (Objects.equals(endWord, nextWord)) {
                                    return length + 1;
                                }
                                queue.offer(nextWord);
                                //字典中每個單詞只能用一次，所以用過一次就需要remove
                                wordSet.remove(nextWord);
                            }
                        }
                    }
                }
                length++;
            }
            return 0;
        }
    }

    class Solution {
        //BSF
        //雙向BFS // 使用双向 BFS 来求解最短转换序列
        //路径连通，則能找到，否則找不到 return 0；
        /*
        * 代码思路：
该题使用的是**双向广度优先搜索（Bidirectional BFS）**算法，用来求解从起始单词 beginWord 变换到目标单词 endWord 的最短转换序列长度。思路是通过逐步修改单词中的字符，并在字典 wordList 中查找可能的下一个单词，直到找到目标单词。

双向 BFS 的优势在于同时从起点和终点进行搜索，可以减少搜索的深度，从而提高算法效率。

核心步骤：
初始化：

将 wordList 转换为一个哈希集合 wordSet，以便快速查找单词是否存在。
如果 endWord 不在 wordSet 中，直接返回 0。
使用 beginSet 和 endSet 分别保存从 beginWord 和 endWord 开始的搜索范围。
使用 visited 集合保存已经访问过的单词，避免重复遍历。
双向 BFS：

逐层扩展 beginSet，每次从 beginSet 中取出单词，尝试对单词的每个字符进行替换。
对每个字符替换后的新单词 nextWord，检查是否在 endSet 中。如果找到，则说明路径连通，返回当前的转换步数。
如果 nextWord 在 wordSet 中且未访问过，加入 nextSet 以供下一轮扩展。
交换 beginSet 和 endSet：

每次扩展完成后，比较 beginSet 和 endSet 的大小，始终从较小的一方进行扩展，以提高搜索效率。
返回结果：

如果两个集合不能连通，返回 0。

        * **/
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // 将wordList转换为一个哈希集合以加快查找速度
            Set<String> wordSet = new HashSet<>(wordList);
            Set<String> beginSet = new HashSet<>();
            Set<String> endSet = new HashSet<>();
            Set<String> visted = new HashSet<>();
            //字典中不包含endWord
            if (!wordSet.contains(endWord)) {
                return 0;
            }
            // 初始化双向BFS的起点和终点集合
            int step = 1;// 起始步数为1
            beginSet.add(beginWord);
            endSet.add(endWord);
            // 当两端都没有搜索完成时继续搜索
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                // 存放下一轮要搜索的单词集合
                Set<String> nextSet = new HashSet<>();
                //每輪loop 遍歷beginSet, 從beginSet裡面的每一個word展開，看是否到達endset
                for (String word : beginSet) {
                    char[] wordChar = word.toCharArray();
                    for (int i = 0; i < word.length(); i++) {
                        // 尝试改变每一个字符
                        for (char c = 'a'; c <= 'z'; c++) {
                            //先保存當前被替換的字符wordChar[i]
                            char pre = wordChar[i];
                            wordChar[i] = c;
                            String nextWord = new String(wordChar);
                            // 如果在endSet中找到该单词，说明路径连通，返回步数
                            if (endSet.contains(nextWord)) {
                                return step + 1;
                            }
                            //nextWord沒有被訪問過，且存在字典中
                            if (visted.add(nextWord) && wordSet.contains(nextWord)) {
                                // 如果新单词存在于字典中且未被访问过，将其加入下一轮的集合
                                nextSet.add(nextWord);
                            }
                            //還原當前替換的字符，繼續用下一個新的字符替換後進行比較
                            wordChar[i] = pre;
                        }
                    }
                }
                // 为了提高效率，总是从较小的集合开始搜索
                if (endSet.size() < nextSet.size()) {
                    System.out.printf("endSet: %s%n", endSet.toString());
                    System.out.printf("nextSet: %s%n", nextSet.toString());

                    beginSet = endSet;
                    endSet = nextSet;
                } else {
                    System.out.printf("endSet: %s%n", endSet.toString());
                    System.out.printf("nextSet: %s%n", nextSet.toString());
                    beginSet = nextSet;
                }
                // 增加搜索深度
                step++;
            }
            // 如果无法连通，返回0
            return 0;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}