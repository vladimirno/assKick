package com.asskick;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
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
    
	public void deleteFiles(View view)
    {
		File file = new File("/data/data/com.asskick/files/");
		DeleteRecursive(file);
    }
    
    //delete all the files that the app is creating
    void DeleteRecursive(File fileOrDirectory) 
    {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
            {
                DeleteRecursive(child);            	
            }

        fileOrDirectory.delete();
    }
}
