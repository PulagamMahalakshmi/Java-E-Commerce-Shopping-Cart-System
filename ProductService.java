import java.sql.*;
import java.util.ArrayList;

public class ProductService {

    public ArrayList<Product> getProducts() {

        ArrayList<Product> products = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM products";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("category")
                ));

            }

            con.close();

        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return products;
    }
}