package kr.co.soldesk.config;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.interceptor.CheckLoginInterceptor;
import kr.co.soldesk.interceptor.CheckWriterInterceptor;
import kr.co.soldesk.interceptor.TopMenuInterceptor;
import kr.co.soldesk.mapper.BoardMapper;
import kr.co.soldesk.mapper.TopMenuMapper;
import kr.co.soldesk.mapper.UserMapper;
import kr.co.soldesk.service.BoardService;
import kr.co.soldesk.service.TopMenuService;

@Configuration 
@EnableWebMvc // controller들을 조정
@PropertySource("/WEB-INF/properties/db.properties")
@ComponentScan("kr.co.soldesk.dao")
@ComponentScan("kr.co.soldesk.service")
@ComponentScan("kr.co.soldesk.controller")
public class ServletAppContext implements WebMvcConfigurer {

	@Value("${db.classname}")
	private String db_classname;

	@Value("${db.url}")
	private String db_url;

	@Value("${db.username}")
	private String db_username;

	@Value("${db.password}")
	private String db_password;
	//@Value를 맨 위에 올려놔야지 읽을 수 있음.
	
	
	@Autowired
	private TopMenuService topMenuService; 
	
	@Autowired
	private BoardService boardService;
	
	//autowired와 이름이 정해져있다면 resource를 사용
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	
	//-------------------------------------------------------------------
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		
		return res;
	}
	
	
	//소스와 메세지 별도 관리하도록 등록
	@Bean
    public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	

	// 데이터베이스 접속 정보를 관리하는 Bean

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);

		return source;
	}

	// 쿼리문과 접속 정보를 관리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	// 쿼리문 실행을 위한 객체(Mapper 관리)
	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	@Bean
	public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}

	
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() { //multipart가 충돌되지 않도록??
		return new StandardServletMultipartResolver(); //객체 생성하여 반환
	}
	
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// View 로 보내질 최종 요청응답에 관한 환경설정
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
		// [/WEB-INF/views/] : JSP가 들어갈 위치. (경로, 확장자)
	}

	// 정적 파일(이미지, 영상, 소리)의 경로를 맵핑
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		// [/**] : 어디서든 이라는 뜻
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
		CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService);
		
		
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		reg1.addPathPatterns("/**");
		
		InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
		//로그인 되지 않은 상태에서 접근을 막는 카테고리
		reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*"); //이러한 것들을 읽을 수?수정?할 수 없게 함
		reg2.excludePathPatterns("/board/main"); //board의 main 페이지는 읽을 수 있게 예외처리

		InterceptorRegistration reg3 = registry.addInterceptor(checkWriterInterceptor);
		//로그인 되지 않은 상태에서 접근을 막는 카테고리
		reg3.addPathPatterns("/board/modify", "/board/delete"); 
	}
	
}
