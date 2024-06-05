/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaproject;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author megas
 */
public class CaptainFrame extends javax.swing.JFrame {
 private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "karmegam";
    private int orderId;
    private String memberName;
    private String itemName;
    private int maxQuantity;
    private double maxBillAmount;
    private String maxOrderTime;
    private Object conn;
    private String memberEmail;
    

    /**
     * Creates new form CaptainFrame
     */
    public CaptainFrame() {
        initComponents();
        displayOrderDetails();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton1.setText("ACCEPT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 680, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon("E:\\javaproject4\\FOODZEE1.jpg")); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setText("                                                                     FOODZEE DELIVERY PARTNER PAGE");

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton2.setText("LOGOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel4.setText("                                                          NEW ORDER");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                        .addGap(111, 111, 111)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 610, 520));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 330, 310));

        jButton3.setText("jButton3");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton4.setText("TASK COMPLETED");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 240, 330, 40));

        jButton5.setBackground(new java.awt.Color(255, 102, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton5.setText("RECEIVED CASH");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 330, 130, -1));

        jLabel5.setBackground(new java.awt.Color(255, 153, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setText("                    RATINGS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(32, 32, 32)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 190, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon("E:\\javaproject4\\background.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1350, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void displayOrderDetails() {
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "SELECT o.order_id, m.USERNAME AS member_name, m.ADDRESS AS member_address, mi.ITEM_NAME, MAX(o.quantity) AS max_quantity, MAX(o.BILL_AMOUNT) AS max_bill_amount, MAX(o.order_time) AS max_order_time " +
                     "FROM orderrs o " +
                     "JOIN members m ON o.member_id = m.ID " +
                     "JOIN menu_items mi ON o.item_id = mi.ITEM_ID " +
                     "GROUP BY o.order_id, m.USERNAME, m.ADDRESS, mi.ITEM_NAME";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        // Clear the text area before displaying new data
        jTextArea2.setText("");
        int prevOrderId = 0;
        String prevMemberName = "";
        String prevMemberAddress = "";
        StringBuilder orderDetails = new StringBuilder();
        while (rs.next()) {
            // Append order details to the StringBuilder
            int orderId = rs.getInt("order_id");
            String memberName = rs.getString("member_name");
            String memberAddress = rs.getString("member_address");
            String itemName = rs.getString("ITEM_NAME");
            int maxQuantity = rs.getInt("max_quantity");
            double maxBillAmount = rs.getDouble("max_bill_amount");
            String maxOrderTime = rs.getTimestamp("max_order_time").toString();
            
            // Append order ID and member name if they are different from the previous record
            if (orderId != prevOrderId || !memberName.equals(prevMemberName)) {
                if (orderDetails.length() > 0) {
                    orderDetails.append("\n"); // Add a newline before starting a new order
                }
                orderDetails.append(String.format("Order ID: %-5d\nOrder Placed by %-8s", orderId, memberName));
                
                // Append member address if it's different from the previous record
                if (!memberAddress.equals(prevMemberAddress)) {
                    orderDetails.append(String.format("from %-50s\n", memberAddress));
                    prevMemberAddress = memberAddress;
                }
                
                // Append bill amount and order time only if they are not already appended for the current order
                orderDetails.append(String.format("Bill Amount: %.2f\n", maxBillAmount));
                orderDetails.append(String.format("Order Time: %-24s\n", maxOrderTime));
            }
            
            // Append item details if not null
            if (itemName != null && maxQuantity != 0 ) {
                orderDetails.append(String.format("%s", itemName));
                orderDetails.append(String.format(" X %d\n", maxQuantity));
            }
            if (maxQuantity != 0) {
                
            }
            
            // Update previous order ID and member name
            prevOrderId = orderId;
            prevMemberName = memberName;
        }
        // Set the font size and style
        Font font = new Font("Arial", Font.BOLD, 18); // Example: Arial, bold, size 12
        jTextArea2.setFont(font);
        
        // Append the constructed order details to the text area
        jTextArea2.append(orderDetails.toString());
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
    }
}

private int getMemberId() throws SQLException {
        int memberId = -1;
        String query = "SELECT id FROM members WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(query);
            String username = "mega";
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    memberId = resultSet.getInt("id");
                }
            }
        }
        return memberId;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  if (jTextArea2.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No new orders", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
  

    
    // Assuming you have already retrieved the member's email address
    String memberQuery = "SELECT EMAIL FROM MEMBERS WHERE ID = ?";
     try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            PreparedStatement memberStatement = conn.prepareStatement(memberQuery);
      int memberId = getMemberId();;
            memberStatement.setInt(1, memberId); // Using member ID from session
            ResultSet memberResultSet = memberStatement.executeQuery();
            if (memberResultSet.next()) {
                memberEmail = memberResultSet.getString("EMAIL");
            }
            memberResultSet.close();
            memberStatement.close();// Replace with the actual member's email address
     }
     catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
    }
    String captainName = "Ilyas"; // Replace with the actual captain's name

