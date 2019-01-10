package exchangeStructures;

import orderSpecs.MarketId;

public class Market 
{
	private Exchange exchange;
	private MarketId marketId;
	private OfferBook offerBook;
	private BidBook bidBook;
	
	public Market(Exchange _exchange, MarketId _marketId)
	{
		this.setExchange(_exchange);
		this.setMarketId(_marketId);
		this.offerBook = new OfferBook();
		this.bidBook = new BidBook();
	}

	public Exchange getExchange() 
	{
		return exchange;
	}

	public void setExchange(Exchange exchange) 
	{
		this.exchange = exchange;
	}

	public MarketId getMarketId() 
	{
		return marketId;
	}

	public void setMarketId(MarketId marketId) 
	{
		this.marketId = marketId;
	}
	
	public OfferBook getOfferBook()
	{
		return this.offerBook;
	}
	
	public BidBook getBidBook()
	{
		return this.bidBook;
	}
}
