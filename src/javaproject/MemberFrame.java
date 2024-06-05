package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.Timer;
import java.util.TimerTask;



public class MemberFrame extends JFrame  {

    // Database connection details
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "karmegam";

    private JTextField searchBar;
    private JLabel label = new JLabel(); // Initialize the label here

    public MemberFrame() {
        setTitle("Foodzee");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Colors
        Color primaryColor = new Color(255, 128, 0); // Orange color
        Color secondaryColor = Color.WHITE; // White color

        // Logo Icon
        ImageIcon logoIcon = new ImageIcon("E:\\javaproject4\\FOODZEE.jpg");

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(secondaryColor);

        // Add user profile panel to main panel
        JPanel userProfilePanel = createUserProfilePanel(primaryColor, secondaryColor, logoIcon);
        mainPanel.add(userProfilePanel, BorderLayout.NORTH);

        // Search Bar
        searchBar = new JTextField(20);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 16));
        searchBar.setPreferredSize(new Dimension(200, 30)); // Adjusting the size of the search bar
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(primaryColor);
        searchButton.setForeground(Color.WHITE); // Change text color to white
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHotels();
            }
        });
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Center aligning the search components
        searchPanel.setBackground(secondaryColor);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        userProfilePanel.add(searchPanel, BorderLayout.CENTER); // Adding search bar to the center of user profile panel

        // Create panel for hotel selection
        JPanel hotelPanel = createHotelPanel(primaryColor, secondaryColor);
        JScrollPane hotelScrollPane = new JScrollPane(hotelPanel);
        hotelScrollPane.setBorder(BorderFactory.createEmptyBorder());
        hotelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hotelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(hotelScrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Create and add menu bar
        createMenuBar(primaryColor, secondaryColor);

        setVisible(true);
    }

    // Method to create and add menu bar
    private void createMenuBar(Color primaryColor, Color secondaryColor) {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu menuMenu = new JMenu("Menu");
    menuBar.add(menuMenu);

    JMenuItem orderdetailsMenuItem = createMenuItem("Order Details", e -> openOrderDetails());
    JMenuItem aboutMenuItem = createMenuItem("About", e -> openAbout());
    JMenuItem logoutMenuItem = createMenuItem("Logout", e -> logout());
    JMenuItem ratingMenuItem = createMenuItem("Rating", e -> openRating()); // Add a new menu item for rating

    menuMenu.add(orderdetailsMenuItem);
    menuMenu.add(aboutMenuItem);
    menuMenu.add(logoutMenuItem);
    menuMenu.add(ratingMenuItem); // Add the rating menu item
}

