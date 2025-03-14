package admin;

import java.sql.Connection;
import java.sql.DriverManager;

public class m_dbinfo {
   public static Connection dbinfo() {
      String db = "com.mysql.jdbc.Driver"; //Database 사용 라이브러리 명(드라이버)
      String db_url = "jdbc:mysql://kbsn.or.kr:3306/chang_c"; // Database접속 경로
      String db_user = "chang_c"; //Database접속 ID
      String db_pass = "c2025chang"; //Database접속 password
      Connection con = null;
      try {
    	  
    	  
    	 
         Class.forName(db); //라이브러리 사용
         con = DriverManager.getConnection(db_url, db_user, db_pass);   
         System.out.println(con);
      } catch (Exception e) {
         System.out.println("Database 연결 실패!!");
      }
      return con;
   }
}
