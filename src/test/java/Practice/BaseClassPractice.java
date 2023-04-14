package Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClassPractice {

	@BeforeSuite
	public void bsconfig()
	{
		System.out.println("before suite");
	}
	
	@AfterSuite
	public void asconfig()
	{
		System.out.println("after suite");
	}
	
	@BeforeClass
	public void bcconfig()
	{
		System.out.println("before class");
	}
	
	@AfterClass
	public void acconfig()
	{
		System.out.println("after class");
	}
	
	@BeforeMethod
	public void bmconfig()
	{
		System.out.println("before method");
	}
	
	@AfterMethod
	public void amconfig()
	{
		System.out.println("after method");
	}
	
	@Test
	public void test()
	{
		System.out.println("test");
	}
	
	@Test
	public void test2()
	{
		System.out.println("test 2");
	}
	
}
