import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Suppose we have a class:
 * <p>
 * <p>
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * <p>
 * <p>
 * The same instance of Foo will be passed to three different threads. Thread A
 * will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed
 * after first(), and third() is executed after second().
 * <p>
 * Note:
 * <p>
 * We do not know how the threads will be scheduled in the operating system, even
 * though the numbers in the input seem to imply the ordering. The input format
 * you see is mainly to ensure our tests' comprehensiveness.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,
 * 3] means thread A calls first(), thread B calls second(), and thread C calls
 * third(). "firstsecondthird" is the correct output.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(), thread B calls
 * third(), and thread C calls second(). "firstsecondthird" is the correct output.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * nums is a permutation of [1, 2, 3].
 * <p>
 * <p>
 * Related Topics Concurrency 👍 1491 👎 204
 */
       
/*
 2024-10-17 12:16:27
*/

class PrintInOrder {
    public static void main(String[] args) {
        Solution solution = new PrintInOrder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //1.Semaphore 實現
    class Foo0 {

        Semaphore semaphoreFirst;
        Semaphore semaphoreSecond;

        public Foo0() {
            // 創建兩個信號量,初始值都為0
            semaphoreFirst = new Semaphore(0);
            semaphoreSecond = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // 直接執行first,不需要等待
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            // 釋放一個sem1的許可,允許second方法執行
            semaphoreFirst.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // 獲取sem1的許可,如果first沒有執行完,這裡會阻塞
            semaphoreFirst.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            // 釋放一個sem2的許可,允許third方法執行
            semaphoreSecond.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            semaphoreSecond.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    //2.synchronised 實現
    class Foo1 {

        private boolean firstDone;
        private boolean secondDone;

        public Foo1() {
            firstDone = false;
            secondDone = false;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstDone = true;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            /**
             * wait(), notify() 和 notifyAll() 方法都是 Object 類的方法,
             * 必須在擁有對象監視鎖的情況下才能調用。
             * 在調用 wait() 和 notifyAll() 方法時,當前線程必須先獲得對象的監視鎖。
             * 在這個情況下,對象的監視鎖確實是指 Foo 實例對象的監視鎖。
             *
             *
             * 1在 Java 中,每個對象都有一個與之關聯的監視鎖(monitor lock)。
             * 2當線程進入一個 synchronized 方法或代碼塊時,它會獲得該對象的監視鎖。
             * 3wait(), notify() 和 notifyAll() 方法都是 Object 類的方法,必須在擁有對象監視鎖的情況下才能調用。
             * 4在您的 Foo 類中,first() 方法是 synchronized 的,所以調用這個方法的線程會自動獲得 Foo 實例的監視鎖。
             * */
            while (!firstDone) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondDone = true;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (!secondDone) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }


    //1.AtomicInteger 實現
    class Foo {

        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        AtomicInteger atomicInteger2 = new AtomicInteger(0);

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // 直接執行first,不需要等待
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            atomicInteger1.incrementAndGet();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // 獲取atomicInteger1的許可,如果first沒有執行完,這裡會阻塞
            while (atomicInteger1.get() != 1) {
                //wait for the first job to be done
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            // 釋放一個atomicInteger2的許可,允許third方法執行
            atomicInteger2.incrementAndGet();

        }

        public void third(Runnable printThird) throws InterruptedException {
            while (atomicInteger2.get() != 1) {
                //wait for the second job to be done
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}