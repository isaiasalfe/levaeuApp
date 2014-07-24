package models.wsGeo

import org.codehaus.jackson.annotate.JsonProperty

class Path {

  @JsonProperty
  var id:Long = 0
  
  @JsonProperty
  var source: Point = new Point()
  
  @JsonProperty
  var target: Point = new Point()
}