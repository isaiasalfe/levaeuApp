package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models.vo.RotaVO
import models.wsGeo.Carrier
import models.wsGeo.Route
import models.wsGeo.WSGeo
import models.wsGeo.Route
import java.util.ArrayList

case class Rota(id: Long, id_veiculo: Long, candidata: Boolean){
  
  def this() = this(0, 0, false)
  
}
 
object Rota {
 
  val rota = {
    get[Long]("id") ~
    get[Long]("id_veiculo") ~
    get[Boolean]("candidata") map {
      case id~id_veiculo~candidata => Rota(id, id_veiculo, candidata)
    }
  }
  
    def euQuero(idRota: Long, idVeiculo:Long) : Mensagem = {
    
	  var mensagem:Mensagem = new Mensagem("A rota já foi atribuida a outro usuário!", "ERROR")
	  var rota:Rota = new Rota()
	  
	  try{
		  rota = Rota.findById(idRota)
	  } catch {
      	  case e: Exception => rota = null
	  }
	  
	  if(rota == null){
	    
	    var rota = new Rota(idRota, idVeiculo, true)
	    Rota.save(rota)
	    mensagem = new Mensagem("Rota vinculada com sucesso!", "SUCESS")
	    
	  }
	  
	  mensagem
  }

   def getRotasDisponiveis():Array[RotaVO] = {
    
	  var routes: Array[Route] = WSGeo.getAllRoutes()
	  
	  var rotasDisponiveis = removerRotasCadastradas(routes)
	  
	  var rotasParser: Array[RotaVO] = new RotasParser().parse(rotasDisponiveis.toArray)
	  rotasParser
  }
    
  def getRotasDisponiveis(transportadora: Transportadora):Array[RotaVO] = {
    
	  var carrier: Carrier = new Carrier(transportadora)
	  var routes: Array[Route] = WSGeo.getRoutesByCarrier(carrier)
	  
	  var rotasDisponiveis = removerRotasCadastradas(routes)
	  
	  var rotasParser: Array[RotaVO] = new RotasParser().parse(rotasDisponiveis.toArray)
	  rotasParser
  }
  
  def removerRotasCadastradas(routes: Array[Route]):List[Route] = {
    
	  var rotasDisponiveis = List[Route]() 
	  
	  var rotas: List[Rota] = Rota.all()
	  
	  for(i <- 0 to routes.length -1) {
	    
	    var encontrou:Boolean = false
	    
	    for(j <- 0 to rotas.length -1) {
	      if(routes(i).id == rotas(j).id)
	        encontrou = true;
	    }
	    
	    if(!encontrou){
	      rotasDisponiveis.+:(routes(i))
	    }
	    
	  }
	  
	  rotasDisponiveis
  }
  
  def consolidar():Array[Int] = {
    
	  var routes:Array[Route] = WSGeo.getAllRoutes
	  Route.listIds(routes)
  }

  def all(): List[Rota] = DB.withConnection { implicit c =>
    SQL("select * from rota").as(rota *)
  }
   
  def findById(idRota: Long): Rota = DB.withConnection { implicit c =>
    	SQL("select * from rota where id = " + idRota).as(rota.single)
  }
  
  def save(rota: Rota) {
    DB.withConnection { implicit connection =>
      SQL(""" 
            INSERT INTO Rota(id, id_veiculo, candidata) 
            VALUES({id}, {id_veiculo}, {candidata})
    	""").on(
          'id -> rota.id,
          'id_veiculo -> rota.id_veiculo,
          'candidata -> rota.candidata
      ).executeUpdate
    }
  }


}