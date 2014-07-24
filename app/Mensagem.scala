package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Mensagem (mensagem: String, tipo: String)

object Mensagem{
  
    val veiculo = {
    get[String]("mensagem") ~
    get[String]("tipo") map {
      case mensagem~tipo => Mensagem(mensagem, tipo)
    }
  }
  
}