//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }
}
//leetcode submit region end(Prohibit modification and deletion)


import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        int target = scanner.nextInt();
        int[] gun = new int[total];
        for (int i = 0; i < total; i++) {
            gun[i] = scanner.nextInt();
        }
        for (int j = 0; j < target; j++) {
            map.put(j + 1, 1);
        }

        int left = 0;
        int count = target;
        int result = 0;
        int min = total;
        for (int right = 0; right < total; right++) {
            if (map.containsKey(gun[right])) {
                map.put(gun[right], map.get(gun[right]) - 1);
                if (map.get(gun[right]) >= 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (right - left < min) {
                    min = right - left;
                }
                if (map.containsKey(gun[left])) {
                    map.put(gun[left], map.get(gun[left]) + 1);
                    if (map.get(gun[left]) > 0) count++;
                }
                left++;
            }
        }
        System.out.println(min == total ? -1 : min + 1);

    }

}