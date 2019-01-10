package fills;

import orderSpecs.ClientId;
import orderSpecs.ClientOrderId;
import orderSpecs.Quantity;
//import orderSpecs.MarketId;
//import orderTypes.RestingOrder;
import orderTypes.SweepingOrder;

public class Fill 
{
	private SweepingOrder sweepedOrder;
	private ClientId CounterPartyId;
	
	public Fill()
	{
		//MarketId Mid = R.getMarketId();
	}

	public SweepingOrder getSweepedOrder() 
	{
		return sweepedOrder;
	}

	public void setSweepedOrder(SweepingOrder sweepedOrder) 
	{
		this.sweepedOrder = sweepedOrder;
	}

	public ClientId getClientId()
	{
		return this.getSweepedOrder().getClientId();
	}
	
	public Quantity getQuantity()
	{
		return this.getSweepedOrder().getQuantity();
	}
	
	public ClientOrderId getClientOrderId()
	{
		return this.getSweepedOrder().getClientOrderId();
	}
	
	public ClientId getCounterpartyId() 
	{
		return CounterPartyId;
	}

	public void setCounterpartyId(ClientId counterPartyId) 
	{
		CounterPartyId = counterPartyId;
	}
}
