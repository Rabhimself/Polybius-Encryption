import java.io.*;
import java.util.*;


public class TestRunner
{

	public static void main(String[] args) throws IOException
	{
		String fileName;

		Scanner console = new Scanner(System.in);
		StringBuilder s = new StringBuilder();
		/*
		 s = new Scanner(new File("WP.txt"))
		    .useDelimiter("\\A").next();
		 
		long start = System.nanoTime();    
		//if (command.toLowerCase().equals("e")){
					ETable e = new ETable();
		System.out.println(e.parseString(s));
		}
*/		
		int i = 0;
		do
		{
		System.out.println("1: Encrypt a file");
		System.out.println("2: Decrypt a file");
		
		System.out.println("Choice: ");
		i = console.nextInt();
		clearConsole();
		}while(i != 1 && i != 2);
		
		System.out.println("File: ");
		fileName = console.next();

		switch(i)
		{
			case 1:
				ETable e = new ETable();
				System.out.println(e.parseString(fileName));
				break;
			
			case 2:
				DTable d = new DTable();
				System.out.println(d.decrypt(fileName));
				break;
		}

		console.close();

	}


	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
}
