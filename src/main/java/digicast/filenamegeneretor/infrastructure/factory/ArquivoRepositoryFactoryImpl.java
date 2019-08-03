package digicast.filenamegeneretor.infrastructure.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import digicast.filenamegeneretor.domain.entity.Arquivo;
import digicast.filenamegeneretor.domain.exception.DiretorioException;
import digicast.filenamegeneretor.domain.factory.ArquivoRepositoryFactory;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;
import digicast.filenamegeneretor.infrastructure.repository.ArquivoRepositoryImpl;

@Component
public class ArquivoRepositoryFactoryImpl implements ArquivoRepositoryFactory {

	@Override
	public ArquivoRepository getNew(String pathRoot) throws DiretorioException{

		List<Arquivo> listArquivo = new ArrayList<>();
		Stream<Path> w=null;
		
		try {
			w = Files.walk(Paths.get(pathRoot));
			
		} catch (IOException e) {
		  throw new DiretorioException(e.getMessage(),e);
		}

		w.sorted().forEach((x) -> {
			if (x.toFile().isFile()) {
				listArquivo.add(geraArquivo(x.normalize()));

			}

		});

		return new ArquivoRepositoryImpl(listArquivo);
	}

	private Arquivo geraArquivo(Path path) {

		String nomeArquivo = path.getFileName().toString();
		String onlyPath = path.getParent().toString();

		String[] filePaths = onlyPath.split(File.separator);

		Arquivo arquivo = new Arquivo(UUID.randomUUID(), nomeArquivo, onlyPath, null);

		return arquivo;
	}

}
