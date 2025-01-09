// 책 재고 입력 시, 숫자만 입력 가능함을 알려주는 코드입니다.
function duplicateBookNameCheck(input) {
    const originalValue = input.value;
    let sanitizedValue = originalValue.replace(/[^0-9]/g, '');

    if (originalValue !== sanitizedValue) {
        alert('숫자만 입력 가능합니다.');
    }

    input.value = sanitizedValue;
}