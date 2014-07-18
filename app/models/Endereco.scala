package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Endereco(id: Long, logradouro: String, numero: String, complemento: String, bairro: String, cep:String, id_ponto: Long)
 
object Endereco {
 
  val endereco = {
    get[Long]("id") ~
    get[String]("logradouro") ~
    get[String]("numero") ~
    get[String]("complemento") ~
    get[String]("bairro") ~
    get[String]("cep") ~
    get[Long]("id_ponto") map {
      case id~logradouro~numero~complemento~bairro~cep~id_ponto => Endereco(id, logradouro, numero, complemento, bairro, cep, id_ponto)
    }
  }

  def all(): List[Endereco] = DB.withConnection { implicit c =>
    SQL("select * from endereco").as(endereco *)
  }
  
  def findById(idEndereco: Long): List[Endereco] = DB.withConnection { implicit c =>
    SQL("select * from endereco where id = " + idEndereco).as(endereco *)
  }


}