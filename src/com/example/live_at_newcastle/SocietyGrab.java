package com.example.live_at_newcastle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.net.*;
import java.io.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

//import com.example.drawer_contents.DrawerActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SocietyGrab {

	private static final String SEARCH1 = "<a href=\"/soc/content/";
	private static final String SEARCH2 = "<a href=\"http://nusu.co.uk/soc/content";

	private List<Society> societies = new ArrayList<Society>();

	/*
	 * Code used during testing creation of this class to print out the
	 * different societies and the relevant links
	 */
	/*
	 * public void printArray() {
	 * System.out.println(String.format("%-60s%s","Society","URL"));
	 * System.out.println("-----------------------------" +
	 * "------------------------------------------------------------------");
	 * 
	 * for (int i = 0; i<societies.size(); i++) {
	 * System.out.println(societies.get(i)); } }
	 */

	class RetrieveWebSource extends AsyncTask<String, Void, String> {

		private ProgressDialog pd;

		public RetrieveWebSource(Context context) {
			pd = new ProgressDialog(context);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd.setMessage("Loading...");
			pd.show();
		}

		protected String doInBackground(String... urls) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet(urls[0]);
				HttpResponse response = client.execute(request);
				InputStream in = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));
				String inputLine = "";
				StringBuilder str = new StringBuilder();
				while ((inputLine = reader.readLine()) != null) {
					str.append(inputLine);
				}
				in.close();
				return str.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()) {
				pd.dismiss();
			}
		}
	}

	public void findAndSplit(String webpageSource) {
		if (webpageSource != null) {
			searchA(webpageSource);
			searchB(webpageSource);
			Collections.sort(societies);
		}
	}

	public List<String> getNames(Context context) {

		if (societies.size() == 0) {
			try {
				String webpage = new RetrieveWebSource(context)
						.execute(
								"http://www.nusu.co.uk/soc/content/926793/_a-z_of_societies/full_a-z_of_societies/")
						.get();
				findAndSplit(webpage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		List<String> socNames = new ArrayList<String>();
		for (Society s : societies) {
			socNames.add(s.getName());
		}
		return socNames;
	}

	public List<String> getURLs(Context context) {

		if (societies.size() == 0) {
			try {
				String webpage = new RetrieveWebSource(context)
						.execute(
								"http://www.nusu.co.uk/soc/content/926793/_a-z_of_societies/full_a-z_of_societies/")
						.get();
				findAndSplit(webpage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		List<String> socURLs = new ArrayList<String>();
		for (Society s : societies) {
			socURLs.add(s.getUrl());
		}
		return socURLs;
	}

	private void searchA(String webpageSource) {
		int i = 0;
		int searchFirst = 0;
		int j;
		boolean match = false;
		Society s;

		while (searchFirst >= 0) {
			searchFirst = webpageSource.indexOf(SEARCH1, searchFirst + 1);
			j = searchFirst;
			j = webpageSource.indexOf('\"', j + 9);
			s = new Society();
			s.setUrl(webpageSource.substring(searchFirst + 9, j));
			i = j;
			while (!match) {
				match = webpageSource.substring(i, i + 1).matches("[A-Z]");
				i++;
			}
			match = false;
			j = webpageSource.indexOf('<', i);
			s.setName(webpageSource.substring(i - 1, j));
			if (!(s.getName().length() == 1) && !(s.getUrl().contains(" "))) {
				societies.add(s);
			}
		}
	}

	private void searchB(String webpageSource) {
		int i = 0;
		int searchFirst = 0;
		int j;
		boolean match = false;
		Society s;

		while (searchFirst >= 0) {
			searchFirst = webpageSource.indexOf(SEARCH2, searchFirst + 1);
			j = searchFirst;
			j = webpageSource.indexOf('\"', j + 9);
			s = new Society();
			s.setUrl(webpageSource.substring(searchFirst + 9, j));
			i = j;
			while (!match) {
				match = webpageSource.substring(i, i + 1).matches("[A-Z]");
				i++;
			}
			match = false;
			j = webpageSource.indexOf('<', i);
			s.setName(webpageSource.substring(i - 1, j));
			if (!(s.getName().length() == 1) && !(s.getUrl().contains(" "))) {
				societies.add(s);
			}
		}
	}
}