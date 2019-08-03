package digicast.filenamegeneretor.application.controller.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import digicast.filenamegeneretor.Principal;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProcessaArquivoControllerImpl implements ProcessaArquivoController  {
	
	private static Logger LOG = LoggerFactory.getLogger(Principal.class);

	@Override
	public void lerDiretorio() {
		
		System.out.println("===================================================");
		System.out.println("INICIO PROJETO.... GMA TESTE-GMA-23333");
		System.out.println("===================================================");
	 
		
	}

	

	private void copiaInit() throws IOException {
		LOG.info("EXECUTANDO LEITURA...");

		Stream<Path> w = Files.walk(Paths.get("/home/carguimaraes/Documentos/gma"));

		w.sorted().forEach((x) -> {
			if (x.toFile().isDirectory()) {
				System.out.println("--------------" + x.normalize());
			} else {
				copiarARquivo(x.normalize());

			}

		});

	}

	private void copiarARquivo(Path path) {

		// String valor=path.toUri().getPath();

		String fullPath = path.toUri().getPath();
		String nomeArquivo = path.getFileName().toString();

		String novoNome = "";
		String[] filePaths = fullPath.split(File.separator);
		for (String x : filePaths) {

			novoNome = novoNome + "_" + x;
		}

		System.out.println(fullPath);
		System.out.println(nomeArquivo);
		System.out.println(novoNome);

		File source = new File(fullPath);
		File dest = new File("/home/carguimaraes/Documentos/out/" + novoNome);

		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
