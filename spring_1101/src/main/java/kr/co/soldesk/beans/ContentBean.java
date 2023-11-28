package kr.co.soldesk.beans;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentBean {

	private int content_idx;
	
	@NotBlank //유효성 검사, NOT NULL
	private String content_subject; 
	@NotBlank
	private String content_text;
	private MultipartFile upload_file; //이미지? resources에 갖다 놓을 거
	private String content_file; //파일명
	private int content_writer_idx;
	private String content_writer_name;

	private int content_board_idx;
	private String content_date;
}
