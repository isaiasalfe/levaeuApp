package models.wsGeo

import play.api.libs.ws.WS
import play.api.libs.concurrent.Promise
import play.api.libs.ws.Response
import java.util.concurrent.TimeUnit

object WSGeo {

  def testeWsRotas: String = {
    
    var promise:Promise[Response] = WS.url("http://localhost:9009/ws/getRoutesByCarrier").post("")
    var response = promise.await(10000, TimeUnit.MILLISECONDS).get
    response.json.toString
  }

}