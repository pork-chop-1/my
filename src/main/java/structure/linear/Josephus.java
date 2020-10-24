package structure.linear;

public class Josephus {
  public static void main(String[] args) {
    CirCleSingleList list1 = new CirCleSingleList();
    list1.createList(5);
    list1.printAll();
    System.out.println("-----------------------------");
    list1.countBoy(1, 2, 5);
  }
}

class Boy {
  public int no;
  public Boy next;

  public Boy() {
  }

  public Boy(int bNo) {
    this.no = bNo;
  }

  @Override
  public String toString() {
    return "Boy [no=" + no + "]";
  }
}

class CirCleSingleList {
  public Boy head;

  public CirCleSingleList() {

  }

  public void createList(int count) {
    if (count < 1) {
      return;
    }
    Boy cur = head;
    for (int i = 1; i < count + 1; i++) {
      if (i == 1) {
        head = new Boy(i);
        cur = head;
        cur.next = cur;
      } else {
        cur.next = new Boy(i);
        cur = cur.next;
        cur.next = head;
      }
    }
  }

  public void printAll() {
    if (head == null) {
      System.out.println("列表为空");
      return;
    }
    Boy cur = head;
    do {
      System.out.println(cur);
      cur = cur.next;
    } while (cur != head);
  }

  /***
   * 
   * @param startNum 起始的编号
   * @param countNum 每次叫几下
   * @param num      一共有几个人
   */
  public void countBoy(int startNum, int countNum, int num) {
    if (num < startNum) {
      System.out.println("没有那么多人");
      return;
    }
    if (this.head == null) {
      System.out.println("空环");
    }
    Boy cur = head;
    // 到达最初的那个人的前面
    do {
      if ((startNum + num - 2) % num + 1 == cur.no) {
        // System.out.println(cur);
        break;
      }
      cur = cur.next;
    } while (cur != head);
    // 开始出圈
    while (cur.next != cur) { // 只剩下cur了
      for (int i = 0; i < countNum - 1; i++) { // 经过count-1个人
        cur = cur.next;
      }
      System.out.println(cur.next);
      cur.next = cur.next.next;
    }
    this.head = cur; // 把头重新指向

    System.out.println("最后一个:" + cur);
  }
}