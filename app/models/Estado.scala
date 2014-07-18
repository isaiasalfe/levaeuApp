package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Estado(id: Long, nome: String)
 
object Estado {
 
  val estado = {
    get[Long]("id") ~
    get[String]("nome") map {
      case id~nome => Estado(id, nome)
    }
  }

  def all(): List[Estado] = DB.withConnection { implicit c =>
    SQL("select * from estado").as(estado *)
  }


}