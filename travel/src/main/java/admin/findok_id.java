package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class findok_id extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	m_selectid si = new m_selectid();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String aname = request.getParameter("aname");
			String atel = request.getParameter("atel");
			String aemail = request.getParameter("aemail");
			String aid = this.si.idok(aname,atel,aemail);
			
			if (aid == null) {
			    this.pw.write("<script>" 
			            + "alert('해당되는 아이디가 없습니다.');" 
			            + "history.go(-1);" 
			            + "</script>");
			}else {
				request.setAttribute("aid", aid);

				RequestDispatcher rd = request.getRequestDispatcher("./findok_id.jsp");
				rd.forward(request, response);
			}
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('findok_id error');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}
	}

}
