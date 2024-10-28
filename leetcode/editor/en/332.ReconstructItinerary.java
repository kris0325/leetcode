import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi]
 * represent the departure and the arrival airports of one flight. Reconstruct the
 * itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary
 * must begin with "JFK". If there are multiple valid itineraries, you should
 * return the itinerary that has the smallest lexical order when read as a single
 * string.
 * <p>
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than [
 * "JFK", "LGB"].
 * <p>
 * <p>
 * You may assume all tickets form at least one valid itinerary. You must use all
 * the tickets once and only once.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],[
 * "ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL",
 * "SFO"] but it is larger in lexical order.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 * <p>
 * <p>
 * Related Topics Depth-First Search Graph Eulerian Circuit 👍 5869 👎 1865
 */
       
/*
 2024-07-05 11:56:44

 Reconstruct Itinerary
Category	Difficulty	Likes	Dislikes
algorithms	Hard (43.25%)	5869	1865
Tags
depth-first-search | graph

Companies
google

You are given a list of airline tickets where tickets[i] = [fromi, toi]
represent the departure and the arrival airports of one flight.
Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus,
the itinerary must begin with "JFK".
If there are multiple valid itineraries,
 you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.


*/
class ReconstructItinerary {
    public static void main(String[] args) {
        Solution solution = new ReconstructItinerary().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    //Time Limit Exceeded
    class Solution {
        //backtracking: treemap 优化时间复杂度
        // Map<出发机场, Map<到达机场,机票张数>>arrivalAirportMap
        // Map<到达机场,机票张数>使用treeMap，实现对达到机场按字典顺序排序
        Map<String, Map<String, Integer>> arrivalAirportMap;
        List<String> result = new LinkedList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            arrivalAirportMap = new HashMap<String, Map<String, Integer>>();
            sortTickets(tickets, arrivalAirportMap);
            result.add("JFK");
            backtracking(tickets.size());
            return result;


        }

        public void sortTickets(List<List<String>> tickets, Map<String, Map<String, Integer>> arrivalAirportMap) {
            for (List<String> ticket : tickets) {
                // Map<到达机场,机票张数>使用treeMap，实现对达到机场按字典顺序排序
                Map<String, Integer> arrivalTmptMap = new TreeMap<>();
                if (arrivalAirportMap.containsKey(ticket.getFirst())) {
                    arrivalTmptMap = arrivalAirportMap.get(ticket.getFirst());
                    arrivalTmptMap.put(ticket.getLast(), arrivalTmptMap.getOrDefault(ticket.getLast(), 0) + 1);
                } else {
                    arrivalTmptMap.put(ticket.getLast(), 1);
                }
                arrivalAirportMap.put(ticket.getFirst(), arrivalTmptMap);
            }
        }

        public boolean backtracking(Integer totalTicketNums) {
            if (result.size() == totalTicketNums + 1) {
                return true;
            }
            //遍歷Map<到达机场,机票张数>使用treeMap，當機票數不為0，才可繼續尋找路徑
            //所以需要先判斷路徑是否正確，即result.getLast()到達機場必須存在arrivalAirportMap中
            if (arrivalAirportMap.containsKey(result.getLast())) {
                for (Map.Entry<String, Integer> departureAirport : arrivalAirportMap.get(result.getLast()).entrySet()) {
                    int ticketNum = departureAirport.getValue();
                    if (ticketNum > 0) {
                        result.add(departureAirport.getKey());
                        departureAirport.setValue(ticketNum - 1);
                        if (backtracking(totalTicketNums)) {
                            return true;
                        }
                        result.removeLast();
                        departureAirport.setValue(ticketNum);
                    }
                }
            }
            return false;

        }
    }


    //Time Limit Exceeded
    class Solution2 {
        //backtracking:
        List<String> result;
        List<String> path = new LinkedList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            path.add("JFK");
            //先排序，以便得到的result 以便满足：return the itinerary that has the smallest lexical order when read as a single string
            Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
            //记录当前ticket是否使用过
            boolean[] isCurrentTicketUsed = new boolean[tickets.size()];
            backtracking(tickets, isCurrentTicketUsed);
            return result;

        }

        // backtracking 返回值 boolean 表示是否寻找到路径
        public boolean backtracking(List<List<String>> tickets, boolean[] isCurrentTicketUsed) {
            //terminate condition
            if (path.size() == tickets.size() + 1) {
                result = new LinkedList<>(path);
                return true;
            }
            for (int i = 0; i < tickets.size(); i++) {
                //当前tickets.get(i)没使用，且path路径的最后一个机场与当前tickets.get(i)的出发点机场是同一个，则为有效路径，加入path
                if (!isCurrentTicketUsed[i] && tickets.get(i).getFirst().equals(path.getLast())) {
                    isCurrentTicketUsed[i] = true;
                    //将当前机票上的到达地机场加入path
                    path.add(tickets.get(i).getLast());
                    if (backtracking(tickets, isCurrentTicketUsed)) {
                        return true;
                    }
                    //回溯 重置状态
                    path.removeLast();
                    isCurrentTicketUsed[i] = false;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}