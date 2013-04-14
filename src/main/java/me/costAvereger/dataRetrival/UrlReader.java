package me.costAvereger.dataRetrival;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;

public class UrlReader extends DataFetcher {
	String sourceUrl;
	public UrlReader(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	public String fetch() {
		System.out.println("downloading data");
		try {
			return readJsonFromUrl(sourceUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String readJsonFromUrl(String urlString) throws IOException {
		InputStream stream = new URL(urlString).openStream();
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(stream,Charset.forName("UTF-8")));
			return readAll(rd);
		} finally {
			if (rd != null)
				rd.close();
		}
	}
}
