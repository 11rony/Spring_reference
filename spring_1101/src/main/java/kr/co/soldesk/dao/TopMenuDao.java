package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.mapper.TopMenuMapper;

//sql문의 결과 데이터가 저장되는 클래스
@Repository 	//데이터베이스 레포지토리 저장
public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper; //쿼리문이 이 안에 있음
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList(); //쿼리문의 실제 내용물이 이곳(getTopMenuList)에 저장되어 있음
		
		return topMenuList;
	}
}
