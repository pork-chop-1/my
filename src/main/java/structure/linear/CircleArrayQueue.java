package structure.linear;

public class CircleArrayQueue {
  public static void main(String[] args) {
    CircleArray circleArray = new CircleArray(3);
    circleArray.add(1);
    circleArray.add(2);
    circleArray.add(3);
    circleArray.printAll();
    circleArray.push();
    circleArray.printAll();
    circleArray.push();
    circleArray.printAll();
    circleArray.add(4);
    circleArray.add(5);
    circleArray.add(6);
    circleArray.printAll();
  }
}

/**
 * 首进尾出
 */
class CircleArray {
  public int maxCount;
  public int[] data;
  // 头部指向，从0开始
  public int front;
  // 尾部从0开始
  public int rear;

  public CircleArray(int count) {
    maxCount = count + 1;
    // 因为要空出一格
    data = new int[count+1];
    front = 0;
    rear = 0;
  }
  public boolean isEmpty() {
    if(rear == front) {
      return true;
    }
    return false;
  }

  public boolean isFull() {
    // 取极限： 假如现在长度为4，是队首为0，队尾为3,3-0=3， 3+1=4；
    // 其他情况例如：长度4，队首为2，队尾为1， 1+1 = 2
    if((rear + 1) % maxCount == front) {
      return true;
    }
    return false;
  }

  public void add(int a) {
    if(isFull()) {
      System.out.println("is Full");
      return;
    }
    data[rear] = a;
    rear = (rear + 1) % maxCount;
  }

  public void push() {
    if(isEmpty()) {
      System.out.println("queue empty");
      return;
    }
    int temp = data[front];
    front = (front + 1) % maxCount;
    System.out.println(temp);
  }

  public int size() {
    return (rear - front + maxCount) % maxCount;
  }

  public void printAll() {
    for(int i = front; i < front + size(); i++) {
      System.out.printf("data[%d] = %d\n", i % maxCount, data[i%maxCount]);
    }
  }
}
