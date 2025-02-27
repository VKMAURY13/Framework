package com.comcast.CRM.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws IOException
	{   
		FileInputStream fis = new FileInputStream("./configAppData/vtcommondata.properties");
		Properties p = new Properties();
		p.load(fis);
		
		return p.getProperty(key);
	}
	
	public String getDataFromJSONFile(String key) throws IOException, ParseException
	{
		FileReader fr = new FileReader("./confiAppData/appcommondata.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);
		JSONObject jsonobject = (JSONObject)obj;
		
		return jsonobject.get(key).toString();
		
	}

}
