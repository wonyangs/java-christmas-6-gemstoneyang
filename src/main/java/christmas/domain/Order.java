package christmas.domain;

/**
 * 주문 내역과 주문 날짜를 저장하는 클래스
 */
public class Order {
    private final Date date;
    private final OrderHistory orders;

    public Order(Date date, OrderHistory orders) {
        this.date = date;
        this.orders = orders;
    }

    public int totalPrice() {
        return orders.totalPrice();
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
