package fwolves.assignment.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileManagerService {
	@Autowired
	ServletContext app;
	
	private Path getPath(String folder, String filename) {
		File dir = new File(app.getRealPath("/files/" + folder));
		if(!dir.exists()) {
			dir.mkdir();
		}
		return Paths.get(dir.getAbsolutePath(), filename);
	}
	
	public byte[] read(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		try {
			return Files.readAllBytes(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		path.toFile().delete();
	}
	
	public List<String> list(String folder){
		List<String> filenames = new ArrayList<String>();
		File dir = Paths.get(app.getRealPath("/files/"),folder).toFile();
		if(dir.exists()) {
			File[] files = dir.listFiles();
			for(File file: files) {
				filenames.add(file.getName());
			}
		}
		return filenames;
	}
	
	public List<String> save(String folder, MultipartFile[] files){
		List<String> filenames = new ArrayList<>();
		for(MultipartFile file: files) {
			String name = System.currentTimeMillis() + file.getOriginalFilename();
			String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
			System.err.println(filename);
			Path path = this.getPath(folder, filename);
			System.err.println(path);
			try {
				file.transferTo(path);
				filenames.add(filename);
			} catch (Exception e) {
				e.fillInStackTrace();
			}
		}
		return filenames;
	}
	
	public File save(MultipartFile file, String folder){
		File dir = new File(app.getRealPath("/images/"+folder));
		if (!dir.exists()) {
			dir.mkdirs();
		} 
			String name = System.currentTimeMillis() + file.getOriginalFilename();
			String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
			Path path = this.getPath(folder, filename); 
			try {
				File saveFiled = new File(dir, name);
				file.transferTo(saveFiled);
				System.err.println(saveFiled.getAbsolutePath());
				return saveFiled;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}  
	}
	/**
	 * Lưu file upload vào thư mục
	 * 
	 * @param file chứa file upload từ client
	 * @param path đường dẫn tính từ webroot
	 * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
	 * @throws RuntimeException lỗi lưu file
	 */
	public File save(MultipartFile file, String path, String name) {
		File dir = new File(app.getRealPath(path));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			String newpass = "";
			for (int i = 0; i < 6; i++) {
				int random = (int) Math.round(Math.random() * 9);
				newpass = newpass + random;
			}
			String nametemp = file.getOriginalFilename();
			nametemp = nametemp.substring(nametemp.lastIndexOf("."), nametemp.length());
			File saveFile = new File(dir, name + "_" + newpass + nametemp);
			file.transferTo(saveFile);
			// wait
//			InputStream is = app.getResourceAsStream(saveFile.getAbsolutePath());
//			while (is == null) {
//				is = app.getResourceAsStream(saveFile.getAbsolutePath());
//			}
			return saveFile;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
