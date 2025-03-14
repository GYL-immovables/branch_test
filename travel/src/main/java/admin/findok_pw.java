package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class findok_pw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	m_selectid si = new m_selectid();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String aid = request.getParameter("aid");
			String aname = request.getParameter("aname");
			String atel = request.getParameter("atel");
			String aemail = request.getParameter("aemail");
			
			String selid = this.si.idok(aname,atel,aemail);
			
			if(aid.equals(selid)){
				request.setAttribute("aid", aid);
				RequestDispatcher rd = request.getRequestDispatcher("./update_pw.jsp");
				rd.forward(request, response);
			}else {
				this.pw.write("<script>" 
			            + "alert('정보를 다시 확인해주세요');" 
			            + "history.go(-1);" 
			            + "</script>");
			}
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('findok_pw error');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}
	}
}