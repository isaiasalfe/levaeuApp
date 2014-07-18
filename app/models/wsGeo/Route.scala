package models.wsGeo

import models.vo.GeoJson
import org.codehaus.jackson.annotate.JsonProperty

class Route {

  @JsonProperty
  var id: Int = 0
  
  @JsonProperty
  var track: GeoJson = new GeoJson("Point")
  
  @JsonProperty
  var paths: Array[Path] = new Array[Path](0)
}
