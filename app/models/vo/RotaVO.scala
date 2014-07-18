package models.vo

import org.codehaus.jackson.annotate.JsonProperty



class RotaVO {
  
	@JsonProperty
	var id: Long = 0
	
	@JsonProperty
	var distancia_sede_km: Double = 0.0
	
	@JsonProperty
	var caminho: GeoJson = new GeoJson("Point")

}