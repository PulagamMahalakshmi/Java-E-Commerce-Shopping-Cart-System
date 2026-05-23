import javax.swing.*;
import java.awt.*;

public class LoginGUI {

    public LoginGUI() {

        JFrame frame = new JFrame("Login");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5,1));

        JTextField userField = new JTextField();
        JPasswordField passField =
                new JPasswordField();

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        frame.add(new JLabel("Username"));
        frame.add(userField);

        frame.add(new JLabel("Password"));
        frame.add(passField);

        JPanel buttons = new JPanel();

        buttons.add(login);
        buttons.add(register);

        frame.add(buttons);

        UserService service =
                new UserService();

        // LOGIN
        login.addActionListener(e -> {

            String username =
                    userField.getText();

            String password =
                    new String(passField.getPassword());

            boolean success =
                    service.login(username,password);

            if(success){

                JOptionPane.showMessageDialog(frame,
                        "Login Success");

                frame.dispose();

                new EcommerceGUI();

            }else{

                JOptionPane.showMessageDialog(frame,
                        "Invalid Credentials");
            }
        });

        // REGISTER
        register.addActionListener(e -> {

            String username =
                    userField.getText();

            String password =
                    new String(passField.getPassword());

            service.register(username,password);

            JOptionPane.showMessageDialog(frame,
                    "Registered Successfully");
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new LoginGUI();
    }
}