import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract test case.
 */
public abstract class AbstractTestCase {

    LoggedPrintStream lpsOut;
    LoggedPrintStream lpsErr;

    protected void setup() {

        // Create logged PrintStreams
        this.lpsOut = LoggedPrintStream.create(System.out);
        this.lpsErr = LoggedPrintStream.create(System.err);

        // Set them to stdout / stderr
        System.setOut(this.lpsOut);
        System.setErr(this.lpsErr);
    }

    @SuppressWarnings("deprecation")
    protected void loadToInputStream(String s) throws IOException {

        StringBufferInputStream stream = new StringBufferInputStream(s);
        System.setIn(stream);
    }

    public String getLastLine() {
        String lines = this.lpsOut.buf.toString();
        List<String> list = Arrays.asList(lines.split("\\r?\\n"));
        return list.get(list.size() - 1);
    }

    protected void checkResultLastLine(String expected) {
        String result = this.getLastLine();
        if (!result.equals(expected))
            throw new TestFailedException("Unexpected result : '" + result + "' != '" + expected + "'");
    }


    // Restore System.out / System.err
    protected void cleanup() {

        System.setOut(this.lpsOut.underlying);
        this.lpsOut.flush();
        System.setErr(this.lpsErr.underlying);
        this.lpsErr.flush();

    }

    class TestFailedException extends RuntimeException {
        public TestFailedException(String message) {
            super(message);
        }
    }
}