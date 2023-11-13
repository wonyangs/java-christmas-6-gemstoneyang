package christmas.domain;

/**
 * 주문 내역과 주문 날짜를 저장하는 record
 */
public record Order(Date date, OrderHistory menus) {

    public static Order of(Date date, OrderHistory menus) {
        return new Order(date, menus);
    }
}
