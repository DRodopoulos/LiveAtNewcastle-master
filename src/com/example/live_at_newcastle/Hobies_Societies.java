package com.example.live_at_newcastle;

import java.util.List;

import com.example.live_at_newcastle.R;
import com.example.drawer_contents.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Hobies_Societies extends DrawerActivity implements OnClickListener {

	private Button socListBtn;
	private Button findCentreBtn;
	private Button upcomingBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hobbies_societies);

		socListBtn = (Button) findViewById(R.id.button1);
		findCentreBtn = (Button) findViewById(R.id.button2);
		upcomingBtn = (Button) findViewById(R.id.button3);

		socListBtn.setOnClickListener(this);
		findCentreBtn.setOnClickListener(this);
		upcomingBtn.setOnClickListener(this);
	}

	@Override
	/* Code to be implemented for the buttons */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent myIntent = null;
		boolean launchFail = false;
		if (v == socListBtn) {
			List<String> temp;
			String[] societyNames = null;
			String[] societyURLs = null;
			myIntent = new Intent("com.example.live_at_newcastle.SOCIETYLIST");

			SocietyGrab sg = new SocietyGrab();

			temp = sg.getNames(this);
			if (temp != null) {
				if (temp.size() != 0) {
					societyNames = temp.toArray(new String[0]);
				} else {
					launchFail = true;
				}
			}
			temp = sg.getURLs(this);
			if (temp != null) {
				if (temp.size() != 0) {
					societyURLs = temp.toArray(new String[0]);
				} else {
					launchFail = true;
				}
			}
			if (!launchFail) {
				myIntent.putExtra("societies", societyNames);
				myIntent.putExtra("soclinks", societyURLs);
			}

		}
		if (v == findCentreBtn) {
			myIntent = new Intent("com.example.live_at_newcastle.FINDCENTRE");
		}

		if (!launchFail) {
			if (myIntent != null) {
				myIntent.putExtra("position", 1);
				startActivity(myIntent);
			}
		} else {
			AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setTitle("Warning");
			ad.setMessage("Failed to retrieve society list: check connectivity "
					+ "and try again.");
			ad.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			ad.show();

		}

	}
}
