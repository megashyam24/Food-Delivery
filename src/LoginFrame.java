import javaproject.MemberFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection con;
    private PreparedStatement memberSt;
    private PreparedStatement captainSt;
    private ResultSet rs;

    public LoginFrame() {
        setTitle("Login Page");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel with custom background color
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Custom panel background color
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

    // Username Label
    JLabel userLabel = new JLabel("Username:");
    userLabel.setBounds(100, 100, 80, 25);
    userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(userLabel);

    // Username Text Field
    usernameField = new JTextField(20);
    usernameField.setBounds(200, 100, 200, 25);
    panel.add(usernameField);

    // Password Label
    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setBounds(100, 150, 80, 25);
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(passwordLabel);

    // Password Field
    passwordField = new JPasswordField(20);
    passwordField.setBounds(200, 150, 200, 25);
    panel.add(passwordField);

    // Member Login Button
    JButton memberLoginButton = new JButton("Member Login");
    memberLoginButton.setBounds(150, 200, 150, 30);
    memberLoginButton.setBackground(new Color(33, 150, 243)); // Darker blue color
    memberLoginButton.setForeground(Color.BLACK);
    memberLoginButton.setFocusPainted(false);
    memberLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(memberLoginButton);

    // Captain Login Button
    JButton captainLoginButton = new JButton("Captain Login");
    captainLoginButton.setBounds(310, 200, 150, 30);
    captainLoginButton.setBackground(new Color(255, 193, 7)); // Yellow color
    captainLoginButton.setForeground(Color.black);
    captainLoginButton.setFocusPainted(false);
    captainLoginButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Changed to PLAIN
    panel.add(captainLoginButton);

    // Add a separator
    JSeparator separator = new JSeparator();
    separator.setBounds(100, 250, 380, 10);
    panel.add(separator);

    // Text below separator
    JLabel infoLabel = new JLabel("Don't have an account?");
    infoLabel.setBounds(150, 270, 150, 25);
    infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(infoLabel);

    // Sign Up Link
    JLabel signUpLink = new JLabel("Sign Up Here");
    signUpLink.setForeground(Color.BLUE);
    signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    signUpLink.setBounds(300, 270, 100, 25);
    signUpLink.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(signUpLink);

    // Action listener for the Member Login button
    memberLoginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticateMember(username, password)) {
                JOptionPane.showMessageDialog(null, "Member Login Successful");
                dispose();
         
                openMemberPage();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid member username or password");
                usernameField.setText("");
                passwordField.setText("");
            }
        }
    });

    // Action listener for the Captain Login button
    captainLoginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticateCaptain(username, password)) {
                JOptionPane.showMessageDialog(null, "Captain Login Successful");
                openCaptainPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid captain username or password");
                usernameField.setText("");
                passwordField.setText("");
            }
        }
    });

    // Action listener for the Sign Up link
    signUpLink.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(null, "Redirecting to Sign Up Page");
            SignupFrame s = new SignupFrame();
            s.setVisible(true);
            // Here you can open a new window or perform other actions
        }
    });
}


    private boolean authenticateMember(String username, String password) {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Your database URL
        String dbUser = "system"; // Database username
        String dbPassword = "karmegam"; // Database password

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT * FROM members WHERE username = ? AND password = ?";
            memberSt = con.prepareStatement(sql);
            memberSt.setString(1, username);
            memberSt.setString(2, password);
            rs = memberSt.executeQuery();
            return rs.next(); // If a row is returned, authentication successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    private boolean authenticateCaptain(String username, String password) {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Your database URL
        String dbUser = "system"; // Database username
        String dbPassword = "karmegam"; // Database password

        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String sql = "SELECT * FROM captains WHERE username = ? AND password = ?";
            captainSt = con.prepareStatement(sql);
            captainSt.setString(1, username);
            captainSt.setString(2, password);
            rs = captainSt.executeQuery();
            return rs.next(); // If a row is returned, authentication successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    private void openMemberPage() {
        // Code to open the member page after successful login
        System.out.println("Opening Member Page...");
        // For example, you could open a new JFrame here
        MemberFrame memberFrame = new MemberFrame();
        memberFrame.setVisible(true);
    }

    private void openCaptainPage() {
        // Code to open the captain page after successful login
        System.out.println("Opening Captain Page...");
        // For example, you could open a new JFrame herez
        CaptainFrame captainFrame = new CaptainFrame();
        captainFrame.setVisible(true);
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

                new LoginFrame();
            }
        });
    }
}


class CaptainFrame extends JFrame {
    public CaptainFrame() {
        setTitle("Captain Page");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Welcome to the Captain Page");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
