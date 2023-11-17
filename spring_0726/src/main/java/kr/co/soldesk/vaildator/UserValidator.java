package kr.co.soldesk.vaildator;

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
		UserBean userbean = (UserBean)target; // 유효성 검사 타겟 설정
		String beanName = errors.getObjectName();
		System.out.println(beanName);
		
		// joinUserBean이름의 UserBean 객체에만 적용
		if(beanName.equals("joinUserBean")) { //회원가입 중에만 작동하게 설정 
			if(userbean.getUser_pw().equals(userbean.getUser_pw2())==false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			if(userbean.isUserIdExist()==false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
		
	}
}
