package orderSpecs;

public class MarketId 
{
	private String id; 
	
	public MarketId(String Id)
	{
		this.setId(Id);
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
	public boolean equals(MarketId i)
	{
		if(this.id == i.id)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
