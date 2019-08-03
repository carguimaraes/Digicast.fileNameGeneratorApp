package digicast.filenamegeneretor.domain.factory;

import java.io.IOException;

import digicast.filenamegeneretor.domain.exception.DiretorioException;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;

public interface ArquivoRepositoryFactory {
	
	
	public ArquivoRepository getNew(String pathRoot) throws DiretorioException;

}
