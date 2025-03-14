package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class m_selectadmin {
	Connection con = null;
	m_dbinfo db = new m_dbinfo();
	m_md5 md5 = new m_md5();
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<String> admindata = null;
	ArrayList<ArrayList<String>> alladmin = null;
	
	public ArrayList<ArrayList<String>> adminlist(){
		try {
			this.con = this.db.dbinfo();

			String sql = "select * from admin order by aidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			
			this.alladmin = new ArrayList<ArrayList<String>>();
			while(this.rs.next()) {
				//아이디 이름 이메일 전화번호 부서 직책 가입승인 가입날짜
				this.admindata = new ArrayList<String>();
				this.admindata.add(this.rs.getString("aid"));
				this.admindata.add(this.rs.getString("aname"));
				this.admindata.add(this.rs.getString("aemail"));
				this.admindata.add(this.rs.getString("atel"));
				this.admindata.add(this.rs.getString("dept"));
				this.admindata.add(this.rs.getString("rspofc"));
				this.admindata.add(this.rs.getString("aprv"));
				this.admindata.add(this.rs.getString("adate"));
				this.alladmin.add(this.admindata);
			}
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
		
		return this.alladmin;
	}

	public ArrayList<String> loginselect(String id, String pw) {
		try {
			this.con = this.db.dbinfo();
			pw = md5.md5_code(pw);

			String sql = "select * from admin where aid=? and apw=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, id);
			this.ps.setString(2, pw);
			this.rs = this.ps.executeQuery();
			
			this.admindata = new ArrayList<String>();
			if (this.rs.next()) {  //next() 호출 후 데이터 읽기
				this.admindata.add(this.rs.getString("aid"));
				this.admindata.add(this.rs.getString("aname"));
				this.admindata.add(this.rs.getString("aemail"));
				this.admindata.add(this.rs.getString("atel"));
				this.admindata.add(this.rs.getString("dept"));
				this.admindata.add(this.rs.getString("aprv"));
				this.admindata.add(this.rs.getString("rspofc"));
				this.admindata.add(this.rs.getString("adate"));
			} 
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
		return this.admindata;
	}
}
