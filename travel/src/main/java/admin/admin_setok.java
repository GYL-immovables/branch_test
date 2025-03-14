package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class admin_setok extends HttpServlet {
    private static final long serialVersionUID = 1L;
    copyright cp = new copyright();
    title t = new title();
    payment p = new payment();
    PrintWriter pw = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        this.pw = response.getWriter();

        try {
            // 홈페이지 가입환경 설정
            String site_title = request.getParameter("site_title");

            // 홈페이지 기본환경 설정
            String company_name = request.getParameter("company_name");
            String ceo_name = request.getParameter("ceo_name");
            String business_registration_number = request.getParameter("business_registration_number");
            String tel = request.getParameter("tel");
            String communication_sales_number = request.getParameter("communication_sales_number");
            String additional_business_number = request.getParameter("additional_business_number");
            String company_zipcode = request.getParameter("company_zipcode");
            String company_address = request.getParameter("company_address");
            String privacy_manager = request.getParameter("privacy_manager");
            String privacy_email = request.getParameter("privacy_email");

            // 결제 기본환경 설정
            String bank_name = request.getParameter("bank_name");
            String bank_no = request.getParameter("bank_no");
            String credit_card_use = request.getParameter("credit_card_use");
            String mobile_payment_use = request.getParameter("mobile_payment_use");
            String book_coupon_use = request.getParameter("book_coupon_use");
            Integer min_payment_point = Integer.parseInt(request.getParameter("min_payment_point"));
            Integer max_payment_point = Integer.parseInt(request.getParameter("max_payment_point"));
            String cash_receipt_use = request.getParameter("cash_receipt_use");
            String delivery_company = request.getParameter("delivery_company");
            Integer delivery_fee = Integer.parseInt(request.getParameter("delivery_fee"));
            String desired_delivery_date_use = request.getParameter("desired_delivery_date_use");

            // **모델을 이용한 데이터 저장**
            boolean siteSaved = t.saveSiteTitle(site_title);
            boolean settingsSaved = cp.saveSiteSettings(company_name, ceo_name, business_registration_number, tel, communication_sales_number, additional_business_number, company_zipcode, company_address, privacy_manager, privacy_email);
            boolean paymentSaved = p.savePaymentSettings(bank_name, bank_no, credit_card_use, mobile_payment_use, book_coupon_use, min_payment_point, max_payment_point, cash_receipt_use, delivery_company, delivery_fee, desired_delivery_date_use);

            if (siteSaved && settingsSaved && paymentSaved) {
                request.setAttribute("cpdata", cp.copyright_info());
                request.setAttribute("siteTitle", t.getSiteTitle());

                RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
                rd.forward(request, response);
            } else {
                pw.write("<script>"
                        + "alert('저장 실패'); "
                        + "history.go(-1);"
                        + "</script>");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류 발생 원인: " + e.getMessage()); // 로그 추가
            pw.write("<script>"
                    + "alert('데이터 저장 중 오류가 발생했습니다: " + e.getMessage() + "');"
                    + "history.go(-1);"
                    + "</script>");
        }
    }
}
