package structure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        Node root = createHuffmanTree(new int[]{1,2,5,9,4,7,6});
        root.preOrderPrint();
    }

    public static Node createHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<Node>();

        for(int i : arr) {
            list.add(new Node(i));
        }
        System.out.println(list);
        for(int i = 0; i < arr.length-1; i++) {
            Collections.sort(list);
    
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            list.remove(leftNode);
            list.remove(rightNode);
            Node newNode = new Node(leftNode.value + rightNode.value);
            newNode.left = leftNode;
            newNode.right = rightNode;
            list.add(newNode);
        }



        return list.get(0);
    }
}

class Node implements Comparable<Node>{
    public int value;
    public Node left;
    public Node right;

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
    
    // 前序遍历
    public void preOrderPrint() {
        System.out.println(value);
        if(left != null) {
            left.preOrderPrint();
        }
        if(right != null) {
            right.preOrderPrint();
        }
    }
}
