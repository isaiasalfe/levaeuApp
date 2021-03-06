package models.wsGeo

import play.api.libs.ws.WS
import play.api.libs.concurrent.Promise
import play.api.libs.ws.Response
import java.util.concurrent.TimeUnit
import play.api.Play
import models.Transportadora
import com.codahale.jerkson.Json
import org.codehaus.jackson.map.SerializationConfig
import java.util.ArrayList
import play.Logger

object WSGeo {
	
	val url:String = Play.current.configuration.getString("wsGeo.url").get
	val timeout:Int = Integer.parseInt( Play.current.configuration.getString("wsGeo.timeout").get )
	
	def getRoutesByCarrier(carrier: Carrier): Array[Route] = {

		var json: String = Json.generate(carrier)
		
		var promise:Promise[Response] = WS.url(url + "/ws/getRoutesByCarrier")
		.withHeaders("Content-Type" -> "application/json")
		.post(json) //"application/json"
		
		Logger.info(">>>>>>>>>>>>>Consumindo BDGeo - URL: ")
		Logger.info(url + "/levaeuGeoWS/ws/getRoutesByCarrier")
		
		var response = promise.await(timeout, TimeUnit.MILLISECONDS).get
		
		Logger.info("")
		Logger.info("")
		Logger.info(">>>>>>>>>>>>>Resposta: ")
		Logger.info(response.body.toString())
		Logger.info("")
		Logger.info("")
		var routes:Array[Route] = Json.parse[Array[Route]](response.body)
		
		routes
	}
	
	def getAllRoutes(): Array[Route] = {
		
		var promise:Promise[Response] = WS.url(url + "/ws/getAllRoutes").get()
		var response = promise.await(timeout, TimeUnit.MILLISECONDS).get

		var routes:Array[Route] = Json.parse[Array[Route]](response.body)
		
		routes
	}
	
	def recalculateRoutes(): Array[String] = {
		
		var promise:Promise[Response] = WS.url(url + "/ws/recalculateRoutes").get()
		var response = promise.await(timeout, TimeUnit.MILLISECONDS).get

		var messages:Array[String] = Json.parse[Array[String]](response.body)
		
		messages
	}
}