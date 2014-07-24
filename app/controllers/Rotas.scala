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
import models.vo.PontoVO
import models.wsGeo.Path
import models.vo.PontoVO


object Rotas extends Controller {

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

	def estados = Action {
		val json = Json.generate(Estado.all())
		Ok(json).as("application/json")
	}

  def getRotas(idTransportadora: Long) = Action {
  	
    Logger.info(" --- BUSCANDO ROTAS DA TRANSPORTADORA " + idTransportadora)
    
    var transportadora = Transportadora.findById(idTransportadora)
    var endereco = Endereco.findById(transportadora.id_endereco)
    var ponto = Ponto.findById(endereco.id_ponto)
    
    var point = new Point()
    point.coordinates = new Array[Double](2)
    point.coordinates(0) = ponto.latitude
    point.coordinates(1) = ponto.longitude
    
    var carrier: Carrier = new Carrier(transportadora)
    carrier.carrierLocation = point
	var routes: Array[Route] = WSGeo.getRoutesByCarrier(carrier)
    
    //percorrer as rotas e montar uma lista de rotaVO
    //retornar essa lista de rotaVO
	
	var rotas: Array[RotaVO] = new Array[RotaVO](routes.length)
	
	for(j <- 0 to routes.length -1) {
		
		var rotaVO: RotaVO = new RotaVO
		rotaVO.id = routes(j).id
		rotaVO.distancia_sede_km = 30
		rotaVO.caminho = routes(j).track
		rotaVO.pontos = new Array[PontoVO](routes(j).paths.length * 2)
		
		for(i <- 0 to routes(j).paths.length -1) {
			
		  rotaVO.pontos(i*2) = new PontoVO(routes(j).paths(i).source)
		  rotaVO.pontos((i*2) + 1) = new PontoVO(routes(j).paths(i).target)
		}
		
		rotas(j) = rotaVO
	}
	
    val json = Json.generate(rotas)
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
	
	def euQuero(idRota: Long, idVeiculo:Long) = Action {

	  var mensagem = Rota.euQuero(idRota, idVeiculo)

	  val json = Json.generate(mensagem)
	  Ok(json).as("application/json")
	}
	
	def getRotasProcesso = Action {

	  //o mesmo que o getRotas so que sem o idTransportadora
	  
	  var rotasGeo: Array[RotaVO] = new Array[RotaVO](10)
	  var rotas: Array[RotaVO] = new Array[RotaVO](10)
	  
	  
	  for(i <- 0 to rotasGeo.length -1) {
	    
	    for(j <- 0 to rotas.length -1) {
	      
	      //if(rotasGeo(i).id == rotas.id)
	      
	    }
	    
	  }
	  
	  var json:String = Json.generate("")
	  Ok(json).as("application/json")
	}
}
