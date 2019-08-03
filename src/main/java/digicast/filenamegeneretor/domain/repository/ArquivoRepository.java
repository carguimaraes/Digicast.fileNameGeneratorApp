package digicast.filenamegeneretor.domain.repository;

import java.util.List;

import digicast.filenamegeneretor.domain.entity.Arquivo;

public interface ArquivoRepository {
	
	  public List<Arquivo> getAll();

}
