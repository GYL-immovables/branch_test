function setok(){
    var regp = /^[a-z0-9.-_]+@[a-z0-9ㄱ-힣]+\.[a-z0-9ㄱ-힣]{2,}/i; // 이메일 정규식

    // **1. input type="text" 필수값 검사**
    if(frm.site_title.value == ""){
        alert("홈페이지 제목을 입력해주세요!!");
        return false;
    } else if(frm.aemail.value == ""){
        alert("관리자 이메일을 입력해주세요!!");
        return false;
    } else if(frm.signup_reward.value == ""){
        alert("회원가입 시 지급 포인트를 설정해주세요");
        return false;
    } else if(frm.signup_permission_level.value == ""){
        alert("회원가입 시 권한 레벨을 설정해주세요");
        return false;
    } else if(frm.company_name.value == ""){
        alert("회사명을 입력해주세요!!");
        return false;
    } else if(frm.business_registration_number.value == ""){
        alert("사업자 등록번호를 입력해주세요!!");
        return false;
    } else if(frm.ceo_name.value == ""){
        alert("대표자명을 입력해주세요!!");
        return false;
    } else if(frm.tel.value == ""){
        alert("대표전화번호를 입력해주세요!!");
        return false;
    } else if(frm.communication_sales_number.value == ""){
        alert("통신판매업 신고번호를 입력해주세요!!");
        return false;
    } else if(frm.additional_business_number.value == ""){
        alert("부가통신 사업자번호를 입력해주세요!!");
        return false;
    } else if(frm.company_zipcode.value == ""){
        alert("사업장 우편번호를 입력해주세요!!");
        return false;
    } else if(frm.company_address.value == ""){
        alert("사업장 주소를 입력해주세요!!");
        return false;
    } else if(frm.privacy_manager.value == ""){
        alert("정보관리책임자명을 입력해주세요!!");
        return false;
    } else if(frm.privacy_email.value == ""){
        alert("정보책임자 이메일을 입력해주세요!!");
        return false;
    } else if(frm.bank_name.value == ""){
        alert("무통장 은행명을 입력해주세요!!");
        return false;
    } else if(frm.bank_no.value == ""){
        alert("은행 계좌번호를 입력해주세요!!");
        return false;
    } else if(frm.min_payment_point.value == ""){
        alert("결제 최소 포인트를 입력해주세요!!");
        return false;
    } else if(frm.max_payment_point.value == ""){
        alert("결제 최대 포인트를 입력해주세요!!");
        return false;
    } else if(frm.delivery_company.value == ""){
        alert("배송업체명을 입력해주세요!!");
        return false;
    } else if(frm.delivery_fee.value == ""){
        alert("배송비 가격을 입력해주세요!!");
        return false;
    } else {
        // **2. 이메일 정규식 검사**
        if(!regp.test(frm.aemail.value)){
            alert("올바른 이메일 형식을 입력해주세요(관리자 이메일:).");
            return false;
        } else if(!regp.test(frm.privacy_email.value)){
            alert("올바른 이메일 형식을 입력해주세요(정보책임자 이메일:).");
            return false;
        }

        // **3. 숫자만 입력해야 하는 필드 검사**
        var numberFields = [
            { value: frm.signup_reward.value, name: "회원가입 지급 포인트" },
            { value: frm.signup_permission_level.value, name: "회원가입 권한 레벨" },
            { value: frm.business_registration_number.value, name: "사업자 등록번호" },
            { value: frm.tel.value, name: "대표전화번호" },
            { value: frm.min_payment_point.value, name: "결제 최소 포인트" },
            { value: frm.max_payment_point.value, name: "결제 최대 포인트" },
            { value: frm.delivery_fee.value, name: "배송비 가격" }
        ];

        for (var i = 0; i < numberFields.length; i++) {
            if (isNaN(numberFields[i].value)) {
                alert(numberFields[i].name + "은(는) 숫자만 입력 가능합니다.");
                return false;
            }
        }

        // **4. radio 버튼 값 검사**
        var radioFields = [
            { name: "point_usage", label: "포인트 사용 여부" },
            { name: "credit_card_use", label: "신용카드 결제 사용 여부" },
            { name: "mobile_payment_use", label: "휴대폰 결제 사용 여부" },
            { name: "book_coupon_use", label: "도서상품권 결제 사용 여부" },
            { name: "cash_receipt_use", label: "현금 영수증 발급 여부" },
            { name: "desired_delivery_date_use", label: "희망배송일 설정 여부" }
        ];

        for (var i = 0; i < radioFields.length; i++) {
            var radioChecked = document.querySelector('input[name="'+radioFields[i].name+'"]:checked');
            if (radioChecked === null) {
                alert(radioFields[i].label + "을(를) 선택해주세요.");
                return false;
            }
        }

        // **5. 최종 저장 확인**
        var confirmSave = confirm("해당 정보를 사이트에 반영하시겠습니까?");
        if(confirmSave){
            frm.submit(); // `true`일 경우 폼 제출 → DB 반영
        } else {
            alert("변경 사항이 저장되지 않았습니다."); // `false`일 경우 DB 반영 안 됨
            return false;
        }
    }
}
