// 책 재고 입력 시, 숫자만 입력 가능함을 알려주는 코드입니다.
function duplicateBookNameCheck(input) {
    const originalValue = input.value;
    let sanitizedValue = originalValue.replace(/[^0-9]/g, '');

    if (originalValue !== sanitizedValue) {
        alert('숫자만 입력 가능합니다.');
    }

    input.value = sanitizedValue;
}


function rentalPrice(input) {
    // 숫자 외 문자는 제거
    let value = input.value.replace(/[^0-9]/g, '');
    // 3자리마다 콤마 추가
    input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function purchasePrice(input) {
    // 숫자 외 문자는 제거
    let value = input.value.replace(/[^0-9]/g, '');
    // 3자리마다 콤마 추가
    input.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

