package me.costAvereger.math;

import org.json.JSONArray;
import org.json.JSONException;

public class StatsCalculator {
	JSONArray data;
	public StatsCalculator(JSONArray dataArray) {
		data = dataArray;
	}
	
	public StockInventory calculateCostAverage(double budget, int interval, int boardLot) {
		StockInventory inventory = new StockInventory();
		for (int ctr = 0;ctr<data.length();ctr+=interval) {
			try {
				JSONArray rawData = data.getJSONArray(ctr);
				double costPerShare = rawData.getDouble(1);
				int buyableShare = computeBuyableShare(budget, costPerShare, boardLot);
				inventory.buyStock(costPerShare, buyableShare);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
		return inventory;
	}
	
	private int computeBuyableShare(double budget, double costPerShare, int boardLot) {
		int buyableShareWithoutBoardLot = (int)(budget / costPerShare);
		int buyableShareWithBoardLot = buyableShareWithoutBoardLot - (buyableShareWithoutBoardLot % boardLot);
		return buyableShareWithBoardLot;
	}
}
