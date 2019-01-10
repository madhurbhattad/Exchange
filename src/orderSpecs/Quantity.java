package orderSpecs;

public class Quantity 
{
	private int quantity;
	
	public Quantity(long q)
	{
		this.setQuantity((int)q);
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public boolean equals(Quantity x)
	{
		if(this.quantity == x.quantity)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
