package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class m_selectid {
	Connection con = null;
	m_dbinfo db = new m_dbinfo();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String aid = null;
	String sql = "";
	
	public String idok(String aname, String atel, String aemail) {
		try {
			this.con = this.db.dbinfo();

			this.sql = "select aid from admin where aname=? and atel=? and aemail=?";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1, aname);
			this.ps.setString(2, atel);
			this.ps.setString(3, aemail);
			this.rs = this.ps.executeQuery();
			
			if (this.rs.next()) {  //next() 호출 후 데이터 읽기
				this.aid = this.rs.getString("aid");
			} 
			System.out.println("medel : "+this.aid);
		} catch (Exception e) {
			System.out.println("db select error");

		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			} catch (Exception e) {
				System.out.println("db close error");
			}
		}
		return this.aid;
	}
}
