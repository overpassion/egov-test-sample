* 2022-08-31 Procedure Cursor 사용 목록 조회
@ 환경
Oracle 11g (192.168.100.182)
Mybatis
DBeaver Client

@ 주요 내용
- searchVO에서 sampleList에 대한 setter/getter를 구현 하여야 한다.
- resultMap을 선언 하여야 한다.
- {}는 생략해도 된다.
- {}를 사용하여 감싸는 경우 CALL P_GET_SAMPLE_CURSOR() 프로시저 호출 사이에 [enter] [tab] 문자가 없어야 한다. 
  해당 문자가 포함되면 다음 오류가 발생하니 주의한다.
  Cause: java.sql.SQLException: 해당 위치에 지원되지 않는 SQL92 토큰: 2: 

<resultMap id="egovMapProc" type="egovMap"></resultMap>
<select id="selectSampleListProcedure" parameterType="searchVO" statementType="CALLABLE">
	{ CALL P_GET_SAMPLE_CURSOR(
			#{searchKeyword, mode=IN, jdbcType=VARCHAR}, 
			#{sampleList, jdbcType=CURSOR, mode=OUT, javaType=java.sql.ResultSet, resultMap=egovMapProc}
		) }
</select>


@ P_GET_EMP_CURSOR 프로시저 선언
CREATE OR REPLACE PROCEDURE P_GET_EMP_CURSOR
(
  P_ID IN VARCHAR2,
  SAMPLE_CURSOR OUT SYS_REFCURSOR
)
AS 

BEGIN

  OPEN SAMPLE_CURSOR
FOR
  select * from TB_SAMPLE01 where C_ID LIKE '%'||P_ID||'%';

END;


@ 소스 변경 내역
/src/main/resources/egovframework/sqlmap/example/mappers/EgovSample_Sample_SQL.xml
/src/main/java/egovframework/example/sample/web/EgovSampleController.java
/src/main/java/egovframework/example/sample/service/impl/EgovSampleServiceImpl.java
/src/main/java/egovframework/example/sample/service/impl/SampleMapper.java
/src/main/java/egovframework/example/sample/service/EgovSampleService.java
/src/main/java/egovframework/example/sample/service/SampleDefaultVO.java
/src/main/java/egovframework/example/sample/web/EgovSampleController.java


@ DB Client에서 테스트 구문
DECLARE 
  V_CUR SYS_REFCURSOR;
  V_ID VARCHAR2(16);
  V_NAME VARCHAR2(50);
  V_DESC VARCHAR2(100);
  V_USE_YN CHAR(1);
  V_REG_USER VARCHAR2(10);
BEGIN
P_GET_SAMPLE_CURSOR('', V_CUR);
LOOP  -- 커서를 레코드로 받아 화면에 인쇄
  FETCH V_CUR INTO V_ID , V_NAME, V_DESC, V_USE_YN, V_REG_USER ;
  EXIT WHEN V_CUR%NOTFOUND;
  dbms_output.put_line('   ' || V_ID || ', ' || V_NAME);  -- 원하는 값만 출력
END LOOP;
-- DBMS_OUTPUT.PUT_LINE( V_CUR. );
END;
