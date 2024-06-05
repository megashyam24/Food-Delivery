package javaproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class CartManager extends JFrame {
    private JTextArea cartTextArea;
    private Map<String, Integer> cart;
    private Connection conn;
    private JButton placeOrderButton;
    private JButton clearCartButton;
    private int orderIdCounter=1; 
    private double totalAmount = 0;
    private final String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Your database URL
    private final String dbUser = "system"; // Database username
    private final String dbPassword = "karmegam"; // Database password

    public CartManager() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
        setTitle("Cart");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());

        // Cart items display area
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("Items in Cart"));
        cartTextArea = new JTextArea();
        cartTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        // Total amount label
        JLabel totalLabel = new JLabel();
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalLabel);

        // Place order button
        placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderIdCounter++;
                try {
                    placeOrder(totalAmount);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CartManager.this, "Failed to place the order. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Clear cart button
        clearCartButton = new JButton("Clear Cart");
        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify the action with multiple confirmations
                int confirm1 = JOptionPane.showConfirmDialog(CartManager.this, "Are you sure you want to clear the cart?", "Clear Cart Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm1 == JOptionPane.YES_OPTION) {
                    int confirm2 = JOptionPane.showConfirmDialog(CartManager.this, "This action will remove all items from the cart. Are you absolutely sure?", "Final Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm2 == JOptionPane.YES_OPTION) {
                        // Truncate the contents of the cart table
                        try {
                            String truncateQuery = "TRUNCATE TABLE cart";
                            PreparedStatement truncateStatement = conn.prepareStatement(truncateQuery);
                            truncateStatement.executeUpdate();
                            truncateStatement.close();
                            cart.clear();
                            cartTextArea.setText("\n\n\n\n\n  \tOooho empty.....       ");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(CartManager.this, "Failed to clear the cart. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        // Online payment button
        JButton onlinePaymentButton = new JButton("Online Payment");
        onlinePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = "mega"; // Assuming you have the username of the currently logged in user
                    int memberId = getMemberId(username);

                    // Fetch the balance for the member from the orderrs table
                    String balanceQuery = "SELECT BAL FROM members WHERE ID = ?";
                    PreparedStatement balanceStatement = conn.prepareStatement(balanceQuery);
                    balanceStatement.setInt(1, memberId);
                    ResultSet balanceResultSet = balanceStatement.executeQuery();

                    if (balanceResultSet.next()) {
                        double balance = balanceResultSet.getDouble("BAL");

                        // Check if the balance is sufficient for the payment
                        if (balance >= totalAmount) {
                            // Deduct the total amount from the balance
                            double newBalance = balance - totalAmount;

                            // Update the balance in the orderrs table
                            String updateBalanceQuery = "UPDATE members SET BAL = ? WHERE ID = ?";
                            PreparedStatement updateBalanceStatement = conn.prepareStatement(updateBalanceQuery);
                            updateBalanceStatement.setDouble(1, newBalance);
                            updateBalanceStatement.setInt(2, memberId);
                            updateBalanceStatement.executeUpdate();
                            updateBalanceStatement.close();

                            // Place the order with deduction
                            placeOrder(totalAmount);
                        } else {
                            JOptionPane.showMessageDialog(CartManager.this, "Insufficient balance for online payment.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(CartManager.this, "Balance not found for the member.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    balanceResultSet.close();
                    balanceStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CartManager.this, "Failed to process online payment. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to content pane
        contentPane.add(cartPanel, BorderLayout.CENTER);
        contentPane.add(totalPanel, BorderLayout.SOUTH);
        contentPane.add(placeOrderButton, BorderLayout.NORTH);
        contentPane.add(clearCartButton, BorderLayout.WEST);
        contentPane.add(onlinePaymentButton, BorderLayout.EAST);

        setContentPane(contentPane);
        cart = new HashMap<>(); // Initialize the cart
    }

private void placeOrder(double amount) throws SQLException {
    // Prepare the SQL statement to insert into orderrs
    String insertQuery = "INSERT INTO orderrs (order_id, member_id, item_id, quantity, order_time, bill_amount) " +
                         "SELECT ?, ?, item_id, quantity, CURRENT_TIMESTAMP, ? FROM cart";

    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

    // Get the member_id from the currently logged in user
    String username = "mega"; // Assuming you have the username of the currently logged in user
    int memberId = getMemberId(username);
    System.out.println("Member ID: " + memberId);

    // Set the parameters in the prepared statement
    insertStatement.setInt(1, orderIdCounter);
    insertStatement.setInt(2, memberId); // Set the member ID
    insertStatement.setDouble(3, amount); // Set the total amount as BILL_AMOUNT

    // Execute the insert statement
    int rowsInserted = insertStatement.executeUpdate();
    System.out.println("Rows inserted into orderrs: " + rowsInserted);

    // Close the statement
    insertStatement.close();

    // Insert into order_details table
    String insertDetailsQuery = "INSERT INTO order_details (oid, order_id, member_id, item_id, quantity, order_time, bill_amount) " +
                                "SELECT oid, ?, member_id, item_id, quantity, order_time, bill_amount FROM orderrs WHERE order_id = ?";

    PreparedStatement insertDetailsStatement = conn.prepareStatement(insertDetailsQuery);

    // Set the parameters in the prepared statement for order_details
    insertDetailsStatement.setInt(1, orderIdCounter);
    insertDetailsStatement.setInt(2, orderIdCounter);

    // Execute the insert statement
    int detailsRowsInserted = insertDetailsStatement.executeUpdate();
    System.out.println("Rows inserted into order_details: " + detailsRowsInserted);

    // Close the statement
    insertDetailsStatement.close();

    // Fetch and display the balance
    double balance = fetchBalance(memberId);
    JOptionPane.showMessageDialog(CartManager.this, "Order placed successfully! Your balance is: " + balance, "Success", JOptionPane.INFORMATION_MESSAGE);

    // Clear the cart after placing the order
    clearCart();
}
private void placeOrder() throws SQLException {
    // Prepare the SQL statement to insert into orderrs
    String insertQuery = "INSERT INTO orderrs (order_id, member_id, item_id, quantity, order_time, bill_amount) " +
                         "SELECT ?, ?, item_id, quantity, CURRENT_TIMESTAMP, ? FROM cart";

    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

    // Get the member_id from the currently logged in user
    String username = "mega"; // Assuming you have the username of the currently logged in user
    int memberId = getMemberId(username);
    System.out.println("Member ID: " + memberId);

    // Set the parameters in the prepared statement
    insertStatement.setInt(1, orderIdCounter);
    insertStatement.setInt(2, memberId); // Set the member ID
    // Set the total amount as BILL_AMOUNT

    // Execute the insert statement
    int rowsInserted = insertStatement.executeUpdate();
    System.out.println("Rows inserted into orderrs: " + rowsInserted);

    // Close the statement
    insertStatement.close();

    // Insert into order_details table
    String insertDetailsQuery = "INSERT INTO order_details (oid, order_id, member_id, item_id, quantity, order_time, bill_amount) " +
                                "SELECT oid, ?, member_id, item_id, quantity, order_time, bill_amount FROM orderrs WHERE order_id = ?";

    PreparedStatement insertDetailsStatement = conn.prepareStatement(insertDetailsQuery);

    // Set the parameters in the prepared statement for order_details
    insertDetailsStatement.setInt(1, orderIdCounter);
    insertDetailsStatement.setInt(2, orderIdCounter);

    // Execute the insert statement
    int detailsRowsInserted = insertDetailsStatement.executeUpdate();
    System.out.println("Rows inserted into order_details: " + detailsRowsInserted);

    // Close the statement
    insertDetailsStatement.close();

    // Fetch and display the balance
    double balance = fetchBalance(memberId);
    JOptionPane.showMessageDialog(CartManager.this, "Order placed successfully! Your balance is: " + balance, "Success", JOptionPane.INFORMATION_MESSAGE);

    // Clear the cart after placing the order
    clearCart();
}
private double fetchBalance(int memberId) throws SQLException {
    double balance = 0;
    String balanceQuery = "SELECT BAL FROM members WHERE ID = ?";
    try (PreparedStatement balanceStatement = conn.prepareStatement(balanceQuery)) {
        balanceStatement.setInt(1, memberId);
        try (ResultSet balanceResultSet = balanceStatement.executeQuery()) {
            if (balanceResultSet.next()) {
                balance = balanceResultSet.getDouble("BAL");
            }
        }
    }
    return balance;
}

    public void displayCartContents() {
        StringBuilder message = new StringBuilder("Items in Cart:\n");

        try {
            String query = "SELECT item_name, MAX(quantity) as quantity, price FROM cart INNER JOIN menu_items ON cart.item_id = menu_items.item_id GROUP BY item_name, price";

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                double itemTotal = quantity * price;
                totalAmount += itemTotal;
                message.append(itemName).append(": ").append(quantity).append(" x ").append(price).append(" = ").append(itemTotal).append("\n");
            }
            message.append("\nTotal Amount: ").append(totalAmount);
            cartTextArea.setText(message.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String itemName, int quantity) {
        // Add the item to the cart
        if (cart.containsKey(itemName)) {
            int currentQuantity = cart.get(itemName);
            cart.put(itemName, currentQuantity + quantity);
        } else {
            // Otherwise, add the item to the cart with the specified quantity
            cart.put(itemName, quantity);
        }
    }

    public void clearCart() {
        // Clear the cart
        try {
            String truncateQuery = "TRUNCATE TABLE cart";
            PreparedStatement truncateStatement = conn.prepareStatement(truncateQuery);
            truncateStatement.executeUpdate();
            truncateStatement.close();
            cart.clear();
            cartTextArea.setText("Cart is cleared.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to clear the cart. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getMemberId(String username) throws SQLException {
        int memberId = -1;
        String query = "SELECT id FROM members WHERE username = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    memberId = resultSet.getInt("id");
                }
            }
        }
        return memberId;
    }

    private int getItemIdByName(String itemName) throws SQLException {
        int itemId = 0;
        String sql = "SELECT item_id FROM menu_items WHERE item_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    itemId = rs.getInt("item_id");
                }
            }
        }
        return itemId;
    }

    public static void main(String[] args) {
        // Create an instance of CartManager
        CartManager cartManager = new CartManager();
        // Display the cart contents
        cartManager.displayCartContents();
        // Make the JFrame visible
        cartManager.setVisible(true);
        // Wait for the JFrame to close
        cartManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
