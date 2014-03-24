package com.example.live_at_newcastle;

import com.example.live_at_newcastle.R;
import com.example.drawer_contents.*;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends DrawerActivity implements OnClickListener {
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button)findViewById(R.id.button1);
	    button2 = (Button)findViewById(R.id.button2);
	    button3 = (Button)findViewById(R.id.button3);
	    button4 = (Button)findViewById(R.id.button4);
    
	    button1.setOnClickListener(this);
	    button2.setOnClickListener(this);
	    button3.setOnClickListener(this);
	    button4.setOnClickListener(this);
	    
	  }


	@Override
	public void onClick(View v) 
	{
		/*Code that implements the functionality of the first button*/
		if(v==button1)
		{
			Intent hobiesSoc = new Intent("com.example.live_at_newcastle.HOBIES_SOCIETIES");
			startActivity(hobiesSoc);
		}
		/*Code for button 2*/
		if(v==button2)
		{
			Intent socLife = new Intent("com.example.live_at_newcastle.SOCIALLIFE");
			startActivity(socLife);
		}
		if(v==button3)
		{
			Intent accom = new Intent("com.example.live_at_newcastle.ACCOMMOADATION");
			startActivity(accom);
		}
		if(v==button4)
		{
			Intent settings = new Intent("com.example.live_at_newcastle.SETTINGS");
			startActivity(settings);
		}
		
	}

	
}
