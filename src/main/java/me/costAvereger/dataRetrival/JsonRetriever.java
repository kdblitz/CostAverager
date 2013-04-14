package me.costAvereger.dataRetrival;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRetriever {
	final static String CACHE_DIR = "cache";
	final static boolean isCacheEnabled = true;
	
	DataFetcher dataFetcher;
	String cacheFileName;
	
	public JsonRetriever(String urlString) {
		cacheFileName = urlString.substring(urlString.lastIndexOf('/')+1).replace(':', '-') + ".dat";

		if (isCacheEnabled) {
			LocalFileReader tmpReader = new LocalFileReader(cacheFileName);
			if (tmpReader.doesFileExist()) {
				dataFetcher = tmpReader;
			} else {
				dataFetcher = new UrlReader(urlString);
			}
		} else {
			dataFetcher = new UrlReader(urlString);
		}
	}
	
	public JSONObject readJsonFromUrl() throws IOException, JSONException {
		String jsonText = dataFetcher.fetch();
		
		if (isCacheEnabled && dataFetcher instanceof UrlReader) {
			save(cacheFileName,jsonText);
		}
		
		JSONObject json = new JSONObject(jsonText);
		return json;
	}
	
	private void save(String fileName,String data) {
		PrintWriter fileWriter = null;
		try {
			File cacheDir = new File(CACHE_DIR);
			if (!cacheDir.exists()) {
				cacheDir.mkdir();
			}
			fileWriter = new PrintWriter(CACHE_DIR+"/"+fileName);
			fileWriter.println(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null)
				fileWriter.close();
		}
	}


}
