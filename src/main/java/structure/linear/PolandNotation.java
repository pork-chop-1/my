package structure.linear;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
  public static void main(String[] args) {
    String expression = "(300+4)*5-6";
    System.out.println('0');
    List<String> list =toFixedExpressionList(expression);
    System.out.println(list);

    List<String> list2 = parseSuffixExpressionList(list);
    System.out.println(list2);

    System.out.println(cal(list2));

    // (3+4)*5-6 => 34+5*6-
    // String expression = "300 4 + 5 * 6 -";

    // List<String> list = createList(expression);
    // System.out.println(list);

    // int result = cal(list);
    // System.out.println(result);
  }

  private static List<String> parseSuffixExpressionList(List<String> list) {
    Stack<String> s1 = new Stack<String>(); // 运算符栈

    List<String> s2 = new ArrayList<String>(); // 结果栈
    
    for(String item: list) {
      if(item.matches("\\d+")) {
        s2.add(item);
      } else if(item.equals("(")) {
        s1.add(item);
      } else if(item.equals(")")) {
        while(!s1.peek().equals("(")) {
          s2.add(s1.pop());
        }
        s1.pop();
      } else if(isOperator(item) ) {
        while(!s1.isEmpty() && isOperator(s1.peek()) && priority(item.charAt(0)) <= priority(s1.peek().charAt(0))) {
          s2.add(s1.pop());
        }
        s1.add(item);
      } else {
        throw new RuntimeException("输入错误");
      }
    }
    while(!s1.isEmpty()){
      s2.add(s1.pop());
    }
    return s2;
  }

  private static boolean isOperator(String item) {
    return item.equals("*") || item.equals("/") ||item.equals("+") ||item.equals("-");
  }

  public static List<String> toFixedExpressionList(String expression) {
    int length = expression.length();
    ArrayList<String> list = new ArrayList<String>();
    for(int i = 0; i<length; i++) {
      char t = expression.charAt(i);
      if(t>='0' && t<='9'){
        String tempStr = t + ""; // 待添加的数
        while(i+1<length) {
          t = expression.charAt(i+1);
          if(t>='0' && t<='9'){
            tempStr += t;
            i++;
          } else {
            break;
          }
        }
        list.add(tempStr);
      }
      else {
        list.add(t+"");
      }
    }
    return list;
  }

  public static int priority(char c) {
    if (c == '+' || c == '-') {
      return 0;
    } else if (c == '*' || c == '/') {
      return 1;
    } else {
      return -1;
    }
  }

  public static int cal(List<String> list) {
    LinkedStack<String> stack = new LinkedStack<String>();
    int res = 0;
    for (String item : list) {
      if (item.matches("\\d+")) {
        stack.push(item);
      } else {
        int rhs = Integer.parseInt(stack.pop());
        int lhs = Integer.parseInt(stack.pop());
        switch (item.charAt(0)) {
          case '+':
            res = lhs + rhs;
            break;
          case '-':
            res = lhs - rhs;
            break;
          case '*':
            res = lhs * rhs;
            break;
          case '/':
            res = lhs / rhs;
            break;

          default:
            break;
        }
        stack.push(Integer.toString(res));
      }
    }
    return res;
  }

  public static List<String> createList(String s) {
    ArrayList<String> res = new ArrayList<String>();
    String[] split = s.split(" ");
    for (String item : split) {
      res.add(item);
    }
    return res;
  }
}
