package kr.co.soldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.dao.TopMenuDao;

@Service
//모든 메서드를 모아둠
public class TopMenuService {

	@Autowired //어디서든 사용 가능하기 위한 @
	private TopMenuDao topMenuDao;
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList(); //쿼리문의 실제 내용물이 이곳(getTopMenuList)에 저장되어 있음
										  //결과를 form:form 태그에 뿌리기 위해 맨 앞 소문자
		return topMenuList;
	}
}
