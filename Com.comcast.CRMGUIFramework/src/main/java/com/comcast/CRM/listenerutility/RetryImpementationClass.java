package com.comcast.CRM.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class RetryImpementationClass implements IRetryAnalyzer{
	int count = 0;
	int lastcount = 1;
	
	@Test
	public boolean retry(ITestResult result)
	{
		if(count<lastcount)
		{
			count++;
			return true;
		}
		else
			return false;
	}

}
