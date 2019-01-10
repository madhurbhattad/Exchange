package exchangeStructures;

import java.util.LinkedList;
//import java.util.ArrayList;
//import java.util.List;
import java.util.TreeMap;

import fills.Fill;
//import orderSpecs.ClientId;
//import orderSpecs.ClientOrderId;
//import orderSpecs.MarketId;
import orderSpecs.Price;
import orderSpecs.Quantity;
//import orderSpecs.Side;
import orderTypes.RestingOrder;
import orderTypes.SweepingOrder;

public class OfferBook 
{
	// We use Tree Map because they remain sorted on addition and removal of new element!
	// We need to over write collections.sort before beginning the actual implementation
	// Thus we use PriceComparator in Constructor. Since we need the sorting to be in ascending order.
	
	private TreeMap<Price, Price> offerbook;
	private LinkedList<Fill> filledorders = new LinkedList<Fill>();

	public OfferBook()
	{
		this.setOfferbook(new TreeMap<Price, Price>(new PriceComparator()));
	}
	
	public TreeMap<Price, Price> getOfferbook() 
	{
		return offerbook;
	}

	public void setOfferbook(TreeMap<Price, Price> offerbook) 
	{
		this.offerbook = offerbook;
	}
	
	public TreeMap<Price, Price> getPriceLevels()
	{
		return getOfferbook();
	}
	
	public void addToOfferBook(RestingOrder R)
	{
		Price P = R.getPrice();
		P.addOrder(R);
		//if(!this.offerbook.containsKey(P))
		//{
			//this.offerbook.put(P, P);
		//}
		this.offerbook.put(P, P);
	}
	
	// This is used while using sweep function in exchange
	public SweepingOrder ExecuteOffering(SweepingOrder Sorder)
	{
		int q = Sorder.getQuantity().getQuantity();
		Price p = Sorder.getPrice();
		double price = p.getPrice();
		// The loop runs till the bid price is greater than the offer prices
		while((!this.offerbook.isEmpty())&&(this.offerbook.firstKey().getPrice()<=price))
		{
			Price key = this.offerbook.firstKey();
			int i=0;
			while((!key.getOrders().isEmpty())&&(key.getOrders().get(i).getQuantity().getQuantity() <= q))
			{
				q= q-key.getOrders().get(i).getQuantity().getQuantity();
				
				// This part is used to fill the filledorders list in the book
				
				Fill tempFill1 = new Fill();
				SweepingOrder sweepedOrder1 = Sorder;
				sweepedOrder1.setQuantity(new Quantity(key.getOrders().get(i).getQuantity().getQuantity()));
				tempFill1.setSweepedOrder(sweepedOrder1);
				tempFill1.setCounterpartyId(key.getOrders().get(i).getClientId());
				this.filledorders.add(tempFill1);
				
				key.getOrders().remove();
				if(q==0)
				{
					break;
				}
			}
			if(!key.getOrders().isEmpty()&&(key.getOrders().get(i).getQuantity().getQuantity() > q))
			{
				//int transq= q;
				int tempq = key.getOrders().get(i).getQuantity().getQuantity()-q;
				q=0;
				
				// This part is used to fill the filledorders list in the book
				
				Fill tempFill2 = new Fill();
				SweepingOrder sweepedOrder2 = Sorder;
				tempFill2.setSweepedOrder(sweepedOrder2);
				tempFill2.setCounterpartyId(key.getOrders().get(i).getClientId());
				this.filledorders.add(tempFill2);
				
				RestingOrder tempR = key.getOrders().get(i);
				key.getOrders().remove();
				Quantity tempQuantity = new Quantity(tempq);
				tempR.setQuantity(tempQuantity);
				key.getOrders().addFirst(tempR);
				break;
			}
			//else 
			//{
				//this.offerbook.remove(key);
			//}
			this.offerbook.remove(key);
		}
		Quantity newquantity = new Quantity(q);
		Sorder.setQuantity(newquantity);
		return Sorder;
	}

	public LinkedList<Fill> getFilledorders() 
	{
		return filledorders;
	}

	public void setFilledorders(LinkedList<Fill> filledorders) 
	{
		this.filledorders = filledorders;
	}
}


