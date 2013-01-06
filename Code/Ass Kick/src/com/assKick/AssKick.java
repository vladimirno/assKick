package com.asskick;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class AssKick extends Activity 
{
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
    }

//	@Override
//    protected void onStart()
//	{
//		File file = new File(Context.getFilesDir(), filename);
//	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void semesterWindow(View view)
    {
    	Intent semester = new Intent(this, Semester.class);
    	startActivity(semester);
    }    
}
