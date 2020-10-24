package structure.linear;

import java.util.Arrays;

class SolutionQueen {
  private int max = 8;
  private int[] res = new int[max];
  public int count =0;

  public void print() {
    // Arrays.asList(res).stream().forEach(System.out::println);
    for(int item: res) {
      System.out.print(item + " ");
    }
    System.out.println();
  }

  public void doNow(int n) {
    if(n == max) {
      print();
      count ++;
      return;
    }
    for(int i = 0; i < max; i++) {
      res[n] = i;
      if(judge(n)) {
        doNow(n+1);
      }
    }
  }

  public boolean judge(int n) {
    for(int i= 0; i< n; i++) {
      if(res[i] == res[n] || Math.abs(i-n) == Math.abs(res[i] - res[n])){
        return false;
      }
    }
    return true;
  }

}

public class Queen8 {
  public static void main(String[] args) {
    SolutionQueen s = new SolutionQueen();
    s.doNow(0);
    System.out.println(s.count);
  }
}


/*
package my;

import java.util.*;

class Solution51 {
  private List<List<Integer>> t = new ArrayList<List<Integer>>();
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> res = new ArrayList<List<String>>();
    List<Integer> a = new LinkedList<Integer>();
    dfs(a, 0, n);
    System.out.println(t);
    create(res, n);
    return res;
  }

  private void create(List<List<String>> res, int length) {
    for(int i = 0; i < t.size(); i++) {
      List<String> add = new ArrayList<String>();
      List<Integer> temp = t.get(i);
      int size = temp.size();
      for(int j = 0; j < size; j++) {
        String s = "";
        for(int k = 0; k < length; k++) {
          if(k == temp.get(j)) {
            s+= 'Q';
          } else {
            s+='.';
          }
        }
        add.add(s);
      }
      res.add(add);
    }
  }

  private void dfs(List<Integer> temp, int length, int n) {
    if(length == n) {
      t.add(new ArrayList<>(temp));
    }

    for(int i = 0; i < n; i++) {
        // System.out.println(temp);
      if(isOk(temp, i, length)) { //
        temp.add(i);
        dfs(temp, length + 1, n);
        temp.remove(temp.size() -1);
      }
    }
  }
  public boolean isOk(List<Integer> temp, int j, int i) {
    for(int k = 0; k < temp.size(); k ++) {
      // row：k col：temp.get(k)    (i ,j) (1, 3) (3,2)
      if(k == i || temp.get(k) == j || Math.abs(k-i) == Math.abs(temp.get(k) - j)){
        return false;
      }
    }
    return true;
  }
}

public class Problem51 {
  public static void main(String[] args) {
    Solution51 s = new Solution51();
    System.out.println(s.solveNQueens(5));
    // ArrayList<Integer> test = new ArrayList<>();
    // test.add(0);
    // test.add(3);
    // System.out.println(s.isOk(test, 2, 3));
  }
}


*/