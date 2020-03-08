## cns-boot  
### Codepresso Simple SNS project for practice  
### 메인화면
![main](./images/cns-boot.png)
---  
### 프로젝트 소개
트위터 같은 sns 프로젝트 입니다.
### 제작정보
codepresso 의 dev-ops 과정을 수강하면 작성한 프로젝트입니다.  
##### http://codepresso.kr/  

### 사용기술
*springboot
*MyBatis
*Freemarker

### 필수조건 안내
*java 8 이상이 필요합니다.  
*STS 설치권장  
*Maven 기본설정 
*MySQL 5.7

### 설치 안내 
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

### 실행방법
src/main/java/com/codepresso/CnsBootApplication.java 를 Run As Java Application 하면 됩니다.(내장톰캣을 사용)
### 저작권
codepresso  

![codepresso log](./images/codepresso-logo.png)

