/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.FacultyBean;
import BeanClasses.HostelsBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;
import EnDeCoder.Encoder;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danish
 */
public class HostelFrame extends javax.swing.JFrame {
   
    /**
     * Creates new form HostelFrame
     */
    public HostelFrame() {
        initComponents();
                messComboBox.addItem("YES");
                messComboBox.addItem("NO");
                getHostel();
    }
private void getHostel(){
	try{
		java.util.Vector v=DatabaseManager.getHostel();		
                        DefaultTableModel model=(DefaultTableModel)hostelTable.getModel();
                   tableClear();
                Vector vector =null;
               for(int i=0; i<v.size(); i++){
                   HostelsBean bean= (HostelsBean)v.elementAt(i);
                     vector = new Vector();
                     
                     vector.addElement(bean.getHotelId());
                     vector.addElement(bean.getHostelName());
                     vector.addElement(bean.getNumOfRooms());
                     vector.addElement(bean.getNumOfBathrooms());
                     vector.addElement(bean.getNumOfStories());
                     vector.addElement(bean.getMess());
                     vector.addElement(bean.getStdPerRoom());
                     vector.addElement(bean.getRemarks());
                     model.addRow(vector);
                     
                }   
      }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
    }    
	
}//end Method 
    private void addHostel(){
        
    String hostelName=hostelNameTextField.getText();
    int numOfRooms=Integer.parseInt(""+noOfRoomsTextField.getText());
    int numOfBathrooms=Integer.parseInt(""+noOfBathroomsTextField.getText());
    int numOfStories=Integer.parseInt(""+noOfStoriesTextField.getText());
    String mess=(String)messComboBox.getSelectedItem();
     mess = Encoder.typeEncode(mess);
    int stdPerRoom=Integer.parseInt(""+stdPerRoomTextField.getText());
    String remarks=remarksTextArea.getText();

    try{
               	int rows=DatabaseManager.addHostel(hostelName,numOfRooms,numOfBathrooms,numOfStories,mess,stdPerRoom,remarks);
                
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getHostel();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end 
private void deleteHostel(){
     int row =hostelTable.getSelectedRow();
     String cell=hostelTable.getModel().getValueAt(row, 0).toString();
    try{
           int rows = DatabaseManager.deleteHostel(cell);
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+"Rrecord Removed.");
           getHostel();
           clear();
           }
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }
}

