package me.costAvereger.math;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

public class StatsCalculator {
	public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	JSONArray data;
	public StatsCalculator(JSONArray dataArray) {
		data = dataArray;
	}
	
	/**
	 * Simulates the "stock days" during the past 1 year. Stores data in a {@link Container} object, which contains:
	 * 
	 * <ul>
	 * <li> {@link StockInventory} </li>
	 * <li> {@link Statistics} </li>
	 * </ul>
	 * 
	 * @param budget
	 * @param interval
	 * @param boardLot
	 * @return
	 */
	public Container calculateCostAverage(double budget, String interval, int boardLot) {
		StockInventory inventory = new StockInventory();
		Statistics tracker = new Statistics();
		Container container = new Container(inventory, tracker);
		
		Scheduler scheduler = null;

		for (int ctr = 0;ctr<data.length();ctr++) {
			try {
				JSONArray rawData = data.getJSONArray(ctr);
				Calendar currentDate = Calendar.getInstance();
				currentDate.setTimeInMillis(rawData.getLong(0));

				if(scheduler == null) {
					scheduler = new Scheduler(interval,currentDate);
				}
				
				double costPerShare = rawData.getDouble(1);
				
				if (scheduler.isTimeToBuy(currentDate)) {
					int buyableShare = computeBuyableShare(budget, costPerShare, boardLot);
					inventory.buyStock(costPerShare, buyableShare);
					scheduler.scheduleNextInvestment();
					System.out.println("bought on "+sdf.format(currentDate.getTime())+" "+buyableShare+" shares "+" at "+costPerShare);
				}
				
				recordStatistics(inventory, tracker, costPerShare);
			} catch (JSONException e) {
				e.printStackTrace();
				return container;
			}
		}
		return container;
	}

	private void recordStatistics(StockInventory inventory, Statistics tracker, double costPerShare) {
		if (costPerShare > inventory.getAverageCost()) {
			tracker.recordGain(inventory.getOverallIncome());
		}else {
			tracker.recordLoss(inventory.getOverallIncome());
		}
	}
	
	private int computeBuyableShare(double budget, double costPerShare, int boardLot) {
		int buyableShareWithoutBoardLot = (int)(budget / costPerShare);
		int buyableShareWithBoardLot = buyableShareWithoutBoardLot - (buyableShareWithoutBoardLot % boardLot);
		return buyableShareWithBoardLot;
	}
	
	public static class Container {
		public StockInventory inventory;
		public Statistics tracker;
		
		public Container(StockInventory inventory, Statistics tracker) {
			this.inventory = inventory;
			this.tracker = tracker;
		}
		
		public String showSummary() {
			StringBuilder sb = new StringBuilder();
			sb.append("Inventory info:\n");
			sb.append(inventory.showSummary());
			sb.append("Statistics:\n");
			sb.append(tracker.toString());
			return sb.toString();
		}
	}
	
	/**
	 * Schedules the next investment day
	 */
	static class Scheduler {
		/**
		 * Unit ex. month/day/week etc
		 */
		private int period;
		/**
		 * X number days/week/month to be added
		 */
		private int interval;
		/**
		 * When next investment is scheduled
		 */
		private Calendar investmentDay;
		
		public Scheduler(String interval, Calendar startDay) {
			interval = interval.trim();
			period = getPeriod(interval.charAt(interval.length()-1));
			this.interval = Integer.parseInt(interval.substring(0, interval.length()-1));
			investmentDay = (Calendar)startDay.clone();
		}
		
		public int getPeriod(char abbr) {
			if (abbr == 'd' || abbr == 'D')
				return Calendar.DAY_OF_MONTH;
			else if (abbr == 'w' || abbr == 'W') 
				return Calendar.WEEK_OF_MONTH;
			else if (abbr == 'm' || abbr == 'M')
				return Calendar.MONTH;
			else 
				throw new IllegalArgumentException("abbr not recognized ("+abbr+") only d/w/m supported for now");
		}
		
		public void scheduleNextInvestment() {
			investmentDay.add(period, interval);
		}
		
		boolean isTimeToBuy(Calendar currentDate) {
			return currentDate.equals(investmentDay) || currentDate.after(investmentDay);
		}
	}
}
