package chpt1.inheritance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTestParent {

    @Before
    public void setup() {
        System.out.println("MyTestParent.setup");
    }

    @After
    public void teardown() {
        System.out.println("MyTestParent.teardown");
    }

    @Test
    public void testParent() {
        System.out.println("MyTestParent.testParent");
    }
}
