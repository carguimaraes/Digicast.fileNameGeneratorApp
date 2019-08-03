package digicast.filenamegeneretor.domain.entity;

import lombok.Getter;
import lombok.Setter;

public class Arquivo {
	
	@Getter @Setter
	private long id;

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String path;
	
	@Getter @Setter
	private String metadados;

	
	public Arquivo() {}
	
	public Arquivo(long id, String nome, String path, String metadados) {
		super();
		this.id = id;
		this.nome = nome;
		this.path = path;
		this.metadados = metadados;
	}
}
