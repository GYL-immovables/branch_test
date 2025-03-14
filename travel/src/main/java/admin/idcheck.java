package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PrintWriter pw = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "";
		this.pw = response.getWriter();

		try {
			String id = request.getParameter("aid");
			System.out.println(id);

			if (id.equals("")) {
				msg = "error";
			} else {
				m_dbinfo db = new m_dbinfo();
				this.con = db.dbinfo(); // Database 연결 시작

				String sql = "select count(*) as ctn from admin where aid='" + id + "';";
				Statement st = this.con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				System.out.println(sql);

				// 조건문을 사용한 경우 (한번만 쓸 때 사용 => count나 sum (값이 하나만 나올때)사용시에만 사용)
				if (rs.next() == true) { // 정상적으로 query문이 작동했을 경우
					if (rs.getString("ctn").equals("0")) {
						msg = "ok";
					} else { // 검색한 데이터가 있을 경우
						msg = "no";
					}
					System.out.println(rs.getString("ctn"));
				}
				// else의 경우 (필요없음 오류발생시 exception으로 빠짐)

				rs.close();
				st.close();
				/*
				 * //반복문을 사용한 경우 (모든 경우 사용 가능) while (rs.next()) { //rs.next() : 결과값에 대해서 반복문이
				 * 실행 (true, false) System.out.println(rs.getString("ctn")); }
				 */
			}
			pw.write(msg);

		} catch (NullPointerException ne) { // Front-end가 올바른 값을 전달하지 않을 경우
			msg = "error";
			pw.write(msg); // ok : 사용가능 아이디, no : 사용불능 아이디, error : 데이터 오류
		} catch (Exception e) {
			msg = "idcheck DB error";
			System.out.println(e);
			pw.write(msg);
		} finally {
			pw.close();
		}
	}
}