import java.util.*;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely
 * and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each
 * move consists of turning one wheel one slot.
 * <p>
 * The lock initially starts at '0000', a string representing the state of the 4
 * wheels.
 * <p>
 * You are given a list of deadends dead ends, meaning if the lock displays any
 * of these codes, the wheels of the lock will stop turning and you will be unable
 * to open it.
 * <p>
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it
 * is impossible.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201
 * " -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would
 * be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead
 * end "0102".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009
 * ".
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"],
 * target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 * <p>
 * <p>
 * Related Topics Array Hash Table String Breadth-First Search 👍 4779 👎 221
 */
       
/*
 2024-09-03 14:54:02
*/

class OpenTheLock {
    public static void main(String[] args) {
        Solution solution = new OpenTheLock().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //樸素BFS : 時間複雜度比較高

        /**
         * > 2024/09/03 17:30:09
         * Success:
         * Runtime:124 ms, faster than 40.55% of Java online submissions.
         * Memory Usage:50.4 MB, less than 39.21% of Java online submissions.
         */
        public int openLock(String[] deadends, String target) {
            Queue<String> queue = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();
            HashSet<String> dead = new HashSet<>(List.of(deadends));
            String start = "0000";
            if (dead.contains(start)) {
                return -1;
            }
            int steps = 0;
            queue.offer(start);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int l = 0; l < size; l++) {
                    String cur = queue.poll();
                    if (Objects.equals(cur, target)) {
                        return steps;
                    }
                    //cur 4位，每位有上撥 / 下撥 ，總共可以有8種newstring
                    for (int i = 0; i < 4; i++) {
                        for (int j = -1; j <= 1; j += 2) {
                            char[] curArr = cur.toCharArray();
                            char originalChar = curArr[i];
                            //上撥動
                            //curArr[i] = (char) ((curArr[i] - '0' + 1) % 10 + '0');
                            curArr[i] = originalChar == '9' ? '0' : (char) (originalChar + 1);
                            String up = new String(curArr);
                            if (!visited.contains(up) && !dead.contains(up)) {
                                queue.offer(up);
                                visited.add(up);
                            }
                            //下撥動， + 10回到原點： 因為curArr 已經上撥動了一步，所以先-1換原， 再下撥需要再 -1，即10-2 = 8，所以是+8
//                            curArr[i] = (char) ((curArr[i] - '0' + 8) % 10 + '0');
                            curArr[i] = originalChar == '0' ? '9' : (char) (originalChar - 1);
                            String down = new String(curArr);
                            if (!visited.contains(down) && !dead.contains(down)) {
                                queue.offer(down);
                                visited.add(down);
                            }
                        }
                    }
                }
                //每層遍歷完steps需要+1
                steps++;
            }
            return -1;
        }
    }


    /**
     * 在朴素的 BFS 实现中，空间的瓶颈主要取决于搜索空间中的最大宽度。
     * 那么有没有办法让我们不使用这么宽的搜索空间，同时又能保证搜索到目标结果呢？
     * <p>
     * 「双向 BFS」 可以很好的解决这个问题：
     * 同时从两个方向开始搜索，一旦搜索到相同的值，意味着找到了一条联通起点和终点的最短路径。
     * <p>
     * 对于「有解」、「有一定数据范围」同时「层级节点数量以倍数或者指数级别增长」的情况，「双向 BFS」的搜索空间通常只有「朴素 BFS」的空间消耗的几百分之一，甚至几千分之一。
     */
    class Solution2 {
        //雙向BFS:優化時間複雜度

        /**
         * > 2024/09/03 17:58:42
         * Success:
         * Runtime:61 ms, faster than 94.74% of Java online submissions.
         * Memory Usage:45.3 MB, less than 98.14% of Java online submissions.
         */
        public int openLock(String[] deadends, String target) {
            String start = "0000";
            Set<String> dead = new HashSet<>(List.of(deadends));
            if (dead.contains(start)) {
                return -1;
            }
            Set<String> begin = new HashSet<>(List.of(start));
            Set<String> end = new HashSet<>(List.of(target));
            int level = 0;
            while (!begin.isEmpty() && !end.isEmpty()) {
                Set<String> tmp = new HashSet<>();
                //BFS扩展begin
                for (String s : begin) {
                    if (end.contains(s)) {
                        //路徑連通，即找到最短路徑
                        return level;
                    }
                    if (dead.contains(s)) {
                        continue;
                    }
                    //本層已使用過，標記為 dead，当于对 BFS 进行剪枝。
                    dead.add(s);
                    StringBuilder sb = new StringBuilder(s);
                    //每位可以上/下撥動，總共會有8中可能
                    for (int i = 0; i < 4; i++) {
                        char c = s.charAt(i);
                        //up 需要考慮邊界條件 c == '9'，上撥懂後直接變為0
                        String up = sb.substring(0, i)
                                + (c == '9' ? 0 : c - '0' + 1)
                                + sb.substring(i + 1);
                        //down 需要考慮邊界條件 c == '0'，下撥懂後直接變為9
                        String down = sb.substring(0, i)
                                + (c == '0' ? 9 : c - '0' - 1)
                                + sb.substring(i + 1);
                        if (!dead.contains(up)) {
                            tmp.add(up);
                        }
                        if (!dead.contains(down)) {
                            tmp.add(down);
                        }
                    }
                }
                level++;
                //雙向BFS, 两个set每走一轮交换一次， 每次都交替从左/右进行BFS扩展（可以優化搜索空間，避免某一层元素过大）
                begin = end;
                end = tmp;
            }
            return -1;
        }
    }

    /**
     * 490.The Maze
     * follow up , 求到達終點的最短路徑，即505.The Maze II
     */
    class Solution490 {
        //對當前點按四個方向的BFS遍歷，判斷是否遇到destination( 對{x,y}坐標分別 +1/-1來表示， 向右,向左，向上，向下 走的方向 ）
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            //標記點（x,y坐標）是否被訪問過([maze.length][maze[0].length]的迷宮格子)
            boolean[][] visted = new boolean[maze.length][maze[0].length];
            //隊列存儲點{x,y}坐標
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(start);
            visted[start[0]][start[1]] = true;
            while (!queue.isEmpty()) {
                //當前點cur 的{x,y}坐標
                int[] cur = queue.poll();
                //判斷是否滿足終止條件 check condition
                if (cur[0] == destination[0] && cur[1] == destination[1]) {
                    return true;
                }
                //按四個方向遍歷點，並入隊列
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    //小球沒有撞牆前，一直沿著dir方向走，直到撞牆, (maze[x][y]==0表示當前點還是空格，可以繼續走，為1就表示當前點為牆)
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                    }
                    //小球撞牆後，需要回退一步到有效位置
                    x -= dir[0];
                    y -= dir[1];
                    if (!visted[x][y]) {
                        //新點則入隊列，進行下一輪尋找遍歷
                        queue.offer(new int[]{x, y});
                        visted[x][y] = true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 505.The Maze II
     * BFS在圖中dijkstra的pq實現應用
     * pq<x,y, distance> pq裡存從起點到當前坐標對應的最小距離
     */
    class Solution505 {
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int[][] distance = new int[maze.length][maze[0].length];
            //求最短路徑，所以初始話為Integer.MAX_VALUE
            for (int[] row : distance) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            //start位置的distance初始化為0
            distance[start[0]][start[1]] = 0;
            dijkstra(maze, start, distance);
            return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                    //從start無法到達destination
                    -1
                    //被賦值表示從start可以到達destination，且存的即最短路徑
                    : distance[destination[0]][destination[1]];
        }

        public void dijkstra(int[][] maze, int[] start, int[][] distance) {
            //pq<x,y, 從起點到當前坐標對應的最小距離distance> pq
            //升序排序，distance較小值排在隊列前面，有較高優先級，所以每次poll取最小distance（從起點到當前點坐標，會有多個distance，每次取最小）
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            pq.offer(new int[]{start[0], start[1], 0});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                //按4個方向找neighbors
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    //紀錄每個方向dir走多少步撞牆
                    int step = 0;
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                    }
                    //撞牆後，需要回退一步
                    x -= dir[0];
                    y -= dir[1];
                    //每次都更新從起點到當前點（x,y）的最短距離
                    if (distance[cur[0]][cur[1]] + step < distance[x][y]) {
                        distance[x][y] = distance[cur[0]][cur[1]] + step;
                        pq.offer(new int[]{x, y, distance[x][y]});
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}