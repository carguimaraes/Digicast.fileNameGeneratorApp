package digicast.filenamegeneretor.application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessaArquivoResponse {
	
private List<String> listMsg;
	
	
	public ProcessaArquivoResponse(){
		this.listMsg=new ArrayList<>();
	}
	
	public ProcessaArquivoResponse(List<String> listMsg){
		this.listMsg=listMsg;
	}
	
	
	
	public boolean isvalid() {
		
		return listMsg.isEmpty();
	}
	
	public List<String> getMensagens() {
		 
		return  Collections.unmodifiableList(listMsg);
	}

}
