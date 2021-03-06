package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Ponto(id: Long, latitude: Double, longitude: Double) {
  
  def getCoordenadas(): Array[Double] = {
    
    var coordenadas:Array[Double] = new Array[Double](2)
    coordenadas(0) = latitude
    coordenadas(1) = longitude
    coordenadas
  }
}
 
object Ponto {
 
  val ponto = {
    get[Long]("id") ~
    get[Double]("latitude") ~
    get[Double]("longitude") map {
      case id~latitude~longitude => Ponto(id, latitude, longitude)
    }
  }

  def all(): List[Ponto] = DB.withConnection { implicit c =>
    SQL("select * from ponto").as(ponto *)
  }
  
  def findById(idPonto: Long): Ponto = DB.withConnection { implicit c =>
    SQL("select * from ponto where id = " + idPonto).as(ponto.single)
  }


}