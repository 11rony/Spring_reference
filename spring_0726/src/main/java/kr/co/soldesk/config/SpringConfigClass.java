package kr.co.soldesk.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext servletAppContext=new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		DispatcherServlet dispatcherServlet=new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher", dispatcherServlet);
		
		//부가설정
		servlet.setLoadOnStartup(1); //가장먼저 받아들이겠다는 뜻 => 여기서 출발
		servlet.addMapping("/");
		
		//==============================================================
		//Bean들을 정이하여 관리함
		AnnotationConfigWebApplicationContext rootAppContext=new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		ContextLoaderListener listener=new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		
		//파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
	}
}


/*public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
// DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
@Override
protected String[] getServletMappings() {
	// TODO Auto-generated method stub
	return new String[] { "/" };
}

// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
@Override
protected Class<?>[] getServletConfigClasses() {
	// TODO Auto-generated method stub
	return new Class[] { ServletAppContext.class };
}

// 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정한다.
@Override
protected Class<?>[] getRootConfigClasses() {
	// TODO Auto-generated method stub
	return new Class[] { RootAppContext.class };
}

// 파라미터 인코딩 필터 설정
@Override
protected Filter[] getServletFilters() {
	// TODO Auto-generated method stub
	CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	encodingFilter.setEncoding("UTF-8");
	return new Filter[] {encodingFilter};
}
}*/



