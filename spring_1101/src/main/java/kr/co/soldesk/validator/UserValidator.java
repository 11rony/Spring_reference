package kr.co.soldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.soldesk.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserBean userBean = (UserBean)target;
		String beanName = errors.getObjectName();
		System.out.println(beanName);
		
		//회원가입과 마이페이지에서 동작하도록 설정
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2())==false) { //두 개의 아이디가 동일하지 않았을 때
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(beanName.equals("joinUserBean")) {
				//회원가입시에만
				if(userBean.isUserIdExist()==false) { //아이디 중복 체크하지 않았을 때
					errors.rejectValue("user_id", "DontCheckUserIdExist");
				}
				
			}
			
		}
		
	}

}
