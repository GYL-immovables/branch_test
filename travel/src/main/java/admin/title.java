package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class title {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    m_dbinfo db = new m_dbinfo();

    // **사이트 제목 가져오기**
    public String getSiteTitle() {
        String sitetitle = "쇼핑몰"; // 기본값 설정
        try {
            con = this.db.dbinfo();
            this.sql = "select site_title from admin_site_sign_settings order by aidx desc limit 1";
            this.ps = this.con.prepareStatement(this.sql);
            this.rs = this.ps.executeQuery();

            if (this.rs.next()) {
                sitetitle = this.rs.getString("site_title");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.rs != null) this.rs.close();
                if (this.ps != null) this.ps.close();
                if (this.con != null) this.con.close();
            } catch (Exception e) {
                System.out.println("db 연결 종료 오류 발생!");
            }
        }
        return sitetitle;
    }

    // **사이트 제목 저장 및 업데이트**
    public boolean saveSiteTitle(String sitetitle) {
        boolean success = false;
        try {
            con = this.db.dbinfo();
            this.sql = "insert into admin_site_sign_settings (site_title, aemail, point_usage, signup_reward, signup_permission_level, setting_date) "
                     + "values (?, ?, 'yes', 0, 1, now()) "
                     + "on duplicate key update site_title=?";
            this.ps = this.con.prepareStatement(this.sql);
            this.ps.setString(1, sitetitle);
            this.ps.setString(2, sitetitle);
            int result = this.ps.executeUpdate();

            if (result > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.ps.close();
                this.con.close();
            } catch (Exception e) {
                System.out.println("db 닫기 오류 발생!");
            }
        }
        return success;
    }
}
