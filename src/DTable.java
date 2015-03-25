import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class DTable
{
	Map<String, Character> h = new HashMap<String, Character>();
	ArrayList<Queue<Character>> l= new ArrayList<Queue<Character>>();

	
	
	public DTable()
	{
	
			h.put( "FG" , 'A' );
			h.put( "ID" , 'B' );
			h.put( "VV" , 'C' );
			h.put( "GI" , 'D' );
			h.put( "FX" , 'E' );
			h.put( "XD" , 'F' );
			h.put( "DD" , 'G' );
			h.put( "IG" , 'H' );
			h.put( "DV" , 'I');
			h.put( "XG" , 'J' );
			h.put( "GA" , 'K' );
			h.put( "XA" , 'L' );
			h.put( "GV" , 'M' );
			h.put( "FD" , 'N' );
			h.put( "AG" , 'O' );
			h.put( "AX" , 'P' );
			h.put( "GF" , 'Q' );
			h.put( "VI" , 'R' );
			h.put( "DI" , 'S' );
			h.put( "IA" , 'T' );
			h.put( "DG" , 'U' );
			h.put( "IX" , 'V' );
			h.put( "AD" , 'W' );
			h.put( "VF" , 'X' );
			h.put( "FV" , 'Y' );
			h.put( "DA" , 'Z' );
		
			h.put( "XX" , '1' );
			h.put( "GD" , '2' );
			h.put( "AV" , '3' );
			h.put( "XI" , '4' );
			h.put( "FA" , '5' );
			h.put( "XI" , '6' );
			h.put( "II" , '7' );
			h.put( "AF" , '8' );
			h.put( "IV" , '9' );
			h.put( "VA" , '0' );
			
			h.put( "FF" , ':' );
			h.put( "AA" , ';' );
			h.put( "FI" , ',' );
			h.put( "XF" , '.' );
			h.put( "VD" , '\"' );
			h.put( "DF" , '!' );
			h.put( "XV" , '-' );
			h.put( "IF" , '(' );
			h.put( "DX" , ')' );
			h.put( "AI" , ' ' );
			h.put( "GG" , '?' );
			h.put( "VG" , '\n' );
			h.put( "KK" , '\'');
			h.put( "KV" , '*' );
			h.put( "KG" , '=');
			h.put( "KX" , '/');	
	
	}

	public char getValue(char [] c)
	{
		return h.get(String.valueOf(c));
	}
	
	public String decrypt(String fileName) throws IOException
	{
		PrintWriter writer = new PrintWriter("out2.txt", "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Scanner scanner = new Scanner(System.in);
		
		int i, rows, m = 0;	
		int last = 0, tempI;
		StringBuilder sb = new StringBuilder();
		
		System.out.print("Keyword: ");
		String input = scanner.next();
		
		char [] keyword = input.toCharArray(); 	
		ArrayList<CharacterLink> CharLinkList = new ArrayList<CharacterLink>();
		ArrayList<CharacterLink> SortedKeyword = new ArrayList<CharacterLink>();
		
		int index = 0;
		for(char c : keyword)
		{
			CharacterLink link = new CharacterLink(c,index++);
			CharLinkList.add(link);
		}
		SortedKeyword= (ArrayList<CharacterLink>) CharLinkList.clone();
		Collections.sort(SortedKeyword);
		System.out.println("getting char count");
		int count = 0;
		while(br.ready())
		{
			br.read();
			count++;
		}
		br.close();
		System.out.println(count);
		
		rows = count/keyword.length;
		int remainder = count%keyword.length;
		int collumns = keyword.length;	
		int rFlag = 0;
		if(remainder > 0)
			rFlag = 1;
		char [][] matrix = new char [collumns][rows+rFlag];
		
		br = new BufferedReader(new FileReader(fileName));
		int oddFlag = 1, row, collumn;
		char [] temp = new char[2];
		
		System.out.println("line 123");
		
		
		for(CharacterLink CL : SortedKeyword)
		{
			collumn = CL.getVal();
			if(collumn < remainder)
				rFlag = 1;
			else
				rFlag = 0;
			for(row = 0; row < rows + rFlag; row++)
			{	
			
				matrix[collumn][row] = (char) br.read();
			}			
		}
		
		StringBuffer finalString = new StringBuffer();
		
		int j=0;
		if(remainder > 0)
			rFlag = 1;
		
		for(row=0;row<rows+rFlag;row++)
		{
			if(rFlag == 1 && row==rows)
				collumns = remainder;
				
				
			for(collumn = 0;collumn<collumns;collumn++)
			{
				if(oddFlag++ % 2 != 0)
					temp[0] = matrix[collumn][row];
				else
				{
					temp[1] = matrix[collumn][row];
					
					finalString.append(this.getValue(temp));
					
				}
			}
		}
		
	


		
		
		
		writer.printf("%s", finalString);
		scanner.close();
		writer.close();
		
		return "Finally DONE";
		
	}
}
