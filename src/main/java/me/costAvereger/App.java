package me.costAvereger;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import me.costAvereger.dataRetrival.JsonRetriever;
import me.costAvereger.math.StatsCalculator;
import me.costAvereger.math.StatsCalculator.Container;
import me.costAvereger.math.StockInventory;

public class App 
{
	public static void main(String[] args) throws IOException, JSONException {
		Scanner in = new Scanner(System.in);

		boolean tryAgain;
		do {
			//Ask user input
			System.out.print("Input stockCode: ");
			String stockCode = in.next();
			System.out.print("Board lot: ");
			int boardLot = in.nextInt();
			System.out.print("Your budget: ");
			double budget = in.nextDouble();
			System.out.print("Interval (can be in days/weeks/months)\n" +
					"Example: 15d, 1w, or 2m means (15 days, 1 week, 2 months):");
			String interval = in.next();
			
			//Retrieve data
			String urlString = "http://www.bloomberg.com/markets/chart/data/1Y/"+stockCode+":PM";
			JsonRetriever ret = new JsonRetriever(urlString);
			JSONObject obj = ret.readJsonFromUrl();
	
			//Calculate stuff
			StatsCalculator calc = new StatsCalculator(obj.getJSONArray("data_values"));
			Container info = calc.calculateCostAverage(budget,interval,boardLot);
			System.out.println(info.showSummary());
			System.out.print("another stock(y/n)?");
			String tmp = in.next();
			tryAgain = tmp.equals("y")?true:false;
		} while(tryAgain);
	}
}
