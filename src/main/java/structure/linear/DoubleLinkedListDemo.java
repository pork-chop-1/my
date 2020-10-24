package structure.linear;

public class DoubleLinkedListDemo {
  public static void main(String[] args) {
    DoubleLinkedList list1 = new DoubleLinkedList();
    list1.addByOrder(new HeroNode2(2, "卢俊义", "玉麒麟"));
    list1.addByOrder(new HeroNode2(1, "宋江", "呼保义"));
    list1.addByOrder(new HeroNode2(4, "李鬼", "嗨旋风"));
    list1.addByOrder(new HeroNode2(3, "关胜", "大刀"));
    // list1.del(1);
    list1.update(new HeroNode2(4, "李鬼~~~", "嗨旋风"));
    list1.printAll();

  }
}

class DoubleLinkedList {
  public HeroNode2 head;

  public DoubleLinkedList() {
    this.head = new HeroNode2();
  }

  public void add(HeroNode2 hero) {
    HeroNode2 temp = this.head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = hero;
    hero.pre = temp;
  }

  public void del(int i) {
    HeroNode2 temp = head.next;
    boolean flag = false;
    while (temp != null) {
      if (temp.no == i) {
        flag = true;
        break;
      }

      temp = temp.next;
    }
    if (flag) {
      temp.pre.next = temp.next;

      if(temp.next != null) {
        temp.next.pre = temp.pre;
      }
    } else {
      System.out.println("不存在这个好汉，无法删除");
    }
  }

  public void update(HeroNode2 newHero) {
    HeroNode2 temp = this.head;
    boolean flag = false;
    while (temp.next != null) {
      if (temp.next.no == newHero.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      temp.next.name = newHero.name;
      temp.next.nickName = newHero.nickName;
    } else {
      System.out.println("这个好汉不存在：" + newHero);
    }
  }

  public void addByOrder(HeroNode2 hero) {
    HeroNode2 cur = head;
    boolean isExist = true;
    while(cur.next != null) {
      if(cur.next.no == hero.no) {
        System.out.printf("已经存在第%d位好汉了\n", hero.no);
        isExist = false;
        break;
      }
      if(cur.next.no > hero.no) {
        break;
      }
      cur = cur.next;
    }
    if(isExist) {
      hero.next = cur.next;
      hero.pre = cur;
      if(cur.next != null) {
        cur.next.pre = hero;
      }
      cur.next = hero;

      
    }
  }

  public void printAll() {
    HeroNode2 temp = this.head;
    while (temp.next != null) {
      temp = temp.next;
      System.out.println(temp);
    }
  }
}

class HeroNode2 {
  public int no;
  public HeroNode2 next;
  public HeroNode2 pre;
  public String name;
  public String nickName;

  public HeroNode2(int hNo, String hName, String hNickName) {
    this.no = hNo;
    this.name = hName;
    this.nickName = hNickName;
  }

  public HeroNode2() {

  }

  @Override
  public String toString() {
    return "HeroNode2 [name=" + name + ", nickName=" + nickName + ", no=" + no + "]";
  }
}