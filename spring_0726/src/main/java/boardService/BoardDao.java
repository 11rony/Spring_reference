package boardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.ContentBean;
import kr.co.soldesk.mapper.BoardMapper;

@Repository
public class BoardDao {

	@Autowired
	private BoardMapper boardMapper
	
	public void addContentInfo(ContentBean writeContentBean) {
		boardMapper.addContentInfo(writeContentBean);
	}
	
}
