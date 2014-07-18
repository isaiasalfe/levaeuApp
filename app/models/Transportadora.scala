package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Transportadora(id: Long, id_endereco: Long)
 
object Transportadora {
 
  val transportadora = {
    get[Long]("id") ~
    get[Long]("id_endereco") map {
      case id~id_endereco => Transportadora(id, id_endereco)
    }
  }

  def all(): List[Transportadora] = DB.withConnection { implicit c =>
    SQL("select * from transportadora").as(transportadora *)
  }

  def findById(idTransportadora: Long): Transportadora = DB.withConnection { implicit c =>
    SQL("select * from transportadora where id = " + idTransportadora + "limit 1")
    .as(transportadora.single)
  }
  
  def findBy(key: String, value: String): Option[Transportadora] = DB.withConnection { implicit connection =>
    SQL("select * from transportadora where `$key` = {value} limit 1")
       .as(transportadora.singleOpt)
       //.on("value" -> value).as(transportadora.singleOpt)
}


}