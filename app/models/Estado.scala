package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Estado(id: Pk[Long], nome: String)
 
object Estado {
 
  val simple = {
    get[Pk[Long]]("id") ~
    get[String]("nome") map {
      case id~nome => Estado(id, nome)
    }
  }
 
  def findAll(): Seq[Estado] = {
    DB.withConnection { implicit connection =>
      SQL("select * from estado").as(Estado.simple *)
    }
  }

}