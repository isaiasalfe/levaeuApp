package controllers


import play.api._
import play.api.mvc._
import com.codahale.jerkson.Json

import models._

object Application extends Controller {
  
  def index = Action {

    Ok(views.html.index("Your new application is ready."))
  }

  def estados = Action {
  	
    val json = Json.generate(Estado.all())
    Ok(json).as("application/json")

  }

  def getRotas(idTransportadora: Long) = Action {
  	
  	val transportadora = Transportadora.findById(idTransportadora)

    val json = Json.generate(transportadora)
    Ok(json).as("application/json")

  }

}