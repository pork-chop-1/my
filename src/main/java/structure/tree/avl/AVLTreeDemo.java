package structure.tree.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree AVLTree = new AVLTree();

        int[] arr = {7,6,2,3,4,5,1};
        for(int i : arr) {
            AVLTree.add(new Node(i));
        }

        System.out.println(AVLTree.root.height());
        System.out.println(AVLTree.root.leftHeight());
        System.out.println(AVLTree.root.rightHeight());
        AVLTree.infixOrder();
    }
}

class AVLTree {
    public Node root;

    public void add(Node node) {
        if (root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("根结点为空");
        } else {
            this.root.infixOrder();
        }
    }

    public Node search(int i) {
        if (root == null) {
            System.out.println("root is empty");
            return null;
        } else {
            return root.search(i);
        }
    }

    public Node searchParent(int i) {
        if (root == null) {
            System.out.println("root is empty");
            return null;
        } else {
            return root.searchParent(i);
        }
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node() {
    }
    /**
     * 左旋转
     * 1. 创建一个新的结点，结点值为当前结点值
     * 
     * 2. 新结点的左子树设置为当前结点的左子树，把新结点右结点的子树设置为当前结点的右子树的左子树
     * 
     * 3. 把当前结点的值换成右子树的值
     * 
     * 4. 当前结点的的右子树换成右子树的右子树：类似于temp.right = temp.right.right;
     * 
     * 5. 当前结点的左子树换成新结点
     */
    public void leftRotate() {
        //1. 创建一个新的结点，结点值为当前结点值
        Node temp = new Node(this.value);
        // 2. 新结点的左子树设置为当前结点的左子树，把新结点右结点的子树设置为当前结点的右子树的左子树
        temp.left = this.left;
        temp.right = this.right.left;
        // 3. 把当前结点的值换成右子树的值
        this.value = this.right.value;
        // 4. 当前结点的的右子树换成右子树的右子树：类似于temp.right = temp.right.right;
        this.right = this.right.right;
        // 5. 当前结点的左子树换成新结点
        this.left = temp;
    }

    public void rightRotate() {
        Node temp = new Node(this.value);
        temp.right = this.right;
        temp.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = temp;
    }

    public int leftHeight() {
        if(this.left != null) {
            return this.left.height();
        } else {
            return 0;
        }
    }

    public int rightHeight() {
        if(this.right != null) {
            return this.right.height();
        } else {
            return 0;
        }
    }
    
    /**
     * 返回这个结点的高度
     * @return
     */
    public int height() {
        int left = 0, right = 0;
        if(this.left != null) {
            left = this.left.height();
        }
        if(this.right != null) {
            right = this.right.height();
        }
        return 1 + Math.max(left, right);
    }

    /**
     * 同时找到目标结点和它的父结点，当查找到根结点时，父结点为空
     * 
     * @return 返回一个长度为二的list，它的第一个元素是父结点，第二个元素是目标结点
     */
    public Node[] searchNodeAndItsParent(int i) {
        Node parentNode = null;
        Node targetNode = this;
        Node[] resList = dfsSearchNodeAndItsParent(i, parentNode, targetNode);
        return resList;
    }

    /**
     * 
     * @param i
     * @param parentNode
     * @param targetNode
     */
    private Node[] dfsSearchNodeAndItsParent(int i, Node parentNode, Node targetNode) {
        if (i == targetNode.value) { // 查到目标
            return new Node[] { parentNode, targetNode };
        } else if (i > targetNode.value) {
            if (targetNode.right != null) {
                return dfsSearchNodeAndItsParent(i, targetNode, targetNode.right);
            } else {
                return null;
            }
        } else {
            if (targetNode.left != null) {
                return dfsSearchNodeAndItsParent(i, targetNode, targetNode.left);
            } else {
                return null;
            }
        }

    }

    /**
     * 获取目标的上层结点
     * 
     * @param i
     * @return
     */
    public Node searchParent(int i) {
        if ((this.left != null && this.left.value == i) || (this.right != null && this.right.value == i)) {
            return this;
        }
        if (this.value > i && this.left != null) {
            return this.left.searchParent(i);
        } else if (this.value < i && this.right != null) {
            return this.right.searchParent(i);
        } else {
            return null;
        }
    }

    // 查找特定结点
    public Node search(int i) {
        if (this.value == i) {
            return this;
        }
        if (this.value > i) {
            if (this.left != null) {
                return this.left.search(i);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(i);
            } else {
                return null;
            }
        }
    }

    // 递归添加子结点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value > this.value) {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        } else {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        }

        // 如果右子树的高度-左子树的高度>1
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果右子树的左右结点都存在     左子树的高度大于右子树的高度
            if(this.right.left != null && this.right.right != null & this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
            }

            this.leftRotate();
        }
        // 如果反过来
        if (this.leftHeight() - this.rightHeight() > 1) {
            if(this.left.left != null && this.left.right != null & this.left.leftHeight() < this.left.rightHeight()) {
                this.left.leftRotate();
            }

            this.rightRotate();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

}