package digicast.filenamegeneretor.domain.entity;

 
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
	
	@Getter @Setter
	private String metadados;
	
	private List<MetaDado> lstIpt;
	private List<MetaDado> lstExifIFD0;
	
	private Arquivo() {}
	
	public Arquivo(UUID id, String nome, String path, List<MetaDado> lstIpt,  List<MetaDado> lstExifIFD0 ) {
		super();
		this.id = id;
		this.nome = nome;
		this.path = path;
		this.metadados = metadados;
	}
}
