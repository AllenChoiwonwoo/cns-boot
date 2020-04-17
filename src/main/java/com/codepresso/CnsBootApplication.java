package com.codepresso;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@MapperScan(basePackages = {"com.codepresso.board.vo.* , com.codepresso.member.vo.*"})
//@MapperScan(basePackages = {"com.codepresso"})
//@MapperScan(value= {"com.codepresso")
public class CnsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnsBootApplication.class, args);
	}
	/*
     * SqlSessionFactory 설정 
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
//        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/*.xml");
//        sessionFactory.setMapperLocations(res);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/models/modelConfig.xml"));

        return sessionFactory.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
	@Configuration
	public class WebConfig implements WebMvcConfigurer{
		
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedOrigins("http://localhost:8081","http://localhost:8080")
				.allowedMethods(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name()
						);
		}
	}
}
