/**
 * Test cases.
 * <p>
 * <h3>SUPPLIED EXAMPLES</h3>
 * <pre>
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | nine over eight plus four times two divide-by three |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | 3.79 |
 * +-----------------------------------------------------+------+
 * |                                                     |      |
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | one plus two                                        |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | 3    |
 * +-----------------------------------------------------+------+
 * |                                                     |      |
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | one plus two times three                            |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | 7    |
 * +-----------------------------------------------------+------+
 * |                                                     |      |
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | nine minus three times seven                        |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | -12  |
 * +-----------------------------------------------------+------+
 * |                                                     |      |
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | four minus eight plus six times nine                |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | 50   |
 * +-----------------------------------------------------+------+
 * | Please enter a calculation                          |      |
 * +-----------------------------------------------------+------+
 * | seven over nine plus one                            |      |
 * +-----------------------------------------------------+------+
 * | Result                                              | 1.78 |
 * +-----------------------------------------------------+------+
 * </pre>
 * <p>
 */
public class CalulatorTestCase extends AbstractTestCase {

    private static final String[] NUMBERS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    CalulatorTestCase() {
    }

    public static void main(String[] args) throws Exception {

        CalulatorTestCase tests = new CalulatorTestCase();
        tests.setup();
        tests.test1();
        tests.test2();
        tests.test3();
        tests.test4();
        tests.test5();
        tests.test6();
        tests.test7();
        tests.cleanup();
    }

    public void test1() throws Exception {

        this.loadToInputStream("nine over eight plus four times two divide-by three");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 3.79");
    }

    public void test2() throws Exception {

        this.loadToInputStream("one plus two\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 3");
    }

    public void test3() throws Exception {

        this.loadToInputStream("one plus two times three\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 7");
    }

    public void test4() throws Exception {

        this.loadToInputStream("nine minus three times seven\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: -12");
    }

    public void test5() throws Exception {

        this.loadToInputStream("four minus eight plus six times nine\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 50");
    }

    public void test6() throws Exception {

        this.loadToInputStream("seven over nine plus one\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 1.78");
    }

    public void test7() throws Exception {

        this.loadToInputStream("seven over nine plus one times seven over three divide-by two\n");
        NaturalLanguageCalculator.main(new String[]{});
        this.checkResultLastLine("Result: 1.94");
    }

}