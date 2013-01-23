package com.asskick;

//import android.annotation.TargetApi;
//import java.util.List;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Semester extends Activity 
{

	int numOfCourses = 0;
    public final static String COURSE_MESSAGE = "com.assKick.COURSE_MESSAGE";
    public final static String EX_MESSAGE = "com.assKick.EX_MESSAGE";
    public final static String ASSTEXT="asskick_course.txt";


    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semester);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		readFileInEditor();
	}
    
    @Override
    protected void onNewIntent(Intent intent) 
    {
        super.onNewIntent(intent);
	    String message = intent.getStringExtra(Semester.COURSE_MESSAGE);

	    if(!message.equals(""))
	    {
		    numOfCourses++;
		    
			Button button = setButton(numOfCourses);
			button.setText(message);
		    
			button.setVisibility(View.VISIBLE);
	    }
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void addCourse(View view)
    {
		Intent course = new Intent(this, Semester.class);
		course.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		EditText enterCourse = (EditText) findViewById(R.id.enter_course);
    	String message = enterCourse.getText().toString();
    	course.putExtra(COURSE_MESSAGE, message);

    	try 
		{
    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput(ASSTEXT, MODE_APPEND));
			out.write(message);
			out.write('\n');
			out.close();
			Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}
		startActivity(course);
    }

	public void readFileInEditor()
	{
		try 
		{
			InputStream in = openFileInput(ASSTEXT);
			if (in != null) 
			{
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				int i = 1;
				while ((str = reader.readLine()) != null)
				{
					Button button = setButton(i);
					button.setText(str);				    
					button.setVisibility(View.VISIBLE);
					numOfCourses = i;
					i++;
				}
				in.close();
			}
		}
		catch (java.io.FileNotFoundException e) 
		{
			// that's OK, we probably haven't created it yet
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private Button setButton(int num)
	{
		Button button = null;
		if(num == 1)
		{
		    button = (Button) findViewById(R.id.button1);	    				
		}
		else if(num == 2)
		{
		    button = (Button) findViewById(R.id.button2);	    				
		}
		else if(num == 3)
		{
		    button = (Button) findViewById(R.id.button3);	    				
		}
		else if(num == 4)
		{
		    button = (Button) findViewById(R.id.button4);	    				
		}
		else if(num == 5)
		{
		    button = (Button) findViewById(R.id.button5);	    				
		}
		else if(num == 6)
		{
		    button = (Button) findViewById(R.id.button6);	    				
		}
		else if(num == 7)
		{
		    button = (Button) findViewById(R.id.button7);	    				
		}
		else if(num == 8)
		{
		    button = (Button) findViewById(R.id.button8);	    				
		}
		else if(num == 9)
		{
		    button = (Button) findViewById(R.id.button9);	    				
		}
		else if(num == 10)
		{
		    button = (Button) findViewById(R.id.button10);	    				
		}
		else if(num == 11)
		{
		    button = (Button) findViewById(R.id.button11);	    				
		}
		else if(num == 12)
		{
		    button = (Button) findViewById(R.id.button12);	    				
		}
		return button;		
	}

    public void addEx1(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button1);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx2(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button2);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx3(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button3);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx4(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button4);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx5(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button5);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx6(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button6);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx7(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button7);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx8(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button8);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx9(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button9);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx10(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button10);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx11(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button11);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }
    public void addEx12(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	Button button = (Button) findViewById(R.id.button12);
    	String message = button.getText().toString();
    	ex.putExtra(EX_MESSAGE,message);
    	startActivity(ex);
    }

	public void enterDate(View view)
	{

	}
}