package digicast.filenamegeneretor.domain.entity;

 
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Arquivo {
	
	@Getter 
	private UUID id;

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String path;
	
	private List<MetaDado> lstIpt;
	private List<MetaDado> lstExifIFD0;
	
	private Arquivo() {}
	
	public Arquivo(UUID id, String nome, String path, List<MetaDado> lstIpt,  List<MetaDado> lstExifIFD0 ) {
		super();
		this.id = id;
		this.nome = nome;
		this.path = path;
		this.lstIpt=lstIpt;
		this.lstExifIFD0=lstExifIFD0;
		 
	}
	
	public List<MetaDado> getLstIpt() {
		 
		return  Collections.unmodifiableList(lstIpt);
	}
	public List<MetaDado> getLstExifIFD0() {
		 
		return  Collections.unmodifiableList(lstExifIFD0);
	}
}
