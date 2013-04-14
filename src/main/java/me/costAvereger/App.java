package me.costAvereger;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import me.costAvereger.dataRetrival.JsonRetriever;
import me.costAvereger.math.StatsCalculator;
import me.costAvereger.math.StockInventory;

public class App 
{
	public static void main(String[] args) throws IOException, JSONException {
		Scanner in = new Scanner(System.in);

		//Ask user input
		System.out.print("Input stockCode: ");
		String stockCode = in.nextLine();
		System.out.print("Board lot: ");
		int boardLot = in.nextInt();
		System.out.print("Your budget: ");
		double budget = in.nextDouble();
		System.out.print("Interval(in days): ");
		int interval = in.nextInt();
		
		//Retrieve data
		String urlString = "http://www.bloomberg.com/markets/chart/data/1Y/"+stockCode+":PM";
		JsonRetriever ret = new JsonRetriever(urlString);
		JSONObject obj = ret.readJsonFromUrl();

		//Calculate stuff
		StatsCalculator calc = new StatsCalculator(obj.getJSONArray("data_values"));
		StockInventory info = calc.calculateCostAverage(budget,interval,boardLot);
		System.out.println(info.getOverallIncome());
	}
}