private void updateHostel(){
     int row =hostelTable.getSelectedRow();
     String hostelId=hostelTable.getModel().getValueAt(row, 0).toString();    
    
    String hostelName=hostelNameTextField.getText();
    int numOfRooms=Integer.parseInt(""+noOfRoomsTextField.getText());
    int numOfBathrooms=Integer.parseInt(""+noOfBathroomsTextField.getText());
    int numOfStories=Integer.parseInt(""+noOfStoriesTextField.getText());
    String mess=(String)messComboBox.getSelectedItem();
     mess = Encoder.typeEncode(mess);
    int stdPerRoom=Integer.parseInt(""+stdPerRoomTextField.getText());
    String remarks=remarksTextArea.getText();

     try{
	int rows=DatabaseManager.updateHostel(hostelId,hostelName,numOfRooms,numOfBathrooms,numOfStories,mess,stdPerRoom,remarks);
	if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getHostel();
	    clear();
        	}
     }catch(Exception e){
         e.printStackTrace();
         javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
     } 
}
private void clear(){
        hostelIdTextField.setText("");
        hostelNameTextField.setText("");
        noOfRoomsTextField.setText("");
        noOfBathroomsTextField.setText("");
        noOfStoriesTextField.setText("");
        stdPerRoomTextField.setText("");
        remarksTextArea.setText("");
}
private void tableClear(){
    DefaultTableModel model=(DefaultTableModel)hostelTable.getModel();
    while(model.getRowCount()>0){
        for(int i=0;i<model.getRowCount();++i){
            model.removeRow(i);
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hostelLabel = new javax.swing.JLabel();
        hostelIdLabel = new javax.swing.JLabel();
        hostelNameLabel = new javax.swing.JLabel();
        noOfRoomsLabel = new javax.swing.JLabel();
        noOfBathroomsLabel = new javax.swing.JLabel();
        noOfStoriesLabel = new javax.swing.JLabel();
        messLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hostelTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        hostelIdTextField = new javax.swing.JTextField();
        hostelNameTextField = new javax.swing.JTextField();
        noOfRoomsTextField = new javax.swing.JTextField();
        noOfBathroomsTextField = new javax.swing.JTextField();
        noOfStoriesTextField = new javax.swing.JTextField();
        messComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        stdPerRoomTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        hostelLabel.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        hostelLabel.setText("HOSTEL");
        getContentPane().add(hostelLabel);
        hostelLabel.setBounds(680, 10, 250, 70);

        hostelIdLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelIdLabel.setText("HOSTEL ID");
        getContentPane().add(hostelIdLabel);
        hostelIdLabel.setBounds(130, 110, 132, 29);

        hostelNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelNameLabel.setText("HOSTEL NAME");
        getContentPane().add(hostelNameLabel);
        hostelNameLabel.setBounds(90, 180, 173, 29);

        noOfRoomsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfRoomsLabel.setText("NO. OF ROOMS");
        getContentPane().add(noOfRoomsLabel);
        noOfRoomsLabel.setBounds(80, 250, 180, 29);

        noOfBathroomsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfBathroomsLabel.setText("NO. OF BATHROOMS");
        getContentPane().add(noOfBathroomsLabel);
        noOfBathroomsLabel.setBounds(20, 330, 245, 29);

        noOfStoriesLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfStoriesLabel.setText("NO. OF STORIES");
        getContentPane().add(noOfStoriesLabel);
        noOfStoriesLabel.setBounds(70, 410, 198, 29);

        messLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        messLabel.setText("MESS");
        getContentPane().add(messLabel);
        messLabel.setBounds(200, 490, 66, 29);

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(150, 630, 118, 29);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(310, 820, 180, 70);

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(520, 820, 160, 70);

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton);
        clearButton.setBounds(700, 820, 170, 70);

        backButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backButton.setText("BACK");
        getContentPane().add(backButton);
        backButton.setBounds(900, 820, 180, 70);

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(1110, 820, 180, 70);

        hostelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HOSTEL ID", "HOSTEL", "NO. OF ROOMS", "NO. OF BATHROOMS", "NO. OF STORIES", "MESS", "STD PER ROOM", "REMARKS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hostelTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hostelTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(hostelTable);
        if (hostelTable.getColumnModel().getColumnCount() > 0) {
            hostelTable.getColumnModel().getColumn(5).setPreferredWidth(20);
            hostelTable.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(830, 100, 990, 700);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(310, 610, 510, 180);
        getContentPane().add(hostelIdTextField);
        hostelIdTextField.setBounds(310, 100, 270, 50);
        getContentPane().add(hostelNameTextField);
        hostelNameTextField.setBounds(310, 170, 500, 50);
        getContentPane().add(noOfRoomsTextField);
        noOfRoomsTextField.setBounds(310, 240, 270, 50);
        getContentPane().add(noOfBathroomsTextField);
        noOfBathroomsTextField.setBounds(310, 320, 270, 50);
        getContentPane().add(noOfStoriesTextField);
        noOfStoriesTextField.setBounds(310, 400, 270, 50);

        getContentPane().add(messComboBox);
        messComboBox.setBounds(310, 480, 270, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("STD PER ROOM");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 560, 190, 29);
        getContentPane().add(stdPerRoomTextField);
        stdPerRoomTextField.setBounds(310, 550, 270, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addHostel();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteHostel();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateHostel();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void hostelTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hostelTableMouseClicked
     int row =hostelTable.getSelectedRow();
     String hostelId=hostelTable.getModel().getValueAt(row, 0).toString();        
     HostelsBean bean=DatabaseManager.getHostelById(hostelId);
         hostelIdTextField.setText(""+bean.getHotelId());
         hostelNameTextField.setText(bean.getHostelName());
         noOfRoomsTextField.setText(""+bean.getNumOfRooms());
         noOfBathroomsTextField.setText(""+bean.getNumOfBathrooms());
         noOfStoriesTextField.setText(""+bean.getNumOfStories());
         String mess=Decoder.messDecode(bean.getMess());
         stdPerRoomTextField.setText(""+bean.getStdPerRoom());
         remarksTextArea.setText(bean.getRemarks());
            
    for(int i=0; i<messComboBox.getItemCount(); i++){
	String item=(String)messComboBox.getItemAt(i);
	if(item.equals(bean.getMess()))
		messComboBox.setSelectedItem(item);
                }
    }//GEN-LAST:event_hostelTableMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HostelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HostelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HostelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HostelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HostelFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel hostelIdLabel;
    private javax.swing.JTextField hostelIdTextField;
    private javax.swing.JLabel hostelLabel;
    private javax.swing.JLabel hostelNameLabel;
    private javax.swing.JTextField hostelNameTextField;
    private javax.swing.JTable hostelTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox messComboBox;
    private javax.swing.JLabel messLabel;
    private javax.swing.JLabel noOfBathroomsLabel;
    private javax.swing.JTextField noOfBathroomsTextField;
    private javax.swing.JLabel noOfRoomsLabel;
    private javax.swing.JTextField noOfRoomsTextField;
    private javax.swing.JLabel noOfStoriesLabel;
    private javax.swing.JTextField noOfStoriesTextField;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JTextField stdPerRoomTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
