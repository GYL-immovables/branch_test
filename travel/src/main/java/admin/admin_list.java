package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class admin_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	m_selectadmin sa = new m_selectadmin();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		ArrayList<ArrayList<String>> alladmin = this.sa.adminlist();
		
		request.setAttribute("alladmin", alladmin);
		RequestDispatcher rd = request.getRequestDispatcher("./admin_list.jsp");
		rd.forward(request,response);		
	}
}