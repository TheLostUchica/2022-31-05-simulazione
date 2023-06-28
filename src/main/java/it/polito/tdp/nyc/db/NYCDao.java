package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<City> getCity(String p){
		
		String sql = "SELECT city, AVG(Latitude) AS lat, AVG(Longitude) AS lon, COUNT(*) AS n "
				+ "FROM nyc_wifi_hotspot_locations "
				+ "WHERE Provider=? "
				+ "GROUP BY City";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, p);
			ResultSet res = st.executeQuery();
			
			List<City> result = new LinkedList<>();
			
			while(res.next()) {
				result.add(new City(res.getString("city"), res.getDouble("lat"), res.getDouble("lon"), res.getInt("n")));
			}
			
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
	
	public List<String> getPro(){
		
		String sql = "SELECT DISTINCT Provider "
			+ "FROM nyc_wifi_hotspot_locations";
	
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			List<String> result = new LinkedList<>();
			
			while(res.next()) {
				result.add(res.getString("Provider"));
			}
			
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
	
}
