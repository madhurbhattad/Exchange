package orderTypes;

import orderSpecs.*;

public class RestingOrder 
{
	private ClientId _ClientId;
	private ClientOrderId _ClientOrderId;
	private MarketId _MarketId;
	private Side _Side;
	private Quantity _Quantity;
	private Price _Price;
	
	public RestingOrder(SweepingOrder Sorder)
	{
		this.setClientId(Sorder.getClientId());
		this.setClientOrderId(Sorder.getClientOrderId());
		this.setMarketId(Sorder.getMarketId());
		this.setSide(Sorder.getSide());
		this.setQuantity(Sorder.getQuantity());
		this.setPrice(Sorder.getPrice());
	}

	public ClientId getClientId() 
	{
		return _ClientId;
	}

	public void setClientId(ClientId _ClientId) 
	{
		this._ClientId = _ClientId;
	}

	public ClientOrderId getClientOrderId() 
	{
		return _ClientOrderId;
	}

	public void setClientOrderId(ClientOrderId _ClientOrderId) 
	{
		this._ClientOrderId = _ClientOrderId;
	}

	public MarketId getMarketId() 
	{
		return _MarketId;
	}

	public void setMarketId(MarketId _MarketId) 
	{
		this._MarketId = _MarketId;
	}

	public Side getSide() 
	{
		return _Side;
	}

	public void setSide(Side _Side) 
	{
		this._Side = _Side;
	}

	public Quantity getQuantity() 
	{
		return _Quantity;
	}

	public void setQuantity(Quantity _Quantity) 
	{
		this._Quantity = _Quantity;
	}

	public Price getPrice() 
	{
		return _Price;
	}

	public void setPrice(Price _Price) 
	{
		this._Price = _Price;
	}
	
	public boolean equals(RestingOrder r)
	{
		int decider = 0;
		if(this.getClientId().getClientid() == r.getClientId().getClientid())
		{
			decider++;
		}
		if(this.getClientOrderId().getClientOrderId() == r.getClientOrderId().getClientOrderId())
		{
			decider++;
		}
		if(this.getMarketId().getId() == r.getMarketId().getId())
		{
			decider++;
		}
		if(this.getPrice().getPrice() == r.getPrice().getPrice())
		{
			decider++;
		}
		if(this.getQuantity().getQuantity() == r.getQuantity().getQuantity())
		{
			decider++;
		}
		if(this.getSide().getSide() == r.getSide().getSide())
		{
			decider++;
		}
		if(decider == 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
