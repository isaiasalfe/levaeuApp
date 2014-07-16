package models


package models

import play.api.db._
import play.api.Play.current
 
import anorm._
import anorm.SqlParser._
 
case class Veiculo(id: Pk[Long], placa: String)

 
object Veiculo {
 
  val simple = {
    get[Pk[Long]]("id") ~
    get[String]("placa") map {
      case id~placa => Veiculo(id, placa)
    }
  }
 
  def findAll(): Seq[Veiculo] = {
    DB.withConnection { implicit connection =>
      SQL("select * from veiculo").as(Veiculo.simple *)
    }
  }
 
  def create(veiculo: Veiculo): Unit = {
    DB.withConnection { implicit connection =>
      SQL("insert into veiculo(placa) values ({placa})").on(
        'placa -> veiculo.placa
      ).executeUpdate()
    }
  }
 
}