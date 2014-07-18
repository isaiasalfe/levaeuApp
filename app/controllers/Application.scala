package controllers

import play.api._
import play.api.mvc._
import com.codahale.jerkson.Json
import models._
import models.RotaVO

object Application extends Controller {
  
  def index = Action {

    Ok(views.html.index("Your new application is ready."))
  }

  def estados = Action {
  	
    val json = Json.generate(Estado.all())
    Ok(json).as("application/json")

  }

  def getRotas(idTransportadora: Long) = Action {

    object rota {
      var id:Long = 0
      var pontos:Seq[Int] = Seq(1, 2, 3)
    }
    
  	//val transportadora = Transportadora.findById(idTransportadora)
    val json = Json.generate(rota)
    Ok(json).as("application/json")

  }

}