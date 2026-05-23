public class Order {

    int id;
    String productName;
    int quantity;
    int total;

    public Order(int id, String productName, int quantity, int total) {

        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.total = total;
    }
}