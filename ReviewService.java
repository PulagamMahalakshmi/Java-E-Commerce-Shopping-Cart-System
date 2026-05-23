import java.sql.*;
import java.util.ArrayList;

public class ReviewService {

    public void addReview(String product,
                          int rating,
                          String review) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO reviews(product_name,rating,review) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, product);
            ps.setInt(2, rating);
            ps.setString(3, review);

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Review> getReviews() {

        ArrayList<Review> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM reviews";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                list.add(new Review(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getInt("rating"),
                        rs.getString("review")
                ));
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return list;
    }
}