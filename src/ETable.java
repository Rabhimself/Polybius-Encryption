import java.io.*;
import java.util.*;


public class ETable extends HashMap<String,String> 
{
	private static final long serialVersionUID = 1L;
	Map<Character, String> h = new HashMap<Character, String>();
	Map<Character, Queue<Character>> qMap = new HashMap<Character, Queue<Character>>();
	
	public ETable()
	{
		
		h.put( 'A' , "FG");
		h.put( 'B' , "ID");
		h.put( 'C' , "VV");
		h.put( 'D' , "GI");
		h.put( 'E' , "FX");
		h.put( 'F' , "XD");
		h.put( 'G' , "DD");
		h.put( 'H' , "IG");
		h.put( 'I' , "DV");
		h.put( 'J' , "XG");
		h.put( 'K' , "GA");
		h.put( 'L' , "XA");
		h.put( 'M' , "GV");
		h.put( 'N' , "FD");
		h.put( 'O' , "AG");
		h.put( 'P' , "AX");
		h.put( 'Q' , "GF");
		h.put( 'R' , "VI");
		h.put( 'S' , "DI");
		h.put( 'T' , "IA");
		h.put( 'U' , "DG");
		h.put( 'V' , "IX");
		h.put( 'W' , "AD");
		h.put( 'X' , "VF");
		h.put( 'Y' , "FV");
		h.put( 'Z' , "DA");
	
		h.put( '1' , "XX" );
		h.put( '2' , "GD" );
		h.put( '3' , "AV" );
		h.put( '4' , "XI" );
		h.put( '5' , "FA" );
		h.put( '6' , "XI" );
		h.put( '7' , "II" );
		h.put( '8' , "AF" );
		h.put( '9' , "IV" );
		h.put( '0' , "VA" );
		
		h.put( ':' , "FF" );
		h.put( ';' , "AA" );
		h.put( ',' , "FI" );
		h.put( '.' , "XF" );
		h.put( '\"' , "VD" );
		h.put( '!' , "DF" );
		h.put( '-' , "XV" );
		h.put( '(' , "IF" );
		h.put( ')' , "DX" );
		h.put( ' ' , "AI" );
		h.put( '?' , "GG" );
		h.put( '\n' , "VG" );	
		
		
		h.put( '\'' , "KK");
		h.put( '*' , "KV" );
		h.put( '=' , "KG");
		h.put( '/' , "KX");	
	}
	

	public char [] getValue(char c)
	{
		return h.get(Character.toUpperCase(c)).toCharArray();
	}

	
	public String parseString(String fileName) throws IOException
	{
		PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Scanner scanner = new Scanner(System.in);
		int i, rows, m = 0, index =0;	
		int last = 0, tempI;
		StringBuilder sb = new StringBuilder();
		
		System.out.print("Keyword: ");
		String input = scanner.next();
		char [] keyword = input.toCharArray(); 	
		ArrayList<CharacterLink> CharLinkList = new ArrayList<CharacterLink>();	
		
		for(char c : keyword)
		{
			
			CharacterLink link = new CharacterLink(c,index++);
			CharLinkList.add(link);
		}
		Collections.sort(CharLinkList);
		
		
		//count the document length
		int count = 0;
		while(br.ready())
		{
			br.read();
			count++;
		}
		br.close();
		///done////
		
		count*=2;
		
		//setup the matrix of characters
		rows = count/keyword.length;
		int remainder = count%keyword.length;
		int rFlag = 0;
		if(remainder > 0)
			rFlag = 1;
		int collumns = keyword.length;	
		char [][] matrix = new char [collumns][(rows+rFlag)];
		
		//reload the document
		br = new BufferedReader(new FileReader(fileName));
		int oddFlag = 1, row, collumn;
		
		
		//get a character, then store it in alternating collumns
		
		
		char temp[] = new char[2];
		temp = (this.getValue((char)br.read()));
		
		for(row = 0; row < (rows + rFlag); row++)
		{
			for(collumn = 0;collumn <collumns;collumn++)
			{	
				//we need to alternate between the two characters in the character array
				if(oddFlag++ % 2 != 0)
					matrix[collumn][row] = temp[0];
				else
				{
					matrix[collumn][row] = temp[1];					
					if(br.ready())
					{
						temp = (this.getValue((char)br.read()));
					}
					else
					{
						collumn = collumns;
						row = rows+1;
						break;
					}
				}
			}
		}
		br.close();
		
		for(CharacterLink CL: CharLinkList)
		{
			collumn = CL.getVal();
			//check rFlag
			if(CL.getVal() < remainder)
				rFlag = 1;
			for(row = 0;row<(rows+rFlag);row++)
			{
				if(matrix[collumn][row] != 0x0)
					writer.printf("%s",matrix[collumn][row]);
			}
		}
		
		scanner.close();
		writer.close();
		
		return("DONE");
	}
	
	
	
}
