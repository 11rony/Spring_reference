package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.ContentBean;

public interface BoardMapper {
	
	//statement: 현재의 시퀀스를 가져옴(발생된 시퀀스 번호 가져오기)
	//keyProperty: writeContentBean 객체가 갖고 있는 content_idx(글번호)
	//before = true: 아래의 쿼리문이 발생되기 전에 실행을 시켜줘야함
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class)

	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
	         "content_file, content_writer_idx, content_board_idx, content_date) " +
	         "values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
	         "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	
	//카테고리 식별
	@Select("select board_info_name " + 
			"from board_info_table " + 
			"where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	
	//글 목록 보기
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, to_char(a1.content_date, 'yyyy-mm-dd') as content_date "
			+ "from content_table a1, user_table a2 "
			+ "where a1.content_writer_idx=a2.user_idx and a1.content_board_idx=1 "
			+ "order by a1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx);
	
	//상세페이지
	@Select("select a2.user_name as content_writer_name, " + 
	         "       to_char(a1.content_date, 'YYYY-MM-DD') as content_date, " + 
	         "       a1.content_subject, a1.content_text, a1.content_file, a1.content_writer_idx " + 
	         "from content_table a1, user_table a2 " + 
	         "where a1.content_writer_idx = a2.user_idx " + 
	         "      and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	@Update("update content_table " +
	         "set content_subject = #{content_subject}, content_text = #{content_text}, " +
	         "content_file = #{content_file, jdbcType=VARCHAR} " +
	         "where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("delete from content_table where content_idx = #{content_idx}")
	void deleteContentInfo(int content_idx);
}
