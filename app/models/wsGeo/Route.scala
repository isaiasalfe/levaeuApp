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

object Route {
  
  def listIds(routes: Array[Route]):Array[Int] = {
    
    var ids: Array[Int] = new Array(routes.length)
    
    for(i <- 0 to routes.length -1) {
      
      ids(i) = routes(i).id
    }
    
    ids
  }
}

