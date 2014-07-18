package models.vo;

import java.util.ArrayList;
import java.util.List;

public class GeoJson {
	
	public GeoJson(String type){
		this.type = type;
	}
	
	public String type = "bb";
	
	public List<List<Double>> coordinates = new ArrayList<List<Double>>();
	
	public void addCoordinate(Double x, Double y){
		List<Double> coordinate = new ArrayList<Double>();
		coordinate.add(x);
		coordinate.add(y);
		this.coordinates.add(coordinate);
	}

}
