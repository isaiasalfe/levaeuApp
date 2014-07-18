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
import models.vo.RotaVO
import models.vo.GeoJson
import models.vo.RotaVO
import models.vo.RotaVO


object Application extends Controller {

	def index = Action {
		
		Ok(views.html.index("Your new application is ready."))
		
	}

	def estados = Action {
  	
    val json = Json.generate(Estado.all())
    Ok(json).as("application/json")

  }

  def getRotas(idTransportadora: Long) = Action {
  	
    var transportadora = Transportadora.findById(idTransportadora)
    
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
		
		var json:String = WSGeo.getRotasTransportadora(carrier)
		Ok(json).as("application/json")
	}
}
