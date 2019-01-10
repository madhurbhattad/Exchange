package exchangeStructures;

import java.util.LinkedList;

import fills.Fill;
import orderSpecs.ClientId;
import orderSpecs.ClientOrderId;
import orderSpecs.MarketId;
import orderSpecs.Price;
import orderSpecs.Quantity;
import orderSpecs.Side;
import orderTypes.RestingOrder;
import orderTypes.SweepingOrder;

public class Exchange 
{
	private LinkedList<Market> marketList = new LinkedList<Market>();
	private LinkedList<RestingOrder> restingOrderList = new LinkedList<RestingOrder>(); 
	
	public String exchangeId;
	
	// We can have multiple exchanges, but for the purpose of the assignment, we model a dummy exchange
	public Exchange()
	{
		this.exchangeId = "Dummy Exchange";
	}
	
	// Add a market to the list of markets in the exchange if it is not included
	public void addMarket(Market market)
	{
		if(!this.marketList.contains(market))
		{
			this.marketList.add(market);
		}
	}
	
	// Get a market from the market list given market id
	public Market getMarket(MarketId id)
	{
		int flag= -1;
		for(int i=0; i<this.marketList.size(); i++)
		{
			if(this.marketList.get(i).getMarketId().equals(id))
			{
				flag =i; 
				break;
			}
		}
		return this.marketList.get(flag);
	}
	
	// getComms method
	private Comms ExchangeComms = new Comms();
	
	public Comms getComms() 
	{
		return ExchangeComms;
	}

	public void setComms(Comms exchangeComms) 
	{
		ExchangeComms = exchangeComms;
	}
	
