package manual;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import digicast.filenamegeneretor.Principal;
import digicast.filenamegeneretor.application.controller.impl.ProcessaArquivoControllerImpl;
import digicast.filenamegeneretor.domain.entity.Arquivo;
import digicast.filenamegeneretor.domain.exception.DiretorioException;
import digicast.filenamegeneretor.domain.factory.ArquivoRepositoryFactory;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Principal.class, initializers = ConfigFileApplicationContextInitializer.class)
public class LeArquivoTest {
	
	@Autowired
	private ArquivoRepositoryFactory arquivoRepositoryFactory;
	
	//@Test
	public void geraArquivos()  {
		String paqhArq = "/home/carguimaraes/Documentos/gmax";
		//String paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library";
		
	 
		ArquivoRepository arquivoRepository;
		try {
			arquivoRepository = arquivoRepositoryFactory.getNew(paqhArq);
			List<Arquivo>  listArquivo=arquivoRepository.getAll();
			
			for(Arquivo item: listArquivo) {
				System.out.println("================================================");
				System.out.println("ID..:"+item.getId());
				System.out.println("NOME:"+item.getNome());
				System.out.println("PATH:"+item.getPath());
				System.out.println("================================================");
			}
		} catch (  DiretorioException e) {
			log.error("==========================================");
			log.error("ERRO LEITURA DIRETORIO");
		    log.error(e.getMessage() + "- "+e.getClass().getName());
			log.error("==========================================");
		}
		
		 
		
		
	}
	
	private Arquivo geraArquivo(Path path) {
		 
				String nomeArquivo = path.getFileName().toString();
				String onlyPath=path.getParent().toString();
 
				
				String[] filePaths = onlyPath.split(File.separator);
				 
				//System.out.println("*********************************************");
				//System.out.println("NOME ARQUIVO......: "+nomeArquivo);
				//System.out.println("path.getFileName(): "+onlyPath);
				//System.out.println("0,1,2: "+filePaths[1]+" - "+filePaths[2]);
				//System.out.println("*********************************************");
				
			 
				//String uuidStr = uuid.toString();
				
				Arquivo arquivo= new Arquivo(UUID.randomUUID(),nomeArquivo,onlyPath,null);
				
		return arquivo;
	}
	
	
//	@Ignore(value="usado para teste manual - comentar o ignore para executar o teste")
//	@Test
	public void leArvoreDir() throws IOException {
		
		Stream<Path> w = Files.walk(Paths.get("/home/carguimaraes/Documentos/gma"));
		
		w.sorted().forEach((x) -> {
			if (x.toFile().isDirectory()) {
				System.out.println("--------------" + x.normalize());
			} else {
				System.out.println("FILE--------------" + x.normalize());

			}

		});
 
		
	}
	
	private void copiaInit() throws IOException {
		  

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
