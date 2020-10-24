package structure.linear;

public class LinkedStackDemo {
  public static void main(String[] args) {
    LinkedStack<Integer> list = new LinkedStack<Integer>();
    list.push(1);
    list.push(2);
    list.push(3);
    list.push(4);
    list.pop();
    list.printAll();
  }
}

/**
 * 基于链表的栈
 */
class StackNode<T> {
  public T data;
  public StackNode<T> next;
  public StackNode(T sData) {
    this.data = sData;
  }
  public StackNode() {}
}

class LinkedStack<T> {
  public StackNode<T> top;
  public int size;
  public LinkedStack() {
    this.size = 0;
    top = new StackNode<T>();
  }
  public int size() {
    return this.size;
  }
  public boolean isEmpty() {
    return this.size == 0;
  }
  public void push(T data) {
    StackNode<T> node = new StackNode<T>(data);
    node.next = top.next;
    top.next = node;
    this.size ++;
  }

  public T pop() {
    if(isEmpty()) {
      throw new RuntimeException();
    }
    T data = top.next.data;
    top.next = top.next.next;
    this.size --;
    return data;
  }
  public void printAll() {
    StackNode<T> cur = top;
    for(int i = 0; i < size; i++) {
      System.out.println(cur.next.data);
      cur = cur.next;
    }
  }
  public T peek() {
    if(isEmpty()) {
      throw new RuntimeException();
    }
    return top.next.data;
  }
}