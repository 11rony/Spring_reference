package kr.co.soldesk.beans;



import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ContentBean {

	//valid를 Multipart로 맞춰야 둘다 쓸 수 있음 => ServletAppContext.java에서 standardservlet을 mutipart로 설정
	
	@NotBlank // == NotNull
	private int content_idx;
	@NotBlank
	private String content_subject;
	
	private String content_text;
	private String content_file;
	private int content_write_idx;
	private int content_board_idx;
	private String content_date;
	
	private MultipartFile upload_file; // 실제 파일   

	public int getContent_idx() {
		return content_idx;
	}

	public void setContent_idx(int content_idx) {
		this.content_idx = content_idx;
	}

	public String getContent_subject() {
		return content_subject;
	}

	public void setContent_subject(String content_subject) {
		this.content_subject = content_subject;
	}

	

	public String getContent_text() {
		return content_text;
	}

	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}

	public String getContent_file() {
		return content_file;
	}

	public void setContent_file(String content_file) {
		this.content_file = content_file;
	}

	public int getContent_write_idx() {
		return content_write_idx;
	}

	public void setContent_write_idx(int content_write_idx) {
		this.content_write_idx = content_write_idx;
	}

	public int getContent_board_idx() {
		return content_board_idx;
	}

	public void setContent_board_idx(int content_board_idx) {
		this.content_board_idx = content_board_idx;
	}

	public String getContent_date() {
		return content_date;
	}

	public void setContent_date(String content_date) {
		this.content_date = content_date;
	}

	public MultipartFile getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}
	
	
	
}
