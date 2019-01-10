package orderTypes;

import orderSpecs.*;

public class SweepingOrder 
{
	private ClientId _ClientId;
	private ClientOrderId _ClientOrderId;
	private MarketId _MarketId;
	private Side _Side;
	private Quantity _Quantity;
	private Price _Price;
	
	public SweepingOrder(ClientId clientId, ClientOrderId clientOrderId, MarketId marketId, Side side, Quantity quantity, Price price)
	{
		this.setClientId(clientId);
		this.setClientOrderId(clientOrderId);
		this.setMarketId(marketId);
		this.setSide(side);
		this.setQuantity(quantity);
		this.setPrice(price);
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

}
