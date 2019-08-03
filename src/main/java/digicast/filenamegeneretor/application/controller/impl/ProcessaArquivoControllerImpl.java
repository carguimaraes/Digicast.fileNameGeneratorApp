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

	private void exe_01() throws ImageProcessingException, IOException {
		String paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/Silversea Expeditions Library/Destination/Antarctica/EX0015.tif";

		paqhArq = "/home/carguimaraes/Documentos/Silversea Expeditions Library-20190727T143910Z-001/EX5812.tif";

		System.out.println("===================================================");
		File f = new File(paqhArq);
		Metadata metadata = ImageMetadataReader.readMetadata(f);

		/*
		 * com.drew.metadata.exif.ExifIFD0Directory directory2 =
		 * metadata.getFirstDirectoryOfType(com.drew.metadata.exif.ExifIFD0Directory.
		 * class); StringValue date =
		 * directory2.getStringValue(com.drew.metadata.exif.ExifIFD0Directory.
		 * TAG_IMAGE_DESCRIPTION);
		 * System.out.println("==============>"+date.toString());
		 * 
		 */

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

		/*
		 * for (Directory directory : metadata.getDirectories()) {
		 * 
		 * System.out.println(".DIRETORY CLASSE: "+directory.getClass().getName()+
		 * " --- DIRETORY NAME: "+directory.getName() );
		 * 
		 * for (Tag tag : directory.getTags()) {
		 * System.out.println("...TAG-NAME: "+tag.getTagName()+"--->"+tag.getDescription
		 * ());
		 * 
		 * // System.out.println(tag); } }
		 * 
		 * System.out.println("===================================================");
		 */
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
