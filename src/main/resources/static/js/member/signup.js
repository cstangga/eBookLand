let emailCheckResult = false;
let pwCheckResult = false;

// 이메일 중복 확인 & password 일치 여부를 모두 검증하는 코드입니다.
function isValid() {
    if (emailCheckResult && pwCheckResult) {
        alert('👍회원가입 축하드립니다!')

    } else if (!emailCheckResult) {
        alert('이메일 중복 체크를 먼저 해주세요');
        return false;
    } else {
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    }
}

// 이메일 중복 체크를 하는 코드입니다.
function duplicateEmailCheck() {
    const memberEmail = document.getElementById('email');
    alert("중복체크 실행 " + memberEmail.value)
    if (!memberEmail.value) {
        alert('이메일을 입력해주세요.');
        return false;
    }
    console.log('email : ', memberEmail.value)
    $.ajax(
        {
            url: '/member/sameEmailCheck', // 대괄호를 꼭 써줘야 한다, 그렇지 않으면 controller로 넘어가지 않는다.
            method: 'post',
            data: {
                email: memberEmail.value
            },
            success: function (res) {
                if (res === true) {
                    alert('이미 사용중인 이메일입니다. 다시 입력해주세요!');
                    console.log('사용중인 이메일입니다. 다시 입력해주세요');
                    emailCheckResult = false; // 이메일 중복 확인 실패
                } else {
                    alert('사용 가능한 이메일입니다!');
                    emailCheckResult = true; // 이메일 사용 가능
                }
            },
            error: function () {
                console.log('이메일 중복 체크 오류입니다.');
            },
            complete: function () {
                console.log('이메일 중복 체크 완료fyfyfyfyfy')
            }
        });
    return emailCheckResult;
}

// 비밀번호 확인하는 코드입니다.
const pwCheck = () => {
    const pwCheck = () => {
        const pw = document.getElementById('password').value;
        const pwCheckInput = document.getElementById('confirm-password').value;
        const pwResult = document.getElementById('pwResult');

        if (pw && pwCheckInput) { // 둘 다 값이 있을 때만 체크
            if (pw === pwCheckInput) {
                pwResult.style.color = '#00bd56';
                pwResult.innerHTML = "비밀번호가 일치합니다.";
                pwCheckResult=true;
            } else {
                pwResult.style.color = '#e2263e';
                pwResult.innerHTML = "비밀번호가 일치하지 않습니다.";
            }
        } else {
            pwResult.innerHTML = "";
        }
    };

// keyup 이벤트 + input 이벤트 추가
    document.getElementById('password').addEventListener('input', pwCheck);
    document.getElementById('confirm-password').addEventListener('input', pwCheck);
}

// 우편번호 찾는 코드입니다.
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = '';
            let extraAddr = '';

            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            if (data.userSelectedType === 'R') {
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById("sample6_extraAddress").value = extraAddr;
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            document.getElementById("sample6_detailAddress").focus();

            console.log(document.getElementById('sample6_postcode').value);
            console.log(document.getElementById('sample6_address').value);
            console.log(document.getElementById('sample6_detailAddress').value);
            console.log(document.getElementById('sample6_extraAddress').value);
        }
    }).open();
}

// 핸드폰 번호 입력시, 숫자만 입력 가능함을 알려주는 코드입니다.
function validatePhoneInput(input) {
    const originalValue = input.value;
    let sanitizedValue = originalValue.replace(/[^0-9]/g, '');

    // 11자리까지만 허용
    if (sanitizedValue.length > 11) {
        sanitizedValue = sanitizedValue.substring(0, 11);
        alert('핸드폰 번호는 최대 11자리까지 입력 가능합니다.');
    }

    if (originalValue !== sanitizedValue) {
        alert('숫자만 입력 가능합니다.');
    }

    input.value = sanitizedValue;
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('postcodeBtn').addEventListener('click', sample6_execDaumPostcode);
});