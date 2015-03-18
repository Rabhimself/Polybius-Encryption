import java.io.*;
import java.util.*;


public class ETable extends HashMap<String,String> 
{
	private static final long serialVersionUID = 1L;
	Map<String, String> h = new HashMap<String, String>();
	Map<Character, Queue<Character>> qMap = new HashMap<Character, Queue<Character>>();
	
	ArrayList<Queue<Character>> l= new ArrayList<Queue<Character>>();
	
	public ETable()
	{
		
		h.put( "A" , "FG");
		h.put( "B" , "ID");
		h.put( "C" , "VV");
		h.put( "D" , "GI");
		h.put( "E" , "FX");
		h.put( "F" , "XD");
		h.put( "G" , "DD");
		h.put( "H" , "IG");
		h.put( "I" , "DV");
		h.put( "J" , "XG");
		h.put( "K" , "GA");
		h.put( "L" , "XA");
		h.put( "M" , "GV");
		h.put( "N" , "FD");
		h.put( "O" , "AG");
		h.put( "P" , "AX");
		h.put( "Q" , "GF");
		h.put( "R" , "VI");
		h.put( "S" , "DI");
		h.put( "T" , "IA");
		h.put( "U" , "DG");
		h.put( "V" , "IX");
		h.put( "W" , "AD");
		h.put( "X" , "VF");
		h.put( "Y" , "FV");
		h.put( "Z" , "DA");
	
		h.put( "1" , "XX" );
		h.put( "2" , "GD" );
		h.put( "3" , "AV" );
		h.put( "4" , "XI" );
		h.put( "5" , "FA" );
		h.put( "6" , "XI" );
		h.put( "7" , "II" );
		h.put( "8" , "AF" );
		h.put( "9" , "IV" );
		h.put( "0" , "VA" );
		
		h.put( ":" , "FF" );
		h.put( ";" , "AA" );
		h.put( "," , "FI" );
		h.put( "." , "XF" );
		h.put( "\"" , "VD" );
		h.put( "!" , "DF" );
		h.put( "-" , "XV" );
		h.put( "(" , "IF" );
		h.put( ")" , "DX" );
		h.put( " " , "AI" );
		h.put( "?" , "GG" );
		h.put( "\n" , "VG" );		
	}
	

	public String getValue(char c)
	{
		String s = "" + Character.toUpperCase(c);
		return h.get(s);
	}

	
	public String parseString(StringBuilder s) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
		Scanner scanner = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int m, i;
		
		System.out.print("Keyword: ");
		String input = scanner.next();
		char [] keyword = input.toCharArray(); 
	
		Queue<Character> q = new LinkedList<Character>();	
		
		for(char c: keyword)
		{
			qMap.put(c, new LinkedList<Character>());			
		}
		
		for(i = 0; i < s.length(); i++)
		{
			String s1 = this.getValue(s.charAt(i));
			
			if(s1!=null){
			q.offer(s1.charAt(0));
			q.offer(s1.charAt(1));}
			
		}
		
	
		//System.gc();
			
		while(!q.isEmpty())
		{
			for(char c: keyword)
			{
				if(q.peek() != null)
					qMap.get(c).add(q.poll());
			}	
		}
	

		
		Arrays.sort(keyword);
		
		for(char c: keyword)
		{
			m = qMap.get(c).size();	
			for(i = 0 ; i < m ; i++)
			{
				if((qMap.get(c).peek() != null))
				sb.append(qMap.get(c).poll());	
			}
		}		
		
		writer.printf("%s", sb);
		scanner.close();
		writer.close();
		return "DONE";
		
	}
	
	
	
}
