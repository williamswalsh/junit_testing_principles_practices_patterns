package chpt1.inheritance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest extends MyTestParent {

  @Before
  public void setupChild() {
    System.out.println("MyTest.setupChild");
  }

  @After
  public void teardownChild() {
    System.out.println("MyTest.teardownChild");
  }

  @Test
  public void test() {
    System.out.println("MyTest.Test");
  }

  /**
   * Outcome: ======== MyTestParent.setup MyTest.setupChild MyTest.Test MyTest.teardownChild
   * MyTestParent.teardown
   *
   * <p>There was no parent methods called, when the names were the same setup & teardown. So the
   * child methods overrode the parent methods.
   */
}
