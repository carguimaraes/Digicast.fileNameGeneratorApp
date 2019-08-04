package digicast.filenamegeneretor.application.controller.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import digicast.filenamegeneretor.Principal;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoRequest;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoResponse;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProcessaArquivoControllerImpl implements ProcessaArquivoController  {
	
	 

	@Override
	public ProcessaArquivoResponse lerDiretorio(ProcessaArquivoRequest processaArquivoRequest) {
		
		List<String> lstMsg=new ArrayList<>();
		
		if(processaArquivoRequest==null) {
			 
			return  new ProcessaArquivoResponse(  Arrays.asList("InfoRequest nao pode ser null"));
		}
		
		 
		if(!processaArquivoRequest.isvalid()) {
			return new ProcessaArquivoResponse(processaArquivoRequest.getMensagens());
		}
		
	 	File file=new File( processaArquivoRequest.getRootFolder());
	 	
	 	
		
		//verifica se o nome diretorio existe
		
		return new ProcessaArquivoResponse();
		
	}

	

	 
}
