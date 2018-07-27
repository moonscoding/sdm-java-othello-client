package test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Main Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jul 26, 2018</pre>
 */
public class MainTest {

    @Before
    public void before() throws Exception {
        System.out.println("before");
    }

    @After
    public void after() throws Exception {
        System.out.println("after");
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("testMain");
    }

    /**
     * Method: sum(int a, int b)
     */
    @Test
    public void testSum() throws Exception {
        System.out.println("testSum");
    }

    /**
     * Method: sub(int a, int b)
     */
    @Test
    public void testSub() throws Exception {
        System.out.println("testSub");
    }


} 
