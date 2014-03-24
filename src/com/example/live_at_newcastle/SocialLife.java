package com.example.live_at_newcastle;

import com.example.live_at_newcastle.R;
import com.example.drawer_contents.*;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SocialLife extends DrawerActivity implements OnClickListener
{
	private Button button1;
	private Button button2;
	private Button button3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social_life);
		
		button1 = (Button)findViewById(R.id.button1);
	    button2 = (Button)findViewById(R.id.button2);
	    button3 = (Button)findViewById(R.id.button3);
		
		button1.setOnClickListener(this);
	    button2.setOnClickListener(this);
	    button3.setOnClickListener(this);
	}
	

	@Override
	/*code to be implemented for the functionality of the buttons*/
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}
	
}
