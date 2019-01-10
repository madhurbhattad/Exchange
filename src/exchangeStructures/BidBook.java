package exchangeStructures;

import java.util.LinkedList;
//import java.util.ArrayList;
//import java.util.List;
import java.util.TreeMap;

import fills.Fill;
import orderSpecs.Price;
import orderSpecs.Quantity;
//import orderSpecs.Quantity;
//import orderTypes.SweepingOrder;
import orderTypes.RestingOrder;
import orderTypes.SweepingOrder;

public class BidBook 
{
	// We use Tree Map because they remain sorted on addition and removal of new element!
	// We need to over write collections.sort before beginning the actual implementation
	// Thus we use AntiPriceComparator in Constructor. Since we need the sorting to be in descending order.
	
	private TreeMap<Price, Price> bidbook;
	private LinkedList<Fill> filledorders = new LinkedList<Fill>();

	public BidBook()
	{
		this.setOfferbook(new TreeMap<Price, Price>(new AntiPriceComparator()));
	}

	public TreeMap<Price, Price> getBidbook() 
	{
		return bidbook;
	}

	public void setOfferbook(TreeMap<Price, Price> bidbook) 
	{
		this.bidbook = bidbook;
	}
	
	public TreeMap<Price, Price> getPriceLevels()
	{
		return getBidbook();
	}
	
	public void addToBidBook(RestingOrder R)
	{
		Price P = R.getPrice();
		P.addOrder(R);
		//if(!this.bidbook.containsKey(P))
		//{
			//this.bidbook.put(P, P);
		//}
		this.bidbook.put(P, P);
	}
	
	public SweepingOrder ExecuteBidding(SweepingOrder Sorder)
	{
		int q = Sorder.getQuantity().getQuantity();
		Price p = Sorder.getPrice();
		double price = p.getPrice();
		// The loop runs till the bid price is greater than the offer prices
		while((!this.bidbook.isEmpty())&&(this.bidbook.firstKey().getPrice()>=price))
		{
			Price key = this.bidbook.firstKey();
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
				//int transq = q;
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
				//this.bidbook.remove(key);
			//}
			this.bidbook.remove(key);
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
