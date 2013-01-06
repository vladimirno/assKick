package com.asskick;

//import android.annotation.TargetApi;
//import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Semester extends Activity 
{

	int numOfCourses = 0;
    public final static String EXTRA_MESSAGE = "com.assKick.MESSAGE";

    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semester);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if(savedInstanceState != null)
		{
			
		}
	}
    
/*    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) 
    {
    	super.onSaveInstanceState(savedInstanceState);
    	savedInstanceState.
	}
  */  
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//      super.onRestoreInstanceState(savedInstanceState);
//    }
    
/*    @Override	  
    public void onPause()
    {
    	super.onPause();
    	
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    }
  */  

    @Override
    protected void onNewIntent(Intent intent) 
    {
        super.onNewIntent(intent);
	    String message = intent.getStringExtra(Semester.EXTRA_MESSAGE);

	    if(!message.equals(""))
	    {
		    numOfCourses++;
		    
			Button button = setButton(numOfCourses);
			button.setText(message);
		    
			button.setVisibility(View.VISIBLE);
	    }
    }
    
/*
	@Overrid
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_semester, menu);
		return true;
	}
*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
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

	public void addCourse(View view)
    {
		Intent course = new Intent(this, Semester.class);
		course.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		EditText enterCourse = (EditText) findViewById(R.id.enter_course);
    	String message = enterCourse.getText().toString();
    	course.putExtra(EXTRA_MESSAGE, message);
		startActivity(course);
    }

    public void addEx(View view) 
    {
    	Intent ex = new Intent(this, CourseIntent.class);
    	startActivity(ex);
    }

	public void enterDate(View view)
	{

	}
}
//Calendar beginTime = Calendar.getInstance();
//beginTime.set(2012, 11, 19, 7, 30);
//Calendar endTime = Calendar.getInstance();
//endTime.set(2012, 11, 19, 10, 30);
//Intent calIntent = new Intent(Intent.ACTION_INSERT)
//        .setData(Events.CONTENT_URI)
//        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
//        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
//        .putExtra(Events.TITLE, message)
//        .putExtra(Events.DESCRIPTION, "from ass kick")
////        .putExtra(Events.EVENT_LOCATION, "The gym")
//        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
////        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
//startActivity(calIntent);
