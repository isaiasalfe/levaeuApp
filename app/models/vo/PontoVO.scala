package models.vo

import org.codehaus.jackson.annotate.JsonProperty
import models.wsGeo.Path
import models.wsGeo.Point
import models.Ponto

class PontoVO {
  
  	@JsonProperty
	var x: Double = 0.0
	
	@JsonProperty
	var y: Double = 0.0
	
	
	def this(point: Point) = {
	  this()
	  x = point.coordinates(0)
	  y = point.coordinates(1)
	}
  	
  	def this(ponto: Ponto) = {
  	  this()
	  x = ponto.latitude
	  y = ponto.longitude
	}
}