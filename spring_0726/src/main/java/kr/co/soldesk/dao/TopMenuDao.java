package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.mapper.TopMenuMapper;

//sql문의 결과 데이터가 저장되는 클래스
@Repository //F3을 누르면 @component가 포함되 있음

public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		
		return topMenuList;
	}
	
	
}
