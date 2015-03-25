import java.io.*;
import java.util.*;


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
	
	@SuppressWarnings("unchecked")
	public String decrypt(String fileName) throws IOException
	{
		PrintWriter writer = new PrintWriter("out2.txt", "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Scanner scanner = new Scanner(System.in);
		ArrayList<CharacterLink> CharLinkList = new ArrayList<CharacterLink>();
		ArrayList<CharacterLink> SortedKeyword = new ArrayList<CharacterLink>();
		int rows, oddFlag = 1, row, column, index =0;
		
		System.out.print("Keyword: ");
		String input = scanner.next();
		
		char [] keyword = input.toCharArray(); 	

		//we go through each character in the keyword, and map it to it's original index
		//this will be used later when transposing the array properly
		for(char c : keyword)
		{
			CharacterLink link = new CharacterLink(c,index++);
			CharLinkList.add(link);
		}
		
		//Create an alphabetized list of the keyword. Instead of sorting the array columns
		//I just accessed the array columns in alphabetical order
		SortedKeyword= (ArrayList<CharacterLink>) CharLinkList.clone();
		Collections.sort(SortedKeyword);
		
		
		//We need to first get the length of the file, in order to set the array dimensions correctly
		System.out.println("getting char count");
		int count = 0;
		while(br.ready())
		{
			br.read();
			count++;
		}
		br.close();
		//close the file
		
		//now set dimensions based on the char count of the file
		rows = count/keyword.length;
		int remainder = count%keyword.length;
		int columns = keyword.length;	
		int rFlag = 0;
		if(remainder > 0)
			rFlag = 1;//this variable will be used to extend loop iterations if needed
		char [][] matrix = new char [columns][rows+rFlag];//as well as help determine the size of the array
		
		//reopen the file
		br = new BufferedReader(new FileReader(fileName));
		
		char [] temp = new char[2];
		
		//Now to go through the reordered keyword and begin filling
		//each column based on the index returned by the .getVal();
		for(CharacterLink CL : SortedKeyword)
		{
			column = CL.getVal();
			
			//If the column originally held an extra character, run the iteration an extra time
			if(column < remainder)
				rFlag = 1;
			else
				rFlag = 0;
			
			for(row = 0; row < rows + rFlag; row++)
			{	
				//filling the array is done at constant time, since the location is known
				//had I used arraylists with the .add method, adding would be slow, since the end
				//of the array list is found with an internal loop.
				matrix[column][row] = (char) br.read();
			}			
		}
		
		StringBuffer finalString = new StringBuffer();
		
		//if the last row is not full, we set a flag
		if(remainder > 0)
			rFlag = 1;
		
		//This entire loop structure's running time will be linear
		//as each character in the 2dim array will be read
		for(row=0;row<rows+rFlag;row++)
		{
			//the flag will be used to set the number of iterations ran
			//over the last row, as to avoid printing null characters
			if(rFlag == 1 && row==rows)
				columns = remainder;
				
				
			for(column = 0;column<columns;column++)
			{
				//using arrays, so accessing each character at a known location is done in constant time
				if(oddFlag++ % 2 != 0)
					temp[0] = matrix[column][row];
				else
				{
					temp[1] = matrix[column][row];
					
					//since a string buffer is used, the string pool
					//is kept clean, and garbage collection is done minimally
					finalString.append(this.getValue(temp));
					
				}
			}
		}		
		
		//close my files
		writer.printf("%s", finalString);
		scanner.close();
		writer.close();
		
		return "Finally DONE";
		
	}
}
