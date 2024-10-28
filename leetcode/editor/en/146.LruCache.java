import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (
 * LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise
 * return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache. If the number of keys exceeds the
 * capacity from this operation, evict the least recently used key.
 * <p>
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 10⁴
 * 0 <= value <= 10⁵
 * At most 2 * 10⁵ calls will be made to get and put.
 * <p>
 * <p>
 * Related Topics Hash Table Linked List Design Doubly-Linked List 👍 20844 👎 105
 * 0
 */
       
/*
 2024-08-21 18:06:23
 LRU Cache
Category	Difficulty	Likes	Dislikes
algorithms	Medium (42.43%)	20844	1050
Tags
design

Companies
amazon | bloomberg | facebook | google | microsoft | palantir | snapchat | twitter | uber | yahoo | zenefits
*/

class LruCache {
    public static void main(String[] args) {
//        Solution solution = new LruCache().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        //https://www.youtube.com/watch?v=q0UrgXS5lnk&list=PLbaIOC0vpjNU8ldi-uUxkQ2GSeZm4wf7D&index=11
        //时间窗口映射Timed Window Map
        //LRU基于访问时间，LRU适合突发性的、周期性的批量操作
        //Hashmap 存<key, Node> 存儲緩存 實現 get,put o(1)操做
        //Double Linked Node 雙鍊錶 維護Node的使用的新舊，從head到tail,表示從最久使用到最近使用
        class Node {
            int val;
            int key;
            Node pre;
            Node next;

            public Node(int key, int val) {
                this.val = val;
                this.key = key;
            }
        }

        Map<Integer, Node> cache;
        //表示連接最久使用的節點
        Node head;
        //表示連接最近使用的節點
        Node tail;
        int size;
        int capacity;

        //初始化LRUCache
        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node cur = cache.get(key);
            //更新当前节点为最新使用，即移動到練表末尾:
            //1.斷開表示當前的節點位置
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            //2.移動到最近使用節點
            moveToTail(cur);
            return cur.val;
        }

        //當前使用的節點插入到tail節點前
        private void moveToTail(Node cur) {
            cur.next = tail;
            cur.pre = tail.pre;
            tail.pre.next = cur;
            tail.pre = cur;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node existNode = cache.get(key);
                existNode.val = value;
                //復用get
                get(key);
                return;
            }
            //cache容量滿，evict最少使用node
            if (size == capacity) {
                Node lruNode = head.next;
                //移除lruNode節點的頻率紀錄
                lruNode.next.pre = lruNode.pre;
                lruNode.pre.next = lruNode.next;
                //移除節點
                cache.remove(lruNode.key);
                size--;
            }
            //寫入cache
            Node newNode = new Node(key, value);
            cache.put(newNode.key, newNode);
            size++;
            //紀錄頻率
            moveToTail(newNode);
        }
    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */


    /**
     * WindowedMap:(LRU变种 从尾部插入， 从头部取出最早插入的元素判断是否失效)
     * IMplement WindowedMap: key:value store , where entries get expired after certain time EXPIRY_TIME (pre configured value)
     * three API calls
     * get(key)
     * put(key, val)
     * get_average() -> average of Yall the values (non-expired)
     * The data would be streaming in increasing timestamp
     * They put heavy focus on concurrency ,
     * <p>
     * LinkedHashMap +
     */

    class WindowedMap<K, V extends Number> {
        //存储键值对时间戳
        class Node<V> {
            V val;
            Long timesstamp;

            Node(V val, Long timesstamp) {
                this.val = val;
                this.timesstamp = timesstamp;
            }
        }
        //过期时间
        private final long EXPIRY_TIEM;
        //按插入顺序存储键值对，    //因为链表是按照插入顺序排序的，头部为最老节点，尾部为最新节点，所以头部之后的条目不会过期
        private final LinkedHashMap<K, Node<V>> linkedHashMap;
        private final AtomicLong totalVal = new AtomicLong(0);
        private final AtomicLong count = new AtomicLong(0);

        public WindowedMap(Long expiryTime) {
            EXPIRY_TIEM = expiryTime;
            // LinkedHashMap with access order set to false (insertion order)
            this.linkedHashMap = new LinkedHashMap<K, Node<V>>(16, 0.75f, false) {
                protected boolean removeEldestEntry(Map.Entry<K, Node<V>> eldest) {
                    // Automatically remove expired entries based on expiry time
                    return (System.currentTimeMillis() - eldest.getValue().timesstamp > EXPIRY_TIEM);
                }
            };
        }


        //插入键值对
        public synchronized void put(K key, V val){
            long now = System.currentTimeMillis();
            Node<V> oldNode = linkedHashMap.remove(key);
            //如果鍵存在，则先移除，更新totalVal，count
            if(null != oldNode){
                totalVal.addAndGet(-oldNode.val.longValue());
                count.decrementAndGet();
            }
            //插入新节点到队列尾部 //注意：compile error linkedHashMap.put(key,val);
            linkedHashMap.put(key, new Node<>(val, now));
            totalVal.addAndGet(val.longValue());
            count.incrementAndGet();
            //处理过期条目
            cleanUpExpiredEntries();

        }

        //获取key对应的val
        public synchronized V get(K key){
            //处理过期条目
            cleanUpExpiredEntries();

            Node<V> node = linkedHashMap.get(key);
            return node == null? null : node.val;
        }

        //计算average
        public synchronized double getAverage(){
            //处理过期条目
            cleanUpExpiredEntries();

            return count.get() == 0
                    ? 0.0
                    : (double) totalVal.longValue()/count.longValue();
        }

        public void  cleanUpExpiredEntries(){
            long currentTime = System.currentTimeMillis();
            Iterator<Map.Entry<K, Node<V>>> iterator = linkedHashMap.entrySet().iterator();

            while (iterator.hasNext()){
                Map.Entry<K, Node<V>> entry = iterator.next();
                Node<V> node = entry.getValue();

                if((currentTime -node.timesstamp)> EXPIRY_TIEM){
                    iterator.remove();
                    totalVal.addAndGet(-node.val.longValue());
                    count.decrementAndGet();
                } else {
                    //因为链表是按照插入顺序排序的，头部为最老节点，尾部为最新节点，所以头部之后的条目不会过期
                    break;
                }

            }
        }
    }

    /** confluent coding
     * You are given a list of documents with id and text.
     * Eg :-
     * DocId, Text
     * 1, "Cloud computing is the on-demand availability of computer system resources."
     * 2, "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems."
     * 3, "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers."
     *
     * Search a given phrase in all the documents in a efficient manner. Assume that you have more than 1 million docs.
     * Eg :-
     * search("cloud") >> This should output [1,2,3]
     * search("cloud monitoring") >> This should output [2]
     * search("Cloud computing is") >> This should output [1,3]
     * 为了在超过100万份文档中高效地搜索单词 / 短语，一个常见的方法是预处理数据，构建倒排索引（搜索引擎中常用的技术）。倒排索引将词或短语映射到包含它们的文档ID上，从而在搜索时可以快速检索到相关文档。
     *
     * 以下是使用HashMap实现倒排索引的解决方案，其中键是单词或短语，值是包含该单词或短语的文档ID集合。预处理步骤构建索引，搜索查询通过索引快速查找相关文档。
     * */

    class DocumentWordSearch{
        // 倒排索引：用于存储单词或短语到文档ID的映射
        // Inverted index to store word -> document IDs mapping
        private final Map<String, Set<Integer>>invertedIndex = new HashMap<>();


        // Method to add documents to the inverted index
        public void addDocument(int docId, String text){
            String[] words  = text.toLowerCase().split("\\s+");
            for (int i = 0; i< words.length;i++){
                invertedIndex.computeIfAbsent(words[i], k-> new HashSet<>()).add(docId);
            }
        }

        // Method to search for a phrase and return matching document IDs
        public List<Integer> search(String query){
            String normalizedQuery = query.toLowerCase();
            Set<Integer> docIds = invertedIndex.getOrDefault(normalizedQuery, Collections.emptySet());
            return new ArrayList<>(docIds);
        }
    }

    class DocumentPhraseSearch{
        // Inverted index to store phrase -> document IDs mapping
        private final Map<String, Set<Integer>>invertedIndex = new HashMap<>();

        // Method to add documents to the inverted index
        public void addDocument(int docId, String text){
            //Tokenize the text into words
            String[] words= text.toLowerCase().split("\\s+");
            for (int i=0; i< words.length; i++){
                //// Build phrases by progressively adding more words
                for (int j =i ;j<words.length ; j++){
                    String phrase = String.join(" ",Arrays.copyOfRange(words,i,j+1));
                    //// Add the phrase to the inverted index
                    invertedIndex.computeIfAbsent(phrase, k -> new HashSet<>()).add(docId);
                }
            }
        }

        // Method to search for a phrase and return matching document IDs
        public List<Integer> search(String query){
            String normalizedQuery = query.toLowerCase();
            Set<Integer> docIds = invertedIndex.getOrDefault(normalizedQuery, Collections.emptySet());
            return new ArrayList<>(docIds);
        }
    }


    /**
     * isVariadic
     *
     * */

    class Function {
        String name;
        List<String> argumentTypes;
        boolean isVariadic;

        Function(String name, List<String> argumentTypes, boolean isVariadic) {
            this.name = name;
            this.argumentTypes = argumentTypes;
            this.isVariadic = isVariadic;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    class FunctionLibrary {
        private Map<String, List<Function>> functionMap = new HashMap<>();  // 使用HashMap优化

        // 注册函数方法
        public void register(Set<Function> functions) {
            for (Function function : functions) {
                String key = generateKey(function.argumentTypes, function.isVariadic); // 生成唯一键
                functionMap.computeIfAbsent(key, k -> new ArrayList<>())
                        .add(function);  // 将函数添加到Map
            }
        }

        // 查找匹配的函数方法
        public List<Function> findMatches(List<String> inputArgumentTypes) {
            List<Function> matches = new ArrayList<>();
            String key = generateKey(inputArgumentTypes, false);

            // 检查精确匹配的函数
            if (functionMap.containsKey(key)) {
                matches.addAll(functionMap.get(key));
            }

            // 检查可变参数匹配的函数
            for (Map.Entry<String, List<Function>> entry : functionMap.entrySet()) {
                String functionKey = entry.getKey();
                if (isVariadicKey(functionKey) && matchesVariadic(inputArgumentTypes, functionKey)) {
                    matches.addAll(entry.getValue());
                }
            }
            return matches;
        }

        // 根据参数类型列表生成唯一键
        private String generateKey(List<String> argumentTypes, boolean isVariadic) {
            StringBuilder keyBuilder = new StringBuilder();
            for (String arg : argumentTypes) {
                keyBuilder.append(arg).append(",");
            }
            if (isVariadic) {
                keyBuilder.append("...");  // 标识可变参数
            }
            return keyBuilder.toString();
        }

        // 判断一个键是否为可变参数的键
        private boolean isVariadicKey(String key) {
            return key.endsWith("...");
        }

        // 检查可变参数函数是否匹配
        private boolean matchesVariadic(List<String> inputArgumentTypes, String functionKey) {
            //这里减去3是因为可变参数的键以"..."结尾。通过去掉最后3个字符，我们移除了表示可变参数的"..."。
            // 移除结尾的 "..."
            String argsString = functionKey.substring(0, functionKey.length() - 3);
            String[] fixedArgs = argsString.split(",");

            // 可变参数类型是最后一个固定参数的类型
            String variadicType = fixedArgs[fixedArgs.length - 1].trim();

            // 检查固定参数数量（不包括可变参数）
            if (fixedArgs.length - 1 > inputArgumentTypes.size()) {
                return false;
            }

            // 检查固定参数类型匹配（除了最后一个，因为最后一个是可变参数类型）
            for (int i = 0; i < fixedArgs.length - 1; i++) {
                if (!fixedArgs[i].trim().equals(inputArgumentTypes.get(i))) {
                    return false;
                }
            }

            // 检查可变参数部分
            for (int i = fixedArgs.length - 1; i < inputArgumentTypes.size(); i++) {
                if (!variadicType.equals(inputArgumentTypes.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

//    public class Main {
//        public static void main(String[] args) {
//            Function funcA = new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false);
//            Function funcB = new Function("FuncB", Arrays.asList("String", "Integer"), true);
//            Function funcC = new Function("FuncC", Arrays.asList("Integer"), true);
//            Function funcD = new Function("FuncD", Arrays.asList("Integer", "Integer"), true);
//            Function funcE = new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false);
//            Function funcF = new Function("FuncF", Arrays.asList("String"), false);
//            Function funcG = new Function("FuncG", Arrays.asList("Integer"), false);
//
//            FunctionLibrary library = new FunctionLibrary();
//            Set<Function> functions = new HashSet<>(Arrays.asList(funcA, funcB, funcC, funcD, funcE, funcF, funcG));
//            library.register(functions);
//
//            System.out.println(library.findMatches(Arrays.asList("String"))); // [FuncF]
//            System.out.println(library.findMatches(Arrays.asList("Integer"))); // [FuncC, FuncG]
//            System.out.println(library.findMatches(Arrays.asList("Integer", "Integer", "Integer", "Integer"))); // [FuncC, FuncD]
//            System.out.println(library.findMatches(Arrays.asList("Integer", "Integer", "Integer"))); // [FuncC, FuncD, FuncE]
//            System.out.println(library.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer"))); // [FuncB]
//            System.out.println(library.findMatches(Arrays.asList("String", "Integer", "Integer"))); // [FuncA, FuncB]
//        }
//    }


//leetcode submit region end(Prohibit modification and deletion)

}