import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminGUI {

    public AdminGUI() {

        JFrame frame = new JFrame("Admin Panel");
        frame.setSize(500, 600);
        frame.setLayout(new GridLayout(6,2,10,10));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField deleteIdField = new JTextField();

JButton deleteBtn =
        new JButton("Delete Product");

frame.add(new JLabel("Delete Product ID"));
frame.add(deleteIdField);

frame.add(new JLabel(""));
frame.add(deleteBtn);

        JButton addBtn = new JButton("Add Product");

        frame.add(new JLabel("Product Name"));
        frame.add(nameField);

        frame.add(new JLabel("Price"));
        frame.add(priceField);

        frame.add(new JLabel("Stock"));
        frame.add(stockField);

        frame.add(new JLabel("Category"));
        frame.add(categoryField);

        frame.add(new JLabel(""));
        frame.add(addBtn);

        addBtn.addActionListener(e -> {

            try {

                String name = nameField.getText();
                int price =
                        Integer.parseInt(priceField.getText());

                int stock =
                        Integer.parseInt(stockField.getText());

                String category =
                        categoryField.getText();

                Connection con =
                        DBConnection.getConnection();

                String query =
                        "INSERT INTO products(name,price,stock,category) VALUES(?,?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, name);
                ps.setInt(2, price);
                ps.setInt(3, stock);
                ps.setString(4, category);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,
                        "Product Added Successfully!");

                nameField.setText("");
                priceField.setText("");
                stockField.setText("");
                categoryField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame,
                        "Error: " + ex.getMessage());
            }
        });
        deleteBtn.addActionListener(e -> {

    try {

        int id =
                Integer.parseInt(deleteIdField.getText());

        Connection con =
                DBConnection.getConnection();

        String query =
                "DELETE FROM products WHERE id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if(rows > 0){

            JOptionPane.showMessageDialog(frame,
                    "Product Deleted");

        }else{

            JOptionPane.showMessageDialog(frame,
                    "Product Not Found");
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(frame,
                ex.getMessage());
    }
});
JTextField updateIdField = new JTextField();
JTextField newStockField = new JTextField();

JButton updateBtn =
        new JButton("Update Stock");

frame.add(new JLabel("Update Product ID"));
frame.add(updateIdField);

frame.add(new JLabel("New Stock"));
frame.add(newStockField);

frame.add(new JLabel(""));
frame.add(updateBtn);

updateBtn.addActionListener(e -> {

    try {

        int id =
                Integer.parseInt(updateIdField.getText());

        int stock =
                Integer.parseInt(newStockField.getText());

        Connection con =
                DBConnection.getConnection();

        String query =
                "UPDATE products SET stock=? WHERE id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, stock);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        if(rows > 0){

            JOptionPane.showMessageDialog(frame,
                    "Stock Updated");

        }else{

            JOptionPane.showMessageDialog(frame,
                    "Product Not Found");
        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(frame,
                ex.getMessage());
    }
});

        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new AdminGUI();
    }
}