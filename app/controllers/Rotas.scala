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
import models.vo.GeoJson
import models.vo.RotaVO
import models.wsGeo.Route
import models.vo.PontoVO
import models.wsGeo.Path
import models.vo.RotaVO
import models.wsGeo.Route


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
	var rotas: Array[RotaVO] = Rota.getRotasDisponiveis(transportadora)
	
    val json = Json.generate(rotas)
    Ok(json).as("application/json")

  }
  
  def getRotasProcesso = Action {

	  var rotas: Array[RotaVO] = Rota.getRotasDisponiveis()
	  
	  var json:String = Json.generate("")
	  Ok(json).as("application/json")
	}

  def consolidar() = Action {
    
	var mensagem:Mensagem = Rota.consolidar
	var json:String = Json.generate(mensagem)
	
    Ok(json).as("application/json")
  }
  
  def excluirCandidatas = Action {

	  var mensagem:Mensagem = Rota.excluirCandidatas
	  var json:String = Json.generate(mensagem)
	  
	  Ok(json).as("application/json")
  }
	
	def euQuero(idRota: Long, idVeiculo:Long) = Action {

	  var mensagem = Rota.euQuero(idRota, idVeiculo)

	  val json = Json.generate(mensagem)
	  Ok(json).as("application/json")
	}
	

}
