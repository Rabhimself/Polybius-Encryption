
public class CharacterLink implements Comparable<CharacterLink>
{
	private char key;
	private int val;
	
	public CharacterLink(char k, int v)
	{
		this.setKey(k);
		this.setVal(v);		
	}
	
	public char getKey()
	{
		return key;
	}
	public void setKey(char key)
	{
		this.key = key;
	}
	public int getVal()
	{
		return val;
	}
	public void setVal(int val)
	{
		this.val = val;
	}

	@Override
	public int compareTo(CharacterLink other)
	{
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
		if(this.getKey()<other.getKey())
			return -1;
		else if(this.getKey()>other.getKey())
			return 1;
		else
			return 0;
	}
	
}
