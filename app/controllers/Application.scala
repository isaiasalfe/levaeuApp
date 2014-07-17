package controllers

import models.Estado
import play.api._
import play.api.mvc._
import com.codahale.jerkson.Json

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def estados = Action {
  	//Ok("{\"mensagem\":\"Ola!\"}").as("application/json")
    val estados = Estado.findAll()
    val json = Json.generate(estados)
    Ok(json).as("application/json")
  }

}