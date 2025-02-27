package com.comcast.CRM.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	//Get Random Number
	public int getRandomNumber()
	{
		Random random = new Random();
		int randomnum = random.nextInt(4999);
		
		return randomnum;
	}
	
	//Get Current Date in a Format as YYYY-MM-DD
	
	public String getSystemDateYYYYMMDD()
	{
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		
		String currentdate = sim.format(date);
		
		return currentdate;
	}
	
	//Get required date in a format
	
	public String getRequiredDateYYYYMMDD(int days)
	{
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requireddate = sim.format(cal.getTime());
		
		return requireddate;
		
	}

}
