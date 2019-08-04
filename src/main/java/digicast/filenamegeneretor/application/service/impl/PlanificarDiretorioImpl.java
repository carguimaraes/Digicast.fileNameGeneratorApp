package digicast.filenamegeneretor.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import digicast.filenamegeneretor.application.controller.ProcessaArquivoRequest;
import digicast.filenamegeneretor.application.service.PlanificarDiretorio;
import digicast.filenamegeneretor.domain.entity.Arquivo;
import digicast.filenamegeneretor.domain.exception.DiretorioException;
import digicast.filenamegeneretor.domain.factory.ArquivoRepositoryFactory;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanificarDiretorioImpl implements PlanificarDiretorio{
	
	@Autowired
	private ArquivoRepositoryFactory arquivoRepositoryFactory;

	@Override
	public void executar(ProcessaArquivoRequest processaArquivoRequest) {
		 
		try {
			
			ArquivoRepository arquivoRepository=	arquivoRepositoryFactory.getNew(processaArquivoRequest.getRootFolder());
			
			
			List<Arquivo> listArquivo=arquivoRepository.getAll();
			
			
			System.out.println("--------------------------------------------------------");
			for(Arquivo item: listArquivo) {
			
				String nomeNovo=processaArquivoRequest.getBusinessUnit()+"_"+
				processaArquivoRequest.getDepartment()+"_"+
				processaArquivoRequest.getExportDate()+"_"+
				processaArquivoRequest.getMetadataType()+"_"+
				processaArquivoRequest.getVoyageId()+"_"+
				processaArquivoRequest.getProjectId()+"_"+
				processaArquivoRequest.getJobId()+"_"+
				item.getNome();
				
			//	log.info("--------------------------------------------------------");
			//	log.info("--->"+nomeNovo);
			//	log.info("--------------------------------------------------------");
				
				
					
					System.out.println("--->"+nomeNovo);
			
			}
			System.out.println("--------------------------------------------------------");
			
			
		} catch (DiretorioException e) {
		  log.error(e.getMessage(),e);
		}
		
	}

}
