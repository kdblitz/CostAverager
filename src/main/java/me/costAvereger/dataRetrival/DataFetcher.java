package me.costAvereger.dataRetrival;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class DataFetcher {
	public abstract String fetch();
	
	protected String readAll(BufferedReader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		String data;
		while((data = rd.readLine())!=null) {
			sb.append(data);
		}
		return sb.toString();
	}
}
