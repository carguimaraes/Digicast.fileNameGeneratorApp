package digicast.filenamegeneretor;
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
		
	}

}
