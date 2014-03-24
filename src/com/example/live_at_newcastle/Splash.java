package com.example.live_at_newcastle;

import com.example.live_at_newcastle.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity 
{
	Thread timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		 timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(1000);
				} catch(InterruptedException e)
				{
					finish();
					System.exit(0);
				}finally
				{
					Intent openMenu = new Intent("com.example.live_at_newcastle.MAINACTIVITY");
					openMenu.putExtra("position", 0);
					startActivity(openMenu);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		timer.interrupt();
		finish();
	}
	
}
