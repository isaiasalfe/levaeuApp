package models.wsGeo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Point {

	@JsonProperty
	public String type = "Point";
	
	@JsonProperty
	public double [] coordinates;
	
}
