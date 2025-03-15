package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import travel.dto_setting;
import travel.m_dbinfo;

// aset 테이블의 정보를 저장하는 모델 (INSERT 전용)
public class m_setting {
    // DB 연결 관련 객체
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null; // SQL 쿼리문

    // DB 연결 정보 객체
    m_dbinfo db = new m_dbinfo();

    // 1. 기본 환경 설정 정보 저장 (INSERT)
    public boolean insertSet(dto_setting data) {
        boolean success = false;
        try {
            this.con = this.db.dbinfo();
            this.sql = "insert into aset (aidx, atitle, aemail, apoint_use, sign_point, sign_lv, comp_nm, "
                     + "business_no, ceo_nm, tel, com_no, add_no, addr_no, com_addr, info_nm, info_email, "
                     + "bank_nm, bank_no, card_use, mobile_use, book_coupon, min_point, max_point, "
                     + "cash_receipt, del_com, del_price, del_date, set_date) "
                     + "values ('0', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";

            this.ps = this.con.prepareStatement(this.sql);

            //INSERT 데이터
            this.ps.setString(1, data.getAtitle());
            this.ps.setString(2, data.getAemail());
            this.ps.setString(3, data.getApoint_use());
            this.ps.setString(4, data.getSign_point());
            this.ps.setString(5, data.getSign_lv());
            this.ps.setString(6, data.getComp_nm());
            this.ps.setString(7, data.getBusiness_no());
            this.ps.setString(8, data.getCeo_nm());
            this.ps.setString(9, data.getTel());
            this.ps.setString(10, data.getCom_no());
            this.ps.setString(11, data.getAdd_no());
            this.ps.setString(12, data.getAddr_no());
            this.ps.setString(13, data.getCom_addr());
            this.ps.setString(14, data.getInfo_nm());
            this.ps.setString(15, data.getInfo_email());
            this.ps.setString(16, data.getBank_nm());
            this.ps.setString(17, data.getBank_no());
            this.ps.setString(18, data.getCard_use());
            this.ps.setString(19, data.getMobile_use());
            this.ps.setString(20, data.getBook_coupon());
            this.ps.setString(21, data.getMin_point());
            this.ps.setString(22, data.getMax_point());
            this.ps.setString(23, data.getCash_receipt());
            this.ps.setString(24, data.getDel_com());
            this.ps.setString(25, data.getDel_price());
            this.ps.setString(26, data.getDel_date());
            
            System.out.println("실행할 SQL: " + this.sql); // 실행할 SQL 쿼리 확인
            int result = this.ps.executeUpdate();
            
            if (result > 0) {
            	System.out.println("DB INSERT 성공");
                success = true;
            }else {
            	System.out.println("DB INSERT 실패");
            }
        } catch (Exception e) {
        	System.out.println("DB INSERT 중 오류 발생!");
            e.printStackTrace();
        } finally {
            try {
                this.ps.close();
                this.con.close();
            } catch (Exception e) {
                System.out.println("DB 닫기 오류 발생!");
            }
        }
        return success;
    }
}
