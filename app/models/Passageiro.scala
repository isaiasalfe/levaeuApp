package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Passageiro(id: Long, data_nascimento: String, mensalidade: Double)
 
object Passageiro {
 
  val passageiro = {
    get[Long]("id") ~
    get[String]("data_nascimento") ~
    get[Double]("mensalidade") map {
      case id~data_nascimento~mensalidade => Passageiro(id, data_nascimento, mensalidade)
    }
  }

  def all(): List[Passageiro] = DB.withConnection { implicit c =>
    SQL("select * from passageiro").as(passageiro *)
  }


}