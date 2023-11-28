package kr.co.soldesk.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//스프링 전체 툴? 프레임워크에 대한 설정
/*
public class SpringConfigClass implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		// Dispatcher : servletAppContext를 resolve로 밀어준다.
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);		
		
		//부가설정
		servlet.setLoadOnStartup(1); //가장 먼저 요청정보를 받아들이겠다는 뜻 => 여기서 출발
		servlet.addMapping("/");
		
		//-----------------------------------------------------------------------------------------------
		
		//Bean들을 정리하여 관리함
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class); //Bean이 이곳(RootAppContext)에 모여있다는 것을 알려줌
		
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		
		// 파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
	}

	
}
*/

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
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
	
	
	/*
	null: 사용자가 입력한 내용을 임시기억할 아파치톰캑에서 제공하는 서버의 임시 기억장소 
	52428800: 업로드 데이터의 용량(1024*50) 50M(한번에 올릴 수 있는 데이터)
	524288000: 파일데이터를 포함한 전체용량 500M
	0: 파일의 임계값(데이터를 받아서 자동으로 저장) =임계값에 제한을 두지 않겠다는 말
	*/
	@Override
	protected void customizeRegistration(Dynamic registration) {
	   // TODO Auto-generated method stub
	   super.customizeRegistration(registration);
	   
	   MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
	   registration.setMultipartConfig(config1);
	}
	
}