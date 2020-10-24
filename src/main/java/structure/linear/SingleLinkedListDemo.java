package structure.linear;

import java.util.Stack;

public class SingleLinkedListDemo {
  public static void main(String[] args) {
    HeroLinkedList list1 = new HeroLinkedList();
    list1.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
    list1.addByOrder(new HeroNode(1, "宋江", "呼保义"));
    list1.addByOrder(new HeroNode(4, "李鬼", "嗨旋风"));
    // list.addByOrder(new HeroNode(4, "李鬼", "嗨旋风"));
    list1.addByOrder(new HeroNode(3, "关胜", "大刀"));
    list1.printAll();
    System.out.println("--------------------------");
    HeroLinkedList list2 = new HeroLinkedList();
    list2.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
    list2.addByOrder(new HeroNode(1, "宋江", "呼保义"));
    list2.addByOrder(new HeroNode(4, "李鬼", "嗨旋风"));
    // list.addByOrder(new HeroNode(4, "李鬼", "嗨旋风"));
    list2.addByOrder(new HeroNode(3, "关胜", "大刀"));
    list2.printAll();
    System.out.println("--------------------------");
    HeroLinkedList list3 = new HeroLinkedList();
    list3.head = merge(list1.head, list2.head);
    list3.printAll();
  }

  public static HeroNode merge(HeroNode lhs, HeroNode rhs) {
    HeroNode head = new HeroNode();
    HeroNode cur = head;
    HeroNode curLhs = lhs.next;
    HeroNode curRhs = rhs.next;

    while (curLhs != null || curRhs != null) {
      if (curLhs != null && curRhs != null) {
        if (curLhs.no <= curRhs.no) {
          cur.next = curLhs;
          cur = cur.next;
          curLhs = curLhs.next;
        } else {
          cur.next = curRhs;
          cur = cur.next;
          curRhs = curRhs.next;
        }
      } else if (curLhs != null) {
        cur.next = curLhs;
        cur = cur.next;
        curLhs = curLhs.next;
      } else if (curRhs != null) {
        cur.next = curRhs;
        cur = cur.next;
        curRhs = curRhs.next;
      }
    }

    return head;
  }

  public static void reversePrint(HeroNode head) {
    HeroNode cur = head.next;
    Stack<HeroNode> stack = new Stack<>();
    while (cur != null) {
      stack.push(cur);
      cur = cur.next;
    }
    while (stack.size() != 0) {
      System.out.println(stack.pop());
    }
  }

  public static HeroNode reverseList(HeroNode head) {
    HeroNode curr = head;
    HeroNode reverseHead = new HeroNode();
    while (curr.next != null) {
      // 暂时保存curr.next.next 这个数据是原数据还没被翻转的后边部分
      HeroNode temp = curr.next.next;

      // 这两步将curr.next 这个节点插入reverse中
      curr.next.next = reverseHead.next;
      reverseHead.next = curr.next;

      // curr是谁已经不重要了，curr.next 指向下一个
      curr.next = temp;
    }

    return reverseHead;
  }
}

class HeroNode {
  public int no;
  public HeroNode next;
  public String name;
  public String nickName;

  public HeroNode(int hNo, String hName, String hNickName) {
    this.no = hNo;
    this.name = hName;
    this.nickName = hNickName;
  }

  public HeroNode() {

  }

  @Override
  public String toString() {
    return "HeroNode [name=" + name + ", nickName=" + nickName + ", no=" + no + "]";
  }
}

class HeroLinkedList {
  public HeroNode head;
  public int size;

  public HeroLinkedList() {
    this.size = 0;
    this.head = new HeroNode();
  }

  public void add(HeroNode hero) {
    HeroNode temp = this.head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = hero;
    this.size++;
  }

  public void del(int i) {
    HeroNode temp = head;
    boolean flag = false;
    while (temp.next != null) {
      if (temp.next.no == i) {
        flag = true;
        break;
      }

      temp = temp.next;
    }
    if (flag) {
      temp.next = temp.next.next;
      this.size--;
    } else {
      System.out.println("不存在这个好汉，无法删除");
    }
  }

  public void update(HeroNode newHero) {
    HeroNode temp = this.head;
    boolean flag = false;
    while (temp.next != null) {
      if (temp.next.no == newHero.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      temp.next = newHero;
    } else {
      System.out.println("这个好汉不存在：" + newHero);
    }
  }

  public void addByOrder(HeroNode hero) {
    HeroNode temp = this.head;
    boolean flag = true;
    while (temp.next != null) {
      if (temp.next.no == hero.no) {
        System.out.printf("已经存在第%d位好汉了\n", hero.no);
        flag = false;
        break;
      }
      // if(temp.next.next == null) {
      // break;
      // }
      if (temp.next.no > hero.no) {
        break;
      }

      temp = temp.next;
    }
    if (flag) {
      hero.next = temp.next;
      temp.next = hero;
      this.size++;
    }
  }

  public void printAll() {
    HeroNode temp = this.head;
    while (temp.next != null) {
      temp = temp.next;
      System.out.println(temp);
    }
  }
}