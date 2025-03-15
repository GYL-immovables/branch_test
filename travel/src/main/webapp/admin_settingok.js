function setok() {
    var regp = /^[a-z0-9.-_]+@[a-z0-9ㄱ-힣]+\.[a-z0-9ㄱ-힣]{2,}/i; // 이메일 정규식 (이메일 형식 유효성 검사)

    // 1. input type="text" 필수값 검사
    if (frm.atitle.value == "") {
        alert("홈페이지 제목을 입력해주세요");
        return false;
    } else if (frm.aemail.value == "") {
        alert("관리자 이메일을 입력해주세요");
        return false;
    } else if (frm.sign_point.value == "") {
        alert("회원가입 시 지급 포인트를 설정해주세요");
        return false;
    } else if (frm.sign_lv.value == "") {
        alert("회원가입 시 권한 레벨을 설정해주세요");
        return false;
    } else if (frm.comp_nm.value == "") {
        alert("회사명을 입력해주세요");
        return false;
    } else if (frm.business_no.value == "") {
        alert("사업자 등록번호를 입력해주세요");
        return false;
    } else if (frm.ceo_nm.value == "") {
        alert("대표자명을 입력해주세요");
        return false;
    } else if (frm.tel.value == "") {
        alert("대표전화번호를 입력해주세요");
        return false;
    } else if (frm.com_no.value == "") {
        alert("통신판매업 신고번호를 입력해주세요");
        return false;
    } else if (frm.add_no.value == "") {
        alert("부가통신 사업자번호를 입력해주세요");
        return false;
    } else if (frm.addr_no.value == "") {
        alert("사업장 우편번호를 입력해주세요");
        return false;
    } else if (frm.com_addr.value == "") {
        alert("사업장 주소를 입력해주세요");
        return false;
    } else if (frm.info_nm.value == "") {
        alert("정보관리책임자명을 입력해주세요");
        return false;
    } else if (frm.info_email.value == "") {
        alert("정보책임자 이메일을 입력해주세요");
        return false;
    } else if (frm.bank_nm.value == "") {
        alert("무통장 은행명을 입력해주세요");
        return false;
    } else if (frm.bank_no.value == "") {
        alert("은행 계좌번호를 입력해주세요");
        return false;
    } else if (frm.min_point.value == "") {
        alert("결제 최소 포인트를 입력해주세요");
        return false;
    } else if (frm.max_point.value == "") {
        alert("결제 최대 포인트를 입력해주세요");
        return false;
    } else if (frm.del_com.value == "") {
        alert("배송업체명을 입력해주세요");
        return false;
    } else if (frm.del_price.value == "") {
        alert("배송비 가격을 입력해주세요");
        return false;
    } else {
        // 2. 이메일 정규식 검사
        if (!regp.test(frm.aemail.value)) { // 관리자 이메일 유효성 검사
            alert("올바른 이메일 형식을 입력해주세요(관리자 이메일:)");
            return false;
        } else if (!regp.test(frm.info_email.value)) { // 정보책임자 이메일 유효성 검사
            alert("올바른 이메일 형식을 입력해주세요(정보책임자 이메일:)");
            return false;
        }

        // 3. 숫자만 입력해야 하는 필드 검사
        var no_f = [
            frm.sign_point.value,  // 회원가입 지급 포인트
            frm.sign_lv.value,     // 회원가입 권한 레벨
            frm.business_no.value, // 사업자 등록번호
            frm.tel.value,         // 대표전화번호
            frm.min_point.value,   // 결제 최소 포인트
            frm.max_point.value,   // 결제 최대 포인트
            frm.del_price.value    // 배송비 가격
        ];

        for (var i = 0; i < no_f.length; i++) {
            if (isNaN(parseInt(no_f[i]))) { // parseInt()를 사용해 숫자인지 확인
                alert("숫자만 입력해야 하는 필드에 올바른 값을 입력해주세요.");
                return false;
            }
        }

        // 4. 라디오 버튼 값 검사
        var radio_f = [
            "apoint_use",   // 포인트 사용 여부
            "card_use",     // 신용카드 결제 사용 여부
            "mobile_use",   // 휴대폰 결제 사용 여부
            "book_coupon",  // 도서상품권 결제 사용 여부
            "cash_receipt", // 현금 영수증 발급 여부
            "del_date"      // 희망배송일 설정 여부
        ];

        for (var i = 0; i < radio_f.length; i++) {
            var radiobtn = document.getElementsByName(radio_f[i]); // 해당 이름의 radio 버튼 가져오기
            var w = 0, count = 0;

            while (w < radiobtn.length) {
                if (radiobtn[w].checked) {
                    count++;
                }
                w++;
            }

            if (count == 0) { // 선택된 값이 없으면 알림창 표시
                alert("필수 선택 항목을 체크해주세요.");
                return false;
            }
        }

        // 5. 최종 저장 확인
        var confirmSave = confirm("해당 정보를 사이트에 반영하시겠습니까?");
        if (confirmSave) {
            frm.submit(); // true일 경우 폼 제출 → DB 반영
        } else {
            alert("변경 사항이 저장되지 않았습니다."); // false일 경우 DB 반영 안 됨
            return false;
        }
    }
}

// 6️. 취소 버튼 동작 (setno)
function setno() {
    var confirmNo = confirm("취소하시게 되면 입력한 정보값이 모두 초기화 됩니다.");
    if (confirmNo) {
        history.go(-1); // 설정 페이지 새로고침 (초기화)
    }
}
