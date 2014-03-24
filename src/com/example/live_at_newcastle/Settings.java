package com.example.live_at_newcastle;

import com.example.live_at_newcastle.R;
import com.example.drawer_contents.*;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.*;

public class Settings extends DrawerActivity implements OnClickListener
{
	private Button about;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		

		about = (Button) findViewById(R.id.button1);

		about.setOnClickListener(this);
	    
	    
	}
	

	@Override
	/*Implement code here for button functionality*/
	public void onClick(View v) 
	{
		
		if(v==about)
		{
			Intent myIntent = new Intent("com.example.live_at_newcastle.ABOUT");
			startActivity(myIntent);
			    
		}
			
	}
	
}
