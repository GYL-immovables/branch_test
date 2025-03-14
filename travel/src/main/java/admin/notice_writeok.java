package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 5,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 100
)
public class notice_writeok extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PrintWriter pw = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        this.pw = response.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        m_dbinfo db = new m_dbinfo(); // DB 접속 정보


        // 🔹 텍스트 데이터 받기
        //String aid = request.getParameter("aid");  // 관리자 ID
        String aid = "adminid2";
        String ncheck = request.getParameter("ncheck");  // 공지사항 여부 (Y/N)
        String nsubject = request.getParameter("nsubject");  // 제목
        String writer = request.getParameter("writer");  // 글쓴이
        String ntext = request.getParameter("ntext");  // 게시글 내용

        // 작성자 값이 없으면 "관리자"로 설정
        if (writer == null || writer.trim().isEmpty()) {
            writer = "관리자";
        }

        // 🔹 파일 데이터 받기
        Part nfile = request.getPart("nfile");
        String fileName = (nfile != null && nfile.getSize() > 0) ? nfile.getSubmittedFileName() : null;

        // 🔹 콘솔 출력 (서버에서 값 확인)
        System.out.println("📩 [공지사항 데이터 수신]");
        System.out.println("관리자 ID: " + aid);
        System.out.println("공지 여부: " + ncheck);
        System.out.println("제목: " + nsubject);
        System.out.println("작성자: " + writer);
        System.out.println("내용: " + ntext);
        System.out.println("첨부 파일: " + (fileName != null ? fileName : "첨부 파일 없음"));

        try {
            con = db.dbinfo(); // DB 연결
            String sql;
            int result;

            if (fileName == null) {
                // 🔹 첨부 파일이 없는 경우
                sql = "INSERT INTO notice (aid, ncheck, nsubject, writer, ntext, nview, ndate) VALUES (?, ?, ?, ?, ?, 0, NOW())";
                ps = con.prepareStatement(sql);
                ps.setString(1, aid);
                ps.setString(2, ncheck);
                ps.setString(3, nsubject);
                ps.setString(4, writer);
                ps.setString(5, ntext);
            } else {
                // 🔹 첨부 파일이 있는 경우
                sql = "INSERT INTO notice (aid, ncheck, nsubject, writer, nfile, ntext, nview, ndate) VALUES (?, ?, ?, ?, ?, ?, 0, NOW())";
                ps = con.prepareStatement(sql);
                ps.setString(1, aid);
                ps.setString(2, ncheck);
                ps.setString(3, nsubject);
                ps.setString(4, writer);
                ps.setString(5, fileName);
                ps.setString(6, ntext);
                
                
            }

            result = ps.executeUpdate();

            if (result > 0) {
                pw.print("공지사항 저장 완료");
            } else {
                pw.print("공지사항 저장 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.print("데이터베이스 오류 발생");
        } finally {
        	try {
        		ps.close();
                con.close();
			} catch (Exception e) {
				
			}
            
        }
    }
}
