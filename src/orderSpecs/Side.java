package orderSpecs;

public class Side 
{
	public static final Side BUY = new Side("BUY");
	public static final Side SELL = new Side("SELL");
	
	private String side;
	
	public Side(String sideValue)
	{
		this.setSide(sideValue);
	}

	public String getSide() 
	{
		return side;
	}

	public void setSide(String side) 
	{
		this.side = side;
	}
	
	public boolean isEquals(Side s)
	{
		if(this.side == s.side)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
