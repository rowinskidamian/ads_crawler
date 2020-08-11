<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TotalAdsToShow</title>
</head>
<body>

<c:forEach var="districtData" items="${totalAdsList}"></c:forEach>
District name: ${districtData.districtName}, total ads: ${districtData.noOfAds} .

</body>
</html>
