package structure.linear;

public class Calculator {
  public static void main(String[] args) {
    LinkedStack<Integer> numbStack = new LinkedStack<Integer>();
    LinkedStack<Character> operStack = new LinkedStack<Character>();

    String expression = "300-200*6+1";
    int length = expression.length();

    int keepNum = 0;

    int result = 0;
    // 如果是数字，则入数字栈
    // 如果是符号：如果符号栈空，直接入符号栈
    //              如果符号栈不空：
    //                  如果当前符号优先度小于或等于栈顶符号，则作计算操作，结果入数字栈，当前入符号栈
    //                  如果有优先度小于栈顶，入符号栈

    for (int i = 0; i < length; i++) {
      if(isOperator(expression.charAt(i))) {
        char o = expression.charAt(i);
        if(operStack.isEmpty()) { // 如果栈空
          operStack.push(o);
        } else {
          if(priority(o) <= priority(operStack.peek())) {
            int right = numbStack.pop();
            int left = numbStack.pop();
            char oper = operStack.pop();
            result = calculate(left, oper, right);
            numbStack.push(result);

            operStack.push(o);
          } else {
            operStack.push(o);
          }
        }
      } else {
        int tNumb = expression.charAt(i) - '0';
        if(i != length -1 && !isOperator(expression.charAt(i+1))){
          keepNum = keepNum*10+tNumb;
        } else {
          numbStack.push(keepNum*10+tNumb);
          keepNum = 0;
        }
      }
    }

    while(!operStack.isEmpty()) {
      int right = numbStack.pop();
      int left = numbStack.pop();
      char oper = operStack.pop();
      result = calculate(left, oper, right);
      numbStack.push(result);
    }

    System.out.printf("%s， 答案是%d", expression, result);
  }

  public static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '/' || c == '*';
  }

  public static int calculate(int lhs, char operator, int rhs) {
    int result = 0;
    switch (operator) {
      case '+':
        result = lhs + rhs;
        break;
      case '-':
        result = lhs - rhs;
        break;
      case '*':
        result = lhs * rhs;
        break;
      case '/':
        result = lhs / rhs;
        break;
    
      default:
        break;
    }
    return result;
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
}
