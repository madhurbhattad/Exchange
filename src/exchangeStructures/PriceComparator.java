package exchangeStructures;

import java.util.Comparator;

import orderSpecs.Price;

public class PriceComparator implements Comparator<Price>
{
	@Override
	public int compare(Price p1, Price p2)
	{
		if(p1.getPrice() > p2.getPrice())
		{
			return 1;
		}
		else if(p1.getPrice() == p2.getPrice())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}
