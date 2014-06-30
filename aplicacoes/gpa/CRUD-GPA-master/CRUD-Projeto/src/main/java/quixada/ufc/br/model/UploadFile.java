package quixada.ufc.br.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

public class UploadFile {

	
	private List<MultipartFile> file;

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
	
	
	
}
