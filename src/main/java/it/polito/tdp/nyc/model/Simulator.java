package it.polito.tdp.nyc.model;

import java.util.*;

public class Simulator {
	
	City city;
	int n;
	int hotspot;	
	int hotmancanti;
	double time;
	
	Queue<Double> queue;
	Model model;
	List<City> risolte;
	
	public Simulator(int n, Model model, City city) {
		this.city = city;
		this.model = model;
		this.n = n;
		time=0;
		queue = new PriorityQueue<>();
		this.hotmancanti = this.city.getHot();
		risolte = new LinkedList<City>();
		risolte.add(city);
	}
	
	public void init() {
		for(int i = 0; i<n; i++) {
			queue.add(time);
		}
	}
	
	public void run() {
		process(queue.poll());
	}
	
	public void process(double event) {
		
		time = event;
		
		if(hotmancanti!=0) {
			double time = (Math.random()*10) + 10;
			queue.add(event+time);
			this.hotmancanti--;
			this.hotspot++;
		}else {
			
			//problema 1 --> tutta la squadra deve finire RISOLTO
			//problema 2 --> citta senza adiacenze 
			//probelma 3 --> adiacenze duplicate
			
			if(this.queue.size()==0) {
				
				Adiacenza aa = null;
				
				for(Adiacenza a : model.getAd(city)) {
					if(!risolte.contains(a.getCity())) {
						aa = a;
					}
				}
				
				if(aa!=null) {
					city = aa.getCity();
					risolte.add(city);
					double t = aa.getDistanza()*60/50;
					this.hotmancanti = city.getHot();
					
					for(int i = 0; i<n; i++) {
					queue.add(event+t);
					}
				}
			}
		}
	}
	
	public double getTime() {
		return this.time;
	}
	
	
}
