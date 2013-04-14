package me.costAvereger.math;

/**
 * Clas which stores statistics related on a specific stock
 *
 */
public class StockInventory {
	/**
	 * Number of stocks bought
	 */
	private int stocksBought;
	/**
	 * Average cost of stock per share, based on when the share was bought
	 */
	private double averageCost;
	/**
	 * The current price of the stock
	 */
	private double currentCost;
	
	public void buyStock(double currentCost, int quantityToBuy) {
		this.currentCost = currentCost;

		int newQuantity = stocksBought + quantityToBuy;
		double oldAverageCost = (double)stocksBought/newQuantity * averageCost;
		double toAddAverageCost = (double)quantityToBuy/newQuantity*currentCost;

		averageCost = oldAverageCost + toAddAverageCost;
		stocksBought = newQuantity;
	}
	
	public int getStockQuantity() {
		return stocksBought;
	}
	
	public double getAverageCost() {
		return averageCost;
	}
	
	public double getCurrentStockPrice() {
		return currentCost;
	}
	
	/**
	 * Gets the income/loss overall per share. Positive value denotes income, negative denotes loss.
	 * @return income/loss
	 */
	public double getIncomePerShare() {
		return currentCost - averageCost;
	}
	
	public double getOverallIncome() {
		return getIncomePerShare() * stocksBought;
	}
}
