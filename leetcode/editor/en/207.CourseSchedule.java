import java.util.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
 * bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * <p>
 * <p>
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * <p>
 * <p>
 * Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
 * ? 16347 👎 735
 */
       
/*
 2024-09-06 16:42:14
*/

class Job {
    String name;
    String cmd;
    List<Job> deps;

    public Job(String name, String cmd) {
        this.name = name;
        this.cmd = cmd;
        this.deps = new ArrayList<>();
    }
}

// Event class to represent an event with eventId, userId, and timestamp
class Event {
    String eventId;
    String userId;
    long timestamp; // Timestamp in milliseconds

    public Event(String eventId, String userId, long timestamp) {
        this.eventId = eventId;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}

class CourseSchedule {
    public static void main(String[] args) {
        //print joblist
//        Job job1 = new Job("Job1", "cmd1");
//        Job job2 = new Job("Job2", "cmd2");
//        Job job3 = new Job("Job3", "cmd3");
//        Job job4 = new Job("Job4", "cmd4");
//
//        job1.deps.add(job2);
//        job2.deps.add(job3);
//        job2.deps.add(job4);
//        List<Job> jobs = Arrays.asList(job1, job2, job3);
//
//        Solution solution = new CourseSchedule().new Solution();

//        solution.printAllJobs(jobs);


        //balanceAccounts
//        Solution solution = new CourseSchedule().new Solution();
//        int[] accounts = {3, 4, 9, 15, 20, 27, 28};
//        int threshold = 15;
//        System.out.println("Minimum transactions: " + solution.balanceAccounts(accounts, threshold));


        // Example events
//        List<Event> events = Arrays.asList(
//                new Event("event1", "user1", 1670000000000L),
//                new Event("event2", "user2", 1670000010000L),
//                new Event("event3", "user1", 1670000600000L),
//                new Event("event4", "user3", 1670000700000L)
//        );
//        Solution solution = new CourseSchedule().new Solution();
//
//        // Aggregate events by 1-minute intervals
//        Map<Long, Set<String>> result1Min = solution.aggregateEventByInterval(events, 1);
//        System.out.println("Aggregation by 10-minute intervals: " + result1Min);

        Map<String, Integer> accounts = new HashMap<>();
        accounts.put("Account1", 50);
        accounts.put("Account2", 200);
        accounts.put("Account3", 150);
        accounts.put("Account4", 300);
        accounts.put("Account5", 75);

        int threshold = 100;
        Solution solution = new CourseSchedule().new Solution();
        int result = solution.balanceAccounts(accounts, threshold);
        System.out.println("transactions:"+result);

    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution0 {
        //BFS

        /**
         * 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序
         * 。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
         * 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
         */
        //hashmap 保存圖graph邻接表
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //構建圖邻接表
            Map<Integer, List<Integer>> graph = new HashMap<>();
            //根據邊構建圖，計算入度
            int[] indegree = new int[numCourses];
            for (int i = 0; i < prerequisites.length; i++) {
                //這裡是用掛練表的方式保存圖adjacent list
                int end = prerequisites[i][0];
                int start = prerequisites[i][1];
                graph.computeIfAbsent(start, key -> new ArrayList<>()).add(end);
                //注意題中prerequisites元素數組【0，1】，定義圖的方向是從start 1指向 end 0，所以0的入度增加，
                indegree[end]++;
            }
            //找到有向圖的入口，即入度為0的點
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                //選擇入度為0的節點作為起點，入隊列，
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            //BFS topSort. 根據拓撲排序的順序，進行廣度優先搜索
            int count = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                count++;
                for (int neibor : graph.getOrDefault(cur, Collections.emptyList())) {
                    //上完一門課，入度-1，再判斷該節點入度是否為0，即是否還有
                    if (--indegree[neibor] == 0) {
                        queue.offer(neibor);
                    }
                }
            }
            //上的課程count 與總課程數是否相同，相同則表示可以can finish all courses
            return count == numCourses;
        }
    }

    class Solution2 {
        //BFS

