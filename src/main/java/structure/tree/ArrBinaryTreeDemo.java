package structure.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}

class ArrBinaryTree {
    public int data[];

    public ArrBinaryTree(int[] arr) {
        this.data = arr;
    }

    // 无参前序遍历
    public void preOrder() {
        preOrder(0);
    }

    // 前序遍历
    public void preOrder(int i) {
        System.out.println(data[i]);

        if(i*2+1<data.length) {
            preOrder(i*2+1);
        }
        if(i*2+2<data.length) {
            preOrder(i*2+2);
        }
    }
}
