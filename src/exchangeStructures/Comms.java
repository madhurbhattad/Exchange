package exchangeStructures;

import java.util.LinkedList;

import fills.Fill;

public class Comms 
{
	public String comments;
	
	public Comms()
	{
		this.comments = "Exchange Comments";
	}
	
	private LinkedList<OrderConfirmations> RestingOrderConfirmations = new LinkedList<OrderConfirmations>();

	public LinkedList<OrderConfirmations> getRestingOrderConfirmations() 
	{
		return RestingOrderConfirmations;
	}

	public void setRestingOrderConfirmations(LinkedList<OrderConfirmations> restingOrderConfirmations) 
	{
		RestingOrderConfirmations = restingOrderConfirmations;
	} 
	
	public void addRestingOrderConfirmation(OrderConfirmations RestingOrderConfirmation)
	{
		this.RestingOrderConfirmations.add(RestingOrderConfirmation);
	}

	private LinkedList<Fill> Fills = new LinkedList<Fill>(); 
	
	public LinkedList<Fill> getFills() 
	{
		return Fills;
	}

	public void setFills(LinkedList<Fill> fills) 
	{
		Fills = fills;
	}
	
	public void addFills(Fill f)
	{
		this.Fills.add(f);
	}
}
