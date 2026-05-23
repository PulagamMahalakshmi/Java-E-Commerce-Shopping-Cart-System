import java.sql.*;
import java.util.ArrayList;

public class OrderService {

    // SAVE ORDER
    public void saveOrder(String productName, int qty, int total) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "INSERT INTO orders(product_name, quantity, total_price) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, productName);
            ps.setInt(2, qty);
            ps.setInt(3, total);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Order Save Error: " + e.getMessage());
        }
    }

    // GET ALL ORDERS
    public ArrayList<Order> getOrders() {

        ArrayList<Order> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM orders";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getInt("total_price")
                );

                list.add(order);
            }

        } catch (Exception e) {
            System.out.println("Order Fetch Error: " + e.getMessage());
        }

        return list;
    }
}