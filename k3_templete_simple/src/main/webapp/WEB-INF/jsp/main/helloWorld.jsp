<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx }/js/jq/jquery-1.11.1.js" type="text/javascript" ></script>
<script src="${ctx }/js/jq/i18n/grid.locale-kr.js" type="text/javascript" ></script>

<script>
$(document).ready(function(){

	$("#btn").click(function(){	document.location.href="${ctx}/koem/HAGU/list.do";});
	$("#btn1").click(function(){	document.location.href="${ctx}/hi.do";});
		
});

</script>


<title>헬로 ~월드</title>
</head>
<body>
hello world !!
<P>&nbsp;</P>
<P>&nbsp;</P>

<input type="button" id="btn" value="하구" />
<input type="button" id="btn1" value="연안" />

</body>
</html>