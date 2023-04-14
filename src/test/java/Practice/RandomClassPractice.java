package Practice;

import java.util.Random;

public class RandomClassPractice {

	public static void main(String[] args) 
	{
		Random ran=new Random();
		int value = ran.nextInt(1000);
		System.out.println(value);
	}

}
