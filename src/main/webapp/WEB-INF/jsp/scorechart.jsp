
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
	<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
		codebase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0
		" width="600" height="500" id="Column3D">
		<param name="movie"
			value="${pageContext.request.contextPath}/FusionCharts/Column3D.swf" />
		<param name="FlashVars"
			value="&dataURL=${pageContext.request.contextPath}/score/datachart.action?studentId=${studentId}&chartWidth=600&chartHeight=500">
			<param name="quality" value="high" />
			<embed
				src="${pageContext.request.contextPath}/FusionCharts/Column3D.swf"
				flashVars="&dataURL=${pageContext.request.contextPath}/score/datachart.action?studentId=${studentId}&chartWidth=600&chartHeight=500"
				quality="high" width="600" height="500" name="Column3D"
				type="application/x-shockwave-flash"
				pluginspage="http://www.macromedia.com/go/getflashplayer" />
	</object>

</body>
</html>