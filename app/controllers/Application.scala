package controllers


import play.api._
import play.api.mvc._
import com.codahale.jerkson.Json
import models._
import play.api.libs.ws.WS
import play.api.libs.ws.Response
import scala.collection.immutable.Nil
import play.api.libs.concurrent.Promise
import models.wsGeo.WSGeo
import models.wsGeo.Carrier
import models.wsGeo.Point
import models.vo.RotaVO
import models.vo.GeoJson
import models.vo.RotaVO
import models.wsGeo.Route


object Application extends Controller {

	def index = Action {
		
		Ok(views.html.index("Your new application is ready."))
		
	}

	def estados = Action {
  	
    val json = Json.generate(Estado.all())
    Ok(json).as("application/json")

  }

  def getRotas(idTransportadora: Long) = Action {
  	
//    var transportadora = Transportadora.findById(idTransportadora)
//    var endereco = Endereco.findById(transportadora.id_endereco)
//    var ponto = Point.findById(endereco.id_ponto)
//    
//    var point = new Point()
//    point.coordinates = (ponto.latitude, ponto.longitude)
//    
//    var carrier: Carrier = new Carrier(transportadora)
//    carrier.point = point
//		
//	var routes: Array[Route] = WSGeo.getRoutesByCarrier(carrier)
    
    //percorrer as rotas e montar uma lista de rotaVO
    //retornar essa lista de rotaVO
	
    var rotaVO: RotaVO = new RotaVO
    rotaVO.id = 1
    rotaVO.distancia_sede_km = 10.2
    rotaVO.caminho.addCoordinate(1, 2)
    
    val json = Json.generate(rotaVO)
    Ok(json).as("application/json")

  }

	def testeWsRotas = Action {
		var transportadora: Transportadora = new Transportadora(45, 1)
		var carrier: Carrier = new Carrier(transportadora)
		carrier.carrierLocation.coordinates = new Array[Double](2)
		carrier.carrierLocation.coordinates(0) = 12.44
		carrier.carrierLocation.coordinates(1) = 23.99
		
		var routes:Array[Route] = WSGeo.getRoutesByCarrier(carrier)
		var json:String = Json.generate(routes)
		
		Ok(json).as("application/json")
	}
}
