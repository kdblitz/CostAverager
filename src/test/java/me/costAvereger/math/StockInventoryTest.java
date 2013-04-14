package me.costAvereger.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockInventoryTest {
	StockInventory data;
	
	@Before
	public void init() {
		data = new StockInventory();
	}
	
	@Test
	public void testAddStockLinearSample() {
		data.buyStock(100, 5);
		data.buyStock(100, 5);
		Assert.assertEquals(10, data.getStockQuantity() );
		Assert.assertEquals(100, data.getAverageCost(), 0.001);
		Assert.assertEquals(100, data.getCurrentStockPrice(), 0.001);
		Assert.assertEquals(0, data.getIncomePerShare(), 0.001);
	}
	
	@Test
	public void testAddStockIncreasingPrice() {
		data.buyStock(100, 5);
		data.buyStock(200, 5);
		Assert.assertEquals(10, data.getStockQuantity() );
		Assert.assertEquals(150, data.getAverageCost(), 0.001);
		Assert.assertEquals(200, data.getCurrentStockPrice(), 0.001);
		Assert.assertEquals(50, data.getIncomePerShare(), 0.001);
	}
	
	@Test
	public void testAddStockDecreasingPrice() {
		data.buyStock(200, 1);
		data.buyStock(100, 3);
		Assert.assertEquals(4, data.getStockQuantity() );
		Assert.assertEquals(125, data.getAverageCost(), 0.001);
		Assert.assertEquals(100, data.getCurrentStockPrice(), 0.001);
		Assert.assertEquals(-25, data.getIncomePerShare(), 0.001);
	}
}
