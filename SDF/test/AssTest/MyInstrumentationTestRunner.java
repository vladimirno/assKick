package com.AssTest;

import junit.framework.TestSuite;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

public class MyInstrumentationTestRunner extends InstrumentationTestRunner 
{
	@Override
	public TestSuite getAllTests()
	{
		InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
		
		suite.addTestSuite(AssKickClassTestCase.class);
		//and you will keep an adding all the tests you want  this
		//rubber to run.
		//suite.addTestSuite(ContextDependentTextCase.calss)//we'll do this later
	
		suite.addTestSuite(SemesterTestCase.class);
		return suite;
	}
	
	@Override
	public ClassLoader getLoader()
	{
		return MyInstrumentationTestRunner.class.getClassLoader();
	}
}
