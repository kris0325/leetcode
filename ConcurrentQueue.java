/**
 * @Author kris
 * @Create 2024-07-30 11:07
 * @Description OOD: 請設計一個queue. 设计一个队列数据结构，使用Java实现，支持基本的队列操作如入队、出队和查看队头元素。队列应该设计为能够处理泛型类型。
 */
public class ConcurrentQueue <T>{
    private ConcurrentLinkedQueue<T> elements;
    public ConcurrentQueue(){
        elements = new ConcurrentLinkedQueue<T>();
    }

    public void enqueue(T element){
        elements.offer(element);
    }

    public T dequeue(){
        T element = elements.poll();
        if(element == null){
            throw new NoSuchElementException("queue is empty");
        }
        return element;
    }

    public T peek(){
        T element = elements.peek();
        if(element == null){
            throw new NoSuchElementException("queue is empty");
        }
        return element;
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public int size(){
        return elements.size();
    }


}
