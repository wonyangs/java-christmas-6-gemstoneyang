package christmas.domain;

/**
 * 주문 내역을 저장하는 클래스
 */
public class Order {
    private final OrderHistory orders;
    private final Date date;

    public Order(OrderHistory orders, Date date) {
        this.orders = orders;
        this.date = date;
    }

    public int totalPrice() {
        return orders.totalPrice();
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
