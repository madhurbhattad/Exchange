package orderSpecs;

import java.util.LinkedList;

import orderTypes.RestingOrder;

public class Price 
{
	private double price;
	private LinkedList<RestingOrder> listOrders = new LinkedList<RestingOrder>();
	
	public Price(double priceValue)
	{
		this.setPrice(priceValue);
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public void addOrder(RestingOrder R)
	{
		if(R.getQuantity().getQuantity()>0)
		{
			this.listOrders.add(R);	
		}
	}
	
	public LinkedList<RestingOrder> getOrders()
	{
		return this.listOrders;
	}
	
	public boolean equals(Price x)
	{
		if(this.price == x.getPrice())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
