package com.AssTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.asskick.AssKick;
import com.asskick.CourseIntent;
import com.asskick.Semester;

import android.content.Context;
import junit.framework.TestCase;

public class AssKickClassTestCase extends TestCase 
{
	String assKickName;
	String semesterName;
	String courseName;
	Method[] methods;
	Field[] fields;
	public void setUp()
	{
		try 
		{
			super.setUp();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		assKickName = AssKick.class.getName().toString();
		semesterName = Semester.class.getName().toString();
		courseName = CourseIntent.class.getName().toString();
		fields = Semester.class.getFields();
		methods = Semester.class.getMethods();
	}
	public void testNameOfClass()
	{
		assertEquals("assKickNameTest", "com.asskick.AssKick", assKickName);
		assertEquals("semseterNameTest", "com.asskick.Semester", semesterName);
		assertEquals("courseNameTest", "com.asskick.CourseIntent", courseName);
		for(int i = 0; i < fields.length; i++)
		{
			String str = fields[i].getName().toString();
			System.out.println(str);
		}
		for(int i = 0; i < methods.length; i++)
		{
			String str = methods[i].getName().toString();
			System.out.println(str);
		}
		AssKick a = new AssKick();
//		assertEquals("courseNameTest", "com.asskick.CourseIntent", a.getComponentName());
	}
	
	public void simthing()
	{
		
	}
}
