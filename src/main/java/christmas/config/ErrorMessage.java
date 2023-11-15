package christmas.config;

public enum ErrorMessage {
    INVALID_DATE_INPUT("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_INPUT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_DRINK_ORDER("음로만 주문할 수 없습니다. 다시 입력해 주세요."),
    MAX_COUNT_ORDER("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
