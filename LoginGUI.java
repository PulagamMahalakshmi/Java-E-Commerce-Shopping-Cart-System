import javax.swing.*;
import java.awt.*;

public class LoginGUI {

    public LoginGUI() {

        JFrame frame =
                new JFrame("Amazon Mini Login");

        frame.setSize(400, 300);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setLayout(
                new BorderLayout(10,10)
        );

        // 🟢 TITLE
        JLabel title =
                new JLabel("🛒 Login System");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24)
        );

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        frame.add(title,
                BorderLayout.NORTH);

        // 🟢 CENTER PANEL
        JPanel center =
                new JPanel();

        center.setLayout(
                new GridLayout(4,1,10,10)
        );

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        20,30,20,30
                )
        );

        JTextField userField =
                new JTextField();

        JPasswordField passField =
                new JPasswordField();

        center.add(
                new JLabel("Username")
        );

        center.add(userField);

        center.add(
                new JLabel("Password")
        );

        center.add(passField);

        frame.add(center,
                BorderLayout.CENTER);

        // 🟢 BUTTON PANEL
        JPanel bottom =
                new JPanel();

        JButton login =
                new JButton("Login");

        JButton register =
                new JButton("Register");

        bottom.add(login);
        bottom.add(register);

        frame.add(bottom,
                BorderLayout.SOUTH);

        UserService service =
                new UserService();

        // 🟢 LOGIN
        login.addActionListener(e -> {

            String username =
                    userField.getText();

            String password =
                    new String(
                            passField.getPassword()
                    );

            if(username.isEmpty()
                    || password.isEmpty()){

                JOptionPane.showMessageDialog(
                        frame,
                        "Please Enter Username & Password"
                );

                return;
            }

            boolean success =
                    service.login(
                            username,
                            password
                    );

            if(success){

                JOptionPane.showMessageDialog(
                        frame,
                        "Login Success"
                );

                frame.dispose();

                new EcommerceGUI();

            }else{

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Credentials"
                );
            }
        });

        // 🟢 REGISTER
        register.addActionListener(e -> {

            String username =
                    userField.getText();

            String password =
                    new String(
                            passField.getPassword()
                    );

            if(username.isEmpty()
                    || password.isEmpty()){

                JOptionPane.showMessageDialog(
                        frame,
                        "Please Fill All Fields"
                );

                return;
            }

            service.register(
                    username,
                    password
            );

            JOptionPane.showMessageDialog(
                    frame,
                    "Registered Successfully"
            );
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new LoginGUI();
    }
}
