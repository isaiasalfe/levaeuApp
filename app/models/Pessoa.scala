package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Pessoa(id: Long, nome: String, tipo_pessoa: String, numero_documento: String)
 
object Pessoa {
 
  val pessoa = {
    get[Long]("id") ~
    get[String]("nome") ~
    get[String]("tipo_pessoa") ~
    get[String]("numero_documento") map {
      case id~nome~tipo_pessoa~numero_documento => Pessoa(id, nome, tipo_pessoa, numero_documento)
    }
  }

  def all(): List[Pessoa] = DB.withConnection { implicit c =>
    SQL("select * from pessoa").as(pessoa *)
  }


}