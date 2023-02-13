package fwolves.assignment.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fwolves.assignment.service.ParamService;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import fwolves.assignment.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private ParamService paramService;

	@Autowired
	ServletContext app;
	
	@Override
	public ResponseEntity<File> save(MultipartFile file, String folder) {
		File saved = paramService.save(file, "/images/" + folder);
		return ResponseEntity.ok(saved);
	}
 
	@Override
	public List<String> save(MultipartFile[] files, String folder) {
		File dir = new File(app.getRealPath("/images/" + folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		List<String> filenames = new ArrayList<>();
		for (MultipartFile file : files) {
			String name = System.currentTimeMillis() + file.getOriginalFilename();
			String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
			System.err.println(filename);  
			try {
				File saveFiled = new File(dir, filename);
				file.transferTo(saveFiled);
				filenames.add(filename);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return filenames;
	}

}
