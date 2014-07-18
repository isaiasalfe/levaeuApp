package models.vo

import org.codehaus.jackson.annotate.JsonProperty
import models.wsGeo.Path
import models.wsGeo.Point

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
}