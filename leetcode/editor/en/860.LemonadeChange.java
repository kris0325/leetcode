import java.util.HashMap;

/**
 * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue
 * to buy from you and order one at a time (in the order specified by bills). Each
 * customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction
 * is that the customer pays $5.
 * <p>
 * Note that you do not have any change in hand at first.
 * <p>
 * Given an integer array bills where bills[i] is the bill the iᵗʰ customer pays,
 * return true if you can provide every customer with the correct change, or false
 * otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: bills = [5,5,5,10,20]
 * Output: true
 * Explanation:
 * From the first 3 customers, we collect three $5 bills in order.
 * From the fourth customer, we collect a $10 bill and give back a $5.
 * From the fifth customer, we give a $10 bill and a $5 bill.
 * Since all customers got correct change, we output true.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: bills = [5,5,10,10,20]
 * Output: false
 * Explanation:
 * From the first two customers in order, we collect two $5 bills.
 * For the next two customers in order, we collect a $10 bill and give back a $5
 * bill.
 * For the last customer, we can not give the change of $15 back because we only
 * have two $10 bills.
 * Since not every customer received the correct change, the answer is false.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= bills.length <= 10⁵
 * bills[i] is either 5, 10, or 20.
 * <p>
 * <p>
 * Related Topics Array Greedy 👍 2388 👎 165
 */
       
/*
 2024-07-22 17:34:20
 860.柠檬水找零
 Lemonade Change
Category	Difficulty	Likes	Dislikes
algorithms	Easy (53.25%)	2388	165
Tags
design | queue

Companies
Unknown
*/

class LemonadeChange {
    public static void main(String[] args) {
        Solution solution = new LemonadeChange().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //greedy：
        // case1: bill[i] ==5 收取 $5
        // case2: bill[i] ==10 收取 $10, give change $5
        // case3: bill[i] ==20 收取$20，因為 $5零錢可以有更多組合，所以根據greedy算法，優先消耗$10 +$5組合零錢， 沒有$10 +$5的組合，再考慮3個$5
        public boolean lemonadeChange(int[] bills) {
            HashMap<Integer, Integer> currency2Count = new HashMap<>();
            Boolean result = true;
            Integer FIVE_CURRENCY = 5;
            Integer TEN_CURRENCY = 10;
            Integer TWENTY_CURRENCY = 20;
            Integer fiveCount = 0;
            Integer tenCount = 0;
            Integer twentyCount = 0;

            for (int i = 0; i < bills.length; i++) {
                if (bills[i] == FIVE_CURRENCY) {
                    fiveCount++;
                } else if (bills[i] == TEN_CURRENCY) {
                    if (fiveCount >= 1) {
                        fiveCount--;
                        tenCount++;
                    } else {
                        result = false;
                    }
                } else if (bills[i] == TWENTY_CURRENCY) {
                    if (tenCount >= 1 && fiveCount >= 1) {
                        tenCount--;
                        fiveCount--;
                        twentyCount++;
                    } else if (fiveCount >= 3) {
                        fiveCount -= 3;
                        twentyCount++;
                    } else {
                        result = false;
                    }
                }
            }
            return result;
        }
    }

    class Solution1 {
        //greedy：
        // case1: bill[i] ==5 收取 $5
        // case2: bill[i] ==10 收取 $10, give change $5
        // case3: bill[i] ==20 收取$20，因為 $5零錢可以有更多組合，所以根據greedy算法，優先消耗$10 +$5組合零錢， 沒有$10 +$5的組合，再考慮3個$5
        public boolean lemonadeChange(int[] bills) {
            HashMap<Integer, Integer> currency2Count = new HashMap<>();
            Boolean result = true;
            Integer FIVE_CURRENCY = 5;
            Integer TEN_CURRENCY = 10;
            Integer TWENTY_CURRENCY = 20;

            for (int i = 0; i < bills.length; i++) {
                if (bills[i] == FIVE_CURRENCY) {
                    //收錢
                    currency2Count.put(FIVE_CURRENCY, currency2Count.getOrDefault(FIVE_CURRENCY, 0) + 1);
                } else if (bills[i] == TEN_CURRENCY) {
                    if (currency2Count.getOrDefault(FIVE_CURRENCY, 0) >= 1) {
                        //找零 1張$5
                        currency2Count.put(FIVE_CURRENCY, currency2Count.get(FIVE_CURRENCY) - 1);
                        //收錢
                        currency2Count.put(TEN_CURRENCY, currency2Count.getOrDefault(TEN_CURRENCY, 0) + 1);
                    } else {
                        result = false;
                    }
                } else if (bills[i] == 20) {
                    if (currency2Count.getOrDefault(TEN_CURRENCY, 0) >= 1 && currency2Count.getOrDefault(FIVE_CURRENCY, 0) >= 1) {
                        //找零 1張$5 +1張$10
                        currency2Count.put(TEN_CURRENCY, currency2Count.get(TEN_CURRENCY) - 1);
                        currency2Count.put(FIVE_CURRENCY, currency2Count.get(FIVE_CURRENCY) - 1);
                        //收錢
                        currency2Count.put(TWENTY_CURRENCY, currency2Count.getOrDefault(TWENTY_CURRENCY, 0) + 1);
                    } else if (currency2Count.getOrDefault(FIVE_CURRENCY, 0) >= 3) {
                        //找零 3張$5
                        currency2Count.put(FIVE_CURRENCY, currency2Count.get(FIVE_CURRENCY) - 3);
                        //收錢
                        currency2Count.put(TWENTY_CURRENCY, currency2Count.getOrDefault(TWENTY_CURRENCY, 0) + 1);
                    } else {
                        result = false;
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}