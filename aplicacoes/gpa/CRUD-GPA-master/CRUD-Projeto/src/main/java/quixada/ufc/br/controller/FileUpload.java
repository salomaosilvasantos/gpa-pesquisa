package quixada.ufc.br.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import quixada.ufc.br.model.UploadFile;

@Controller
public class FileUpload {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayForm(){
		return "file_upload_form";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("uploadForm") UploadFile uploadform, Model map){
		List<MultipartFile> files = uploadform.getFile();
		List<String> fileNames = new ArrayList<String>();
		if(null != files && files.size() > 0){
			for(MultipartFile multipartFile : files) {
				
				String fileName = multipartFile.getOriginalFilename();
				fileNames.add(fileName);
				
			}
		}
		map.addAttribute("files", fileNames);
		
		return "file_upload_sucess";
	}
	
}
