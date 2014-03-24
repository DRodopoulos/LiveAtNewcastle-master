package com.example.live_at_newcastle;

import com.example.drawer_contents.*;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class IndividualSocPage extends DrawerActivity implements OnClickListener {

	
	private static final String nusuFacebook = "http://www.facebook.com/NewcastleSU";
	private static final String nusuTwitter = "http://mobile.twitter.com/NewcastleSU";
	private WebView myWebView;
	private ImageButton facebookBtn;
	private ImageButton twitterBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_soc_page);
		myWebView = (WebView) findViewById(R.id.soc_webview);
		myWebView.setWebViewClient(new WebViewClient(){

		    @Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url){
		      view.loadUrl(url);
		      return true;
		    }
		});
		
		Bundle extras = getIntent().getExtras();
		String url = extras.getString("url");
		if (url != null) {
			myWebView.loadUrl(url);		
		}
		
		facebookBtn = (ImageButton) findViewById(R.id.facebook_imgbtn);
		twitterBtn = (ImageButton) findViewById(R.id.twitter_imgbtn);
		
		facebookBtn.setOnClickListener(this);
		twitterBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if (v == facebookBtn) {
			myWebView.loadUrl(nusuFacebook);
		}
		if (v == twitterBtn) {
			myWebView.loadUrl(nusuTwitter);
		}
	}
	
}
