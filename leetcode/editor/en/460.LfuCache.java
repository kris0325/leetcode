import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * <p>
 * Implement the LFUCache class:
 * <p>
 * <p>
 * LFUCache(int capacity) Initializes the object with the capacity of the data
 * structure.
 * int get(int key) Gets the value of the key if the key exists in the cache.
 * Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or
 * inserts the key if not already present. When the cache reaches its capacity, it should
 * invalidate and remove the least frequently used key before inserting a new item.
 * For this problem, when there is a tie (i.e., two or more keys with the same
 * frequency), the least recently used key would be invalidated.
 * <p>
 * <p>
 * To determine the least frequently used key, a use counter is maintained for
 * each key in the cache. The key with the smallest use counter is the least
 * frequently used key.
 * <p>
 * When a key is first inserted into the cache, its use counter is set to 1 (due
 * to the put operation). The use counter for a key in the cache is incremented
 * either a get or put operation is called on it.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
 * "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * <p>
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is
 * most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest,
 * invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 * // cache=[4,3], cnt(4)=2, cnt(3)=3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= capacity <= 10⁴
 * 0 <= key <= 10⁵
 * 0 <= value <= 10⁹
 * At most 2 * 10⁵ calls will be made to get and put.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Related Topics Hash Table Linked List Design Doubly-Linked List 👍 5769 👎 332
 */
       
/*
 2024-10-22 16:53:51
*/

class LfuCache {
    public static void main(String[] args) {
        Solution solution = new LfuCache().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {
        //LRU基于访问頻率
        //LFU适合较平稳的、有"冷热"数据区分的场景
        //LFU使用多个哈希表 實現
        //緩存容量
        private int capacity;
        //當前最小頻率
        private int minFreq;
        //存儲key 到node的映射
        private Map<Integer, Node> cache;
        //存儲頻率到該頻率下的節點列表的映射

        //LinkedHashSet 相當與双向链表的功能的。用於實現：提供接近 O(1) 的查找、插入和删除性能。
        // 新节点是从尾部（末尾）添加的。
        //删除操作是从头部（开始）进行的。
        private Map<Integer, LinkedHashSet<Node>> freqMap;


        public LFUCache(int capacity) {
            this.capacity = capacity;
            //全局最小頻率，便於o(1)刪除操作
            this.minFreq = 0;
            this.cache = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        public int get(int key) {
            //沒命中緩存
            if (!cache.containsKey(key)) {
                return -1;
            }
            //命中緩存
            Node node = cache.get(key);
            //更新頻率
            updateFreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) return;
            Node node = cache.get(key);
            //命中緩存
            if (node != null) {
                node.value = value;
                updateFreq(node);
            } else {
                //如果緩存滿了，則先基於頻率刪除最不經常使用的，即minFreq
                if (cache.size() == capacity) {
                    Node removed = removeMinFreqNode();
                    cache.remove(removed.key);
                }
                //再添加新節點
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                //新加入了節點，所以更新最少使用頻率為1
                minFreq = 1;
            }
        }

        private Node removeMinFreqNode() {
            LinkedHashSet<Node> set = freqMap.get(minFreq);
            //这行代码从LinkedHashSet中移除并返回第一个元素。LinkedHashSet的iterator()方法返回的迭代器按插入顺序遍历元素，
            // 所以next()会返回最早插入的元素。
            // 即刪除頭部元素
            Node removed = set.iterator().next();
            set.remove(removed);
            //當前值的minFreq set為空
            if (set.isEmpty()) {
                freqMap.remove(minFreq);
            }
            return removed;
        }

        private void updateFreq(Node node) {
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            //移除當前節點老的頻率紀錄
            set.remove(node);
            if (freq == minFreq && set.isEmpty()) {
                //維護最小頻率的更新，如果minFreq set空了，則需要將minFreq++設為最小頻率
                minFreq++;
            }
            //更新當前節點頻率
            node.freq++;
            addNode(node);
        }

        private void addNode(Node node) {
            //这段代码将新节点添加到对应频率的LinkedHashSet中。LinkedHashSet维护插入顺序，
            // 所以新节点会被添加到该集合的末尾。
            freqMap.computeIfAbsent(node.freq, key -> new LinkedHashSet<>())
                    .add(node);
        }

        class Node {
            int key;
            int value;
            int freq;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                freq = 1;
            }

        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}