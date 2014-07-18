package models.wsGeo

import play.api.libs.ws.WS
import play.api.libs.concurrent.Promise
import play.api.libs.ws.Response
import java.util.concurrent.TimeUnit
import play.api.Play
import models.Transportadora
import com.codahale.jerkson.Json
import org.codehaus.jackson.map.SerializationConfig

object WSGeo {
	
	val url:String = Play.current.configuration.getString("wsGeo.url").get
	val timeout:Int = Integer.parseInt( Play.current.configuration.getString("wsGeo.timeout").get )
	
	def getRotasTransportadora(carrier: Carrier): String = {

//		var json: String = Json.generate(carrier)
		
		var promise:Promise[Response] = WS.url(url + "/ws/getRoutesByCarrier").post("")
		var response = promise.await(timeout, TimeUnit.MILLISECONDS).get
		
		response.json.toString
	}
}