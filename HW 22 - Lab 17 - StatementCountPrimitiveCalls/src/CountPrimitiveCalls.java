
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Jay Shin
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */
                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement block = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(block);
                    s.addToBlock(i, block);
                }
                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */
                Statement ifBlock = s.newInstance();
                Condition c = s.disassembleIf(ifBlock);
                count = countOfPrimitiveCalls(ifBlock);
                s.assembleIf(c, ifBlock);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */
                Statement ifBlock = s.newInstance();
                Statement elseBlock = s.newInstance();
                Condition c = s.disassembleIfElse(ifBlock, elseBlock);
                count = countOfPrimitiveCalls(ifBlock)
                        + countOfPrimitiveCalls(elseBlock);
                s.assembleIfElse(c, ifBlock, elseBlock);
                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */
                Statement whileBlock = s.newInstance();
                Condition c = s.disassembleWhile(whileBlock);
                count = countOfPrimitiveCalls(whileBlock);
                s.assembleWhile(c, whileBlock);
                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */
                String instruction = s.disassembleCall();
                if (instruction.equals("move") || instruction.equals("turnleft")
                        || instruction.equals("turnright")
                        || instruction.equals("infect")
                        || instruction.equals("skip")) {
                    count = 1;
                }
                s.assembleCall(instruction);
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
