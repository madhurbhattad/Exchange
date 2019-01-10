package exchangeStructures;

import orderTypes.RestingOrder;

public class OrderConfirmations 
{
	private RestingOrder R;
	private String S;
	
	public RestingOrder getRestingOrder() 
	{
		return R;
	}
	public void setRestingOrder(RestingOrder r) 
	{
		R = r;
	}
	public String getMessage() 
	{
		return S;
	}
	public void setMessage(String s) 
	{
		S = s;
	}
	
	public OrderConfirmations(RestingOrder R, String S)
	{
		this.setRestingOrder(R);
		this.setMessage(S);
	}
}
