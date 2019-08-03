package digicast.filenamegeneretor.infrastructure.repository;

import java.util.Collections;
import java.util.List;

import digicast.filenamegeneretor.domain.entity.Arquivo;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;

public class ArquivoRepositoryImpl implements ArquivoRepository{
	
	List<Arquivo> listArquivo; 
	
	
	public ArquivoRepositoryImpl(List<Arquivo> listArquivo) {
		this.listArquivo=listArquivo;
	}

	@Override
	public List<Arquivo> getAll() {
		 
		return  Collections.unmodifiableList(listArquivo);
	}

}
