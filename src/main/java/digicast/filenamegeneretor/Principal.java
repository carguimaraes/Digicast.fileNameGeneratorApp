package digicast.filenamegeneretor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		  LOG.info("EXECUTANDO LEITURA...");
		
		  Stream<Path> w = Files.walk(Paths.get("/home/carguimaraes/Documentos/gma"));
		  
		//  w.filter(Files::isRegularFile)
		 // 	.forEach(System.out::println);
		 // w.forEach(System.out::println);
		  
		  w.sorted().forEach((x)->{
	          if( x.toFile().isDirectory()) {
	        	  System.out.println("--------------"+x.normalize());
	          }
	          else
	          {
	        	  System.out.println(x.normalize());  
	          }
			  
		  });
		  
	}

}