	// Sweeping function for the exchange
	public void sweep(SweepingOrder Sorder)
	{
		MarketId Mid = Sorder.getMarketId();
		int flag=0;
		for(int i=0; i<this.marketList.size(); i++)
		{
			if(this.marketList.get(i).getMarketId().equals(Mid))
			{
				flag =i; 
				break;
			}
		}
		if(Sorder.getSide().equals(Side.BUY))
		{
			//RestingOrder R = new RestingOrder(Sorder);
			//this.marketList.get(flag).getBidBook().addToBidBook(R);
			//this.restingOrderList.add(R);
			
			SweepingOrder TempOrder= this.marketList.get(flag).getOfferBook().ExecuteOffering(Sorder);
			//If order can be executed completely
			if(TempOrder.getQuantity().getQuantity() == 0)
			{
				//this.marketList.get(flag).getBidBook().addToBidBook(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(Sorder), "Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
				// Add the filled orders in the Comms class
				LinkedList<Fill> filledOrders = this.marketList.get(flag).getOfferBook().getFilledorders();
				//LinkedList<Fill> filledfromOrders = new LinkedList<Fill>(); 
				for(int i=0; i< filledOrders.size(); i++)
				{
					this.getComms().addFills(filledOrders.get(i));
				}
			}
			//If the order can't be executed completely
			if((TempOrder.getQuantity().getQuantity()!= 0)&&(TempOrder.getQuantity().getQuantity()<Sorder.getQuantity().getQuantity()))
			{
				this.marketList.get(flag).getBidBook().addToBidBook(new RestingOrder(TempOrder));
				this.restingOrderList.add(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(TempOrder), "Partially Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
				LinkedList<Fill> filledOrders = this.marketList.get(flag).getOfferBook().getFilledorders();
				for(int i=0; i< filledOrders.size(); i++)
				{
					this.getComms().addFills(filledOrders.get(i));
				}
			}
			//If the order isn't executed at all
			if(TempOrder.getQuantity().getQuantity() == Sorder.getQuantity().getQuantity())
			{
				this.marketList.get(flag).getBidBook().addToBidBook(new RestingOrder(TempOrder));
				this.restingOrderList.add(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(TempOrder), "Not Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
			}
		}
		else if(Sorder.getSide().equals(Side.SELL))
		{
			//RestingOrder R = new RestingOrder(Sorder);
			//this.marketList.get(flag).getOfferBook().addToOfferBook(R);
			//this.restingOrderList.add(R);
			
			SweepingOrder TempOrder= this.marketList.get(flag).getBidBook().ExecuteBidding(Sorder);
			//If order can be executed completely
			if(TempOrder.getQuantity().getQuantity() == 0)
			{
				//this.marketList.get(flag).getOfferBook().addToOfferBook(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(Sorder), "Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
				LinkedList<Fill> filledOrders = this.marketList.get(flag).getBidBook().getFilledorders();
				for(int i=0; i< filledOrders.size(); i++)
				{
					this.getComms().addFills(filledOrders.get(i));
				}
			}
			//If the order can't be executed completely
			if((TempOrder.getQuantity().getQuantity()!= 0)&&(TempOrder.getQuantity().getQuantity()<Sorder.getQuantity().getQuantity()))
			{
				this.marketList.get(flag).getOfferBook().addToOfferBook(new RestingOrder(TempOrder));
				this.restingOrderList.add(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(TempOrder), "Partially Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
				LinkedList<Fill> filledOrders = this.marketList.get(flag).getBidBook().getFilledorders();
				for(int i=0; i< filledOrders.size(); i++)
				{
					this.getComms().addFills(filledOrders.get(i));
				}
			}
			//If the order isn't executed at all
			if(TempOrder.getQuantity().getQuantity() == Sorder.getQuantity().getQuantity())
			{
				this.marketList.get(flag).getOfferBook().addToOfferBook(new RestingOrder(TempOrder));
				this.restingOrderList.add(new RestingOrder(TempOrder));
				OrderConfirmations tempOrderConfirmation = new OrderConfirmations(new RestingOrder(TempOrder), "Not Executed!");
				this.getComms().addRestingOrderConfirmation(tempOrderConfirmation);
			}
		}
	}
	
	// This method refreshes the resting order list
	
	public void refresh()
	{
		this.restingOrderList = new LinkedList<RestingOrder>();
		for(int i=0; i< this.marketList.size(); i++)
		{
			//int bid = this.marketList.get(i).getBidBook().getPriceLevels().size();
			LinkedList<Price> l = new LinkedList<Price>(this.marketList.get(i).getBidBook().getPriceLevels().keySet());
			for(int j=0; j< l.size(); j++)
			{
				int bid = l.get(j).getOrders().size();
				for(int jt=0; jt<bid; jt++)
				{
					RestingOrder tempOrder = l.get(j).getOrders().get(jt);
					if(tempOrder != null)
					{
						this.restingOrderList.add(tempOrder);
					}
				}
			}
			LinkedList<Price> m = new LinkedList<Price>(this.marketList.get(i).getOfferBook().getPriceLevels().keySet());
			for(int k=0; k< m.size(); k++)
			{
				int offer = m.get(k).getOrders().size();
				for(int kt=0; kt<offer; kt++)
				{
					RestingOrder tempOrder = l.get(k).getOrders().get(kt);
					if(tempOrder != null)
					{
						this.restingOrderList.add(tempOrder);
					}
				}
			}
		}
	}
	
	// Write getOrder method
	public RestingOrder getOrder(ClientOrderId cid)
	{
		this.refresh();
		int flag = -1;
		for(int i=0; i<this.restingOrderList.size(); i++)
		{
			if(this.restingOrderList.get(i).getClientOrderId().equals(cid))
			{
				flag=i;
				break;
			}
		}
		if(flag!= -1)
		{
			return this.restingOrderList.get(flag);
		}
		else
		{
			return null;
		}
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Testing");
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		if( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 )
		{
			System.out.println("Empty offer book tested!");
		}
		if( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 )
		{
			System.out.println("Empty bid book tested!");
		}
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(clientId0,clientOrderId0,marketId0,side0,quantity0,price0);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		if( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 )
		{
			System.out.println("Successful bid order addition!");
		}
		// There should be no price levels in the offer book
		if( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 )
		{
			System.out.println("The offer book is still empty!");
		}
		// The exchange should know about this order
		if( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) )
		{
			System.out.println("The exchange receives the order!");
		}
		
		//Some random debugging
		//RestingOrder r = exchange.getOrder( sweepingOrder.getClientOrderId() );
		//System.out.println(r.getClientId().getClientid()+ " " + r.getQuantity().getQuantity()+ " " + r.getPrice().getPrice());
		//RestingOrder r1 = new RestingOrder(sweepingOrder);
		//System.out.println(r1.getClientId().getClientid()+ " " + r1.getQuantity().getQuantity()+ " " + r1.getPrice().getPrice());
		

		// Make sure that the above sweeping order generated a resting order
		// Specify the price level we will examine
		Price priceOfPriceLevel = sweepingOrder.getPrice();
		// We want the first order at that price level
		int orderIndex = 0; 
		if( 
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.get( priceOfPriceLevel )
				.getOrders()
				.get( orderIndex )
				.equals( new RestingOrder( sweepingOrder ) ) 
		)
		{
			System.out.println("Sweeping Order Generates a Resting Order!");
		}
		
		// Make sure that the market sent an alert to the client
		// about the new resting order via the fake comms link
		RestingOrder restingOrder = exchange.getComms().getRestingOrderConfirmations().getLast().getRestingOrder();
		if( restingOrder.equals( new RestingOrder( sweepingOrder ) ) )
		{
			System.out.println("Order Confirmation Works Test1");
		}
		if( restingOrder.getQuantity().equals( new Quantity( 1000 ) ) )
		{
			System.out.println("Order Confirmation Works Test2");
		}
		// The message is sitting in comms just so we can check
		// it. Now that we've checked it, we can remove it so it
		// doesn't interfere with the rest of our tests.
		exchange.getComms().getRestingOrderConfirmations().removeLast();
		
		// We're moving on to our second order, a sell of 500
		// shares. It will match partially with our 1st order
		// which is now a resting order in the bid book.
		ClientId clientId1 = new ClientId( "Bob" );
		ClientOrderId clientOrderId1 = new ClientOrderId( "VZFZF" );
		MarketId marketId1 = new MarketId( "IBM" );
		Side side1 = Side.SELL;
		Quantity quantity1 = new Quantity( 500L ); // Half of the 1000 that's already in the book
		Price price1 = new Price( 1200000L );
		SweepingOrder sweepingOrder1 = new SweepingOrder(
			clientId1,
			clientOrderId1,
			marketId1,
			side1,
			quantity1,
			price1
		);
		// Sweep exchange with this order
		exchange.sweep( sweepingOrder1 );
		
		// There should be one price level in the bid book
		if( exchange.getMarket( marketId1 ).getBidBook().getPriceLevels().size() == 1 )
		{
			System.out.println("There are still elements in bid book");
		}
		// There should be no price levels in the offer book
		if( exchange.getMarket( marketId1 ).getOfferBook().getPriceLevels().firstKey().getOrders().isEmpty())
		{
			System.out.println("Offer book is empty!");
		}
		
		
		// Some debugging
	    //boolean ae = exchange.getMarket(marketId1).getOfferBook().getPriceLevels().firstKey().getOrders().isEmpty();
		//System.out.println(ae);
		
		// Executing Fill
		Fill fill = exchange.getComms().getFills().get(0);
		System.out.println(exchange.getComms().getFills().size());
		System.out.println("Expected client id " + fill.getClientId().getClientid());
		System.out.println("Expected clientorder id " + fill.getClientOrderId().getClientOrderId());
		System.out.println("Expected counterparty id " + fill.getCounterpartyId().getClientid());
		System.out.println("Expected quantity " + fill.getQuantity().getQuantity());
		exchange.getComms().getFills().removeLast();
		System.out.println(exchange.getComms().getFills().size());
		
		orderIndex = 0; 
		// Retrieve that order and check its contents
		restingOrder = exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().get( priceOfPriceLevel ).getOrders().get( orderIndex );
		// It should have an unfilled quantity of 500 shares
		if( restingOrder.getQuantity().equals( new Quantity( 500L ) ) )
		{
			System.out.println("This Works");
		}
		// It should have the clientOrderId of the original sweeping
		// order that created it
		if( restingOrder.getClientId().equals( sweepingOrder.getClientId() ) )
		{
			System.out.println("This Works");
		}
		
		// There are 500 shares sitting in the bid book at $128
		// We will now add three orders to the bid book then sweep
		// with a sell order that takes out the first three
		// orders in the bid book but, because of its limit price
		// doesn't match with the fourth. The remainder of the
		// sweeping order will then be added to the offer book
		// as a resting order.
		
		ClientId clientId2 = new ClientId( "Steve" );
		ClientOrderId clientOrderId2 = new ClientOrderId( "UnP17az" );
		MarketId marketId2 = new MarketId( "IBM" );
		Side side2 = Side.BUY;
		Quantity quantity2 = new Quantity( 300 );
		Price price2 = new Price( 1270000 );
		SweepingOrder sweepingOrder2 = new SweepingOrder(
			clientId2,
			clientOrderId2,
			marketId2,
			side2,
			quantity2,
			price2
		);
		
		// Sweep exchange with this order
		exchange.sweep( sweepingOrder2 );
		
		// Make sure there are now two price levels for the bid book of
		// market "IBM"
		if(
			exchange
				.getMarket( marketId2 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		)
		{
			System.out.println("There are two prices in the bid book now!");
		}
		
		// Make sure that this last sweeping order didn't match - there 
		// was nothing to match with - and became a resting order
		orderIndex = 0;
		if(
			exchange
				.getMarket(marketId2)
				.getBidBook()
				.getPriceLevels()
				.get( price2 )
				.getOrders()
				.get( orderIndex )
				.equals( 
					new RestingOrder( sweepingOrder2 ) 
				)
		)
		{
			System.out.println("The last order has nothing to match with and becomes a resting order!");
		}
		
		// There should now be two orders in the bid book, one
		// for 500 shares at $128 and one for 300 shares at $127
		// We will add another order at $127 to makes sure that
		// price level correctly manages more than one order
		
		ClientId clientId3 = clientId2; // Same client
		ClientOrderId clientOrderId3 = new ClientOrderId( "llLWE" );
		MarketId marketId3 = new MarketId( "IBM" );
		Side side3 = Side.BUY;
		Quantity quantity3 = new Quantity( 300 );
		Price price3 = new Price( 1270000 );
		SweepingOrder sweepingOrder3 = new SweepingOrder(
			clientId3,
			clientOrderId3, // Same client, different order
			marketId3,
			side3,
			quantity3,
			price3
		);
		
		// Sweep exchange with this order
		exchange.sweep( sweepingOrder3 );
		
		// Make sure exchange knows about this order
		if( exchange.getOrder( clientOrderId3 ) != null )
		{
			System.out.println("We receive another buy order!");
		}
		
		// There should be two price levels in the bid book
		if(
			exchange
				.getMarket( marketId3 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		)
		{
			System.out.println("There are two price levels in the bid book");
		}
		
		// There should now be three orders in the bid book, one
		// for 500 shares at $128, one for 300 shares at $127,
		// and another one for 300 shares at $127. We will add
		// a fourth order at $125.
		
		ClientId clientId4 = clientId3;
		ClientOrderId clientOrderId4 = new ClientOrderId( "ZuPER" );
		MarketId marketId4 = new MarketId( "IBM" );
		Side side4 = Side.BUY;
		Quantity quantity4 = new Quantity( 200 );
		Price price4 = new Price( 1250000 );
		SweepingOrder sweepingOrder4 = new SweepingOrder(
				clientId4,
				clientOrderId4, // Same client, different order
				marketId4,
				side4,
				quantity4,
				price4
		);
		exchange.sweep( sweepingOrder4 );
		
		// There should now be four orders in the bid book, one
		// for 500 shares at $128, one for 300 shares at $127,
		// another one for 300 shares at $127, and an order for
		// 200 shares at $125. 
		
		// There should be three price levels in the bid book
		if(
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 3
		)
		{
			System.out.println("We receive a third order and there are three price levels in the bid book now.");
		}
		
		// There should be no price levels in the offer book
		if(exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size()== 0)
		{
			System.out.println("Offer Book is empty.");
		}
		
		// We will now sweep with a sell order for 1500 shares 
		// at $126. It should generate 3 fills for the clients
		// and 3 for the counterparties. The 4th order in the 
		// book is below the sweeping order's sell price of 
		// $126. The sweeping order will then become a resting 
		// order to sell 400 shares at $126.
		
		ClientId clientId5 = new ClientId( "Eric");
		ClientOrderId clientOrderId5 = new ClientOrderId( "BqUCC5" );
		MarketId marketId5 = new MarketId( "IBM" );
		Side side5 = Side.SELL;
		Quantity quantity5 = new Quantity( 1500 );
		Price price5 = new Price( 1260000 );
		SweepingOrder sweepingOrder5 = new SweepingOrder(
			clientId5,
			clientOrderId5,
			marketId5,
			side5,
			quantity5,
			price5
		);
		exchange.sweep( sweepingOrder5 );
		
		// There should now be two orders in the book, an
		// order to sell 400 shares at $126 in the offer book,
		// and an order to buy 200 shares at $125 in the bid
		// book. Confirm that there is one order on each side.
		if(
			exchange
				.getMarket( new MarketId( "IBM" ) )
				.getOfferBook()
				.getPriceLevels()
				.get( new Price( 1260000L ) )
				.getOrders()
				.size()
			== 1
		)
		{
			System.out.println("There is an element in the offer book");
		}
		
		if(
				exchange
					.getMarket( new MarketId( "IBM" ) )
					.getBidBook()
					.getPriceLevels()
					.get( new Price( 1250000L ) )
					.getOrders()
					.size()
				== 1
		)
		{
			System.out.println("There is an element in the bid book");
		}
		
		
		// Making sure that old orders were unregistered
		
		if( exchange.getOrder( clientOrderId0 ) == null )
		{
			System.out.println("Old orders successfully unregistered");
		}
		if( exchange.getOrder( clientOrderId1 ) == null )
		{
			System.out.println("Old orders successfully unregistered");
		}
		if( exchange.getOrder( clientOrderId2 ) == null )
		{
			System.out.println("Old orders successfully unregistered");
		}
		if( exchange.getOrder( clientOrderId3 ) == null )
		{
			System.out.println("Old orders successfully unregistered");
		}
	}
	
}
