import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AverageTest {

    @Test
    public void test1() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE - 1;
        int result = Average.average(j, k);

        assertEquals(Integer.MAX_VALUE - 1, result);
    }

    @Test
    public void test2() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE + 1;
        int result = Average.average(j, k);

        assertEquals(Integer.MIN_VALUE + 1, result);
    }

    @Test
    public void test3() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE;
        int result = Average.average(j, k);

        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    public void test4() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE;
        int result = Average.average(j, k);

        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void test5() {
        int j = 5;
        int k = 8;
        int result = Average.average(j, k);

        assertEquals(6, result);
    }

    @Test
    public void test6() {
        int j = -5;
        int k = -8;
        int result = Average.average(j, k);

        assertEquals(-6, result);
    }

    @Test
    public void test7() {
        int j = 11;
        int k = -4;
        int result = Average.average(j, k);

        assertEquals(3, result);
    }

    @Test
    public void test8() {
        int j = -3;
        int k = 2;
        int result = Average.average(j, k);

        assertEquals(0, result);
    }

    @Test
    public void test9() {
        int j = 3;
        int k = 5;
        int result = Average.average(j, k);

        assertEquals(4, result);
    }

    @Test
    public void test10() {
        int j = -3;
        int k = -5;
        int result = Average.average(j, k);

        assertEquals(-4, result);
    }
}