package com.example.live_at_newcastle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.drawer_contents.DrawerActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindCentre extends DrawerActivity implements
		OnItemSelectedListener {

	private GoogleMap socMap;
	private static final CameraPosition NUSUPOS = new CameraPosition(
			new LatLng(54.9789971, -1.6152622), 17, 0, 0);
	private static final CameraPosition SPORTSCENTREPOS = new CameraPosition(
			new LatLng(54.9825665, -1.6244823), 17, 0, 0);
	private Spinner mySpinner;
	
	private Marker nusuMarker;
	private Marker scMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_centre);
		mySpinner = (Spinner) findViewById(R.id.socMap_spinner);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.socMap_elements, R.layout.spinner_list_title);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mySpinner.setAdapter(adapter);
		mySpinner.bringToFront();
		mySpinner.setOnItemSelectedListener(this);

		if (mapIsSafe()) {
			socMap.moveCamera(CameraUpdateFactory.newCameraPosition(NUSUPOS));
			nusuMarker = socMap.addMarker(new MarkerOptions()
					.position(NUSUPOS.target)
					.anchor(0.3f, 0f)
					.title("Newcastle University Students' Union")
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			scMarker = socMap.addMarker(new MarkerOptions()
					.position(SPORTSCENTREPOS.target)
					.anchor(0.4f, 1f)
					.title("Newcastle University Sports Centre")
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			nusuMarker.showInfoWindow();
			socMap.setMyLocationEnabled(true);
		}

	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		if (mapIsSafe()) {
			switch (pos) {
			// Setting position on NUSU and enabling the correct marker
			case 0: nusuMarker.setVisible(true);
					scMarker.setVisible(false);
					nusuMarker.showInfoWindow();
					socMap.moveCamera(CameraUpdateFactory.newCameraPosition(NUSUPOS));
					break;
			// Setting position on sports centre and enabling correct marker
			case 1: nusuMarker.setVisible(false);
					scMarker.setVisible(true);
					scMarker.showInfoWindow();
					socMap.moveCamera(CameraUpdateFactory.newCameraPosition(SPORTSCENTREPOS));
					break;
			// Changing map type to normal
			case 2: socMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
					break;
			// Changing map type to satellite
			case 3: socMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
					break;
			// Changing map type to hybrid
			case 4: socMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
					break;
			}
		}
				
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback

	}

	private boolean mapIsSafe() {
		if (socMap == null) {
			socMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.socMap)).getMap();
			// Check if we were successful in obtaining the map.
			if (socMap != null) {
				// The Map is verified. It is now safe to manipulate the map.
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

}
