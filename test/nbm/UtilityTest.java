/*
 * Testing class used to test functions from Utility class
 */
package nbm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jurek
 */
public class UtilityTest {

    Message message = new Message("textwords.csv", "testfiles/test_messages.json", "testfiles/test_trendinglist.txt", "testfiles/test_mentions.txt", "testfiles/test_sir.txt"); // initialize utility object
 
    public UtilityTest() {}
    

  
    @BeforeClass
    public static void setUpClass() { System.out.println("Setting up class\n."); }
    
    @AfterClass
    public static void tearDownClass(){ System.out.println("Tearing down class."); }
    
    @Before
    public void setUp() { System.out.println("Setting up test."); }
    
    @After
    public void tearDown() { System.out.println("Tearing down test\n."); }

    /**
     * Test of addMessage method, of class Utility.
     */
    @Test
    public void testAddMessage() {
        System.out.println("Testing: addMessage");
        String header = "S123456789";
        String body = "+123456789 Simple message";
        Message instance = message;
        String[] expResult = {"","Type:\tSMS\n","Sender:\t+123456789\n","",
        "Message:\tSimple message\n","Status:\tMessage was successfully saved to file."};
        String[] result = instance.addMessage(header, body);
        try { Assert.assertArrayEquals(expResult, result); System.out.println("addMessage method tested - Test Passed");} 
        catch (AssertionError e) { System.out.println("addMessage method tested - Test Failed"); throw e; }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTrendingList method, of class Utility.
     */
    @Test
    public void testGetTrendingList() {
        System.out.println("Testing: getTrendingList");
        Message instance = message;
        String expResult = "1. #tomatosoup\t2\n";
        String result = instance.getTrending();
        try{ assertEquals(expResult, result); System.out.println("getTrendingList method tested - Test Passed");}
        catch (Exception e){ System.out.println("getTrendingList method tested - Test Failed"); }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMentionList method, of class Utility.
     */
    @Test
    public void testGetMentionList() {
        System.out.println("Testing: getMentionList");
        Message instance = message;
        String expResult = "1. @jesica\n";
        String result = instance.getMentions();
        try{  assertEquals(expResult, result); System.out.println("getMentionList method tested - Test Passed");}
        catch(Exception e){System.out.println("getMentionList method tested - Test Failed");}   
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSIR method, of class Utility.
     */
    @Test
    public void testGetSIR() {
        System.out.println("Testing: getSIR");
        Message instance = message;
        String expResult = "1. 22-33-44 Theft\n";
        String result = instance.getSIR();
        try{assertEquals(expResult, result); System.out.println("getSIR method tested - Test Passed");}
        catch (Exception e){ System.out.println("getSIR method tested - Test Passed");}
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of selectFile method, of class Utility. - select test_example.txt to pass this test
     */
    @Test
    public void testSelectFile() {
        System.out.println("Testing: selectFile");
        Message instance = message;
        String expResult = "C:\\Users\\Jurek\\Documents\\NetBeansProjects\\NBM\\testfiles\\test_example.txt";
        String result = instance.selectFile();
        try{ assertEquals(expResult, result); System.out.println("Testing method tested - Test Passed");}
        catch(Exception e){ System.out.println("Testing method tested - Test Passed");} 
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
