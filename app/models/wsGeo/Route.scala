package models.wsGeo

import org.codehaus.jackson.annotate.JsonProperty

class Route {

  @JsonProperty
  var id: Int = 0
  
  @JsonProperty
  var track = {}
  
  @JsonProperty
  var paths: Array[Path] = new Array[Path](0)
}