package structure.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        /**
         * 1 
         * / \ 
         * 2 3 
         * / \ /\ 
         * 4 5 6 7 中序的话，因该输出：4, 2, 5,1,6,3,7
         */
        TreeNode root = new TreeNode(1, "宋江");
        TreeNode node1 = new TreeNode(2, "卢俊义");
        TreeNode node2 = new TreeNode(3, "公孙胜");
        TreeNode node3 = new TreeNode(4, "刘唐");
        TreeNode node4 = new TreeNode(5, "张顺");
        TreeNode node6 = new TreeNode(6, "张横");
        TreeNode node7 = new TreeNode(7, "阮小二");

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node6);
        node2.setRight(node7);

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree(root);
        binaryTree.preThreadedNode(binaryTree.getRoot());
        binaryTree.preThreadedList();
        // System.out.println(node6.getRight() +", "+ node6.getLeft());
    }
}

// 线索化二叉树
class ThreadedBinaryTree {
    private TreeNode root;

    // 前一个结点
    private TreeNode pre;

    // 无参中序线索化
    public void ThreadedNode() {
        ThreadedNode(root);
    }

    public void preThreadedNode(TreeNode node) {
        if (node == null) {
            return;
        }

        // 处理前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        // 处理后驱结点,处理的是前一个结点的后驱结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        // 处理左子树
        if(node.getLeftType() == 0) {
            preThreadedNode(node.getLeft());
        }
        // 处理右子树
        preThreadedNode(node.getRight());
    }

    // 中序线索化
    public void ThreadedNode(TreeNode node) {
        if (node == null) {
            return;
        }

        // 处理左子树
        ThreadedNode(node.getLeft());

        // 处理前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        // 处理后驱结点,处理的是前一个结点的后驱结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        // 处理右子树
        ThreadedNode(node.getRight());
    }

    // 前序线索化输出
    public void preThreadedList() {
        TreeNode temp = this.root;
        while (temp != null) {
            // 找到leftType是1的，也就是顺序的第一个
            while (temp.getLeftType() == 0) {
                System.out.println(temp);
                temp = temp.getLeft();
            }
            // 输出第一个
            System.out.println(temp);
            // 如果这个结点的rightType是1，表示右结点就是下一个
            while (temp.getRightType() == 1) {
                temp = temp.getRight();
                System.out.println(temp);
            }
            // 如果不是，则它是下一个子树，跳跃到它，进行下一轮循环
            // 增加一个判定，如果右边是空，表示已经循环到最后了
            if(temp.getRight() != null) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
    }

    // 中序遍历，线索二叉树
    public void threadedList() {
        TreeNode temp = this.root;
        while (temp != null) {
            // 找到leftType是1的，也就是顺序的第一个
            while (temp.getLeftType() == 0) {
                temp = temp.getLeft();
            }
            // 输出第一个
            System.out.println(temp);
            // 如果这个结点的rightType是1，表示右结点就是下一个
            while (temp.getRightType() == 1) {
                temp = temp.getRight();
                System.out.println(temp);
            }
            // 如果不是，则它是下一个子树，跳跃到它，进行下一轮循环
            temp = temp.getRight();
        }
    }

    /**
     * 线索化的方法
     * 
     * @param root
     */
    public ThreadedBinaryTree(TreeNode root) {
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
        if (this.root == null) {
            return;
        }
        if (this.root != null && this.root.getNo() == no) {
            this.root = null;
            return;
        }
        if (this.root.delNode(no) == true) {
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

// 线索化二叉树结点
class TreeNode {
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    // leftType = 0 表示指向左子树，=1表示指向前驱结点
    private int leftType;
    // rightType = 0 表示指向右子树，=1表示指向后驱结点
    private int rightType;

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
            if (this.left.delNode(no) == true) {
                return true;
            }
        }
        if (this.right != null) {
            if (this.right.delNode(no)) {
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
