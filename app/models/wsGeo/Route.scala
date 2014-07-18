package models.wsGeo

import models.vo.GeoJson

class Route {

  var id: Int = 0
  var track: GeoJson = new GeoJson("Point")
  var paths: Array[Path] = new Array[Path](0)
}