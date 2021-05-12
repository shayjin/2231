import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Put a short phrase describing the program here.
 *
 * @author Jay Shin
 *
 */
public final class StatementAndRecursion2 {

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {
                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement block = s.removeFromBlock(i);
                    simplifyIfElse(block);
                    s.addToBlock(i, block);
                }
                break;
            }
            case IF: {
                Statement ifBlock = s.newInstance();
                Condition c = s.disassembleIf(ifBlock);
                simplifyIfElse(ifBlock);
                s.assembleIf(c, ifBlock);
            }
            case IF_ELSE: {
                Statement ifBlock = s.newInstance();
                Statement elseBlock = s.newInstance();
                Condition c = s.disassembleIfElse(ifBlock, elseBlock);
                switch (c.name()) {
                    case "NEXT_IS_NOT_EMPTY":
                        c = c.NEXT_IS_EMPTY;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(c, elseBlock, ifBlock);
                        break;
                    case "NEXT_IS_NOT_ENEMY":
                        c = c.NEXT_IS_ENEMY;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(c, elseBlock, ifBlock);
                        break;
                    case "NEXT_IS_NOT_FRIEND":
                        c = c.NEXT_IS_FRIEND;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(c, elseBlock, ifBlock);
                        break;
                    case "NEXT_IS_NOT_WALL":
                        c = c.NEXT_IS_WALL;
                        simplifyIfElse(ifBlock);
                        simplifyIfElse(elseBlock);
                        s.assembleIfElse(c, elseBlock, ifBlock);
                        break;

                }
                break;
            }
            case WHILE: {
                Statement whileBlock = s.newInstance();
                Condition c = s.disassembleWhile(whileBlock);
                simplifyIfElse(whileBlock);
                s.assembleWhile(c, whileBlock);
                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }

}
