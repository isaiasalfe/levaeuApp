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


object Application extends Controller {

	def index = Action {
		
		Ok(views.html.index("Your new application is ready."))
		
	}

	def estados = Action {
  	
		val json = Json.generate(Estado.all())
		Ok(json).as("application/json")
	}
  
	def testeWsRotas = Action {
		val json:String = WSGeo.testeWsRotas
		Ok(json).as("application/json")
//		Logger.info("Testando rotas");
//		
//		Async {
//			WS.url("http://localhost:9009/ws/getRoutesByCarrier").post("").map { response =>
//			  	
//			  Logger.info(response.json.toString)
//				Ok(response.json).as("application/json")
//			}
//		} 
	}
  
}