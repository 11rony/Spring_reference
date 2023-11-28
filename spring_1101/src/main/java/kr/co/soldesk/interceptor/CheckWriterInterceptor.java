package kr.co.soldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.ContentBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	//interceptor는 autowired가 불가능
	
	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String str1 = request.getParameter("content_idx");
		int content_idx = Integer.parseInt(str1);
		
		//현재 게시글 정보 가져오기
		ContentBean currentContentBean = boardService.getContentInfo(content_idx);
		
		if(currentContentBean.getContent_writer_idx() != loginUserBean.getUser_idx()) {
			
			String contentPath = request.getContextPath();
			response.sendRedirect(contentPath + "/board/not_writer");
			return false;
			
		}
		
		return true;
	}
	
	
}
