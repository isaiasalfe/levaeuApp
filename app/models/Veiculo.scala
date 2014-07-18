package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Veiculo(id: Long, id_transportadora: Long, renavam: String, placa: String, capacidade: Int)
 
object Veiculo {
 
  val veiculo = {
    get[Long]("id") ~
    get[Long]("id_transportadora") ~
    get[String]("renavam") ~
    get[String]("placa") ~
    get[Int]("capacidade") map {
      case id~id_transportadora~renavam~placa~capacidade => Veiculo(id, id_transportadora, renavam, placa, capacidade)
    }
  }

  def findByTransportadora(idTransportadora: Long): List[Veiculo] = DB.withConnection { implicit c =>
    SQL("select * from veiculo where id_transportadora = " + idTransportadora).as(veiculo *)
  }


}