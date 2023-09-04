import java.util.ArrayList;
import java.util.List;

public class Calculator {
    int result;

    public Calculator(int[] nums, String[] operators) {
        List<Integer> stack = new ArrayList<Integer>();
        List<String> opStack = new ArrayList<String>();
        int opIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (stack.size() < 2) { // must keep pushing
                stack.add(nums[i]);
            } else { // can start operating
                if (opIdx < operators.length - 1 && operators[opIdx + 1].equals("Div")) {
                    // handle operation with div
                    opStack.add(operators[opIdx]);
                } else { // can safely group add/sub, take last two
                    int op2 = stack.remove(stack.size() - 1);
                    int op1 = stack.remove(stack.size() - 1);

                    switch (operators[opIdx]) {
                        case "Add":
                            stack.add(op1 + op2);
                            break;
                        case "Sub":
                            stack.add(op1 - op2);
                            break;
                        case "Div":
                            stack.add(op1 / op2);
                            break;
                        default:
                            System.out.println("default switch");
                    }
                }
                opIdx++;
                stack.add(nums[i]);
            }
//            System.out.println(stack);
//            System.out.println(opIdx);
        }
        opStack.add(operators[opIdx]);

        while (stack.size() > 1) {
            int op2 = stack.remove(stack.size() - 1);
            int op1 = stack.remove(stack.size() - 1);
            String operator = opStack.remove(opStack.size()-1);
//            System.out.println(String.format("op1: %s, op2: %s, operator: %s", op1, op2, operator));
            switch (operator) {
                case "Add":
                    stack.add(op1 + op2);
                    break;
                case "Sub":
                    stack.add(op1 - op2);
                    break;
                case "Div":
                    stack.add(op1 / op2);
                    break;
                default:
                    System.out.println("default switch");
            }
        }
        this.result = stack.get(0);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator(new int[]{5, 6, 14, 7}, new String[]{"Add", "Sub", "Div"});
        System.out.println(calc.result);
    }
}
