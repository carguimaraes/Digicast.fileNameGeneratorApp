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

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.iptc.IptcDirectory;

import digicast.filenamegeneretor.Principal;
import digicast.filenamegeneretor.domain.entity.Arquivo;
import digicast.filenamegeneretor.domain.entity.MetaDado;
import digicast.filenamegeneretor.domain.exception.DiretorioException;
import digicast.filenamegeneretor.domain.factory.ArquivoRepositoryFactory;
import digicast.filenamegeneretor.domain.repository.ArquivoRepository;
import digicast.filenamegeneretor.infrastructure.repository.ArquivoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

		List<MetaDado>[] xx=leMeta(onlyPath+"/"+nomeArquivo);
		 
		
		
		Arquivo arquivo = new Arquivo(UUID.randomUUID(), nomeArquivo, onlyPath,xx[0], xx[1]);

		return arquivo;
	}
	
	private  List<MetaDado>[] leMeta(String paqhArq )  {
		File f = new File(paqhArq);
		
		 
		List<MetaDado>[] xx= (List<MetaDado>[]) new List<?>[2];
		
		 
		
		List<MetaDado> listExifIFD0=new ArrayList<>();
		List<MetaDado> listIptc=new ArrayList<>();
		
		
		Metadata metadata=null;
		
		try {
			metadata = ImageMetadataReader.readMetadata(f);
		} catch (ImageProcessingException | IOException e) {
			log.error(e.getMessage(),e);
 
		}
		
		IptcDirectory directory_Iptc = metadata.getFirstDirectoryOfType(com.drew.metadata.iptc.IptcDirectory.class);
		for (Tag tag : directory_Iptc.getTags()) {
			listIptc.add(new MetaDado(tag.getTagName(),tag.getDescription()) );
		}
		
		ExifIFD0Directory directory_ExifIFD0 = metadata.getFirstDirectoryOfType(com.drew.metadata.exif.ExifIFD0Directory.class);
		for (Tag tag : directory_ExifIFD0.getTags()) {
			listExifIFD0.add(new MetaDado(tag.getTagName(),tag.getDescription()) );
		}
	
		
		xx[0]=listIptc;
		xx[1]=listExifIFD0;
		return xx;
	}

}
