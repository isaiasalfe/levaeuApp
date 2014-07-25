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
import models.vo.PontoVO


object Transportadoras extends Controller {

	def findSede(idTransportadora: Long) = Action {
  	
		Logger.info(" --- BUSCANDO SEDE DA TRANSPORTADORA " + idTransportadora)
		
		var transportadora:Transportadora = Transportadora.findById(idTransportadora)
		var ponto:Ponto = transportadora.getPontoSede
		
		val json = Json.generate(new PontoVO(ponto))
		Ok(json).as("application/json")
	}

}
