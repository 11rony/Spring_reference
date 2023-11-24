package kr.co.soldesk.vaildator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.soldesk.beans.UserBean;

public class UserValidator implements Validator {

   @Override
   public boolean supports(Class<?> clazz) {
      return UserBean.class.isAssignableFrom(clazz);
   }

   @Override
   public void validate(Object target, Errors errors) {

      UserBean userBean = (UserBean) target;
      String beanName = errors.getObjectName();
      System.out.println(beanName);

      // 회원가입과 마이페이지에서 동작하도록 설정
      if (beanName.equals("joinuserBean") || beanName.equals("modifyUserBean")) {
         if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
            errors.rejectValue("user_pw", "NotEquals");
            errors.rejectValue("user_pw2", "NotEquals");
         }

         // 회원가입시에만 (중복확인)
         if (beanName.equals("joinUserBean")) {
            if (userBean.isUserIdExist() == false) {
               errors.rejectValue("user_id", "DontCheckUserIdExist");
            }
         }
      }

   }
}