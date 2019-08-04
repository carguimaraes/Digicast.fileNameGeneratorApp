package digicast.filenamegeneretor.application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import digicast.filenamegeneretor.domain.entity.Arquivo;
import lombok.Getter;
import lombok.Setter;

public class ProcessaArquivoRequest {
	
	private List<String> listMsg;
	
	
	public ProcessaArquivoRequest(){
		this.listMsg=new ArrayList<>();
	}
	
	/*
	.ROOT FOLDER FOR FILE RENAME: dir_1 *
    .BUSINESS UNIT..............: SYD *
    .DEPARTMENT.................: SPPJ *
    .METADATA TYPE..............: MAPS *
    .VOYAGE-ID..................: VID *
    .PROJECT-ID.................: PID *
    .JOB-ID.....................: JID *
    .SHIP-ID....................: SILVER SPIRIT
    .DESTINATION................: CARIBE
    .EXPORT DATE................: 20190803 *
    .COLLECTION.................: DIGCAST
	 
	 */
	
	@Getter @Setter
	private String rootFolder;
	@Getter @Setter
	private String businessUnit;
	@Getter @Setter
	private String department;
	@Getter @Setter
	private String metadataType;
	@Getter @Setter
	private String voyageId;
	@Getter @Setter
	private String projectId;
	@Getter @Setter
	private String jobId;
	@Getter @Setter
	private String shipId;
	@Getter @Setter
	private String destination;
	@Getter @Setter
	private String exportDate;
	@Getter @Setter
	private String collection;
	
	
	public boolean isvalid() {
		
		 
		
		return validate();
	}
	
	public boolean validate() {
		this.listMsg=new ArrayList<>();
		
		if(getRootFolder()==null || getRootFolder().trim()=="") {
			listMsg.add("RootFolde nao foi informada");
		}
		
		if(getBusinessUnit()==null || getBusinessUnit().trim()=="") {
			listMsg.add("BusinessUnit nao foi informado");
		}
		
		if(getDepartment()==null || getDepartment().trim()=="") {
			listMsg.add("Department nao foi informado");
		}
		
		if(getMetadataType()==null || getMetadataType().trim()=="") {
			listMsg.add("MetadataType nao foi informado");
		}
		
		if(getVoyageId()==null || getVoyageId().trim()=="") {
			listMsg.add("VoyageId nao foi informado");
		}
		
		if(getProjectId()==null || getProjectId().trim()=="") {
			listMsg.add("ProjectId nao foi informado");
		}
		if(getJobId()==null || getJobId().trim()=="") {
			listMsg.add("JobId nao foi informado");
		}
		
		if(getExportDate()==null || getExportDate().trim()=="") {
			listMsg.add("ExportDate nao foi informada");
		}
		 
		
		return listMsg.isEmpty();
		
	}
	
	public List<String> getMensagens() {
		 
		return  Collections.unmodifiableList(listMsg);
	}

}
