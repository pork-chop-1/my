package structure.tree;

public class BinaryTreeDemo {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(1, "宋江");
    TreeNode node1 = new TreeNode(2, "卢俊义");
    TreeNode node2 = new TreeNode(3, "公孙胜");
    TreeNode node3 = new TreeNode(4, "刘唐");
    TreeNode node4 = new TreeNode(5, "张顺");

    root.setLeft(node1);
    root.setRight(node2);
    node2.setRight(node3);
    node2.setLeft(node4);

    BinaryTree binaryTree = new BinaryTree(root);

    binaryTree.prefixPrint();
    System.out.println("-----------------");
    binaryTree.delNode(9);
    binaryTree.prefixPrint();

    

  }
}

class BinaryTree {
  private TreeNode root;

  public BinaryTree(TreeNode root) {
    this.root = root;
  }

  public TreeNode getRoot() {
    return root;
  }

  public void setRoot(TreeNode root) {
    this.root = root;
  }

  // 删除结点
  public void delNode(int no) {
    if(this.root == null) {
      return;
    }
    if(this.root != null && this.root.getNo() == no) {
      this.root = null;
      return;
    }
    if(this.root.delNode(no) == true) {
      System.out.println("删除成功");
    } else {
      System.out.println("不存在这个结点");
    }
  }

  public void prefixPrint() {
    if (this.root != null) {
      this.root.prefixPrint();
    } else {
      System.out.println("root of the tree is null!");
    }
  }

  public void infixPrint() {
    if (this.root != null) {
      this.root.infixPrint();
    } else {
      System.out.println("root of the tree is null!");
    }
  }

  public void postPrint() {
    if (this.root != null) {
      this.root.postPrint();
    } else {
      System.out.println("root of the tree is null!");
    }
  }

  public TreeNode preOrderSearch(int no) {
    if (this.root == null) {
      System.out.println("root of the tree is null");
      return null;
    }
    return this.root.preOrderSearch(no);
  }
}

class TreeNode {
  private int no;
  private String name;
  private TreeNode left;
  private TreeNode right;

  public TreeNode(int no, String name) {
    this.no = no;
    this.name = name;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "TreeNode [name=" + name + ", no=" + no + "]";
  }

  // 删除节点
  public boolean delNode(int no) {
    if (this.left != null && this.left.no == no) {
      this.left = null;
      return true;
    }
    if (this.right != null && this.right.no == no) {
      this.right = null;
      return true;
    }
    if (this.left != null) {
      if(this.left.delNode(no) == true){
        return true;
      }
    }
    if (this.right != null) {
      if(this.right.delNode(no)) {
        return true;
      }
    }
    return false;
  }

  public void prefixPrint() {
    System.out.println(this);
    if (this.left != null) {
      this.left.prefixPrint();
    }
    if (this.right != null) {
      this.right.prefixPrint();
    }
  }

  public void infixPrint() {
    if (this.left != null) {
      this.left.prefixPrint();
    }
    System.out.println(this);
    if (this.right != null) {
      this.right.prefixPrint();
    }
  }

  public void postPrint() {
    if (this.left != null) {
      this.left.prefixPrint();
    }
    if (this.right != null) {
      this.right.prefixPrint();
    }
    System.out.println(this);
  }

  public TreeNode preOrderSearch(int no) {
    if (this.no == no) {
      return this;
    }

    TreeNode temp = null;
    if (this.left != null) {
      temp = this.left.preOrderSearch(no);
    }
    if (temp != null) {
      return temp;
    }
    if (this.right != null) {
      temp = this.right.preOrderSearch(no);
    }
    return temp;
  }
}