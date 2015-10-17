import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


/**
 * <h1>PROGRAMMING EXERCISE: NATURAL LANGUAGE CALCULATOR</h1>
 */
public class NaturalLanguageCalculator {

    private static final String INPUT_PROMPT = "Please enter a calculation:";

    private static final String[] DIGITS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    /**
     * Main class.
     */
    public static void main(String[] args) throws Exception {

        System.out.println(NaturalLanguageCalculator.INPUT_PROMPT);

        /**
         * Get input string which is is Infix notation.
         *
         * Infix notation : https://en.wikipedia.org/wiki/Infix_notation
         * */
        Scanner in = new Scanner(System.in);
        String infixNotation = in.nextLine();
        in.close();

        /** Split infixNotation string into tokens */
        String[] tokens = infixNotation.toLowerCase().split(" ");

        /**
         * Edsger Dijkstra's "shunting-yard algorithm" is a method for parsing mathematical expressions
         * specified in infix notation : https://en.wikipedia.org/wiki/Shunting-yard_algorithm.
         *
         * The shunting-yard algorithm has been later generalized into operator-precedence parsing.
         * https://en.wikipedia.org/wiki/Operator-precedence_parser
         *
         * However, because of the limited scope of the problem I have used the Shunting-yard_algorithm as inspiration
         * for a much simpler solution. In all honesty I wouldn't be able to implement the Shunting-yard_algorithm
         * in under 4 hours.
         */

        /** Operator & value stacks are FIFO queues */
        LinkedList<String> operatorQueue = new LinkedList<>();
        LinkedList<Double> valueQueue = new LinkedList<>();

        /** The result of each operation is stored in a FILO List */
        Stack<Double> resultStack = new Stack<>();

        /** Separate values and operators. */
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            Double d = NaturalLanguageCalculator.wordToNumber(token);
            if (d == null) {
                operatorQueue.add(token);
            } else {
                valueQueue.push(d);
            }
        }

        /** Pop the first number onto the result stack */
        resultStack.push(valueQueue.removeLast());

        /** Iterate over each word and preform stack based calculation */
        while (operatorQueue.size() != 0) {

            String op = operatorQueue.removeFirst();

            switch (op) {
                case "multiply-by":
                case "times": {

                    /** For multiplication  - multiply last number on the stack to the next number */
                    Double next = valueQueue.removeLast();
                    Double last = resultStack.pop();
                    resultStack.push(last * next);
                    continue;
                }
                case "subtract":
                case "minus":
                case "less": {

                    /** For subtraction just add the next number to the stack signed negative */
                    Double next = valueQueue.removeLast();
                    resultStack.push(-1 * next);
                    continue;
                }
                case "divide-by":
                case "over": {

                    /** For division, divide the last number by the next number */
                    Double next = valueQueue.removeLast();
                    Double last = resultStack.pop();
                    resultStack.push(last / next);
                    continue;
                }
                case "add":
                case "plus":

                    /** For addition just add the next number to the result stack */
                    Double next = valueQueue.removeLast();
                    resultStack.push(next);
                    continue;
            }
        }

        /** Iterates through all the Doubles in the stack and adds their value */
        double sun = 0.0;
        while (!resultStack.empty()) {
            sun += resultStack.pop();
        }

        /** Without this the output for Example 2 will be "3.00" instead of "3" */
        if (sun == (long) sun) System.out.println(String.format("Result: %d", (long) sun));
        else System.out.println(String.format("Result: %.2f", sun));

    }

    /**
     * Index of Word.
     *
     * @return Returns null of the word is not a recognised number.
     */
    private static Double wordToNumber(String word) {

        for (int i = 0; i < NaturalLanguageCalculator.DIGITS.length; i++) {
            if (word.equalsIgnoreCase(NaturalLanguageCalculator.DIGITS[i])) {
                return new Double(i);
            }
        }
        return null;
    }
}

