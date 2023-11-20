package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.service.TopMenuService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public interface TopMenuMapper {
	
	@Select("select board_info_idx, board_info_name\r\n"
			+ "from board_info_table\r\n"
			+ "order by board_info_idx")
	List<BoardInfoBean> getTopMenuList();
}
