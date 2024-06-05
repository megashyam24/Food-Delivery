package javaproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;

public class SignupFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField mobileField;
    private JTextField addressField;
    private JCheckBox captainCheckbox;

    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel vehicleNoLabel;
    private JTextField vehicleNoField;
    private JLabel salaryLabel;
    private JTextField salaryField;
    private JLabel vehicleTypeLabel;
    private JTextField vehicleTypeField;

    private Connection con;
    private PreparedStatement memberSt;
    private PreparedStatement captainSt;
    private ResultSet rs;

    public SignupFrame() {
        setTitle("Signup Page");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(200, 230, 201));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Signup");
        titleLabel.setBounds(250, 30, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(100, 70, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(200, 70, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 110, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(200, 110, 200, 25);
        panel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 150, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(200, 150, 200, 25);
        panel.add(emailField);

        JLabel mobileLabel = new JLabel("Mobile:");
        mobileLabel.setBounds(100, 190, 80, 25);
        panel.add(mobileLabel);

        mobileField = new JTextField(20);
        mobileField.setBounds(200, 190, 200, 25);
        panel.add(mobileField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 230, 80, 25);
        panel.add(addressLabel);

        addressField = new JTextField(20);
        addressField.setBounds(200, 230, 200, 25);
        panel.add(addressField);

        captainCheckbox = new JCheckBox("Signup as Captain");
        captainCheckbox.setBounds(200, 270, 150, 25);
        panel.add(captainCheckbox);

        // Captain-specific fields (initially hidden)
        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(100, 310, 80, 25);
        ageLabel.setVisible(false);
        panel.add(ageLabel);

        ageField = new JTextField(20);
        ageField.setBounds(200, 310, 200, 25);
        ageField.setVisible(false);
        panel.add(ageField);

        vehicleNoLabel = new JLabel("Vehicle No:");
        vehicleNoLabel.setBounds(100, 350, 80, 25);
        vehicleNoLabel.setVisible(false);
        panel.add(vehicleNoLabel);

        vehicleNoField = new JTextField(20);
        vehicleNoField.setBounds(200, 350, 200, 25);
        vehicleNoField.setVisible(false);
        panel.add(vehicleNoField);

        salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(100, 390, 80, 25);
        salaryLabel.setVisible(false);
        panel.add(salaryLabel);

        salaryField = new JTextField(20);
        salaryField.setBounds(200, 390, 200, 25);
        salaryField.setVisible(false);
        panel.add(salaryField);

        vehicleTypeLabel = new JLabel("Vehicle Type:");
        vehicleTypeLabel.setBounds(100, 430, 100, 25);
        vehicleTypeLabel.setVisible(false);
        panel.add(vehicleTypeLabel);

        vehicleTypeField = new JTextField(20);
        vehicleTypeField.setBounds(200, 430, 200, 25);
        vehicleTypeField.setVisible(false);
        panel.add(vehicleTypeField);

        JButton signupButton = new JButton("Signup");
        signupButton.setBounds(250, 470, 100, 30);
        panel.add(signupButton);

        captainCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = captainCheckbox.isSelected();
                ageLabel.setVisible(isSelected);
                ageField.setVisible(isSelected);
                vehicleNoLabel.setVisible(isSelected);
                vehicleNoField.setVisible(isSelected);
                salaryLabel.setVisible(isSelected);
                salaryField.setVisible(isSelected);
                vehicleTypeLabel.setVisible(isSelected);
                vehicleTypeField.setVisible(isSelected);
                panel.revalidate();
                panel.repaint();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String mobile = mobileField.getText();
                String address = addressField.getText();
                boolean isCaptain = captainCheckbox.isSelected();

                try {
                    if (isCaptain) {
                        int age = Integer.parseInt(ageField.getText());
                        String vehicleNo = vehicleNoField.getText();
                        BigDecimal salary = new BigDecimal(salaryField.getText());
                        String vehicleType = vehicleTypeField.getText();
                        captainSignup(username, password, email, age, vehicleNo, mobile, address, salary, vehicleType);
                    } else {
                        memberSignup(username, password, email, mobile, address);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for age and salary.");
                }
            }
        });
    }

    private void memberSignup(String username, String password, String email, String mobile, String address) {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "system";
        String dbPassword = "karmegam";

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "INSERT INTO members (username, password, email, mobile_number, address) VALUES (?, ?, ?, ?, ?)";
            memberSt = con.prepareStatement(sql);
            memberSt.setString(1, username);
            memberSt.setString(2, password);
            memberSt.setString(3, email);
            memberSt.setString(4, mobile);
            memberSt.setString(5, address);
            memberSt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Member Signup Successful");
            dispose();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "User already exists");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while signing up. Please try again.");
        } finally {
            closeResources();
        }
    }

    private void captainSignup(String username, String password, String email, int age, String vehicleNo,
                               String mobile, String address, BigDecimal salary, String vehicleType) {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbUser = "system";
        String dbPassword = "karmegam";

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "INSERT INTO captains (username, password, email, age, vehicleno, mobile_number, " +
                         "address, salary, vehicle_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            captainSt = con.prepareStatement(sql);
            captainSt.setString(1, username);
            captainSt.setString(2, password);
            captainSt.setString(3, email);
            captainSt.setInt(4, age);
            captainSt.setString(5, vehicleNo);
            captainSt.setString(6, mobile);
            captainSt.setString(7, address);
            captainSt.setBigDecimal(8, salary);
            captainSt.setString(9, vehicleType);
            captainSt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Captain Signup Successful");
            dispose();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Captain already exists");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while signing up. Please try again.");
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (memberSt != null) memberSt.close();
            if (captainSt != null) captainSt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Use the system look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new SignupFrame();
            }
        });
    }
}
