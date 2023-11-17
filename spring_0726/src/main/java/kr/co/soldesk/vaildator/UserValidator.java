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
		UserBean userbean = (UserBean)target; // ��ȿ�� �˻� Ÿ�� ����
		String beanName = errors.getObjectName();
		System.out.println(beanName);
		
		// joinUserBean�̸��� UserBean ��ü���� ����
		if(beanName.equals("joinUserBean")) { //ȸ������ �߿��� �۵��ϰ� ���� 
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
