// ✅ WebSocket 클라이언트 (전역 변수)
var stompClient = null;
var isWebSocketConnected = false; // WebSocket 연결 상태

function connectWebSocket() {
    if (stompClient !== null && isWebSocketConnected) {
        console.warn("🚨 WebSocket이 이미 연결되어 있음.");
        return;
    }

    var socket = new SockJS("/ws-stomp");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("✅ [공통 WebSocket] 기본 WebSocket 연결 완료");
        isWebSocketConnected = true;

        // ✅ 관리자는 전체 채팅 메시지를 구독
        if (window.location.pathname.includes("/admin/chatmanagement")) {
            console.log("📌 [관리자 페이지] 관리자 WebSocket 채널 구독 실행");
            stompClient.subscribe("/sub/chat/admin", function (message) {
                var receivedMessage = JSON.parse(message.body);
                console.log("📩 [관리자] 새로운 메시지 수신!", receivedMessage);
            });
        }
    }, function (error) {
        console.error("❌ WebSocket 연결 실패. 1초 후 재시도", error);
        setTimeout(connectWebSocket, 1000);
    });
}

// ✅ 페이지 로드 시 WebSocket 연결 실행 (한 번만 실행)
window.addEventListener("load", function () {
    console.log("📌 [공통 WebSocket] 페이지 로드 시 WebSocket 연결 실행");
    connectWebSocket();
});
