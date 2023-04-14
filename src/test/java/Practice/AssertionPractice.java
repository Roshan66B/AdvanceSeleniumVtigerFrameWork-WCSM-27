package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {

	@Test
	public void practice()
	{
		System.out.println("step 1");
		System.out.println("step 2");
		Assert.assertEquals(true, true); //condition pass-act=pesp
		System.out.println("step 3");
		System.out.println("step 4");	

	}
	
	@Test
	public void practice1()
	{
		SoftAssert sa=new SoftAssert();
		
		System.out.println("step 1-practice");
		
		System.out.println("step 2-practice ");
		
		sa.assertEquals("A", "A"); //pass
		
		System.out.println("step 3-practice");
		
		sa.assertTrue(false); //fail
		
		System.out.println("step 4-practice");
		Assert.assertTrue(false); //failed
		
		sa.assertAll(); //failures will be logged here
	}
}
