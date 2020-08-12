<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TotalAdsToShow</title>
</head>
<body>

<c:forEach var="districtAdsAmount" items="${totalAdsList}">
District name: ${districtAdsAmount.districtName}, total ads: ${districtAdsAmount.noOfAds}. <br>
</c:forEach>

</body>
</html>
