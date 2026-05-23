import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EcommerceGUI {

    ShoppingCart cart = new ShoppingCart();
    JTextArea area = new JTextArea();

    ArrayList<Product> products;

    public EcommerceGUI() {

        ProductService service = new ProductService();
        products = service.getProducts();

        JFrame frame = new JFrame("Amazon Mini Store");

        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        frame.getContentPane().setBackground(
                new Color(245,245,245)
        );

        // 🟢 TOP PANEL
        JPanel top = new JPanel();

        JTextField search = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        top.add(new JLabel("🔍 Search Product: "));
        top.add(search);
        top.add(searchBtn);

        frame.add(top, BorderLayout.NORTH);

        // 🟢 CENTER PRODUCTS
        JPanel center = new JPanel();

        center.setBackground(new Color(245,245,245));

        center.setLayout(
                new GridLayout(0, 3, 15, 15)
        );

        for (Product p : products) {

            center.add(createCard(p, frame));
        }

        JScrollPane productScroll =
                new JScrollPane(center);

        frame.add(productScroll, BorderLayout.CENTER);

        // 🟢 RIGHT CART PANEL
        JPanel right = new JPanel();

        right.setLayout(new BorderLayout());
        right.setPreferredSize(
                new Dimension(350, 650)
        );

        JLabel cartTitle =
                new JLabel("🛒 Shopping Cart");

        cartTitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        cartTitle.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20)
        );

        right.add(cartTitle, BorderLayout.NORTH);

        area.setEditable(false);

        area.setFont(
                new Font("Consolas",
                        Font.PLAIN,
                        14)
        );

        JScrollPane cartScroll =
                new JScrollPane(area);

        right.add(cartScroll, BorderLayout.CENTER);

        // 🟢 BUTTONS
        JPanel bottom = new JPanel();

        bottom.setLayout(
                new GridLayout(4,1,10,10)
        );

        JButton view =
                new JButton("View Cart");

        JButton total =
                new JButton("Total");

        JButton checkout =
                new JButton("Checkout");

        JButton history =
                new JButton("Order History");

        bottom.add(view);
        bottom.add(total);
        bottom.add(checkout);
        bottom.add(history);

        right.add(bottom, BorderLayout.SOUTH);

        frame.add(right, BorderLayout.EAST);

        // 🟢 VIEW CART
        view.addActionListener(e -> {

            area.setText(cart.viewCart());
        });

        // 🟢 TOTAL
        total.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    frame,

                    "Total = Rs."
                    + cart.getTotal()

                    + "\nDiscount = Rs."
                    + cart.getDiscount()

                    + "\nFinal = Rs."
                    + cart.getFinalAmount()
            );
        });

        // 🟢 CHECKOUT
        checkout.addActionListener(e -> {

            if(cart.cart.isEmpty()){

                JOptionPane.showMessageDialog(
                        frame,
                        "Cart Empty!"
                );

                return;
            }

            cart.saveOrdersToDB();

            double amount =
                    cart.checkout();

            JOptionPane.showMessageDialog(
                    frame,

                    "Order Placed Successfully!"
                    + "\nPaid: Rs."
                    + amount
            );

            area.setText("");
        });

        // 🟢 ORDER HISTORY
        history.addActionListener(e -> {

            new OrderHistoryGUI();
        });

        // 🟢 SEARCH
        searchBtn.addActionListener(e -> {

            String text =
                    search.getText().toLowerCase();

            boolean found = false;

            for(Product p : products){

                if(p.name.toLowerCase()
                        .contains(text)){

                    area.setText(
                            "🔍 PRODUCT FOUND\n\n"
                            + "Name: "
                            + p.name

                            + "\nPrice: Rs."
                            + p.price

                            + "\nStock: "
                            + p.stock

                            + "\nCategory: "
                            + p.category
                    );

                    found = true;
                }
            }

            if(!found){

                JOptionPane.showMessageDialog(
                        frame,
                        "No Product Found!"
                );
            }
        });

        frame.setVisible(true);
    }

    // 🟢 PRODUCT CARD
    private JPanel createCard(Product p, JFrame frame) {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setLayout(
                new GridLayout(8,1,5,5)
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.GRAY
                        ),

                        BorderFactory.createEmptyBorder(
                                10,10,10,10
                        )
                )
        );

        // 🟢 PRODUCT INFO
        JLabel name =
                new JLabel("🛍 " + p.name);

        JLabel price =
                new JLabel("Price: ₹ " + p.price);

        JLabel stock =
                new JLabel("Stock: " + p.stock);

        JLabel category =
                new JLabel("Category: "
                        + p.category);

        // 🟢 QUANTITY PANEL
        JPanel qtyPanel = new JPanel();

        JButton minus =
                new JButton("-");

        JLabel qtyLabel =
                new JLabel("1");

        JButton plus =
                new JButton("+");

        int[] qty = {1};

        plus.addActionListener(e -> {

            if(qty[0] < p.stock){

                qty[0]++;

                qtyLabel.setText(
                        "" + qty[0]
                );
            }
        });

        minus.addActionListener(e -> {

            if(qty[0] > 1){

                qty[0]--;

                qtyLabel.setText(
                        "" + qty[0]
                );
            }
        });

        qtyPanel.add(minus);
        qtyPanel.add(qtyLabel);
        qtyPanel.add(plus);

        // 🟢 ADD BUTTON
        JButton add =
                new JButton("🛒 Add to Cart");

        add.addActionListener(e -> {

            int qtyValue = qty[0];

            if(qtyValue > p.stock){

                JOptionPane.showMessageDialog(
                        frame,
                        "Not enough stock!"
                );

                return;
            }

            p.stock -= qtyValue;

            stock.setText(
                    "Stock: " + p.stock
            );

            cart.addProduct(p, qtyValue);

            JOptionPane.showMessageDialog(
                    frame,
                    p.name + " Added!"
            );
        });

        // 🟢 REMOVE BUTTON
        JButton remove =
                new JButton("❌ Remove");

        remove.addActionListener(e -> {

            String input =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Product ID:"
                    );

            if(input != null){

                try{

                    int id =
                            Integer.parseInt(input);

                    cart.removeProduct(id);

                    area.setText(
                            cart.viewCart()
                    );

                    JOptionPane.showMessageDialog(
                            frame,
                            "Item Removed"
                    );

                }catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid Input"
                    );
                }
            }
        });

        // 🟢 REVIEW BUTTON
        JButton reviewBtn =
                new JButton("⭐ Review");

        reviewBtn.addActionListener(e -> {

            String ratingInput =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Rating (1-5)"
                    );

            String review =
                    JOptionPane.showInputDialog(
                            frame,
                            "Write Review"
                    );

            try{

                int rating =
                        Integer.parseInt(
                                ratingInput
                        );

                ReviewService rs =
                        new ReviewService();

                rs.addReview(
                        p.name,
                        rating,
                        review
                );

                JOptionPane.showMessageDialog(
                        frame,
                        "Review Added!"
                );

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Rating"
                );
            }
        });

        // 🟢 ADD COMPONENTS
        card.add(name);
        card.add(price);
        card.add(stock);
        card.add(category);
        card.add(qtyPanel);
        card.add(add);
        card.add(remove);
        card.add(reviewBtn);

        return card;
    }

    public static void main(String[] args) {

        new EcommerceGUI();
    }
}