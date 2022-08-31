* 2018-05-25 부분 다운로드 테스트
testSimpleWeb\src\main\java\egovframework\example\download\ResumeDownload.java
testSimpleWeb\src\main\webapp\images\test_endofthefjord.jpeg

* 2018-09-11 oracle CLOB List 테스트
/src/main/java/egovframework/example/sample/service/SampleDefaultVO.java
/src/main/webapp/WEB-INF/jsp/egovframework/example/sample/egovSampleListClob.jsp
/pom.xml 다음 추가
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>
        
        <dependency>
            <groupId>ojdbc</groupId>
            <artifactId>ojdbc</artifactId>
            <version>14</version>
            <scope>system</scope>
            <systemPath>${basedir}/src/main/webapp/WEB-INF/lib/ojdbc14.jar</systemPath>
        </dependency>

/src/main/java/egovframework/example/sample/service/EgovSampleService.java
/src/main/java/egovframework/example/sample/service/impl/EgovSampleServiceImpl.java
/src/main/java/egovframework/example/sample/service/impl/SampleDAO.java
/src/main/java/egovframework/example/sample/web/EgovSampleController.java 
/egovSampleListClob.do URL 추가

/src/main/resources/egovframework/sqlmap/example/sample/EgovSample_Sample_SQL.xml
resultMap 사용
	<resultMap id="clobMap" class="egovMap">
		<result property="id" column="id"/>
		<result property="name" column="name"/>
		<result property="description" column="description"/>
		<result property="useYn" column="use_yn"/>
		<result property="regUser" column="reg_user"/>
	  <result property="CONTENT" column="CONTENT" jdbcType="CLOB" javaType="java.lang.String"/>
	</resultMap>


* 2019-03-06 Mybatis에서 resultMap으로 egovMap여러개 결과 값 가져오기

@RequestMapping(value = "/egovSampleListMultiMap.do")
public String selectSampleListMultiMap(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {
Controller에 위 코드 추가함.
/src/main/java/egovframework/example/sample/web/EgovSampleController.java
/src/main/java/egovframework/example/sample/service/EgovSampleService.java
/src/main/java/egovframework/example/sample/service/impl/EgovSampleServiceImpl.java
/src/main/java/egovframework/example/sample/service/impl/SampleMapper.java
/src/main/resources/egovframework/sqlmap/example/mappers/EgovSample_Sample_SQL.xml

------------------------------------------------------------
* mysql 프로시저 생성
CREATE PROCEDURE SELECT_MULTI_MAP()
begin 
	select * from sample;
	select * from IDS;
	select ID, NAME from sample;
	select COUNT(*) as CNT from sample;
end

* 프로시저 호출 
call SELECT_MULTI_MAP();

* 2019-08-09 log4j2.xml 위치 변경하기 


* 2019-08-28 : Mybatis Interceptor 설정 테스트
- MYSQL 로그 테이블 추가
CREATE TABLE example.query_log (
	seq INT NOT NULL auto_increment primary key,
	id varchar(500) NULL,
	query varchar(5000) NULL
)
- 변경파일 목록
src/main/resources/egovframework/sqlmap/example/mappers/EgovSample_Log_SQL.xml
src/main/resources/egovframework/sqlmap/example/sample/EgovSample_Sample_SQL.xml
src/main/resources/egovframework/spring/context-mapper.xml
src/main/java/egovframework/example/interceptor/InterceptorLogDAO.java
src/main/java/egovframework/example/interceptor/InterceptorLogMapper.java
src/main/java/egovframework/example/interceptor/InterceptorLogVO.java
src/main/java/egovframework/example/interceptor/QueryInterceptor.java
src/main/java/egovframework/example/interceptor/UpdateInterceptor.java
src/main/java/egovframework/example/interceptor/UpdateLogInterceptor.java

UpdateLogInterceptor.java => 로그를 DB에 저장하는 기능 테스트


* 2019-08-28 : log4j2 JDBCAppender 설정 및 테스트
- 변경파일 목록
src/main/java/egovframework/example/cmmn/ConnectionFactory.java
src/main/resources/log4j2.xml
src/main/resources/egovframework/spring/context-datasource.xml
src/main/resources/egovframework/spring/context-log.xml



* 2019-09-30 log4j2 동적 로그 경로 설정
- 변경파일 목록
/src/main/resources/log4j2.xml


* 2020-01-08 Spring Validation 테스트
- 변경파일 목록
/pom.xml
src/main/resources/egovframework/spring/context-validator.xml
src/main/java/egovframework/example/sample/web/EgovSampleController.java
src/main/webapp/WEB-INF/jsp/egovframework/example/sample/egovSampleRegister.jsp
src/main/java/egovframework/example/sample/service/SampleVO.java


