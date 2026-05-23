import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OrderHistoryGUI {

    public OrderHistoryGUI() {

        JFrame frame =
                new JFrame("Order History");

        frame.setSize(600, 500);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // 🟢 TEXT AREA
        JTextArea area =
                new JTextArea();

        area.setEditable(false);

        area.setFont(
                new Font("Consolas",
                        Font.PLAIN,
                        15)
        );

        area.setBackground(Color.WHITE);

        area.setMargin(
                new Insets(10,10,10,10)
        );

        // 🟢 TITLE
        JLabel title =
                new JLabel("🛒 Order History");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22)
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        frame.add(title,
                BorderLayout.NORTH);

        // 🟢 DATABASE FETCH
        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
            "SELECT * FROM order_items ORDER BY order_id";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            StringBuilder sb =
                    new StringBuilder();

            String currentOrder = "";

            while(rs.next()){

                String newOrder =
                        rs.getString("order_id");

                // 🟢 NEW ORDER
                if(!newOrder.equals(currentOrder)){

                    currentOrder = newOrder;

                    sb.append(
                    "\n====================\n"
                    );

                    sb.append("ORDER ID: ")
                      .append(currentOrder)
                      .append("\n");

                    sb.append(
                    "====================\n"
                    );
                }

                sb.append("• ")
                  .append(
                    rs.getString(
                    "product_name"
                    )
                  )

                  .append(" x")
                  .append(
                    rs.getInt(
                    "quantity"
                    )
                  )

                  .append(" = Rs.")
                  .append(
                    rs.getInt(
                    "total_price"
                    )
                  )

                  .append("\n");
            }

            // 🟢 SET TEXT
            area.setText(
                    sb.toString()
            );

            con.close();

        } catch (Exception e) {

            area.setText(
                    "Error Loading Orders\n"
                    + e.getMessage()
            );
        }

        // 🟢 SCROLL
        JScrollPane scroll =
                new JScrollPane(area);

        frame.add(scroll,
                BorderLayout.CENTER);

        frame.setVisible(true);
    }
}