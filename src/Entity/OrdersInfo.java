package src.Entity;

public class OrdersInfo {
    private int order_id;
    private int book_id;
    private int ordered_books;

    public OrdersInfo(int order_id, int book_id, int ordered_books) {
        this.order_id = order_id;
        this.book_id = book_id;
        this.ordered_books = ordered_books;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getOrdered_books() {
        return ordered_books;
    }

    public void setOrdered_books(int ordered_books) {
        this.ordered_books = ordered_books;
    }

    @Override
    public String toString() {
        return "OrderInformation{" +
                "order_id=" + order_id + ", " +
                "book_id=" + book_id + ", " +
                "ordered_books=" + ordered_books + '}';
    }
}