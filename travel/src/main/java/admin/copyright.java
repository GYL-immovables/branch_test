package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class copyright {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    m_dbinfo db = new m_dbinfo();

    // **기본 환경 설정 데이터 가져오기**
    public ArrayList<String> copyright_info() {
        ArrayList<String> cpdata = new ArrayList<>();
        try {
            con = this.db.dbinfo();
            this.sql = "select * from site_basic_settings order by midx desc limit 1";
            this.ps = this.con.prepareStatement(this.sql);
            this.rs = this.ps.executeQuery();

            if (this.rs.next()) {
                cpdata.add(this.rs.getString("company_name"));
                cpdata.add(this.rs.getString("ceo_name"));
                cpdata.add(this.rs.getString("business_registration_number"));
                cpdata.add(this.rs.getString("tel"));
                cpdata.add(this.rs.getString("communication_sales_number"));
                cpdata.add(this.rs.getString("additional_business_number"));
                cpdata.add(this.rs.getString("company_zipcode"));
                cpdata.add(this.rs.getString("company_address"));
                cpdata.add(this.rs.getString("privacy_manager"));
                cpdata.add(this.rs.getString("privacy_email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.rs.close();
                this.ps.close();
                this.con.close();
            } catch (Exception e) {
                System.out.println("db 연결 종료 오류 발생!");
            }
        }
        return cpdata;
    }

    // **기본 환경 설정 데이터 저장 및 업데이트**
    public boolean saveSiteSettings(String company_name, String ceo_name, String business_registration_number,
                                    String tel, String communication_sales_number, String additional_business_number,
                                    String company_zipcode, String company_address, String privacy_manager, String privacy_email) {
        boolean success = false;
        try {
            con = this.db.dbinfo();
            this.sql = "insert into site_basic_settings (company_name, ceo_name, business_registration_number, tel, communication_sales_number, additional_business_number, company_zipcode, company_address, privacy_manager, privacy_email, setting_date) "
                     + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now()) "
                     + "on duplicate key update company_name=?, ceo_name=?, business_registration_number=?, tel=?, communication_sales_number=?, additional_business_number=?, company_zipcode=?, company_address=?, privacy_manager=?, privacy_email=?";
            
            this.ps = this.con.prepareStatement(this.sql);
            
            // insert 데이터 바인딩
            this.ps.setString(1, company_name);
            this.ps.setString(2, ceo_name);
            this.ps.setString(3, business_registration_number);
            this.ps.setString(4, tel);
            this.ps.setString(5, communication_sales_number);
            this.ps.setString(6, additional_business_number);
            this.ps.setString(7, company_zipcode);
            this.ps.setString(8, company_address);
            this.ps.setString(9, privacy_manager);
            this.ps.setString(10, privacy_email);

            // update 데이터 바인딩
            this.ps.setString(11, company_name);
            this.ps.setString(12, ceo_name);
            this.ps.setString(13, business_registration_number);
            this.ps.setString(14, tel);
            this.ps.setString(15, communication_sales_number);
            this.ps.setString(16, additional_business_number);
            this.ps.setString(17, company_zipcode);
            this.ps.setString(18, company_address);
            this.ps.setString(19, privacy_manager);
            this.ps.setString(20, privacy_email);

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
