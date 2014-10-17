<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Home-${pageContext.request.contextPath}</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/extjs/resources/css/ext-all.css" />
 	<script type="text/javascript" src="${pageContext.request.contextPath}/extjs/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-all-debug-w-comments.js"></script>
    
<!-- App js -->
	<script src="${pageContext.request.contextPath}/js/score-forms.js"></script>
	<script type="text/javascript">
		if (Ext.BLANK_IMAGE_URL.substr(0, 5) != 'data:') {
			Ext.BLANK_IMAGE_URL = '<c:url value="/resources/lib/ext-3.3.1/resources/images/default/s.gif" />';
		}
		var loadUrl = '<c:url value="/load" />';
		var addUrl = '<c:url value="../score/save.action" />';
	</script>	

	<style type="text/css">
		body {
		  	font: normal 12px helvetica,arial,verdana,tahoma,sans-serif;
		}
		.my-form-class {
			margin:  20px 30px;
		}
	</style>
  	<input type="hidden" id="scoreId" value="${scoreId}"/>
</head>
<body>

</body>
</html>
