import java.util.Stack;

class BasicCal {
    public int calculate(String s) {
        int last_num = 0; 
        char last_op = '+';
        int curr_num = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                curr_num = curr_num * 10 + s.charAt(i) - '0';
                continue;
            }

            // the idea for optimisation is that if you see double +, eg: a + b + c, if u see + before c, then know can add b to result
            // when u see 1st +, add the a to result, then use a value to keep track of b until uk next operator is + / -
            if (!Character.isWhitespace(s.charAt(i))) {
                if (last_op == '+') {
                    result += last_num;
                    last_num = curr_num;
                } else if (last_op == '-') {
                    result -= last_num;
                    last_num = -1 * curr_num;
                } else if (last_op == '*') {
                    //eg: a + b * c + d
                    last_num = last_num * curr_num;
                } else if (last_op == '/') {
                    last_num = last_num / curr_num;
                } else {

                }

                last_op = s.charAt(i);
                curr_num = 0;
            }
        }

        return result;
    }
}