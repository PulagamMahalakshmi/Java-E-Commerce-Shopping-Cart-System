public class Product {

    int id;
    String name;
    int price;
    int stock;
    String category;

    public Product(int id,
                   String name,
                   int price,
                   int stock,
                   String category) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}