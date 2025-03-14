<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	// 컨트롤러에서 전달한 사이트 제목 가져오기
	String siteTitle = (String) request.getAttribute("siteTitle");
	if (siteTitle == null || siteTitle.equals("")) {
	    siteTitle = "쇼핑몰"; // 기본값 설정
	}
%>  
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= siteTitle %></title> <!-- 홈페이지 제목 반영 -->
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/mainlogin.css?v=3">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>

<body class="bodycss">
    <header class="admin_title">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
    </header>
    <%@include file="./index_view.jsp" %>
    <footer class="admin_copy_login">
        <div>
           <%@include file="./index_footer.jsp" %>
        </div>
    </footer>

<body>

</body>
</html>
