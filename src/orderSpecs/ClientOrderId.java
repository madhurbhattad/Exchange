package orderSpecs;

public class ClientOrderId 
{
	private String clientOrderId;
	
	public ClientOrderId(String id)
	{
		this.setClientOrderId(id);
	}

	public String getClientOrderId() 
	{
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) 
	{
		this.clientOrderId = clientOrderId;
	}
	
	public boolean equals(ClientOrderId x)
	{
		if(this.getClientOrderId() == x.getClientOrderId())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
