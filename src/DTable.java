import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class DTable
{
	Map<String, String> h = new HashMap<String, String>();
	ArrayList<Queue<Character>> l= new ArrayList<Queue<Character>>();

	
	
	public DTable()
	{
	
			h.put( "FG" , "A" );
			h.put( "ID" , "B" );
			h.put( "VV" , "C" );
			h.put( "GI" , "D" );
			h.put( "FX" , "E" );
			h.put( "XD" , "F" );
			h.put( "DD" , "G" );
			h.put( "IG" , "H" );
			h.put( "DV" , "I" );
			h.put( "XG" , "J" );
			h.put( "GA" , "K" );
			h.put( "XA" , "L" );
			h.put( "GV" , "M" );
			h.put( "FD" , "N" );
			h.put( "AG" , "O" );
			h.put( "AX" , "P" );
			h.put( "GF" , "Q" );
			h.put( "VI" , "R" );
			h.put( "DI" , "S" );
			h.put( "IA" , "T" );
			h.put( "DG" , "U" );
			h.put( "IX" , "V" );
			h.put( "AD" , "W" );
			h.put( "VF" , "X" );
			h.put( "FV" , "Y" );
			h.put( "DA" , "Z" );
		
			h.put( "XX" , "1" );
			h.put( "GD" , "2" );
			h.put( "AV" , "3" );
			h.put( "XI" , "4" );
			h.put( "FA" , "5" );
			h.put( "XI" , "6" );
			h.put( "II" , "7" );
			h.put( "AF" , "8" );
			h.put( "IV" , "9" );
			h.put( "VA" , "0" );
			
			h.put( "FF" , ":" );
			h.put( "AA" , ";" );
			h.put( "FI" , "," );
			h.put( "XF" , "." );
			h.put( "VD" , "\"" );
			h.put( "DF" , "!" );
			h.put( "XV" , "-" );
			h.put( "IF" , "(" );
			h.put( "DX" , ")" );
			h.put( "AI" , " " );
			h.put( "GG" , "?" );
			h.put( "VG" , "\n" );
		
	
	}
	
	public String decrypt(StringBuilder s) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("out2.txt", "UTF-8");
		Scanner scanner = new Scanner(System.in);
		
		Map<Character, Queue<Character>> qMap = new HashMap<Character, Queue<Character>>();
		int i, rows, m = 0;	
		int last = 0, temp;
		StringBuilder sb = new StringBuilder();
		
		
		System.out.print("Keyword: ");
		String input = scanner.next();
		char [] keyword = input.toCharArray(); 
		char [] sortedKeyword = Arrays.copyOf(keyword, keyword.length);

		Arrays.sort(sortedKeyword);
		
		rows = s.length()/keyword.length;
		int remainder = s.length()%keyword.length;

		for(char c: keyword)
		{
			qMap.put(c, new LinkedList<Character>());			
		}
		
		for(char c : sortedKeyword)
		{
			if(--remainder > 0)
				last = 1;
			if(remainder == 0)
				last = 0;
			
			for(i = 0; i < rows + last; i++)
			{	
				temp = s.charAt(m++);
				qMap.get(c).offer((char) temp);	
			}			
		}

		StringBuffer finalString = new StringBuffer();
		
		int j=0;
		for(i = 0; i < rows; i++)
		{		
		
			for(char c : keyword)
			{
				if(qMap.get(c).peek() != null)
					sb.append(qMap.get(c).poll());
			}
		}
		i=0;

		while(i < sb.length()-2)
		{
			for(j = 0; j < 2 ; j++)
			{
				char temp1, temp2;
				temp1 = sb.charAt(i++);
				temp2= sb.charAt(i++);
				String tempS = "" +temp1 + temp2;
				finalString.append(h.get(tempS));
			}
			
		}
		
		
		
		writer.printf("%s", finalString);
		scanner.close();
		writer.close();
		
		return "Finally DONE";
		
	}
}
