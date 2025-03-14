<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 회원관리</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
	<!-- 상단 로고 & 메뉴 -->
	<%@include file="./top.jsp" %>
	<!-- 상단 로고 & 메뉴 -->
	<!-- 회원 리스트 -->
	<%@include file="./shop_member_list_view.jsp" %>
	<!-- 회원 리스트 -->
	<!-- 카피라이터 및 하단 -->
	<%@include file="./footer.jsp" %>
	<!-- 카피라이터 및 하단 -->
</body>
</html>