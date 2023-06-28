package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class City {
	
	String name;
	double lat;
	double lon;
	LatLng point;
	int hot;
	
	public City(String name, double lat, double lon, int hot) {
		super();
		this.name = name;
		point = new LatLng(lat, lon);
		this.hot = hot;
	}

	public int getHot() {
		return this.hot;
	}
	
	public String getName() {
		return name;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public LatLng getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
	
	
	
	
	

}
