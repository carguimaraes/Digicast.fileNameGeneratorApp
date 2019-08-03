package manual;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;

import digicast.filenamegeneretor.Principal;
import digicast.filenamegeneretor.application.controller.ProcessaArquivoController;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes =Principal.class)
//para nao executar o Run é mais simples que usar:  @SpringBootTest(classes =Principal.class)
@ContextConfiguration(classes = Principal.class, initializers = ConfigFileApplicationContextInitializer.class)
public class LeMetadadosTest {

	@Autowired
	private ProcessaArquivoController processaArquivoController;
	
	
//	@Ignore(value="usado para teste manual - comentar o ignore para executar o teste")
//	@Test
	public void diretorios() throws ImageProcessingException, IOException {
		
		String paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library/Destination/Antarctica/EX0015.tif"; 
		paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/EX5812.tif";

		 
		File f = new File(paqhArq);
		Metadata metadata = ImageMetadataReader.readMetadata(f);
		
		 for (Directory directory : metadata.getDirectories()) {
		 
		 System.out.println(".DIRETORY CLASSE: "+directory.getClass().getName()+ " --- DIRETORY NAME: "+directory.getName() );
		  
		  for (Tag tag : directory.getTags()) {
		  System.out.println("...TAG-NAME: "+tag.getTagName()+"--->"+tag.getDescription
		  ());
		  
		   System.out.println(tag);
		   }
		  }
		  
		  System.out.println("===================================================");
		 
		 
	}
	
	
//	@Ignore(value="usado para teste manual - comentar o ignore para executar o teste")
//	@Test
	public void fields() {
		
		 

		System.out.println(".com.drew.metadata.iptc.IptcDirectory");
		Field[] fx = com.drew.metadata.iptc.IptcDirectory.class.getDeclaredFields();

		for (Field fff : fx) {
			System.out.println("...TAG-NAME: " + fff.getName());
		}

		System.out.println("------------------------------------------------------------------");
		System.out.println(".com.drew.metadata.exif.ExifDirectoryBase");
		fx = com.drew.metadata.exif.ExifDirectoryBase.class.getDeclaredFields();
		for (Field fff : fx) {
			System.out.println("...TAG-NAME: " + fff.getName());
		}

		 
		
	}

//	@Ignore(value="usado para teste manual - comentar o ignore para executar o teste")
//	@Test
	public  void leTag() throws ImageProcessingException, IOException {
			
		String paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library/Destination/Antarctica/EX0015.tif"; 
		paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/EX5812.tif";

		System.out.println("===================================================");
		File f = new File(paqhArq);
		Metadata metadata = ImageMetadataReader.readMetadata(f);

			
		ExifIFD0Directory directory2 = metadata.getFirstDirectoryOfType(com.drew.metadata.exif.ExifIFD0Directory.class);
		StringValue date =	 directory2.getStringValue(ExifIFD0Directory.TAG_IMAGE_DESCRIPTION);
		System.out.println("==============>"+date.toString());
			 
			 
			 
	}

	//@Ignore(value="usado para teste manual - comentar o ignore para executar o teste")
	//@Test
	public void listaTags() throws ImageProcessingException, IOException {
		String paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library/Destination/Antarctica/EX0015.tif";

		paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/EX5812.tif";

		System.out.println("===================================================");
		File f = new File(paqhArq);
		Metadata metadata = ImageMetadataReader.readMetadata(f);

		System.out.println(".com.drew.metadata.iptc.IptcDirectory");
		Field[] fx = com.drew.metadata.iptc.IptcDirectory.class.getDeclaredFields();

		for (Field fff : fx) {
			System.out.println("...TAG-NAME: " + fff.getName());
		}

		System.out.println("------------------------------------------------------------------");
		System.out.println(".com.drew.metadata.exif.ExifDirectoryBase");
		fx = com.drew.metadata.exif.ExifDirectoryBase.class.getDeclaredFields();
		for (Field fff : fx) {
			System.out.println("...TAG-NAME: " + fff.getName());
		}

		 
	}

}
