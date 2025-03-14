<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // request에서 copyright 데이터를 가져옴
    ArrayList<String> cpdata = (ArrayList<String>) request.getAttribute("cpdata");

    // 데이터가 존재하는 경우에만 출력
    if (cpdata != null && cpdata.size() == 10) {
%>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 <%= cpdata.get(0) %> |
        대표 : <%= cpdata.get(1) %> |
        사업자등록번호 : <%= cpdata.get(2) %> |
        번호 : <%= cpdata.get(3) %> |
        통신판매업신고번호 : <%= cpdata.get(4) %> |
        통신사업자번호 : <%= cpdata.get(5) %> |
        우편번호 : <%= cpdata.get(6) %> |
        주소 : <%= cpdata.get(7) %> |
        정보관리책임자 : <%= cpdata.get(8) %> |
        책임자이메일 : <%= cpdata.get(9) %>
        All rights reserved.
    </div>
</footer>
<%
    } else {
%>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 | 등록된 회사 정보가 없습니다.
    </div>
</footer>
<%
    }
%>
