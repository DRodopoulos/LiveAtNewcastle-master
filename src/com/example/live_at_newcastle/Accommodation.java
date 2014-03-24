package com.example.live_at_newcastle;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.drawer_contents.DrawerActivity;

public class Accommodation extends DrawerActivity implements OnClickListener
{
	private Button button1;
	private Button button2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accom);
		
		button1 = (Button)findViewById(R.id.button1);
	    button2 = (Button)findViewById(R.id.button2);
	    
	    button1.setOnClickListener(this);
	    button2.setOnClickListener(this);
	}
	


	@Override
	/*Code to be implemented for when buttons are pressed*/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
