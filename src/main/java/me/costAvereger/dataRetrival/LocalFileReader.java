package me.costAvereger.dataRetrival;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class LocalFileReader extends DataFetcher {
	String fileName;
	public LocalFileReader(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String fetch() {
		System.out.println("using cache");
		try {
			return readJsonFromFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String readJsonFromFile(String fileName) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(JsonRetriever.CACHE_DIR+"/"+fileName));
			return readAll(reader);
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
	/**
	 * Determine if the latest data file was already downloaded.
	 * @return true if file exist and was retrieved today
	 */
	public boolean doesFileExistAndLatest() {
		File file = new File(JsonRetriever.CACHE_DIR+"/"+fileName);
		if (file.exists()) {
			// file was downloaded today
			Calendar modifiedDate = Calendar.getInstance();
			modifiedDate.setTimeInMillis(file.lastModified());

			Calendar today = Calendar.getInstance();
			return modifiedDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) 
					&& modifiedDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
		}
		// file doesn't exist
		return false; 
	}
}
