import java.util.*;

import static java.util.Arrays.copyOfRange;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in
 * ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: lists = []
 * Output: []
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * k == lists.length
 * 0 <= k <= 10⁴
 * 0 <= lists[i].length <= 500
 * -10⁴ <= lists[i][j] <= 10⁴
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10⁴.
 * <p>
 * <p>
 * Related Topics Linked List Divide and Conquer Heap (Priority Queue) Merge Sort
 * 👍 19645 👎 724
 */
       
/*
 2024-09-17 15:45:26
*/

class MergeKSortedLists {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 2, 1};
        int[] arr = {3, 1, 2};

        Solution solution = new MergeKSortedLists().new Solution();
//        System.out.printf("count:" + solution.countInversion(arr));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        //pq 迭代法实现
        //pq 维护一个大小为k的最小堆，每次取堆顶元素，为k个元素中的最小值
        /**
         * @param lists
         * @return ListNode
         * 复杂度分析
         * 时间复杂度：考虑优先队列中的元素不超过 k 个，那么插入和删除的时间代价为 O(logk)，这里最多有 kn 个点，对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn×logk)。
         * 空间复杂度：这里用了优先队列，优先队列中的元素不超过 k 个，故渐进空间复杂度为 O(k)。
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode dummy = new ListNode(0);
            PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
            //遍歷練表lists, 取每個練表的第一個元素加入queue
            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }
            ListNode cur = dummy;
            while (!pq.isEmpty()) {
                //取出對頂元素
                cur.next = pq.poll();
                //更新cur
                cur = cur.next;
                if (pq.isEmpty()) {
                    //最後一個list，無須遍歷，直接接到cur後面即可
                    break;
                }
                //如果取出的當前節點下一個節點不為null，則將其入隊列，排序
                if (cur.next != null) {
                    pq.offer(cur.next);
                }
            }
            return dummy.next;
        }
    }

    class Solution {
        //基于divide and conquer 思想： 類似merge sort ，先partition，直到变为2个链表，
        // 再递归调用合并2个有序链表兩兩比較，再合併結果
        //tc：o(nlogn)
        /**
         * 时间复杂度: O(N log k)，其中 N 是所有链表节点的总数，k 是链表的数量。每次合并两个链表的时间复杂度为 O(N)，而我们需要进行 log k 次合并。
         * 空间复杂度: O(log k)，用于递归调用的栈空间。
         * */
        public ListNode mergeKLists(ListNode[] lists) {
            return partition(lists, 0, lists.length - 1);
        }

        //遞歸divide， 直到分到子list只有一個元素， 然後兩兩一組比較，merge,
        public ListNode partition(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            if (left < right) {
                int mid = (left + right) / 2;
                ListNode l1 = partition(lists, left, mid);
                ListNode l2 = partition(lists, mid + 1, right);
                return mergeSort(l1, l2);
            }
            return null;
        }

        //合并2个有序链表
        public ListNode mergeSort(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeSort(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeSort(l1, l2.next);
                return l2;
            }
        }
    }

    /**
     * 計算數組中的逆序對
     */
    public class Solution_countInversion {
        public long countInversion(int[] arr) {
            return mergeSortAndCount(arr, 0, arr.length - 1);
        }

        int mergeSortAndCount(int[] arr, int left, int right) {
            int mid = 0;
            int invCount = 0;
            if (left < right) {
                mid = left + (right - left) / 2;
                //遞歸計算左半部分與右半部分的逆序對
                //注意：1.下面2行執行完成後，left內部已經完成排序與統計, 同理right也是，
                invCount += mergeSortAndCount(arr, left, mid);
                invCount += mergeSortAndCount(arr, mid + 1, right);
                //歸併計數跨越逆序對，2然後只需進行外部比較，即left 與right分別比較
                invCount += mergeAndCountHelper(arr, left, mid, right);
            }
            return invCount;
        }

        int mergeAndCountHelper(int[] arr, int left, int mid, int right) {
            int[] leftArr = new int[mid - left + 1];
            //right-mid = right - (mid+1) + 1
            int[] rightArr = new int[right - mid];
            //根據入參數索引，構建零時的左數組，右數組
            leftArr = Arrays.copyOfRange(arr, left, mid + 1);
            rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
            int i = 0, j = 0, k = left;
            int invCount = 0;
            //合併兩個子數組並計算逆序對
            while (i < leftArr.length && j < rightArr.length) {
                if (leftArr[i] <= rightArr[j]) {
                    arr[k++] = leftArr[i++];
                } else {
                    arr[k++] = rightArr[j++];
                    //leftArr[i] > rightArr[j], left經過排序後，那麼可以得出結論：left內部的當前元素到末尾元素均滿足這個條件，
                    // 即可以和rightArr[j]構成逆序對，
                    //所以總共的逆序對計算公式則為： (mid+1) - (left+i)
                    invCount += (mid + 1) - (left + i);
                }
            }
            //右邊數組先比較完，直接複製左邊數組到 arr
            while (i < leftArr.length) {
                arr[k++] = leftArr[i++];
            }
            //左邊數組先比較完，直接複製右邊數組 arr
            while (j < rightArr.length) {
                arr[k++] = rightArr[j++];
            }
            return invCount;
        }
    }

    /**
     * 計算平面上距離最短的點對
     */

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Solution_findClosestPair {
        //divide and conquer
        public long findClosestPair(int n, int[] xs, int[] ys) {
            int legth = xs.length;
            Point[] points = new Point[legth];
            for (int i = 0; i < legth; i++) {
                points[i] = new Point(xs[i], ys[i]);
            }
            return divide(points, 0, legth - 1);
        }

        public Long divide(Point[] points, int left, int right) {
            long cuMinDis = Integer.MAX_VALUE;
            //分成只有1個點
            if (left == right) {
                return cuMinDis;
            }
            //分成只有2個點
            if (left + 1 == right) {
                return distance(points[left], points[right]);
            }
            int mid = left + (right - left) / 2;
            //step1.遞歸分別求左，右兩邊最小距離點對
            long leftMinDis = divide(points, left, mid);
            long rightMinDis = divide(points, mid, right);
            cuMinDis = Math.min(leftMinDis, rightMinDis);

            //step2.中間帶的最小距離的點對 即一個點落在左邊，一個點落在右邊
            //即求兩個點只能落的中間區間滿足條件： 其x軸的範圍是：  [mid-cuMinx, mid+cuMinx]
            //可以基於y坐標排序，然後計算相鄰兩點的最小距離即為所求 ，注意：是o(n)時間複雜度
            //紀錄搜索區間的可能為最小距離點的索引，便於進一步計算最小距離
            List<Integer> possiblePointIndex = new ArrayList<>();
            //中間地帶 其x軸的範圍是： [mid-cuMinx, mid+cuMinx]
            for (int i = left; i <= right; i++) {
                //如果區間內兩個點的x坐標差的平方大於cuMinDis，則沒必要計算了，。所以只需收集小於等於的點
                // 因為 dis的平方 = x坐標差的平方+ y坐標差的平方，那麼他們的距離肯定大於dis的平方
                if (Math.pow(points[mid].x - points[i].x, 2) <= cuMinDis) {
                    possiblePointIndex.add(i);
                }
            }
            //對中間地帶 可能的點，按照y坐標升序排序，然後每2個點計算距離，再比較求最小值
            //由於有continue過濾，所以時間複雜度不是o(n2), 而是o(n)
            Collections.sort(possiblePointIndex, (a, b) -> points[a].y - points[b].y);
            for (int i = 0; i < possiblePointIndex.size(); i++) {
                for (int j = i + 1; j < possiblePointIndex.size(); j++) {
                    //如果區間內兩個點的y坐標差的平方大於cuMinDis，則沒必要計算了，跳過。
                    // 因為 dis的平方 = x坐標差的平方+ y坐標差的平方,那麼他們的距離肯定大於dis的平方
                    if (Math.pow(Math.abs(points[possiblePointIndex.get(i)].y
                            - points[possiblePointIndex.get(j)].y), 2) > cuMinDis) {
                        continue;
                    }
                    long temDis = distance(points[i], points[j]);
                    cuMinDis = Math.min(cuMinDis, temDis);
                }
            }
            return cuMinDis;
        }

        public long distance(Point p1, Point p2) {
            return (long) (p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x);
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

}