    // Define email properties
    String host = "smtp.gmail.com"; // Replace with your SMTP server host
    String username = "foodzeedelivery@gmail.com"; // Replace with your SMTP username
    String password = "ipgr rmcl fxeo knnj"; // Replace with your SMTP password
    String from = "foodzeedelivery@gmail.com"; // Replace with your email address
    String subject = "Order Accepted"; // Email subject
    String body = String.format("Dear Customer,\n\nYour order has been accepted and will be delivered by %s.\n\nThank you for choosing FoodZee!\n\nBest regards,\nThe FoodZee Team", captainName); // Email body

    // Send the email
    sendEmail(host, username, password, from, memberEmail, subject, body);

    // Display confirmation message
    JOptionPane.showMessageDialog(this, "Order Accepted. Email sent to the member.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

    // Find captain ID
    int captainId = findCaptainId(); // Assuming you have a method to find the captain ID

    // Define other necessary variables
    int orderId = 1; // Replace with actual order ID retrieval

    // Update the orderrs table to add captainId and status
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String updateSql = "UPDATE orderrs SET captainId = ?, status = ? WHERE ORDER_ID = ?";
        PreparedStatement updateStatement = conn.prepareStatement(updateSql);

        updateStatement.setInt(1, captainId); // Set the retrieved captain ID
        updateStatement.setString(2, "Y"); // Set status to 'Y'
        updateStatement.setInt(3, orderId); // Set the order ID

        // Execute the update statement
        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("The orderrs table was updated successfully.");
        } else {
            System.out.println("No rows were updated. Please check if the ORDER_ID exists.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
    }
    jTextArea2.setText("Order accepted and yet to be delivered");
    }//GEN-LAST:event_jButton1ActionPerformed
private void sendEmail(String host, String username, String password, String from, String to, String subject, String body) {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
        System.out.println("Email sent successfully.");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
// Method to find the captain ID
private int findCaptainId() {
    int captainId = 0; // Initialize captain ID
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "SELECT id FROM captains WHERE username = ?"; // Assuming captains table has a name column
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "ilyas"); // Provide the captain's name
        
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            captainId = rs.getInt("id");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
    }
    
    return captainId;
}
private int generateDeliveryId() {
    // Here you would implement logic to generate a unique delivery ID.
    // This could involve querying the database for the next available ID from a sequence,
    // retrieving the last ID and incrementing it, or using a UUID.
    // For simplicity, let's assume you have an auto-increment column in your database.

    // Generate a unique delivery ID, for example, by incrementing from the last ID
    int lastDeliveryId = getLastDeliveryId(); // Assuming you have a method to retrieve the last delivery ID
    return lastDeliveryId + 1;
}

private int getLastDeliveryId() {
    // This method would retrieve the last delivery ID from the database
    // For simplicity, let's assume you're querying it from a table named "delivery"
    // and the delivery IDs are stored in a column named "delivery_id"
    
    // Implement logic to retrieve the last delivery ID from the database
    // Example:
    int lastDeliveryId = 0; // Default value if no delivery IDs are found
    
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "SELECT MAX(delivery_id) FROM delivery";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            lastDeliveryId = resultSet.getInt(1);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
    }
    
    return lastDeliveryId;
}


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
        // Logout and open the LoginFrame
        JOptionPane.showMessageDialog(this, "Logging out...", "Logout", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the current frame
        // Open the login frame
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         if (jTextArea2.getText().isEmpty()) {
              JOptionPane.showMessageDialog(this, "No new Orders.");
           
        } else {
            jButton4.setText("TASK COMPLETED");
            // Update order status in the database
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                truncateOrdersTable();
                JOptionPane.showMessageDialog(this, "Order marked as completed.");
                displayOrderDetails(); // Refresh order details
                String username = "ilyas";
                int rating = getRating(username);
                jLabel6.setText(String.valueOf(rating));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed
private int getRating(String username) {
    int rating = 0; // Default value if rating is not found

    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "SELECT rating FROM captains WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                rating = resultSet.getInt("rating");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }

    return rating;
}

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       int orderIdToUpdate = 1; // Replace ... with the order ID you want to update
    
    // TODO add your handling code here:
    if (jTextArea2.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No new Orders.");
    } else {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Construct the SQL update statement
            String sql = "UPDATE orderrs SET STATUSC = 'Y' WHERE ORDER_ID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                // Set the ORDER_ID parameter in the prepared statement
                statement.setInt(1, orderIdToUpdate);
                // Execute the update statement
                int rowsUpdated = statement.executeUpdate();
                // Check if the update was successful
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Cash Received ");
                    jTextArea2.setText("Cash Received");
                    
                } else {
                    
                   
                    JOptionPane.showMessageDialog(this, "Failed to update order status.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton5ActionPerformed
public int status(int i)
{
 return 1;   
}
    private void truncateOrdersTable() {
    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
        String sql = "TRUNCATE TABLE orderrs";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        jTextArea2.setText("Error truncating orders table: " + e.getMessage());
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CaptainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaptainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaptainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaptainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaptainFrame().setVisible(true);
            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}