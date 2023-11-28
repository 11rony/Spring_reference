package kr.co.soldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	
	//로그인 여부 판단하는 객체(로그인된 상태에서 주소 복사해서 다른 창에 붙여넣었을 때 로그인이 유지되면 안됨)
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(loginUserBean.isUserLogin() == false) { //로그인을 하지 않은 상태
			String contextPath = request.getContextPath(); //로그인 되지 않은 상태의 경로. getContextPath(): 현재의 서버 위치
			
			response.sendRedirect(contextPath+"/user/not_login");
			
			return false; //다음 단계로 이전되지 않게 잠금 처리
			
		}
		
		return true; //로그인 상태
	}
	
	
	
	
}
