package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import travel.dto_setting;
import travel.m_dbinfo;

// 홈페이지 제목 처리 모델
public class m_title {
    // DTO 객체 선언
    dto_setting dto = new dto_setting();

    // DB 연결 관련 객체
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null; // select 사용 시 필요
    String sql = null; // SQL 쿼리문

    // DB 연결 정보 객체
    m_dbinfo db = new m_dbinfo();

    // 1. 홈페이지 제목 가져오기 (select)
    public void getTitle() {
        try {
            this.con = this.db.dbinfo();
            this.sql = "select atitle from aset order by aidx desc limit 1";
            this.ps = this.con.prepareStatement(this.sql);
            this.rs = this.ps.executeQuery();

            if (this.rs.next()) {
                this.dto.setAtitle(this.rs.getString("atitle"));
            }
        } catch (Exception e) {
            System.out.println("m_title오류");
            e.printStackTrace();
        } finally {
            try {
                this.rs.close();
                this.ps.close();
                this.con.close();
            } catch (Exception e) {
                System.out.println("DB 연결 종료 오류 발생!");
            }
        }
    }

    //2. DTO 객체 반환 (컨트롤러에서 접근)
    public dto_setting getSettingData() {
        return this.dto;
    }
}
