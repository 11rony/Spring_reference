package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.service.TopMenuService;

public interface TopMenuMapper {

	
	@Select("select board_info_idx, board_info_name "	//sql문으로 인식해주는거. \r\n: 반드시 공백을 입력해라
			+ "from board_info_table "
			+ "order by board_info_idx") 
	List<BoardInfoBean> getTopMenuList();
}
