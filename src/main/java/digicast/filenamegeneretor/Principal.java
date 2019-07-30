package digicast.filenamegeneretor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

@SpringBootApplication
public class Principal implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory.getLogger(Principal.class);

	public static void main(String[] args) {
		  LOG.info("INICIO ...");
	       SpringApplication.run(Principal.class, args);
	       LOG.info("FIM");

	}

	@Override
	public void run(String... args) throws Exception {
		
		String paqhArq="/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library/Destination/Antarctica/EX0015.tif";
		
	    System.out.println("===================================================");
		File f = new File(paqhArq);
		Metadata metadata = ImageMetadataReader.readMetadata(f);
		
		
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}
	   System.out.println("===================================================");
	 

		
	}
	
	private void copiaInit() throws IOException {
		 LOG.info("EXECUTANDO LEITURA...");
			
		  Stream<Path> w = Files.walk(Paths.get("/home/carguimaraes/Documentos/gma"));
		  
		   
		  w.sorted().forEach((x)->{
	          if( x.toFile().isDirectory()) {
	        	  System.out.println("--------------"+x.normalize());
	          }
	          else
	          {
	        	  copiarARquivo(x.normalize());
	        	  
	          }
			  
		  });
		  
	}
	
	private void copiarARquivo(Path path) {
		
		//String valor=path.toUri().getPath();
		
		String fullPath=path.toUri().getPath();
		String nomeArquivo=path.getFileName().toString();
		
		String novoNome="";
		String[] filePaths = fullPath.split(File.separator);
		for(String x : filePaths) {
			 
			novoNome=novoNome+"_"+x;
		}
		
				
		System.out.println(fullPath);  
	    System.out.println(nomeArquivo);  
	    System.out.println(novoNome);  
	    
		
	
		File source = new File(fullPath );
		File dest = new File("/home/carguimaraes/Documentos/out/"+novoNome);
		
		try {
		    FileUtils.copyFile(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}

}
