package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//관리자 등록 => DB에 저장 
public class add_master_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	ArrayList<String> admindata = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		m_dbinfo db = new m_dbinfo();
		
		try {
			// 패스워드 암호화
			String password = new m_md5().md5_code(request.getParameter("apw"));

			this.admindata = new ArrayList<String>();
			this.admindata.add(request.getParameter("aid"));
			this.admindata.add(password);
			this.admindata.add(request.getParameter("aname"));
			this.admindata.add(request.getParameter("aemail"));
			this.admindata.add(request.getParameter("atel"));
			this.admindata.add(request.getParameter("dept"));
			this.admindata.add(request.getParameter("rspofc"));

			m_insertadmin ia = new m_insertadmin();
			Integer result = ia.insertadmin(admindata);
			if(result > 0) {
		         this.pw.write("<script>"
		               + "alert('관리자 등록 신청이 완료되었습니다.');"
		               + "location.href='./index.jsp';" 
		               + "</script>");
		      }else {
		         this.pw.write("<script>"
		               + "alert('서비스 장애 발생');"
		               + "history.go(-1);"
		               + "</script>");
		      }

		} catch (Exception e) {
			this.pw.write("<script>"
		               + "alert('올바르지 않은 접근입니다.');"
		               + "history.go(-1);"
		               + "</script>");
		} finally {
			try {
				this.pw.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
