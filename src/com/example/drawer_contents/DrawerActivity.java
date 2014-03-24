package com.example.drawer_contents;

import java.util.ArrayList;

import com.example.live_at_newcastle.R;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

public class DrawerActivity extends FragmentActivity {

	private String[] mDrawerArray;
	private TypedArray navMenuIcons;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private FrameLayout content;

	private ArrayList<DrawerItem> navDrawerItems;
	private DrawerListAdapter adapter;

	@Override
	public void setContentView(int layoutResID) {

		mDrawerArray = getResources().getStringArray(R.array.drawer_elements);
		navMenuIcons = getResources().obtainTypedArray(R.array.drawer_icons);

		navDrawerItems = new ArrayList<DrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new DrawerItem(mDrawerArray[0], navMenuIcons
				.getResourceId(0, -1)));
		// Hobbies/Societies
		navDrawerItems.add(new DrawerItem(mDrawerArray[1], navMenuIcons
				.getResourceId(1, -1)));
		// Social Life
		navDrawerItems.add(new DrawerItem(mDrawerArray[2], navMenuIcons
				.getResourceId(2, -1)));
		// Accommodation
		navDrawerItems.add(new DrawerItem(mDrawerArray[3], navMenuIcons
				.getResourceId(3, -1)));
		// Settings
		navDrawerItems.add(new DrawerItem(mDrawerArray[4], navMenuIcons
				.getResourceId(4, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.navigation_drawer, null);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_nav_open, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				// super.onDrawerClosed(view);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				// super.onDrawerOpened(drawerView);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		content = (FrameLayout) mDrawerLayout.findViewById(R.id.content_frame);
		getLayoutInflater().inflate(layoutResID, content);

		super.setContentView(mDrawerLayout);

		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		adapter = new DrawerListAdapter(this, navDrawerItems);
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Setting selection of correct item

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey("position")) {
				int pos = extras.getInt("position");
				mDrawerList.setItemChecked(pos, true);
				mDrawerList.setSelection(pos);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Intent myIntent = null;
		switch (position) {
		case 0:
			myIntent = new Intent("com.example.live_at_newcastle.MAINACTIVITY");
			break;
		case 1:
			myIntent = new Intent(
					"com.example.live_at_newcastle.HOBIES_SOCIETIES");
			break;
		case 2:
			myIntent = new Intent("com.example.live_at_newcastle.SOCIALLIFE");
			break;
		case 3:
			myIntent = new Intent(
					"com.example.live_at_newcastle.ACCOMMOADATION");
			break;
		case 4:
			myIntent = new Intent("com.example.live_at_newcastle.SETTINGS");
			break;
		}
		if (myIntent != null) {
			myIntent.putExtra("position", position);
			startActivity(myIntent);
		}
		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		mDrawerList.setSelection(position);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent settingsIntent = new Intent(
					"com.example.live_at_newcastle.SETTINGS");
			settingsIntent.putExtra("position", 4);
			startActivity(settingsIntent);
		}
		return super.onMenuItemSelected(featureId, item);
	}

}