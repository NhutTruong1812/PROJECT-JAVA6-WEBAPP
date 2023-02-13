package fwolves.assignment.service;

import java.io.File;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	public ResponseEntity<File> save(MultipartFile file, String folder);

	public List<String> save(MultipartFile[] files, String folder);
}
