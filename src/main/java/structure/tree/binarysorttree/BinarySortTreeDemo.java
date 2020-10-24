package structure.tree.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        //    7
        //    /\
        //   3  10
        //   /\ /\
        //  1 5 9 12
        //   \
        //    2
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.add(new Node(7));
        binarySortTree.add(new Node(3));
        binarySortTree.add(new Node(10));
        binarySortTree.add(new Node(12));
        binarySortTree.add(new Node(5));
        binarySortTree.add(new Node(1));
        binarySortTree.add(new Node(9));
        binarySortTree.add(new Node(2));
        System.out.println("中序遍历");
        binarySortTree.infixOrder();

        System.out.println("删除了：" + binarySortTree.delNode(2));
        System.out.println("删除了：" + binarySortTree.delNode(5));
        System.out.println("删除了：" + binarySortTree.delNode(9));
        System.out.println("删除了：" + binarySortTree.delNode(12));
        System.out.println("删除了：" + binarySortTree.delNode(7));
        System.out.println("删除了：" + binarySortTree.delNode(3));
        System.out.println("删除了：" + binarySortTree.delNode(10));
        System.out.println("删除了：" + binarySortTree.delNode(1));
        // System.out.println(binarySortTree.root);
        System.out.println("删除后");
        binarySortTree.infixOrder();

    }
}

class BinarySortTree {
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

    public Node delNode(int i) {
        if (root == null) {
            System.out.println("根结点为空");
            return null;
        }
        // 如果只有一个根结点
        if (root.left == null && root.right == null) {
            if (root.value == i) { // 根结点就是要删除的结点
                Node res = root;
                root = null;
                return res;
            } else {
                return null;
            }
        }
        Node[] nodePair = this.root.searchNodeAndItsParent(i);
        Node parent = nodePair[0]; // parent可能为空
        Node target = nodePair[1];
        // 如果是根结点
        if (target.left == null && target.right == null) {
            // 判断这个结点是在父结点的 左边还是右边
            if (parent.left != null && target == parent.left) { // 在左边
                parent.left = null;
            } else { // 在右边
                parent.right = null;
            }
            return target;
        }

        if (target.left != null && target.right != null) { // 要删除的结点两边都不为空
            //从target的右子树找到一个最小的结点。。
            int replace = delLeftMin(target.right);
            int deletedValue = target.value;
            target.value = replace;
            return new Node(deletedValue);
        } else { // 只有一边为空
            // 判断是否为根结点
            if(parent == null) { // 目标结点是根结点
                Node temp = target; // 保存要删除的结点
                if(target.left != null) {
                    root = target.left;
                } else {
                    root = target.right;
                }
                return temp;
            } else {// 目标不是是根结点

                if (target.left != null) { // 它是左边有子树
                    // 判断target到底是在父结点的左边还是右边
                    if (parent.left != null && parent.left == target) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    // 判断target到底是在父结点的左边还是右边
                    if (parent.left != null && parent.left == target) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                }
                return target;
            }
        }
    }

    /**
     * 这个方法删除结点左边最小的结点并返回结点的值
     * @param node
     * @return
     */
    public int delLeftMin(Node node) {
        Node temp = node;
        while(temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
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
