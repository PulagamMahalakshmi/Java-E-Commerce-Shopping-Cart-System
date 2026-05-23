import javax.swing.*;
import java.awt.*;

public class PaymentGUI {

    public PaymentGUI(double amount) {

        JFrame frame = new JFrame("Payment");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6,1,10,10));

        JLabel total =
                new JLabel("Total Amount: Rs." + amount);

        String[] methods = {
                "UPI",
                "Credit Card",
                "Cash On Delivery"
        };

        JComboBox<String> paymentBox =
                new JComboBox<>(methods);

        JButton payBtn =
                new JButton("Pay Now");

        frame.add(new JLabel("Choose Payment Method"));
        frame.add(paymentBox);
        frame.add(total);
        frame.add(new JLabel(""));
        frame.add(payBtn);

        payBtn.addActionListener(e -> {

            String method =
                    paymentBox.getSelectedItem().toString();

            JOptionPane.showMessageDialog(frame,
                    "Payment Successful!\nMethod: "
                    + method);

            frame.dispose();
        });

        frame.setVisible(true);
    }
}