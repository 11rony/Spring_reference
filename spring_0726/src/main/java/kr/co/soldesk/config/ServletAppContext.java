package kr.co.soldesk.config;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.soldesk.interceptor.TopMenuInterceptor;
import kr.co.soldesk.mapper.BoardMapper;
import kr.co.soldesk.mapper.TopMenuMapper;
import kr.co.soldesk.service.TopMenuService;

@Configuration
@EnableWebMvc //controller
@ComponentScan("kr.co.soldesk.dao")
@ComponentScan("kr.co.soldesk.service")
@ComponentScan("kr.co.soldesk.controller")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer{
	
	   @Value("${db.classname}")
	   private String db_classname;
	   
	   @Value("${db.url}")
	   private String db_url;
	   
	   @Value("${db.username}")
	   private String db_username;
	   
	   @Value("${db.password}")
	   private String db_password;

	   @Autowired
	   private TopMenuService topMenuService;
	   
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// view�� ������ ���� ��û���信 ���� ȯ�漳��
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	//���� ������ ��θ� ����
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	//================================================================
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		
		//�޼ҵ� ���ͼ��� �ϳ� �߰�
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService);
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		
		reg1.addPathPatterns("/**"); //������ų �� ����
	}

	
	
	//==============================================================
	 @Bean
	   public BasicDataSource dataSource() {
	      BasicDataSource source = new BasicDataSource();
	      source.setDriverClassName(db_classname);
	      source.setUrl(db_url);
	      source.setUsername(db_username);
	      source.setPassword(db_password);
	      
	      return source;
	   }
	   
	   // �������� ���� ������ �����ϴ� ��ü
	   @Bean
	   public SqlSessionFactory factory(BasicDataSource source) throws Exception{
	      SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	      factoryBean.setDataSource(source);
	      SqlSessionFactory factory = factoryBean.getObject();
	      return factory;
	   }
	   
	   // ������ ������ ���� ��ü(Mapper ����)
	   @Bean
	   public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception{
	      MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
	      factoryBean.setSqlSessionFactory(factory);
	      return factoryBean;
	   }
	   
	   // Mapper ���
	   @Bean
	   public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception{
	      MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
	      factoryBean.setSqlSessionFactory(factory);
	      return factoryBean;
	   }


	 //==============================================================
	   
	
	
	
	
	

}