// Method to open the Rating frame
private void openRating() {
    Rating ratingFrame = new Rating();
    ratingFrame.setVisible(true);
}


    // Method to create menu item with specified properties
    private JMenuItem createMenuItem(String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setFont(new Font("Arial", Font.BOLD, 14));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    // Method to create a button with specified properties
    private JButton createButton(Color backgroundColor, Color foregroundColor, ImageIcon icon, ActionListener actionListener) {
        JButton button = new JButton(icon);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Add a small border
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(actionListener);
        return button;
    }

    private JButton createButton1(String text, Color backgroundColor, Color foregroundColor, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        button.addActionListener(actionListener);
        return button;
    }

    // Method to create panel for hotel selection
    private JPanel createHotelPanel(Color primaryColor, Color secondaryColor) {
        JPanel hotelPanel = new JPanel(new GridLayout(0, 4, 20, 20)); // Increased gap
        hotelPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // Increased padding
        hotelPanel.setBackground(secondaryColor);

        // Example hotel names and images (to be retrieved from the database)
        String[] hotelNames = {"Madurai Mess", "Loss Pollus Hermanus", "CAFEDAY", "Saravana Bhavan"};
        String[] imagePaths = {"E:\\javaproject4\\maduraimess.jpg", "E:\\javaproject4\\lospollus.jpg", "E:\\javaproject4\\CAFE.jpg", "E:\\javaproject4\\SARAVANA.jpg"};

        for (int i = 0; i < hotelNames.length; i++) {
            final String hotelName = hotelNames[i]; // Declare final variable here
            ImageIcon hotelIcon = null;
            try {
                hotelIcon = new ImageIcon(imagePaths[i]);
                Image img = hotelIcon.getImage().getScaledInstance(295, 545, Image.SCALE_SMOOTH);
                hotelIcon = new ImageIcon(img);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JButton hotelButton = createButton(primaryColor, Color.WHITE, hotelIcon, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openHotelMenu(hotelName); // Use final variable here
                }
            });
            // Set the icon alignment to center and scale the icon to fill the button
            hotelButton.setIconTextGap(0);
            hotelButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            hotelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            if (hotelIcon != null) {
                hotelButton.setIcon(hotelIcon);
            }
            hotelPanel.add(hotelButton);
        }
        return hotelPanel;
    }

    // Method to resize ImageIcon
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void openCart() {
        CartManager c = new CartManager();
        c.setVisible(true);
        c.displayCartContents();
    }

    private void openOrderDetails() {
        
        JOptionPane.showMessageDialog(this, "Opening OrderDetails...", "Order Details", JOptionPane.INFORMATION_MESSAGE);
       
    OrderDetails orderDetailsFrame = new OrderDetails();
    orderDetailsFrame.setVisible(true);


    }

    private void openAbout() {
        JOptionPane.showMessageDialog(this, "This is the Foodzee application.", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logging out...", "Logout", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the current frame
            // Open the login frame
            LoginFrame l = new LoginFrame();
            dispose();
        }
    }

    // Method to create user profile panel
    private JPanel createUserProfilePanel(Color primaryColor, Color secondaryColor, ImageIcon logoIcon) {
        JPanel userProfilePanel = new JPanel(new BorderLayout());
        userProfilePanel.setBackground(primaryColor);

        // Logo in the top left corner
        JLabel smallLogoLabel = new JLabel();
        try {
            Image scaledImage = logoIcon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
            ImageIcon smallScaledLogoIcon = new ImageIcon(scaledImage);
            smallLogoLabel.setIcon(smallScaledLogoIcon);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        label.setText("FOODZEE");
        userProfilePanel.add(label);
        userProfilePanel.add(smallLogoLabel, BorderLayout.WEST);

        // Buttons in the top right corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cartButton = createButton1("Cart", primaryColor, Color.WHITE, e -> openCart());
        JButton trackButton = createButton1("Track", primaryColor, Color.WHITE, e -> openTrack());

        buttonPanel.add(cartButton);
        buttonPanel.add(trackButton);
        userProfilePanel.add(buttonPanel, BorderLayout.EAST);

        // Search Bar

        searchBar = new JTextField(20);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 16));
        searchBar.setPreferredSize(new Dimension(200, 30)); // Adjusting the size of the search bar
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(primaryColor);
        searchButton.setForeground(Color.WHITE); // Change text color to white
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHotels();
            }
        });
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Center aligning the search components
        searchPanel.setBackground(secondaryColor);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        userProfilePanel.add(searchPanel, BorderLayout.CENTER); // Adding search bar to the center of user profile panel

        return userProfilePanel;
    }

    // Method to search hotels based on the text entered in the search bar
    private void searchHotels() {
        String searchText = searchBar.getText().trim(); // Trim to remove leading and trailing spaces
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Empty Search", JOptionPane.INFORMATION_MESSAGE);
            return; // Exit the method if the search text is empty
        }
        // Query the database to search for hotels based on the search text
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT hotel_name FROM menu_items WHERE item_name LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + searchText + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    listModel.addElement(resultSet.getString("hotel_name"));
                }
                if (listModel.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No matching hotels found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JList<String> hotelList = new JList<>(listModel);
                    hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    hotelList.addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting()) {
                            String selectedHotel = hotelList.getSelectedValue();
                            if (selectedHotel != null) {
                                openHotelMenu(selectedHotel); // Call the method to open the hotel page
                            }
                        }
                    });
                    JScrollPane scrollPane = new JScrollPane(hotelList);
                    JOptionPane.showMessageDialog(this, scrollPane, "Search Results", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to open the menu of a selected hotel
    private void openHotelMenu(String hotelName) {
        switch (hotelName) {
            case "Madurai Mess":
                // Assuming MaduraiMess is a class representing the menu of Madurai Mess hotel
                MaduraiMess maduraiMess = new MaduraiMess();
                maduraiMess.setVisible(true);
                break;
            case "Loss Pollus Hermanus":
                // Assuming LossPollus is a class representing the menu of Loss Pollus Hermanus hotel
                LossPollus lossPollus = new LossPollus();
                lossPollus.setVisible(true);
                break;
            case "CAFEDAY":
                CAFEDAY cafeDay = new CAFEDAY();
                cafeDay.setVisible(true);
                break;
            case "Saravana Bhavan":
                SaravanaBhavan saravanaBhavan = new SaravanaBhavan();
                saravanaBhavan.setVisible(true);
                break;
            // Add cases for other hotels as needed
            default:
                JOptionPane.showMessageDialog(this, "No menu available for: " + hotelName, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemberFrame::new);
    }

  public JFrame trackFrame;

private void openTrack() {
    boolean isOrderStatusY = isOrderStatusY();
    boolean isOrderStatusC = isOrderStatusC();

    if (isOrderStatusY) {
        SwingUtilities.invokeLater(() -> {
            trackFrame = new JFrame("Tracking Progress");
            trackFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            trackFrame.setSize(800, 600);
            trackFrame.setLocationRelativeTo(null);

            JFXPanel jfxPanel = new JFXPanel();
            trackFrame.add(jfxPanel);

            // Add a close button
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
                
                if (isOrderStatusC) {
                    JOptionPane.showMessageDialog(trackFrame, "Order Delivered.", "Track Order", JOptionPane.INFORMATION_MESSAGE);
                    trackFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(trackFrame, "Waiting for confirmation.", "Track Order", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            trackFrame.add(closeButton, BorderLayout.SOUTH); // Add the button to the frame
            trackFrame.setVisible(true);

            Platform.runLater(() -> {
                WebView webView = new WebView();
                WebEngine webEngine = webView.getEngine();
                webEngine.load("file:///E:/javaproject4/progress.html"); // Use file URI

                Scene scene = new Scene(webView);
                jfxPanel.setScene(scene);
            });
        });
    } else {
        JOptionPane.showMessageDialog(this, "Waiting for confirmation.", "Track Order", JOptionPane.INFORMATION_MESSAGE);
    }
}



    // Method to check the order status in the database
    private boolean isOrderStatusY() {
        boolean status = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT status FROM orderrs WHERE status = 'Y'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    status = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    private boolean isOrderStatusC()
    {
    boolean status = false;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT statusc FROM orderrs WHERE statusc = 'Y'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    status = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
