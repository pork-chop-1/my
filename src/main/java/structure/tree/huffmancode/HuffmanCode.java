package structure.tree.huffmancode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HuffmanCode {
    private static Map<Byte, String> huffmanCodeMap;
    public static void main(String[] args) {
        // 压缩文件
        // zipFile("C:\\Users\\tom\\Desktop\\source.bmp", "C:\\Users\\tom\\Desktop\\target.zip");
        // 解压文件
        unZipFile("C:\\Users\\tom\\Desktop\\target.zip", "C:\\Users\\tom\\Desktop\\二代目.bmp");
        /*
         * String s = "i like like like java do you like a java"; byte[] byteArr =
         * s.getBytes(); System.out.println(Arrays.toString(byteArr));
         * 
         * byte[] res = huffmanCodeZip(byteArr);
         * System.out.println(Arrays.toString(res));
         * 
         * 
         * Node root = createHuffmanTree(byteArr);
         * 
         * Map<Byte, String> codeMap = new HashMap<>();
         * 
         * getCode(codeMap, root, new StringBuilder()); // byteToBitString(true,
         * (byte)1); decode(codeMap, res);
         */
    }

    /**
     * 解压文件
     * @param sourcePath
     * @param targetPath
     */
    public static void unZipFile(String sourcePath, String targetPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(sourcePath);
            ois = new ObjectInputStream(fis);
            byte[] data = (byte[])ois.readObject();
            huffmanCodeMap = (Map<Byte, String>)ois.readObject();

            byte[] origin = decode(huffmanCodeMap, data);
            fos = new FileOutputStream(targetPath);

            fos.write(origin);

            fis.close();
            fos.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件
     * @param sourcePath
     * @param targetPath
     */
    public static void zipFile(String sourcePath, String targetPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(sourcePath);

            byte[] source = new byte[fis.available()];

            fis.read(source);

            byte[] zipped = huffmanCodeZip(source);
            fos = new FileOutputStream(targetPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(zipped);
            oos.writeObject(huffmanCodeMap);

            oos.flush();
            System.out.println("zip completed");

            fis.close();
            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] decode(Map<Byte, String> huffmanMap, byte[] code) {
        // 先转换二进制数组到字符串
        StringBuilder stringBuilder = new StringBuilder();
        // for 一个一个转换
        for(int i = 0; i < code.length; i++) {
            // 到了最后一个的时候就不需要补全了。
            boolean flag = (i == code.length - 1);
            stringBuilder.append(byteToBitString(!flag, code[i]));
        }
        // System.out.println(stringBuilder.toString());

        // 好了现在真正开始解码
        // 反向操作huffmanMap
        Map<String, Byte> reverseMap = new HashMap<>();
        for(Map.Entry<Byte, String> item : huffmanMap.entrySet()) {
            reverseMap.put(item.getValue(), item.getKey());
        }

        ArrayList<Byte> list = new ArrayList<>();
        int fore = 0, after = 0;// 双指针大法
        Byte temp = null;
        for(; after < stringBuilder.length(); fore++) {
            if(reverseMap.containsKey(stringBuilder.substring(after, fore))) { // 如果匹配到了
                list.add(reverseMap.get(stringBuilder.substring(after, fore)));
                after = fore;
            }
        }
        // System.out.println(list);
        // 转换成一个操蛋的数组
        byte[] resArr = new byte[list.size()];
        for(int n = 0; n < list.size(); n ++) {
            resArr[n] = list.get(n);
        }
        // System.out.println(new String(resArr));
        return resArr;
    }

    /**
     * 将一个二进制byte转换为二进制string，注意补码
     * @param flag true表示需要补高位，只有最后一个不需要补高位
     * @param b
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {
        int i = (int) b;
        String str = null;
        if(flag) {
            i |= 256;
            str = Integer.toBinaryString(i);
        } else {
            str = Integer.toBinaryString(i);// 这里返回的是二进制补码
        }
        // System.out.println(str.substring(str.length()-8));
        if(flag) {
            return str.substring(str.length()-8);
        } else {
            return str; // 不需要补位的情况，直接踏马的返回
        }
    }

    /**
     * 封装方法，将字节数组压缩
     * @param origin
     * @return
     */
    public static byte[] huffmanCodeZip(byte[] origin) {
        Node root = createHuffmanTree(origin);

        huffmanCodeMap = new HashMap<>();

        getCode(huffmanCodeMap, root, new StringBuilder());

        byte[] huffmanCodeBytes = zip(origin, huffmanCodeMap);

        return huffmanCodeBytes;
    }

    /**
     * 
     * @param arr 源数据
     * @param codeMap 哈夫曼编码的map
     * @return 压缩后的数据
     */
    public static byte[] zip(byte[] arr, Map<Byte, String> codeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte item : arr) {
            stringBuilder.append(codeMap.get(item));
        }
        // System.out.println(stringBuilder.toString());

        int length = 0;
        if (stringBuilder.length() % 8 == 0) {
            length = stringBuilder.length() / 8;
        } else {
            length = stringBuilder.length() / 8 + 1;
        }

        byte[] zipped = new byte[length];
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            if (i + 8 > stringBuilder.length()) {
                zipped[i / 8] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                zipped[i / 8] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
        }
        return zipped;
    }

    /**
     * 获取哈夫曼树所对应的每一个结点的路径 例如：'a'->101001
     * @param codeMap       储存哈夫曼码
     * @param node          当前结点
     * @param stringBuilder
     */
    public static void getCode(Map<Byte, String> codeMap, Node node, StringBuilder stringBuilder) {
        if (node.data != null) { // 叶子结点
            codeMap.put(node.data, stringBuilder.toString());
        }
        if (node.leftNode != null) {
            stringBuilder.append('0');
            getCode(codeMap, node.leftNode, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (node.rightNode != null) {
            stringBuilder.append('1');
            getCode(codeMap, node.rightNode, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    /**
     * 获取每个字符出现了多少次
     * 
     * @param arr
     * @return
     */
    public static Map<Byte, Integer> getNodes(byte[] arr) {
        Map<Byte, Integer> codeMap = new HashMap<>();
        for (byte item : arr) {
            if (codeMap.containsKey(item)) {
                codeMap.put(item, codeMap.get(item) + 1);
            } else {
                codeMap.put(item, 1);
            }
        }
        // System.out.println(codeMap);
        return codeMap;
    }

    /**
     * 生成哈夫曼树
     * @param byteArr
     * @return 哈夫曼树的头节点
     */
    public static Node createHuffmanTree(byte[] byteArr) {
        Map<Byte, Integer> codeMap = getNodes(byteArr);

        List<Node> nodeList = new ArrayList<Node>();

        for (Entry<Byte, Integer> entry : codeMap.entrySet()) {
            nodeList.add(new Node(entry.getKey(), entry.getValue()));
        }
        // System.out.println(nodeList);

        while (nodeList.size() > 1) {
            Collections.sort(nodeList);

            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            Node newNode = new Node(null, leftNode.weight + rightNode.weight);
            newNode.leftNode = leftNode;
            newNode.rightNode = rightNode;
            nodeList.add(newNode);

        }

        return nodeList.get(0);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("the root is empty");
        } else {
            root.preOrder();
        }

    }
}

class Node implements Comparable<Node> {
    public Byte data;
    public int weight;
    public Node leftNode;
    public Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", weight=" + weight + "]";
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

}
