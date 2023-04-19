package Practice;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine 
{
	@Test
	public void test()
	{
		String BROWSER=System.getProperty("browser"); //Runtime parameter
		System.out.println(BROWSER);
		
		
		String BROWSER1=System.getProperty("browser"); //Runtime parameter
		System.out.println(BROWSER1);
	}
}
