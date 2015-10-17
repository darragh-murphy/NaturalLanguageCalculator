import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

/**
 * <h1>PROGRAMMING EXERCISE: NATURAL LANGUAGE CALCULATOR</h1>
 */
public class NaturalLanguageCalculatorRecursive {

    private static final String[] DIGITS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final Map<String, Double> DIGIT_MAP = range(0, 10).boxed().collect(toMap(i -> NaturalLanguageCalculatorRecursive.DIGITS[i], Double::valueOf));

    private static double arithmetic(Iterator<String> tokens, double lastNumber) {

        if (!tokens.hasNext()) return lastNumber;

        String op = tokens.next();
        double number = NaturalLanguageCalculatorRecursive.getDouble(tokens);
        switch (op) {
            case "multiply-by":
            case "times":
                return NaturalLanguageCalculatorRecursive.arithmetic(tokens, lastNumber * number);
            case "subtract":
            case "minus":
            case "less":
                return lastNumber + NaturalLanguageCalculatorRecursive.arithmetic(tokens, -number);
            case "divide-by":
            case "over":
                return NaturalLanguageCalculatorRecursive.arithmetic(tokens, lastNumber / number);
            case "add":
            case "plus":
                return lastNumber + NaturalLanguageCalculatorRecursive.arithmetic(tokens, number);
            default:
                throw new RuntimeException("Unrecognized operator: " + op);
        }
    }

    private static double getDouble(Iterator<String> iter) {
        if (!iter.hasNext()) throw new RuntimeException("Excepted a number at the end");
        String nextString = iter.next();
        Double aDouble = NaturalLanguageCalculatorRecursive.DIGIT_MAP.get(nextString);
        if (aDouble == null) throw new RuntimeException("Unrecognised number: " + nextString);
        return aDouble;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("Please enter a calculation:");

        String infixNotation = "plus " + new Scanner(System.in).nextLine();

        double sum = NaturalLanguageCalculatorRecursive.arithmetic(stream(infixNotation.toLowerCase().split(" ")).iterator(), 0);

        System.out.println("Result: " + new DecimalFormat("#.##").format(sum));
    }
}