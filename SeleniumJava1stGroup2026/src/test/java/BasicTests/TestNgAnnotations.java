package BasicTests;

import org.testng.annotations.*;

public class TestNgAnnotations {
    /*
    @BeforeSuite - runs once before all tests in the suite
    @AfterSuite - runs once after all tests in the suite
    @BeforeTest - runs before any test method belonging to the classes inside the <test> tag is run
    @AfterTest - runs after all the test methods belonging to the classes inside the <test> tag have run
    @BeforeClass - runs once before the first method of the current class is invoked
    @AfterClass - runs once after all the methods of the current class have been invoked
    @BeforeMethod - runs before each test method
    @AfterMethod - runs after each test method
    */

    @Test
    public void test2() {
        System.out.println("@Test This is Test 2");
    }

    @Test
    public void test1() {
        System.out.println("@Test This is Test 1");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod This runs before each test method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod This runs after each test method");
    }

   @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass This runs once before all test methods in the class");
   }
    @AfterClass
    public void afterClass() {
        System.out.println("@AfterClass This runs once after all test methods in the class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("@AfterTest This runs after all test methods in the <test> tag");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite This runs before the entire test suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite This runs after the entire test suite");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("@BeforeTest This runs before any test method in the <test> tag");
    }
}
