/*This is the course activity where we can add and manage our assignments*/

package com.asskick;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//TO DO
public class CourseIntent extends Activity 
{
    public final static String EX_MESSAGE_2 = "com.assKick.EX_MESSAGE_2";
    private int clickedEx = 1;

    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_intent);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		String Page_Name =  intent.getStringExtra(Semester.EX_MESSAGE);
		Page_Name += " Course";
		
		TextView text = (TextView) findViewById(R.id.course_name1);
		text.setText(Page_Name);
		
		String Course_File =  intent.getStringExtra(Semester.EX_MESSAGE);
		readFileInEditor(Course_File);
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
	
	public void addExercise(View view)
    {
		EditText enterNumOfEx = (EditText) findViewById(R.id.enter_num_of_ex);
    	String message = enterNumOfEx.getText().toString();

		Intent intent = getIntent();
		String Course_File = intent.getStringExtra(Semester.EX_MESSAGE);

		int index = 0;
		while(index < message.length())
		{
			if(message.charAt(index) < '0' || message.charAt(index) > '9')
			{
				Toast.makeText(this, "You can enter only integers.", Toast.LENGTH_LONG).show();
				return;				
			}
			index++;
		}

		int numOfEx = Integer.parseInt(message);
		if(numOfEx > 15)
		{
			Toast.makeText(this, "The max value is 15.", Toast.LENGTH_LONG).show();
			return;
		}
		if(numOfEx < 1)
		{
			Toast.makeText(this, "The min value is 1.", Toast.LENGTH_LONG).show();
			return;
		}

		try 
		{
    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput(Course_File, MODE_APPEND));
    		int i = 1;
    		while(i <= numOfEx)
    		{
				out.write(Integer.toString(i));
				out.write(" ");
				out.write("0");
				out.write("\n");
				CheckBox box = setCheckBox(i);
				Button button = setButtonView(i);
				button.setText(Integer.toString(i));
				box.setVisibility(View.VISIBLE);
				button.setVisibility(View.VISIBLE);
				i++;
    		}
			out.close();
			Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}
		
		Button button = (Button) findViewById(R.id.Submit);
		button.setVisibility(View.INVISIBLE);
		EditText textEdit = (EditText) findViewById(R.id.enter_num_of_ex);
		textEdit.setVisibility(View.INVISIBLE);
		TextView text = (TextView) findViewById(R.id.press_ex);
		text.setVisibility(View.VISIBLE);
    }

	public void readFileInEditor(String Course_File)
	{
		try 
		{
			InputStream in = openFileInput(Course_File);
			if (in != null) 
			{
				InputStreamReader tmp = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(tmp);
				String str;
				int i = 1;
				while ((str = reader.readLine()) != null)
				{
					String ex = new String();
					int j = 0;
					while(str.charAt(j) != ' ')
					{
						ex += str.charAt(j);
						j++;
					}
					CheckBox box = setCheckBox(i);
					Button button = setButtonView(i);
					button.setText(ex);
					box.setVisibility(View.VISIBLE);
					button.setVisibility(View.VISIBLE);
					j++;
					if(str.charAt(j) == '0')	
					{
						box.setChecked(false);					
					}
					else
					{
						box.setChecked(true);
					}
					j++;
					if(j < str.length())
					{
						j++;
						String dateStr = new String();
						while(j < str.length())
						{
							dateStr += str.charAt(j);
							j++;
						}
						TextView text = setTextView(i);
						text.setText(dateStr);
						text.setVisibility(View.VISIBLE);
					}
					i++;
				}				
				in.close();
			}
			Button button = (Button) findViewById(R.id.Submit);
			button.setVisibility(View.INVISIBLE);
			EditText textEdit = (EditText) findViewById(R.id.enter_num_of_ex);
			textEdit.setVisibility(View.INVISIBLE);
			TextView text = (TextView) findViewById(R.id.press_ex);
			text.setVisibility(View.VISIBLE);
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

	private void writeRead(int num)
	{
		Intent intent = getIntent();
		String Course_File = intent.getStringExtra(Semester.EX_MESSAGE);
    	try 
		{
			InputStream in = openFileInput(Course_File);
			InputStreamReader tmp = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(tmp);
			
    		String str;
    		String newStr = new String();
    		while((str = reader.readLine()) != null)
    		{
        		String check = new String();
				int j = 0;
				while(str.charAt(j) != ' ')
				{
					check += str.charAt(j);
					newStr += str.charAt(j);
					j++;
				}
				if((Integer.parseInt(check) == num) || (str.charAt(j+1) == '1'))
				{
					newStr += " ";
					j++;
					newStr += "1";
					j++;
					while(j < str.length())
					{
						newStr += str.charAt(j);
						j++;
					}
					newStr += "\n";
				}
				else
				{
					newStr += " ";
					j++;
					newStr += "0";
					j++;
					while(j < str.length())
					{
						newStr += str.charAt(j);
						j++;
					}
					newStr += "\n";
				}
    		}
			in.close();
    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput(Course_File, 0));
			out.write(newStr);
			Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
			out.close();
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}		
	}
	
	public void onExClick(View view)
	{
		DatePicker date =  (DatePicker) findViewById(R.id.datePicker);
		date.setVisibility(View.VISIBLE);
		Button button = (Button) findViewById(R.id.add_date);
		button.setVisibility(View.VISIBLE);
		int i = 1;
		while(i <= 15)
		{
			button = setButtonView(i);
			button.setVisibility(View.INVISIBLE);
			CheckBox box = setCheckBox(i);
			box.setVisibility(View.INVISIBLE);
			TextView text = setTextView(i);
			text.setVisibility(View.INVISIBLE);
			i++;
		}
		Toast.makeText(this, "Pick a date and click the button below the date picker", Toast.LENGTH_LONG).show();
	}
	
	public void onDateClick(View view)
	{
		DatePicker date = (DatePicker) findViewById(R.id.datePicker);
		date.setVisibility(View.INVISIBLE);
		Button button = (Button) findViewById(R.id.add_date);
		button.setVisibility(View.INVISIBLE);

		String dateStr = new String();
		dateStr += Integer.toString(date.getDayOfMonth());
		dateStr += "/";
		dateStr += Integer.toString((date.getMonth()+1));
		dateStr += "/";
		dateStr += Integer.toString(date.getYear());
		
		Intent intent = getIntent();
		String Course_File =  intent.getStringExtra(Semester.EX_MESSAGE);
		/*
		 * add date to file!!
		 */
		try 
		{
			InputStream in = openFileInput(Course_File);
			InputStreamReader tmp = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(tmp);
			
    		String str;
    		String newStr = new String();
    		while((str = reader.readLine()) != null)
    		{
        		String check = new String();
				int j = 0;
				while(str.charAt(j) != ' ')
				{
					check += str.charAt(j);
					newStr += str.charAt(j);
					j++;
				}
				newStr += " ";
				j++;
				newStr += str.charAt(j);
				j++;

				if(Integer.parseInt(check) == clickedEx)
				{
					while(j < str.length() && (str.charAt(j) != ' '))
					{
						newStr += str.charAt(j);
						j++;
					}
					newStr += " ";
					newStr += dateStr;
					newStr += "\n";
				}
				else
				{
					while(j < str.length())
					{
						newStr += str.charAt(j);
						j++;
					}
					newStr += "\n";
				}
    		}
			in.close();
    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput(Course_File, 0));
			out.write(newStr);
			Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();
			out.close();
			TextView text = setTextView(clickedEx);
			text.setText(dateStr);
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}

		eventOfEx(clickedEx, dateStr, Course_File);
		readFileInEditor(Course_File);
	}

	private void getDateFromFile(int clickedExText)
	{
		Intent intent = getIntent();
		String Course_File = intent.getStringExtra(Semester.EX_MESSAGE);

		try 
		{
			InputStream in = openFileInput(Course_File);
			InputStreamReader tmp = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(tmp);
			
    		String str;
    		String dateStr = new String();
    		while((str = reader.readLine()) != null)
    		{
        		String check = new String();
				int j = 0;
				while(str.charAt(j) != ' ')
				{
					check += str.charAt(j);
					j++;
				}
				j++;
				if(Integer.parseInt(check) == clickedExText)
				{
					while(str.charAt(j) != ' ')
					{
						j++;
					}
					j++;
					while(j < str.length())
					{
						dateStr += str.charAt(j);
						j++;
					}
				}
    		}
			in.close();
			eventOfEx(clickedExText ,dateStr, Course_File);
		}
		catch (Throwable t) 
		{
			Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void eventOfEx(int ex, String date, String course)
	{
		Calendar beginTime = Calendar.getInstance();
		int year;
		int month;
		int day;
		String partDate = new String();
		int i = 0;
		while(date.charAt(i) != '/')
		{
			partDate += date.charAt(i);
			i++;
		}
		day = Integer.parseInt(partDate);
		partDate = new String();
		i++;
		while(date.charAt(i) != '/')
		{
			partDate += date.charAt(i);
			i++;
		}
		month = Integer.parseInt(partDate);
		partDate = new String();
		i++;
		while(i < date.length())
		{
			partDate += date.charAt(i);
			i++;
		}
		year = Integer.parseInt(partDate);
		String message = new String();
		message += "ASS-KICK!! Course: ";
		message += course;
		message += " Ex: ";
		message += Integer.toString(ex);
		
		beginTime.set(year, month-1, day, 7, 30);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month-1, day, 10, 30);
		Intent calIntent = new Intent(Intent.ACTION_INSERT)
		        .setData(Events.CONTENT_URI)
		        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
		        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
		        .putExtra(Events.TITLE, message)
		        .putExtra(Events.DESCRIPTION, "Exercise to do (from Ass-Kick application)")
//		        .putExtra(Events.EVENT_LOCATION, "The gym")
		        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
//		        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
		startActivity(calIntent);
	}

	public void buttonClick1(View view)
	{
		clickedEx = 1;
		onExClick(view);
	}
	public void buttonClick2(View view)
	{
		clickedEx = 2;		
		onExClick(view);
	}
	public void buttonClick3(View view)
	{
		clickedEx = 3;
		onExClick(view);
	}
	public void buttonClick4(View view)
	{
		clickedEx = 4;
		onExClick(view);
	}
	public void buttonClick5(View view)
	{
		clickedEx = 5;
		onExClick(view);
	}
	public void buttonClick6(View view)
	{
		clickedEx = 6;
		onExClick(view);
	}
	public void buttonClick7(View view)
	{
		clickedEx = 7;
		onExClick(view);
	}
	public void buttonClick8(View view)
	{
		clickedEx = 8;
		onExClick(view);
	}
	public void buttonClick9(View view)
	{
		clickedEx = 9;
		onExClick(view);
	}
	public void buttonClick10(View view)
	{
		clickedEx = 10;
		onExClick(view);
	}
	public void buttonClick11(View view)
	{
		clickedEx = 11;
		onExClick(view);
	}
	public void buttonClick12(View view)
	{
		clickedEx = 12;
		onExClick(view);
	}
	public void buttonClick13(View view)
	{
		clickedEx = 13;
		onExClick(view);
	}
	public void buttonClick14(View view)
	{
		clickedEx = 14;
		onExClick(view);
	}
	public void buttonClick15(View view)
	{
		clickedEx = 15;
		onExClick(view);
	}
	
	public void text1Clicked(View view)
	{
		getDateFromFile(1);
	}
	public void text2Clicked(View view)
	{
		getDateFromFile(2);
	}
	public void text3Clicked(View view)
	{
		getDateFromFile(3);
	}
	public void text4Clicked(View view)
	{
		getDateFromFile(4);
	}
	public void text5Clicked(View view)
	{
		getDateFromFile(5);
	}
	public void text6Clicked(View view)
	{
		getDateFromFile(6);
	}
	public void text7Clicked(View view)
	{
		getDateFromFile(7);
	}
	public void text8Clicked(View view)
	{
		getDateFromFile(8);
	}
	public void text9Clicked(View view)
	{
		getDateFromFile(9);
	}
	public void text10Clicked(View view)
	{
		getDateFromFile(10);
	}
	public void text11Clicked(View view)
	{
		getDateFromFile(11);
	}
	public void text12Clicked(View view)
	{
		getDateFromFile(12);
	}
	public void text13Clicked(View view)
	{
		getDateFromFile(13);
	}
	public void text14Clicked(View view)
	{
		getDateFromFile(14);
	}
	public void text15Clicked(View view)
	{
		getDateFromFile(15);
	}
	

	public void onCheckboxClicked(View view) 
	{
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    if(checked)
	    {
		    switch(view.getId()) 
		    {
		        case R.id.checkBox1:
		        {
		            if (checked)
		            {
		            	writeRead(1);
		            }
		            break;
		        }
		        case R.id.checkBox2:
		        {
		            if (checked)
		            {
		            	writeRead(2);
		            }
		            break;
		        }
		        case R.id.checkBox3:
		        {
		            if (checked)
		            {
		            	writeRead(3);
		            }
		            break;
		        }
		        case R.id.checkBox4:
		        {
		            if (checked)
		            {
		            	writeRead(4);
		            }
		            break;
		        }
		        case R.id.checkBox5:
		        {
		            if (checked)
		            {
		            	writeRead(5);
		            }
		            break;
		        }
		        case R.id.checkBox6:
		        {
		            if (checked)
		            {
		            	writeRead(6);
		            }
		            break;
		        }
		        case R.id.checkBox7:
		        {
		            if (checked)
		            {
		            	writeRead(7);
		            }
		            break;
		        }
		        case R.id.checkBox8:
		        {
		            if (checked)
		            {
		            	writeRead(8);
		            }
		            break;
		        }
		        case R.id.checkBox9:
		        {
		            if (checked)
		            {
		            	writeRead(9);
		            }
		            break;
		        }
		        case R.id.checkBox10:
		        {
		            if (checked)
		            {
		            	writeRead(10);
		            }
		            break;
		        }
		        case R.id.checkBox11:
		        {
		            if (checked)
		            {
		            	writeRead(11);
		            }
		            break;
		        }
		        case R.id.checkBox12:
		        {
		            if (checked)
		            {
		            	writeRead(12);
		            }
		            break;
		        }
		        case R.id.checkBox13:
		        {
		            if (checked)
		            {
		            	writeRead(13);
		            }
		            break;
		        }
		        case R.id.checkBox14:
		        {
		            if (checked)
		            {
		            	writeRead(14);
		            }
		            break;
		        }
		        case R.id.checkBox15:
		        {
		            if (checked)
		            {
		            	writeRead(15);
		            }
		            break;
		        }
		    }
	    }
	}
	
	private CheckBox setCheckBox(int num)
	{
		CheckBox box = null;
		if(num == 1)
		{
		    box = (CheckBox) findViewById(R.id.checkBox1);	    				
		}
		else if(num == 2)
		{
		    box = (CheckBox) findViewById(R.id.checkBox2);	    				
		}
		else if(num == 3)
		{
		    box = (CheckBox) findViewById(R.id.checkBox3);	    				
		}
		else if(num == 4)
		{
		    box = (CheckBox) findViewById(R.id.checkBox4);	    				
		}
		else if(num == 5)
		{
		    box = (CheckBox) findViewById(R.id.checkBox5);	    				
		}
		else if(num == 6)
		{
		    box = (CheckBox) findViewById(R.id.checkBox6);	    				
		}
		else if(num == 7)
		{
		    box = (CheckBox) findViewById(R.id.checkBox7);	    				
		}
		else if(num == 8)
		{
		    box = (CheckBox) findViewById(R.id.checkBox8);	    				
		}
		else if(num == 9)
		{
		    box = (CheckBox) findViewById(R.id.checkBox9);	    				
		}
		else if(num == 10)
		{
		    box = (CheckBox) findViewById(R.id.checkBox10);	    				
		}
		else if(num == 11)
		{
		    box = (CheckBox) findViewById(R.id.checkBox11);	    				
		}
		else if(num == 12)
		{
		    box = (CheckBox) findViewById(R.id.checkBox12);	    				
		}
		else if(num == 13)
		{
		    box = (CheckBox) findViewById(R.id.checkBox13);	    				
		}
		else if(num == 14)
		{
		    box = (CheckBox) findViewById(R.id.checkBox14);	    				
		}
		else if(num == 15)
		{
		    box = (CheckBox) findViewById(R.id.checkBox15);	    				
		}
		return box;
	}
	
	private TextView setTextView(int num)
	{
		TextView text = null;
		if(num == 1)
		{
			text = (TextView) findViewById(R.id.textView1);	    				
		}
		else if(num == 2)
		{
			text = (TextView) findViewById(R.id.textView2);	    				
		}
		else if(num == 3)
		{
			text = (TextView) findViewById(R.id.textView3);	    				
		}
		else if(num == 4)
		{
			text = (TextView) findViewById(R.id.textView4);	    				
		}
		else if(num == 5)
		{
			text = (TextView) findViewById(R.id.textView5);	    				
		}
		else if(num == 6)
		{
			text = (TextView) findViewById(R.id.textView6);	    				
		}
		else if(num == 7)
		{
			text = (TextView) findViewById(R.id.textView7);	    				
		}
		else if(num == 8)
		{
			text = (TextView) findViewById(R.id.textView8);	    				
		}
		else if(num == 9)
		{
			text = (TextView) findViewById(R.id.textView9);	    				
		}
		else if(num == 10)
		{
			text = (TextView) findViewById(R.id.textView10);	    				
		}
		else if(num == 11)
		{
			text = (TextView) findViewById(R.id.textView11);	    				
		}
		else if(num == 12)
		{
			text = (TextView) findViewById(R.id.textView12);	    				
		}
		else if(num == 13)
		{
			text = (TextView) findViewById(R.id.textView13);	    				
		}
		else if(num == 14)
		{
			text = (TextView) findViewById(R.id.textView14);	    				
		}
		else if(num == 15)
		{
			text = (TextView) findViewById(R.id.textView15);	    				
		}
		return text;
	}
	
	private Button setButtonView(int num)
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
		else if(num == 13)
		{
			button = (Button) findViewById(R.id.button13);	    				
		}
		else if(num == 14)
		{
			button = (Button) findViewById(R.id.button14);	    				
		}
		else if(num == 15)
		{
			button = (Button) findViewById(R.id.button15);	    				
		}
		return button;
	}
}
