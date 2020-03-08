#cns-boot
#####Codepresso Simple SNS project for practice

![메인페이지](./images/cns-boot.jpg)

###제작정보
codepresso 의 dev-ops 과정을 수강하면 작성한 프로젝트입니다.  
#####http://codepresso.kr/  

###필수조건 안내
spring boot 프로젝트이기 때문에 java 8 이상이 필요합니다.  
sts 도 깔려있어야합니다.  
maven 사용했기에 maven 기본 설정이 되어 있어야합니다.  
DB로 MySQL 5.7 을 사용했기 때문에 mysql 관련 라이브러리가 지정되어있습니다.(+mybatis)  
화면구성은 FreeMaker 를 사용하였습니다.  

###설치 안내 
클론을 통해서 받아서 사용하면 됩니다.  
주의할것은  src/main/resources/application.properies 파일을 보안상의 문제로 git 에는 업로드 하지 않았습니다.  
그래서 제가 사용한 기본값들을 올리니 사용자에 맞게 작성해 사용하면 됩니다.  

    #프리마커 파일(.ftl)의 위치/확장자 지정
    spring.freemarker.template-loader-path: classpath:/templates/step3
	spring.freemarker.suffix: .ftl
    
    ## DataBase Connection 정보 설정
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver(사용자에 맞게 작성)
	spring.datasource.url=db-url (전 rds 사용) 
	spring.datasource.username=사용자
	spring.datasource.password=비밀번호
    
	# mapper 이하를 로깅 위치로 설정.
	logging.level.com.hellokoding.springboot.mybatis.mapper=TRACE

###저작권
codepresso  

![codepresso log](./images/codepresso-logo.png)
