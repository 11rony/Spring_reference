package kr.co.soldesk.service;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import boardService.BoardDao;
import kr.co.soldesk.beans.ContentBean;
import kr.co.soldesk.beans.UserBean;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	private String saveUploadFile(MultipartFile upload_file) {
		
		// 파일 이름 중복성 제거
		String file_name = System.currentTimeMillis() + "_"
				+ FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "."
				+ FilenameUtils.getExtension(upload_file.getOriginalFilename());

		// 파일 경로
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file_name;
	}
	
	
	public void addContentInfo(ContentBean writeContentBean) {
		System.out.println(writeContentBean.getContent_subject());
		System.out.println(writeContentBean.getContent_text());
		System.out.println(writeContentBean.getUpload_file().getSize());
		
		
		MultipartFile upload_file = writeContentBean.getUpload_file();

		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			System.out.println(file_name);
			writeContentBean.setContent_file(file_name);
		}
		
		writeContentBean.setContent_write_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
}
