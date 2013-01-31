package com.AssTest;

import junit.framework.Assert;

import com.asskick.AssKick;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class SemesterTestCase extends ActivityInstrumentationTestCase2 
{
	Context mContext;
	public SemesterTestCase()
	{
		super("com.AssTest",AssKickClassTestCase.class);
	}
	
	public void setUp()
	{
		try
		{
			super.setUp();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mContext = this.getInstrumentation().getContext();
	}
	
	public void testGetSomthing()
	{
		//Assert.assertNotNull(mContext);
	}
}
