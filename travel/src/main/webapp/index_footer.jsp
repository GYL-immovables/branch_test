<%@page import="travel.dto_setting"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    ArrayList<dto_setting> copyright = (ArrayList<dto_setting>) request.getAttribute("copyright");

    if (copyright != null && !copyright.equals(null)) {
        dto_setting data = copyright.get(0); // 첫 번째 객체를 가져옴
%>
<footer class="main_copyright">
    <div>
        대표 : <%= data.getCeo_nm() %> |
        사업자등록번호 : <%= data.getBusiness_no() %> |
        번호 : <%= data.getTel() %> |
        통신판매업신고번호 : <%= data.getCom_no() %> |
        통신사업자번호 : <%= data.getAdd_no() %> |
        우편번호 : <%= data.getAddr_no() %> |
        주소 : <%= data.getCom_addr() %> |
        정보관리책임자 : <%= data.getInfo_nm() %> |
        책임자이메일 : <%= data.getInfo_email() %>
        Copyright ⓒ 2024 <%= data.getComp_nm() %> |
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
