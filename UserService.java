import java.sql.*;

public class UserService {

    public boolean login(String username,
                         String password) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return false;
    }

    public void register(String username,
                         String password) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            System.out.println("User Registered");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}