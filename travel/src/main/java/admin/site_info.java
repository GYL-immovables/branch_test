package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class site_info {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
	m_dbinfo db = new m_dbinfo();

	public String getSiteTitle() {
		String siteTitle = "쇼핑몰"; // 기본값

		try {
			con = this.db.dbinfo();
			this.sql = "select site_title from admin_site_sign_settings limit 1";
			this.ps = this.con.prepareStatement(this.sql);
			this.rs = this.ps.executeQuery();

			if (this.rs.next()) {
				siteTitle = this.rs.getString("site_title");
			}
		} catch (Exception e) {
			
		} finally {
			try {
					this.rs.close();
					this.ps.close();
					this.con.close();
			} catch (Exception e) {
				
			}
		}
		return siteTitle;
	}
}
