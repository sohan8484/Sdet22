package com.crm.vtiger.genericUtils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class FileUtility {
	/*
	 *author @ sohan 
	 */
	/**
	 * @This method is used to read the data from the property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getPropertyKeyValue(String key)throws IOException {
		FileInputStream fis = new FileInputStream(IPathConstants.PROPERTY_FILEPATH);
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}
	/**
	 * this method is used to read the data from the json file
	 * @param jsonKey
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public String getDataFromJson(String jsonKey) throws IOException, ParseException {
		FileReader reader = new FileReader(IPathConstants.JSONFILEPATH);
		JSONParser parser = new JSONParser();
		Object obj=parser.parse(reader);
		JSONObject jsonObject= (JSONObject)obj;
		String value = jsonObject.get(jsonKey).toString();
		return value;
	}
}
