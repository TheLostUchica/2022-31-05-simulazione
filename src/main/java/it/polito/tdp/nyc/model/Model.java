package it.polito.tdp.nyc.model;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	NYCDao dao;
	Graph<City, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new NYCDao();
	}
	
	public void creaGrafo(String provider) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.getCity(provider));
		
		for(City c1 : this.grafo.vertexSet()) {
			for(City c2 : this.grafo.vertexSet()) {
				if(c1.name.compareTo(c2.getName())<0) {
					Graphs.addEdgeWithVertices(this.grafo, c1, c2, LatLngTool.distance(c1.getPoint(), c2.getPoint(), LengthUnit.KILOMETER));
				}
			}
		}
	}
	
	public Graph<City, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
	public List<String> setCombo1(){
		return dao.getPro();
	}
	
	public Set<City> setCombo2(){
		return this.grafo.vertexSet();
	}
	
	public List<Adiacenza> getAd(City ci){
		List<Adiacenza> result = new LinkedList<>();
		
		for(City c : Graphs.neighborListOf(this.grafo, ci)) {
			result.add(new Adiacenza(c, this.grafo.getEdgeWeight(this.grafo.getEdge(c, ci))));
		}
		
		Collections.sort(result);
		return result;
	}
	
}
