package digicast.filenamegeneretor.domain.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class MetaDado {
	
	public MetaDado() {}
	public MetaDado(String tagNome,String tagValor) {
		this.tagNome=tagNome;
		this.tagValor=tagValor;
	}
	
	@Getter @Setter  
	private String tagNome;
	@Getter @Setter 
	private String tagValor;

}
