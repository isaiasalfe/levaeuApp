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
import com.google.gson.Gson


object Veiculos extends Controller {

	def list(idTransportadora: Long) = Action {
  	
		Logger.info(" --- BUSCANDO VEICULOS DA TRANSPORTADORA " + idTransportadora)
		
		val json = Json.generate(Veiculo.findByTransportadora(idTransportadora))
		Ok(json).as("application/json")
	}

}
