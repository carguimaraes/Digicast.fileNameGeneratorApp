package digicast.filenamegeneretor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import digicast.filenamegeneretor.application.controller.ProcessaArquivoRequest;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoResponse;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoController;
import lombok.extern.slf4j.Slf4j;

//https://github.com/drewnoakes/metadata-extractor/wiki/Getting-Started-(Java)
@Slf4j
@SpringBootApplication
public class Principal implements CommandLineRunner {

	@Autowired
	private ProcessaArquivoController processaArquivoController;

	public static void main(String[] args) {
		log.info("*******************************");
		log.info("INICIO");
		log.info("*******************************");
		SpringApplication.run(Principal.class, args);
		log.info("*******************************");
		log.info("FIM");
		log.info("*******************************");
	}

	@Override
	public void run(String... args) throws Exception {
		String paqhArq = "";

		if (args.length > 0) {

			paqhArq = args[0]; // "/home/carguimaraes/Documentos/Silversea Expeditions
								// Library-20190727T143910Z-001/Silversea Expeditions Library";
		//	paqhArq="/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/";
		}

		ProcessaArquivoRequest processaArquivoRequest = new ProcessaArquivoRequest();

		processaArquivoRequest.setRootFolder(paqhArq);
		processaArquivoRequest.setBusinessUnit("MC");
		processaArquivoRequest.setDepartment("CRHQ");
		processaArquivoRequest.setMetadataType("MAPS");
		processaArquivoRequest.setVoyageId("VID");
		processaArquivoRequest.setProjectId("PID");
		processaArquivoRequest.setJobId("JID");
		processaArquivoRequest.setExportDate("20190803");

		ProcessaArquivoResponse processaArquivoResponse = processaArquivoController
				.lerDiretorio(processaArquivoRequest);

		if (processaArquivoResponse.isvalid()) {
			log.info("*******************************");
			log.info("SUCESSO");
			log.info("*******************************");
		} else {
			log.info("*******************************");
			log.info("FALHA");
			log.info("*******************************");
			List<String> listMsg = processaArquivoResponse.getMensagens();
			for (String item : listMsg) {
				log.info("ERRO MSG: " + item);
			}

			log.info("*******************************");
		}

	}

}
