* 2018-06-27 validwhen 테스트 구현 ( Apache Commons Validator )
\src\main\resources\egovframework\message\message-common_ko.properties
\src\main\webapp\WEB-INF\config\egovframework\validator\validator.xml


* 2018-11-01 DoubleSubmit 테스트
/src/main/java/egovframework/com/cmm/taglibs/DoubleSubmitTag.java
/src/main/java/egovframework/com/cmm/util/EgovDoubleSubmitHelper.java
/src/main/webapp/META-INF/double-submit.tld
/src/main/webapp/WEB-INF/web.xml : 다음 추가
    <jsp-config>
        <taglib>
            <taglib-uri>http://www.egovframe.go.kr/tags/double-submit/jsp</taglib-uri>
            <taglib-location>/META-INF/double-submit.tld</taglib-location>
        </taglib>
    </jsp-config>

/src/main/java/egovframework/example/sample/web/EgovSampleController.java : 다음 추가
if (EgovDoubleSubmitHelper.checkAndSaveToken("someKey")) {

/src/main/webapp/WEB-INF/jsp/egovframework/example/sample/egovSampleRegister.jsp : 다음 추가
<%@ taglib prefix="double-submit" uri="http://www.egovframe.go.kr/tags/double-submit/jsp" %>
<form:form commandName="sampleVO" id="detailForm" name="detailForm">
<double-submit:preventer tokenKey="someKey"/>
