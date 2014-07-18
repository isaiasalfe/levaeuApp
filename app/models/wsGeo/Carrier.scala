package models.wsGeo

import org.codehaus.jackson.annotate.JsonProperty

import models.Transportadora

class Carrier(transportadora: Transportadora) {
	 
	@JsonProperty
	var carrierLocation:Point = new Point()
}
