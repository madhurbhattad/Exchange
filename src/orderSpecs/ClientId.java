package orderSpecs;

public class ClientId 
{
	private String clientid;
	
	public ClientId(String id)
	{
		this.setClientid(id);
	}

	public String getClientid() 
	{
		return clientid;
	}

	public void setClientid(String clientid) 
	{
		this.clientid = clientid;
	}
	
	public boolean equals(ClientId x)
	{
		if(this.getClientid() == x.getClientid())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
