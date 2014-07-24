package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import models.vo.RotaVO
import models.wsGeo.Carrier
import models.wsGeo.Route
import models.wsGeo.WSGeo

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
  
  def getRotasDisponiveis(transportadora: Transportadora):Array[RotaVO] = {
    
	  var carrier: Carrier = new Carrier(transportadora)
	  var routes: Array[Route] = WSGeo.getRoutesByCarrier(carrier)
	  var rotas: Array[RotaVO] = new RotasParser().parse(routes)
	  rotas
  }


}