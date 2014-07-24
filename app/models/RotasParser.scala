package models

import models.vo.RotaVO
import models.wsGeo.Route
import models.vo.PontoVO

class RotasParser {
  
	def parse( routes: Array[Route]):Array[RotaVO] = {
  
		var rotas: Array[RotaVO] = new Array[RotaVO](routes.length)
	
		for(j <- 0 to routes.length -1) {
			
			var rotaVO: RotaVO = new RotaVO
			rotaVO.id = routes(j).id
			rotaVO.distancia_sede_km = 30
			rotaVO.caminho = routes(j).track
			rotaVO.pontos = new Array[PontoVO](routes(j).paths.length * 2)
			
			for(i <- 0 to routes(j).paths.length -1) {
				
			  rotaVO.pontos(i*2) = new PontoVO(routes(j).paths(i).source)
			  rotaVO.pontos((i*2) + 1) = new PontoVO(routes(j).paths(i).target)
			}
			
			rotas(j) = rotaVO
		}
		
		rotas;
  }
}