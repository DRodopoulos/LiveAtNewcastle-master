package com.example.live_at_newcastle;

public class Society implements Comparable<Society> {

	private String name;
	private String url;
	
	public Society(String name, String url) {
		setName(name);
		setUrl(url);
	}

	public Society() {}
	
	public String toString() {
		return String.format("%-60s%s",name,url);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name.replace("&amp;", "&");
		name = name.replace("&nbsp;", "");
		if (name.contains("Latin, Ballroom") && !(name.contains(" and Salsa"))) {
			name = name + " and Salsa";
		}
		if (name.contains("Twenty Minute") && !(name.contains(" Society"))) {
			name = name + " Society";
		}
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (!(url.substring(0, 4).equals("http"))) {
			url = "http://www.nusu.co.uk" + url;
		}
		else {
			url = "http://www." + url.substring(7);
		}
		this.url = url;
	}
	
	public int compareTo(Society rhs) {
		if (this.name.charAt(0) > rhs.name.charAt(0)) {
			return 1;
		}
		else if (this.name.charAt(0) == rhs.name.charAt(0)) {
			return 0;
		}
		else {
			return -1;
		}
//		comment two
	}
}
