package com.example.live_at_newcastle;

import java.util.Arrays;
import java.util.List;

import com.example.drawer_contents.DrawerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SocietyList extends DrawerActivity {

	
	private List<String> societyNames;
	private List<String> societyURLs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.society_list);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey("societies")) {
				societyNames = Arrays.asList(extras.getStringArray("societies"));
			}
			if (extras.containsKey("soclinks")) {
				societyURLs= Arrays.asList(extras.getStringArray("soclinks"));
			}
		}
		ListView myList = (ListView) findViewById(R.id.soc_list);
		myList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, societyNames));
		myList.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                    int position, long id) {
        		Intent indSocPage = new Intent("com.example.live_at_newcastle.INDSOCPAGE");
        		indSocPage.putExtra("url", societyURLs.get(position));
        		indSocPage.putExtra("position", 1);
        		startActivity(indSocPage);

            }   
        });       
	}

}
