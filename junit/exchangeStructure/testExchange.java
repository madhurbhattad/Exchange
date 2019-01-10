package exchangeStructure;

import exchangeStructures.*;
import orderSpecs.*;
import orderTypes.*;
import junit.framework.*;

public class testExchange extends TestCase 
{

	//@Test
	//public void test() {
		//fail("Not yet implemented");
	//}
	
	public void testEmptyBooks()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
	}
	
	public void testClientOrder()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(
			clientId0,
			clientOrderId0,
			marketId0,
			side0,
			quantity0,
			price0
		);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		// The exchange should know about this order
		assertTrue( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) );
	}
	
	public void testgeneratingRestingOrder()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(
			clientId0,
			clientOrderId0,
			marketId0,
			side0,
			quantity0,
			price0
		);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		// The exchange should know about this order
		assertTrue( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) );

		// Make sure that the above sweeping order generated a resting order
		// Specify the price level we will examine
		Price priceOfPriceLevel = sweepingOrder.getPrice();
		// We want the first order at that price level
		int orderIndex = 0; 
		assertTrue( 
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.get( priceOfPriceLevel )
				.getOrders()
				.get( orderIndex )
				.equals( new RestingOrder( sweepingOrder ) ) 
		);	
	}
	
	public void testMultipleOrders()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(
			clientId0,
			clientOrderId0,
			marketId0,
			side0,
			quantity0,
			price0
		);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		// The exchange should know about this order
		assertTrue( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) );

		// Make sure that the above sweeping order generated a resting order
		// Specify the price level we will examine
		Price priceOfPriceLevel = sweepingOrder.getPrice();
		// We want the first order at that price level
		int orderIndex = 0; 
		assertTrue( 
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.get( priceOfPriceLevel )
				.getOrders()
				.get( orderIndex )
				.equals( new RestingOrder( sweepingOrder ) ) 
		);
		
		// Make sure that the market sent an alert to the client
		// about the new resting order via the fake comms link
		RestingOrder restingOrder = exchange.getComms().getRestingOrderConfirmations().getLast().getRestingOrder();
		assertTrue( restingOrder.equals( new RestingOrder( sweepingOrder ) ) );
		assertTrue( restingOrder.getQuantity().equals( new Quantity( 1000 ) ) );
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
		assertTrue( exchange.getMarket(marketId0).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket(marketId0).getOfferBook().getPriceLevels().firstKey().getOrders().isEmpty());
	}
	
	public void testMultipleOrders2()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(
			clientId0,
			clientOrderId0,
			marketId0,
			side0,
			quantity0,
			price0
		);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		// The exchange should know about this order
		assertTrue( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) );

		// Make sure that the above sweeping order generated a resting order
		// Specify the price level we will examine
		Price priceOfPriceLevel = sweepingOrder.getPrice();
		// We want the first order at that price level
		int orderIndex = 0; 
		assertTrue( 
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.get( priceOfPriceLevel )
				.getOrders()
				.get( orderIndex )
				.equals( new RestingOrder( sweepingOrder ) ) 
		);
		
		// Make sure that the market sent an alert to the client
		// about the new resting order via the fake comms link
		RestingOrder restingOrder = exchange.getComms().getRestingOrderConfirmations().getLast().getRestingOrder();
		assertTrue( restingOrder.equals( new RestingOrder( sweepingOrder ) ) );
		assertTrue( restingOrder.getQuantity().equals( new Quantity( 1000 ) ) );
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
		assertTrue( exchange.getMarket(marketId0).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket(marketId0).getOfferBook().getPriceLevels().firstKey().getOrders().isEmpty());
		
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
		assertTrue(
			exchange
				.getMarket( marketId2 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		);
		
		
		// Make sure that this last sweeping order didn't match - there 
		// was nothing to match with - and became a resting order
		orderIndex = 0;
		assert(
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
		);
		
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
		
		// There should be two price levels in the bid book
		assertTrue(
			exchange
				.getMarket( marketId3 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		);

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
		assertTrue(
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 3
		);
		// There should be no price levels in the offer book
		assertTrue(
			exchange
				.getMarket( marketId0 )
				.getOfferBook()
				.getPriceLevels()
				.size()
			== 0
		);
		
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
		assertTrue(
			exchange
				.getMarket( new MarketId( "IBM" ) )
				.getOfferBook()
				.getPriceLevels()
				.get( new Price( 1260000L ) )
				.getOrders()
				.size()
			== 1
		);
		assertTrue(
			exchange
				.getMarket( new MarketId( "IBM" ) )
				.getBidBook()
				.getPriceLevels()
				.get( new Price( 1250000L ) )
				.getOrders()
				.size()
			== 1
		);
		
	}
	
	public void testRemovalOfRestingOrder()
	{
		// Create an exchange and add one market
		Exchange exchange = new Exchange();
		MarketId marketId0 = new MarketId( "IBM" );
		Market market = new Market( exchange, marketId0 );
		exchange.addMarket( market );
		
		// Before we start, there should be no price levels
		// in either the bid book or the offer book of the
		// above market
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 0 );
		
		// Create a client order
		ClientId clientId0 = new ClientId( "Lee" );
		ClientOrderId clientOrderId0 = new ClientOrderId( "ABC" );
		Side side0 = Side.BUY;
		Quantity quantity0 = new Quantity( 1000L );
		Price price0 = new Price( 1280000 );
		SweepingOrder sweepingOrder = new SweepingOrder(
			clientId0,
			clientOrderId0,
			marketId0,
			side0,
			quantity0,
			price0
		);

		// Sweep the exchange with this order
		exchange.sweep( sweepingOrder );
		
		// No part of the first order to buy 1000 shares at $128
		// was executed because the offer book was empty. So 
		// the entire order became a resting order in the bid 
		// book at a price level of $128.
		
		// There should be one price level in the bid book
		assertTrue( exchange.getMarket( marketId0 ).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket( marketId0 ).getOfferBook().getPriceLevels().size() == 0 );
		// The exchange should know about this order
		assertTrue( exchange.getOrder( sweepingOrder.getClientOrderId() ).equals( new RestingOrder( sweepingOrder ) ) );

		// Make sure that the above sweeping order generated a resting order
		// Specify the price level we will examine
		Price priceOfPriceLevel = sweepingOrder.getPrice();
		// We want the first order at that price level
		int orderIndex = 0; 
		assertTrue( 
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.get( priceOfPriceLevel )
				.getOrders()
				.get( orderIndex )
				.equals( new RestingOrder( sweepingOrder ) ) 
		);
		
		// Make sure that the market sent an alert to the client
		// about the new resting order via the fake comms link
		RestingOrder restingOrder = exchange.getComms().getRestingOrderConfirmations().getLast().getRestingOrder();
		assertTrue( restingOrder.equals( new RestingOrder( sweepingOrder ) ) );
		assertTrue( restingOrder.getQuantity().equals( new Quantity( 1000 ) ) );
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
		assertTrue( exchange.getMarket(marketId0).getBidBook().getPriceLevels().size() == 1 );
		// There should be no price levels in the offer book
		assertTrue( exchange.getMarket(marketId0).getOfferBook().getPriceLevels().firstKey().getOrders().isEmpty());
		
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
		assertTrue(
			exchange
				.getMarket( marketId2 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		);
		
		
		// Make sure that this last sweeping order didn't match - there 
		// was nothing to match with - and became a resting order
		orderIndex = 0;
		assert(
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
		);
		
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
		
		// There should be two price levels in the bid book
		assertTrue(
			exchange
				.getMarket( marketId3 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 2
		);

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
		assertTrue(
			exchange
				.getMarket( marketId0 )
				.getBidBook()
				.getPriceLevels()
				.size()
			== 3
		);
		// There should be no price levels in the offer book
		assertTrue(
			exchange
				.getMarket( marketId0 )
				.getOfferBook()
				.getPriceLevels()
				.size()
			== 0
		);
		
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
		assertTrue(
			exchange
				.getMarket( new MarketId( "IBM" ) )
				.getOfferBook()
				.getPriceLevels()
				.get( new Price( 1260000L ) )
				.getOrders()
				.size()
			== 1
		);
		assertTrue(
			exchange
				.getMarket( new MarketId( "IBM" ) )
				.getBidBook()
				.getPriceLevels()
				.get( new Price( 1250000L ) )
				.getOrders()
				.size()
			== 1
		);
		
		// Make sure old orders were unregistered
		assertTrue( exchange.getOrder( clientOrderId0 ) == null );
		assertTrue( exchange.getOrder( clientOrderId1 ) == null );
		assertTrue( exchange.getOrder( clientOrderId2 ) == null );
		assertTrue( exchange.getOrder( clientOrderId3 ) == null );
				
	}
	
}
