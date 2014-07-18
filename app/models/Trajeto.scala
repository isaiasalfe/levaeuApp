package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Trajeto(id: Long, id_endereco_partida: Long, id_endereco_chegada: Long, hora_partida: String, hora_chegada: String)
 
object Trajeto {
 
  val trajeto = {
    get[Long]("id") ~
    get[Long]("id_endereco_partida") ~
    get[Long]("id_endereco_chegada") ~
    get[String]("hora_partida") ~
    get[String]("hora_chegada") map {
      case id~id_endereco_partida~id_endereco_chegada~hora_partida~hora_chegada => Trajeto(id, id_endereco_partida, id_endereco_chegada, hora_partida, hora_chegada)
    }
  }

  def all(): List[Trajeto] = DB.withConnection { implicit c =>
    SQL("select * from trajeto").as(trajeto *)
  }


}