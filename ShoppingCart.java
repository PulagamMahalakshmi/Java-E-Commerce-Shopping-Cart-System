import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class ShoppingCart {

    ArrayList<CartItem> cart = new ArrayList<>();

    public void addProduct(Product p, int qty) {
        cart.add(new CartItem(p, qty));
    }

   // 🟢 SAVE ORDERS TO DATABASE
public void saveOrdersToDB(){

    try{

        Connection con =
                DBConnection.getConnection();

        // ✅ INSERT INTO orders TABLE
        String orderQuery =
        "INSERT INTO orders(total_amount) VALUES(?)";

        PreparedStatement orderPs =
                con.prepareStatement(
                        orderQuery,
                        Statement.RETURN_GENERATED_KEYS
                );

        orderPs.setInt(
                1,
                (int)getFinalAmount()
        );

        orderPs.executeUpdate();

        // ✅ GET GENERATED ORDER ID
        ResultSet rs =
                orderPs.getGeneratedKeys();

        int orderId = 0;

        if(rs.next()){

            orderId = rs.getInt(1);
        }

        // ✅ INSERT PRODUCTS INTO order_items
        String itemQuery =
        "INSERT INTO order_items(order_id, product_name, quantity, total_price) VALUES(?,?,?,?)";

        PreparedStatement itemPs =
                con.prepareStatement(itemQuery);

        for(CartItem item : cart){

            itemPs.setInt(
                    1,
                    orderId
            );

            itemPs.setString(
                    2,
                    item.product.name
            );

            itemPs.setInt(
                    3,
                    item.quantity
            );

            itemPs.setInt(
                    4,
                    item.getTotal()
            );

            itemPs.executeUpdate();
        }

        con.close();

        System.out.println(
                "Order Saved Successfully!"
        );

    }catch(Exception e){

        System.out.println(
                "DB Save Error: "
                + e.getMessage()
        );
    }
}

    public String viewCart() {

        if (cart.isEmpty()) return "Cart is Empty";

        StringBuilder sb = new StringBuilder();

        for (CartItem item : cart) {
            sb.append(item.product.name)
              .append(" x")
              .append(item.quantity)
              .append(" = Rs.")
              .append(item.getTotal())
              .append("\n");
        }

        return sb.toString();
    }

    public int getTotal() {

        int total = 0;

        for (CartItem item : cart) {
            total += item.getTotal();
        }

        return total;
    }

    public int getDiscount() {

        int total = getTotal();

        if (total >= 5000) {
            return total / 10;
        }

        return 0;
    }

    public int getFinalAmount() {
        return getTotal() - getDiscount();
    }

    public int checkout() {
        int finalAmount = getFinalAmount();
        cart.clear();
        return finalAmount;
    }

    // ✅ FIXED REMOVE METHOD
    public void removeProduct(int id) {

        CartItem found = null;

        for (CartItem item : cart) {
            if (item.product.id == id) {
                found = item;
                break;
            }
        }

        if (found != null) {
            cart.remove(found);
        }
    }
}