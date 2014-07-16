package models
 
import play.api.db._
import play.api.Play.current
 
import anorm._
import anorm.SqlParser._
 
case class Pessoa(id: Pk[Long], nome: String)

 
object Pessoa {
 
  val simple = {
    get[Pk[Long]]("id") ~
    get[String]("nome") map {
      case id~nome => Pessoa(id, nome)
    }
  }
 
  def findAll(): Seq[Pessoa] = {
    DB.withConnection { implicit connection =>
      SQL("select * from pessoa").as(Pessoa.simple *)
    }
  }
 
  def create(pessoa: Pessoa): Unit = {
    DB.withConnection { implicit connection =>
      SQL("insert into pessoa(nome) values ({nome})").on(
        'nome -> pessoa.nome
      ).executeUpdate()
    }
  }
 
}

/*
package models

import play.api.db.slick.Config.driver.simple._

class Pessoa extends Table[(Long, String)]("PESSOA") {
  def id = column[Long]("ID")
  def nome = column[String]("NOME")
  def * = id ~ name
}
*/