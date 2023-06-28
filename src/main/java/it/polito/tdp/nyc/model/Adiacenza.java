package it.polito.tdp.nyc.model;

public class Adiacenza implements Comparable<Adiacenza>{
	
	City city;
	double distanza;
	
	public Adiacenza(City city, double distanza) {
		super();
		this.city = city;
		this.distanza = distanza;
	}

	public City getCity() {
		return city;
	}

	public double getDistanza() {
		return distanza;
	}

	@Override
	public int compareTo(Adiacenza o) {
		return (int)(this.getDistanza()-o.getDistanza());
	}

	@Override
	public String toString() {
		return city.getName()+": "+distanza;
	}
	
	
	
	
	

}
