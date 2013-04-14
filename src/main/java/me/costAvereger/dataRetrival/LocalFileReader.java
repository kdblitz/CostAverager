package me.costAvereger.dataRetrival;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	
	public boolean doesFileExist() {
		File file = new File(JsonRetriever.CACHE_DIR+"/"+fileName);
		return file.exists();
	}
}
