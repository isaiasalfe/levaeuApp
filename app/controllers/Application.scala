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


object Application extends Controller {

	def index = Action {
		
		Ok(views.html.index("Your new application is ready."))
		
	}

	def estados = Action {
  	
    val json = Json.generate(Estado.all())
    Ok(json).as("application/json")

  }

  def getRotas(idTransportadora: Long) = Action {
  	
  	val transportadora = Transportadora.findById(idTransportadora)

    val json = Json.generate(transportadora)
    Ok(json).as("application/json")

  }

	def testeWsRotas = Action {
		var transportadora: Transportadora = new Transportadora(45, 1)
		var carrier: Carrier = new Carrier(transportadora)
		
		WSGeo.getRoutesByCarrier(carrier)
		var json:String = "{}"
		Ok(json).as("application/json")
	}
}
