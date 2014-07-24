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
import play.Logger
import java.util.Arrays

case class Rota(id: Long, id_veiculo: Long, candidata: Boolean)
 
object Rota {
 
  val rota = {
    get[Long]("id") ~
    get[Long]("id_veiculo") ~
    get[Boolean]("candidata") map {
      case id~id_veiculo~candidata => Rota(id, id_veiculo, candidata)
    }
  }

  def all(): List[Rota] = DB.withConnection { implicit c =>
   
    SQL("select * from rota").as(rota *)
  }
  
  def findNotIn(ids: Array[Long]): List[Rota] = DB.withConnection { implicit c =>
  	
    if (ids == null || ids.length == 0)
    	return List()  
    
    SQL("select * from rota where id not in (" + List.fromArray(ids).mkString(",") + ")").as(rota *)
  }
  
  def delete(rota: Rota) = {
    
	DB.withConnection { implicit connection =>
		SQL(""" delete from Rota where id = {id_rota} """).on('id_rota -> rota.id).executeUpdate
	}
  }
  
  def updateRotasCandidatas(candidatas: Boolean) = {
    
	DB.withConnection { implicit connection =>
		SQL(""" update Rota set candidata = {candidata} """).on('candidata -> candidatas).executeUpdate
	}
  }
  
  
  def getRotasDisponiveis(transportadora: Transportadora):Array[RotaVO] = {
    
	  var carrier: Carrier = new Carrier(transportadora)
	  var routes: Array[Route] = WSGeo.getRoutesByCarrier(carrier)
	  var rotas: Array[RotaVO] = new RotasParser().parse(routes)
	  rotas
  }
  
  def consolidar() = {
    
	  var routes:Array[Route] = WSGeo.getAllRoutes
	  var rotasObsoletas:List[Rota] = findNotIn( Route.listIds(routes) )
	  
	  if (rotasObsoletas != null && rotasObsoletas.length > 0) {
    
		  for(i <- 0 to rotasObsoletas.length -1) {
			  delete(rotasObsoletas(i))
		  }
	  }
	  
	  updateRotasCandidatas(false)
  }


}