        /**
         * 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序
         * 。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
         * 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
         */
        //arrylist 保存圖graph邻接表
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //构建邻接表，存放前置课程与对应后续课程的数组
            List<List<Integer>> adjacencyList = new ArrayList<>();
            //入度表，即当前课程对应的前置课程数量
            int[] indegree = new int[numCourses];
            //存入度为0，即前置课程为0的课程，可直接学习，入队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            //get the indegree of every course
            for (int[] cp : prerequisites) {
                //注意：根据题意,是cp[1] 指向cp[0]
                indegree[cp[0]]++;
                adjacencyList.get(cp[1]).add(cp[0]);
            }
            //get all courses with the indegree of 0
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            //BFS topSort.
            while (!queue.isEmpty()) {
                //前置课程pre
                int pre = queue.poll();
                //每学习一门前置课程，剩余课程--
                numCourses--;
                for (int cur : adjacencyList.get(pre)) {
                    if (--indegree[cur] == 0) {
                        queue.offer(cur);
                    }
                }
            }
            return numCourses == 0;
        }
    }


    /**
     * remitly : 1.題目描述:
     * 給定一個Job類,包含以下屬性:
     * name: 工作名稱
     * cmd: 工作執行的命令
     * deps: 該工作依賴的其他工作列表
     * <p>
     * 要求實現一個方法,打印出所有工作及其依賴關係的執行命令,要求:
     * 按照依賴關係順序打印
     * 不重複打印已打印過的工作
     * 處理循環依賴的情況，且確保了所有依賴工作都在當前工作之前被處理
     * <p>
     * Java解決方案:
     */
    public class Solution_remitly_printJobList {

        public void printAllJobs(List<Job> jobs) {
            //在全局範圍內，printed紀錄job是否被打印，避免重複打印
            Set<String> printed = new HashSet<>();
            for (Job job : jobs) {
                //visiting 是只紀錄當前路徑，避免循環依賴，所以使用局部遍歷,
                dfs(job, printed, new HashSet<>());
            }
        }

        private void dfs(Job job, Set<String> printed, Set<String> visiting) {
            if (printed.contains(job.name)) {
                return;
            }
            if (visiting.contains(job.name)) {
                System.out.println("Circular dependency detected: " + job.name);
                return;
            }

            visiting.add(job.name);
            //先處理打印job的依賴
            for (Job dep : job.deps) {
                dfs(dep, printed, visiting);
            }

            //再處理打印當前job
            System.out.println(job.cmd);
            printed.add(job.name);

            visiting.remove(job.name);
        }
    }

    /**
     * solution1.print jobs
     * backtracking
     */

    public class Solution_printJobs {
        Set<String> printed = new HashSet<>();

        public void printJobs(List<Job> jobs) {
            for (Job job : jobs) {
                dfs(job, printed, new HashSet<>());
            }
        }

        public void dfs(Job job, Set<String> printed, Set<String> visiting) {
            if (printed.contains(job.name)) {
                System.out.printf("printed" + job.name);
                return;
            }
            if (visiting.contains(job.name)) {
                System.out.printf("circular dependency " + job.name);
                return;
            }
            visiting.add(job.name);
            for (Job dep : job.deps) {
                dfs(dep, printed, visiting);
            }
            System.out.println(job.cmd);
            printed.add(job.name);
            visiting.remove(job.name);
        }
    }

    /**
     * 3.Balance account
     * 題目描述： 給定一組賬戶餘額和一個目標閾值（threshold），
     * 計算使所有賬戶餘額達到或超過閾值所需的最小交易次數。
     * Given an array of account balances and a target threshold,
     * the task is to calculate the minimum number of transactions needed to bring all account balances to at least the threshold.
     * If it's impossible (e.g., not enough rich accounts to balance poor accounts), return -1.
     */
    public class Solution_balanceAccounts {
        public static int balanceAccounts(int[] accounts, int threshold) {
            // Edge case: empty accounts
            if (accounts == null || accounts.length == 0) {
                return 0;
            }

            List<Integer> poorAccounts = new ArrayList<>();
            List<Integer> richAccounts = new ArrayList<>();
            int totalDeficit = 0, totalSurplus = 0;

            // Step 1: Categorize accounts and calculate total deficit and surplus
            for (int balance : accounts) {
                if (balance < threshold) {
                    poorAccounts.add(balance);
                    totalDeficit += (threshold - balance);  // Deficit needed to bring to threshold
                } else if (balance > threshold) {
                    richAccounts.add(balance);
                    totalSurplus += (balance - threshold);  // Surplus above threshold
                }
            }

            // Step 2: Check if balancing is possible
            if (totalSurplus < totalDeficit) {
                return -1;  // Not enough surplus to cover the deficit
            }

            int transactions = 0;
            int poorIndex = 0, richIndex = 0;

            // Step 3: Balance the accounts by transferring between rich and poor
            while (poorIndex < poorAccounts.size()) {
                if (richIndex >= richAccounts.size()) {
                    return -1;  // Rich accounts are depleted, but poor accounts remain
                }

                int poorBalance = poorAccounts.get(poorIndex);
                int richBalance = richAccounts.get(richIndex);

                // Calculate the transfer amount
                int transfer = Math.min(richBalance - threshold, threshold - poorBalance);

                // Perform the transaction
                poorBalance += transfer;
                richBalance -= transfer;

                // Update the balances in the lists
                //这2行，会导致Java oom?
                poorAccounts.set(poorIndex, poorBalance);
                richAccounts.set(richIndex, richBalance);

                // Check if the current poor account is balanced
                if (poorBalance == threshold) {
                    poorIndex++;  // Move to the next poor account
                }

                // Check if the current rich account is exhausted to the threshold
                if (richBalance == threshold) {
                    richIndex++;  // Move to the next rich account
                }

                // Count the transaction
                transactions++;
            }
            return transactions;
        }
    }


    /**
     * follow up 优化，求最小转账次数，并且打印转账路径
     * solution1.balance account
     * tow pointer + greedy
     * tc:o(n)
     */

    public class Solution {
        public  int balanceAccounts(Map<String, Integer> accounts, int threshold) {
            // Edge case: empty accounts
            if (accounts == null || accounts.size() == 0) {
                return 0;
            }

            List<Map.Entry<String, Integer>> poorAccounts = new ArrayList<>();
            List<Map.Entry<String, Integer>> richAccounts = new ArrayList<>();
            int totalDeficit = 0, totalSurplus = 0;

            // Step 1: Categorize accounts and calculate total deficit and surplus
            for (Map.Entry<String, Integer> entry : accounts.entrySet()) {
                int balance = entry.getValue();
                if (balance < threshold) {
                    poorAccounts.add(entry);
                    totalDeficit += (threshold - balance);  // Deficit needed to bring to threshold
                } else if (balance > threshold) {
                    richAccounts.add(entry);
                    totalSurplus += (balance - threshold);  // Surplus above threshold
                }
            }

            // Step 2: Check if balancing is possible
            if (totalSurplus < totalDeficit) {
                return -1;  // Not enough surplus to cover the deficit
            }

            int transactions = 0;
            int poorIndex = 0, richIndex = 0;

            // Step 3: Balance the accounts by transferring between rich and poor
            while (poorIndex < poorAccounts.size()) {
                if (richIndex >= richAccounts.size()) {
                    return -1;  // Rich accounts are depleted, but poor accounts remain
                }

                Map.Entry<String, Integer> poorAccount = poorAccounts.get(poorIndex);
                Map.Entry<String, Integer> richAccount = richAccounts.get(richIndex);

                String poorName = poorAccount.getKey();
                String richName = richAccount.getKey();

                int poorBalance = poorAccount.getValue();
                int richBalance = richAccount.getValue();

                // Calculate the transfer amount greedy
                //在每次转账操作中，程序都会从盈余账户 (richAccounts) 和缺口账户 (poorAccounts) 中选择当前能够转出的最大金额。
                // 这通过以下方式实现：
                //每次从当前的 richAccount 和 poorAccount 中转移的金额为 最小值：
                //richAccount 的盈余（即余额与阈值之差），以及
                //poorAccount 的缺口（即阈值与余额之差）。
                //这个过程可以确保：
                //每次尽可能减少一次交易操作的需求，因为最大可能的金额被转移，从而减少了后续需要的转账次数。
                //每个 poorAccount 在尽可能少的交易次数中被填满。
                //每个 richAccount 在尽可能少的交易中将余额降到阈值。
                int transfer = Math.min(richBalance - threshold, threshold - poorBalance);

                // Perform the transaction
                poorBalance += transfer;
                richBalance -= transfer;

                // Print the transaction details
                System.out.println("Transfer " + transfer + " from " + richName + " to " + poorName);

                // Update the balances in the accounts
                accounts.put(poorName, poorBalance);
                accounts.put(richName, richBalance);

                // Check if the current poor account is balanced
                if (poorBalance == threshold) {
                    poorIndex++;  // Move to the next poor account
                }

                // Check if the current rich account is exhausted to the threshold
                if (richBalance == threshold) {
                    richIndex++;  // Move to the next rich account
                }

                // Count the transaction
                transactions++;
            }
            return transactions;
        }

//        public static void main(String[] args) {
//            Map<String, Integer> accounts = new HashMap<>();
//            accounts.put("Account1", 50);
//            accounts.put("Account2", 200);
//            accounts.put("Account3", 150);
//            accounts.put("Account4", 300);
//            accounts.put("Account5", 75);
//
//            int threshold = 100;
//            int result = balanceAccounts(accounts, threshold);
//
//            System.out.println("Total transactions: " + result);
//        }
    }


    /**
     * 在 4.Remitly 的 SWE 面试中，这道题考察你如何聚合事件数据。假设给定一组事件数据（eventId、userId、timestamp），
     * 你需要将事件按分钟或每 10 分钟进行聚合。这个问题可以使用 HashMap 或 HashSet 进行事件的存储和聚合。
     * <p>
     * 题目描述：
     * 题目要求：
     * 给定一组事件，每个事件包含 eventId、userId 和 timestamp，要求将事件按分钟或者每 10 分钟进行聚合。
     * 可以使用 HashMap 来存储每个时间区间的事件信息，并使用 HashSet 进行去重。
     * <p>
     * eventId: 唯一标识事件
     * userId: 触发事件的用户
     * timestamp: 事件发生的时间，精确到毫秒级
     * 任务：
     * 编写一个方法，将这些事件按分钟或每 10 分钟进行聚合。
     * 聚合时，可以使用 HashMap 进行存储，其中 key 是时间段（例如某个时间点的分钟或者10分钟的分段）
     * ，value 是事件的集合（可以是 HashSet，用于存储去重的事件）。
     */
    public class Solution_aggregateEventByInterval {
        public Map<Long, Set<String>> aggregateEventByInterval(List<Event> events, int intervalMinutes) {
            //use a hashmap where the key is the start time of intervalMinutes, and value is a set of eventIds
            Map<Long, Set<String>> aggregatedEvents = new HashMap<>();
            //convert intervalMinutes into milliseconds
            long intervalMillis = intervalMinutes * 60 * 1000;

            for (Event event : events) {
                //calculate the interval start time,利用 / 向下取整，先除，再乘，可以得到對應間隔區間的開始時間
                long intervalStart = (event.timestamp / intervalMillis) * intervalMinutes;

                //add the eventId to the set by the given interval
                aggregatedEvents.computeIfAbsent(intervalStart, key -> new HashSet<>()).add(event.eventId);
            }
            return aggregatedEvents;
        }
    }

    /**
     * 題目描述：
     * 給定一個包含佔位符的字符串模板和一個或多個包含替換值的映射，實現一個函數來替換模板中的佔位符。
     * 例如：
     * 輸入：
     * 模板字符串: "this is a {bar} {foo}"
     * 替換映射1: {"bar": "temp"}
     * 替換映射2: {"foo": "str"}
     * 輸出：
     * "this is a temp str"
     */

    public class Solution_replacePlaceholders {

        public static String replacePlaceholders(String template, Map<String, String>... replacements) {
            String result = template;
            // 定義正則表達式模式，匹配 {xxx} 形式的佔位符
            Pattern pattern = Pattern.compile("\\{(.*?)\\}");

            // 遍歷所有替換映射
            for (Map<String, String> replacement : replacements) {
                // 創建匹配器
                Matcher matcher = pattern.matcher(result);
                // 用於構建結果字符串
                StringBuffer sb = new StringBuffer();

                // 查找所有匹配項
                while (matcher.find()) {
                    // 獲取佔位符中的鍵（去掉大括號）
                    String key = matcher.group(1);
                    // 從替換映射中獲取值，如果沒有則保留原始佔位符
                    String value = replacement.getOrDefault(key, matcher.group(0));
                    // 將替換值添加到結果中，同時處理可能的特殊字符
                    matcher.appendReplacement(sb, Matcher.quoteReplacement(value));
                }
                // 添加剩餘的未匹配部分
                matcher.appendTail(sb);

                // 更新結果字符串，為下一個替換映射做準備
                result = sb.toString();
            }

            return result;
        }

//        public static void main(String[] args) {
//            String template = "this is a {bar} {foo}";
//            Map<String, String> replacement1 = Map.of("bar", "temp");
//            Map<String, String> replacement2 = Map.of("foo", "str");
//
//            String result = replacePlaceholders(template, replacement1, replacement2);
//            System.out.println(result);  // 輸出: this is a temp str
//        }
    }

    /**
     *     關鍵點解釋：
     *             Pattern.compile("\\{(.*?)\\}"):
     *     創建一個正則表達式模式，匹配 {xxx} 形式的佔位符。
     *             \\{ 和 \\} 匹配字面的大括號。
     *             (.*?) 匹配大括號內的任何字符（非貪婪模式）。
     *             matcher.find():
     *     在字符串中查找下一個匹配項。
     *             matcher.group(1):
     *     獲取第一個捕獲組的內容，即大括號內的文本。
     *             replacement.getOrDefault(key, matcher.group(0)):
     *     如果在替換映射中找到對應的值，則使用該值；否則保留原始佔位符。
     *             Matcher.quoteReplacement(value):
     *     處理替換值中可能存在的特殊字符（如 $ 或 \），確保它們被正確解釋為字面值。
     *             matcher.appendReplacement(sb, ...):
     *     將替換後的文本添加到 StringBuffer 中。
     *             matcher.appendTail(sb):
     *     添加剩餘的未匹配文本到 StringBuffer 中。
     *     這個方法通過逐個應用替換映射，高效地處理了模板中的所有佔位符。
     *     使用 StringBuffer 和 Matcher 的組合可以在處理大型字符串時提供良好的性能。
     * */


//leetcode submit region end(Prohibit modification and deletion)

}