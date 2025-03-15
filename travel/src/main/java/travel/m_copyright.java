package travel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import travel.dto_setting;
import travel.m_dbinfo;

// 사이트 기본 환경 설정 - 카피라이트 관련 데이터 처리 모델
public class m_copyright {
    // DB 연결 관련 객체
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null; // select 사용시 필요
    String sql = null; // SQL 쿼리문

    // DB 연결 정보 객체
    m_dbinfo db = new m_dbinfo();

    // 1. 카피라이트 정보 가져오기 (SELECT)
    public ArrayList<dto_setting> cpdata() { // DTO 리스트로 반환
        ArrayList<dto_setting> copylist = new ArrayList<>();

        try {
            // DB 연결
            this.con = this.db.dbinfo();
            this.sql = "select comp_nm, ceo_nm, business_no, tel, com_no, "
            		 + "add_no, addr_no, com_addr, info_nm, info_email "
                     + "from aset order by aidx desc limit 1";
            this.ps = this.con.prepareStatement(this.sql);
            this.rs = this.ps.executeQuery();

            // 결과가 있을 경우 DTO 리스트에 추가
            while (this.rs.next()) {
                dto_setting dto = new dto_setting();
                dto.setComp_nm(this.rs.getString("comp_nm"));
                dto.setBusiness_no(this.rs.getString("business_no"));
                dto.setCeo_nm(this.rs.getString("ceo_nm"));
                dto.setTel(this.rs.getString("tel"));
                dto.setCom_no(this.rs.getString("com_no"));
                dto.setAdd_no(this.rs.getString("add_no"));
                dto.setAddr_no(this.rs.getString("addr_no"));
                dto.setCom_addr(this.rs.getString("com_addr"));
                dto.setInfo_nm(this.rs.getString("info_nm"));
                dto.setInfo_email(this.rs.getString("info_email"));

                copylist.add(dto);
            }
         // 데이터 크기 출력
            System.out.println("📌 cpdata() 결과: copylist.size() = " + copylist.size());
        } catch (Exception e) {
        	System.out.println("m_copyright오류");
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
        return copylist;
    }
}
