package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class payment {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    m_dbinfo db = new m_dbinfo();

    // **결제 설정 정보 가져오기**
    public String[] getPaymentSettings() {
        String[] pmdata = new String[12]; // 12개 컬럼을 가져와야 함

        try {
            con = this.db.dbinfo();
            this.sql = "select bank_name, bank_no, credit_card_use, mobile_payment_use, book_coupon_use, min_payment_point, max_payment_point, cash_receipt_use, delivery_company, delivery_fee, desired_delivery_date_use from payment_settings order by aidx desc limit 1";
            this.ps = this.con.prepareStatement(this.sql);
            this.rs = this.ps.executeQuery();

            if (this.rs.next()) {
                for (int f = 0; f < pmdata.length; f++) {
                    pmdata[f] = this.rs.getString(f + 1); // NULL이면 자동으로 null 반환
                    if (pmdata[f] == null) pmdata[f] = ""; // NULL 값이 있으면 빈 문자열로 변경
                }
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
        return pmdata;
    }

    // **결제 설정 정보 저장 및 업데이트**
    public boolean savePaymentSettings(String bank_name, String bank_no, String credit_card_use, String mobile_payment_use, 
                                       String book_coupon_use, int min_payment_point, int max_payment_point, 
                                       String cash_receipt_use, String delivery_company, int delivery_fee, String desired_delivery_date_use) {
        boolean success = false;
        try {
            con = this.db.dbinfo();
            this.sql = "insert into payment_settings (bank_name, bank_no, credit_card_use, mobile_payment_use, book_coupon_use, min_payment_point, max_payment_point, cash_receipt_use, delivery_company, delivery_fee, desired_delivery_date_use, setting_date) "
                     + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now()) "
                     + "on duplicate key update bank_name=?, bank_no=?, credit_card_use=?, mobile_payment_use=?, book_coupon_use=?, min_payment_point=?, max_payment_point=?, cash_receipt_use=?, delivery_company=?, delivery_fee=?, desired_delivery_date_use=?";
            
            this.ps = this.con.prepareStatement(this.sql);
            
            // **INSERT 데이터 바인딩**
            this.ps.setString(1, bank_name);
            this.ps.setString(2, bank_no);
            this.ps.setString(3, credit_card_use);
            this.ps.setString(4, mobile_payment_use);
            this.ps.setString(5, book_coupon_use);
            this.ps.setInt(6, min_payment_point);
            this.ps.setInt(7, max_payment_point);
            this.ps.setString(8, cash_receipt_use);
            this.ps.setString(9, delivery_company);
            this.ps.setInt(10, delivery_fee);
            this.ps.setString(11, desired_delivery_date_use);

            // **UPDATE 데이터 바인딩**
            this.ps.setString(12, bank_name);
            this.ps.setString(13, bank_no);
            this.ps.setString(14, credit_card_use);
            this.ps.setString(15, mobile_payment_use);
            this.ps.setString(16, book_coupon_use);
            this.ps.setInt(17, min_payment_point);
            this.ps.setInt(18, max_payment_point);
            this.ps.setString(19, cash_receipt_use);
            this.ps.setString(20, delivery_company);
            this.ps.setInt(21, delivery_fee);
            this.ps.setString(22, desired_delivery_date_use);

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
