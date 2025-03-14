package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class m_insertadmin {
	Connection con = null;
	PreparedStatement ps = null;
	m_dbinfo db = new m_dbinfo();
	Integer result = 0;
	String sql = "";
	
	public Integer insertadmin(ArrayList<String> admin) {
		System.out.println(admin);
		
		try {
			this.con = this.db.dbinfo();
			
			this.sql = "insert into admin (aidx, aid, apw, aname, aemail, atel, dept, rspofc, adate) "
					+ "values ('0',?,?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(this.sql);
			this.ps.setString(1,admin.get(0));
			this.ps.setString(2,admin.get(1));
			this.ps.setString(3,admin.get(2));
			this.ps.setString(4,admin.get(3));
			this.ps.setString(5,admin.get(4));
			this.ps.setString(6,admin.get(5));
			this.ps.setString(7,admin.get(6));
			
			this.result = this.ps.executeUpdate();
			
			
		}catch (Exception e) {
			System.out.println("insert admin error");
		}finally {
			try {
				this.ps.close();
				this.con.close();				
			}catch (Exception e) {
				System.out.println("insert admin close error");
			}
		}
		return this.result;
	}
}
