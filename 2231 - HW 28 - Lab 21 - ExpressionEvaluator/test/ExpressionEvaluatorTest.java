import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code ExpressionEvaluator}'s {@code valueOfExpr}
 * static method.
 *
 * @author Jay Shin
 *
 */
public final class ExpressionEvaluatorTest {

    @Test
    public void testExample() {
        StringBuilder exp = new StringBuilder(
                "281/7/2-1-5*(15-(14-1))+((1))+20=30!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(30, value);
        assertEquals("=30!", exp.toString());
    }

    @Test
    public void test1() {
        StringBuilder exp = new StringBuilder("382*3/19+3*(8-2)/2=69!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(69, value);
        assertEquals("=69!", exp.toString());
    }

    @Test
    public void test2() {
        StringBuilder exp = new StringBuilder("20/2/2/2+1*30/2/2/2=5!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(5, value);
        assertEquals("=5!", exp.toString());
    }

    @Test
    public void test3() {
        StringBuilder exp = new StringBuilder(
                "2*(21/3)+4*(41+3*(21-20))-11 =179!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(179, value);
        assertEquals("=179!", exp.toString());
    }

}
