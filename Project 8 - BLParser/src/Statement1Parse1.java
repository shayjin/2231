import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        String If = tokens.dequeue();
        Reporter.assertElseFatalError(If.equals("IF"),
                "EXPECTED: IF, FOUND: " + If);
        String condition = tokens.dequeue();
        String then = tokens.dequeue();
        Reporter.assertElseFatalError(then.equals("THEN"),
                "EXPECTED: THEN, FOUND: " + then);
        Statement ifStatement = s.newInstance();
        ifStatement.parseBlock(tokens);
        String elseOrEnd = tokens.dequeue();

        if (elseOrEnd.equals("ELSE")) {
            Reporter.assertElseFatalError(elseOrEnd.equals("ELSE"),
                    "EXPECTED: ELSE, FOUND: " + elseOrEnd);
            Statement elseStatement = s.newInstance();
            elseStatement.parseBlock(tokens);
            s.assembleIfElse(parseCondition(condition), ifStatement,
                    elseStatement);
            String end = tokens.dequeue();
            Reporter.assertElseFatalError(end.equals("END"),
                    "EXPECTED: END, FOUND: " + end);
        } else {
            Reporter.assertElseFatalError(elseOrEnd.equals("END"),
                    "EXPECTED: END, FOUND: " + elseOrEnd);
            s.assembleIf(parseCondition(condition), ifStatement);
        }
        String endIf = tokens.dequeue();
        Reporter.assertElseFatalError(endIf.equals("IF"),
                "EXPECTED: IF, FOUND: " + endIf);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";
        String While = tokens.dequeue();
        Reporter.assertElseFatalError(While.equals("WHILE"),
                "EXPECTED: WHILE, FOUND: " + While);
        String condition = tokens.dequeue();
        String Do = tokens.dequeue();
        Reporter.assertElseFatalError(Do.equals("DO"),
                "EXPECTED: DO, FOUND: " + Do);
        Statement whileStatement = s.newInstance();
        whileStatement.parseBlock(tokens);
        s.assembleWhile(parseCondition(condition), whileStatement);
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "EXPECTED: END, FOUND: " + end);
        String endWhile = tokens.dequeue();
        Reporter.assertElseFatalError(endWhile.equals("WHILE"),
                "EXPECTED: WHILE, FOUND: " + While);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";
        String call = tokens.dequeue();
        s.assembleCall(call);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        switch (tokens.front()) {
            case "IF":
                parseIf(tokens, this);
                break;
            case "WHILE":
                parseWhile(tokens, this);
            case "END":
                break;
            default:
                parseCall(tokens, this);
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        Statement statement = this.newInstance();
        int i = 0;
        while (!tokens.front().equals("END") && !tokens.front().equals("ELSE")
                && !tokens.front().equals("### END OF INPUT ###")) {
            statement.parse(tokens);
            this.addToBlock(i, statement);
            i++;
        }

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);
        System.out.println(tokens);

        in.close();
        out.close();
    }

}
