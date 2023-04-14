package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPracticeTest {
	
	@Test
	public void createUserTest()
	{
		System.out.println("user created"); //executed-failed
	}
	
	@Test
	public void modifyUserTest()
	{
		System.out.println("user modified"); //should not executed
	}
	
	@Test(dependsOnMethods = "createUserTest")
	public void deleteUserTest()
	{
		System.out.println("user deleted");
	}
}

