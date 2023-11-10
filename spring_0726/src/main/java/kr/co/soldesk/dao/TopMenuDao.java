package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.mapper.TopMenuMapper;

//sql���� ��� �����Ͱ� ����Ǵ� Ŭ����
@Repository //F3�� ������ @component�� ���Ե� ����

public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		
		return topMenuList;
	}
	
	
}
