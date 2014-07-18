package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

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


}