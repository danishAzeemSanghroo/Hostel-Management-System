/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

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
public class HostelPane extends javax.swing.JPanel {

    /**
     * Creates new form HostelPane
     */
    public HostelPane() {
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
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 102, 255));

        hostelLabel.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        hostelLabel.setText("HOSTEL");

        hostelIdLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelIdLabel.setText("HOSTEL ID");

        hostelNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelNameLabel.setText("HOSTEL NAME");

        noOfRoomsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfRoomsLabel.setText("NO. OF ROOMS");

        noOfBathroomsLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfBathroomsLabel.setText("NO. OF BATHROOMS");

        noOfStoriesLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        noOfStoriesLabel.setText("NO. OF STORIES");

        messLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        messLabel.setText("MESS");

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        remarksLabel.setText("REMARKS");

        hostelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HOSTEL ID", "HOSTEL", "ROOMS", "BATHROOMS", "STORIES", "MESS", "STD PER ROOM", "REMARKS"
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
            hostelTable.getColumnModel().getColumn(0).setPreferredWidth(15);
            hostelTable.getColumnModel().getColumn(1).setPreferredWidth(20);
            hostelTable.getColumnModel().getColumn(2).setResizable(false);
            hostelTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            hostelTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            hostelTable.getColumnModel().getColumn(4).setPreferredWidth(10);
            hostelTable.getColumnModel().getColumn(5).setPreferredWidth(5);
            hostelTable.getColumnModel().getColumn(7).setPreferredWidth(10);
        }

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("STD PER ROOM");

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add_1.jpg"))); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update_1.jpg"))); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear_1.jpg"))); // NOI18N
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete_1.jpg"))); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back_1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hostelIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(stdPerRoomTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(messComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 131, Short.MAX_VALUE)
                                        .addComponent(noOfStoriesTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(noOfBathroomsTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(noOfRoomsTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hostelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(hostelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(429, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addComponent(hostelIdLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(hostelNameLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(noOfRoomsLabel))
                        .addComponent(noOfBathroomsLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(noOfStoriesLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(messLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(130, 130, 130)
                            .addComponent(remarksLabel)))
                    .addGap(0, 1550, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(hostelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(hostelIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hostelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(noOfRoomsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(noOfBathroomsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(noOfStoriesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(messComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stdPerRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 115, Short.MAX_VALUE)
                    .addComponent(hostelIdLabel)
                    .addGap(41, 41, 41)
                    .addComponent(hostelNameLabel)
                    .addGap(41, 41, 41)
                    .addComponent(noOfRoomsLabel)
                    .addGap(51, 51, 51)
                    .addComponent(noOfBathroomsLabel)
                    .addGap(51, 51, 51)
                    .addComponent(noOfStoriesLabel)
                    .addGap(51, 51, 51)
                    .addComponent(messLabel)
                    .addGap(41, 41, 41)
                    .addComponent(jLabel1)
                    .addGap(41, 41, 41)
                    .addComponent(remarksLabel)
                    .addGap(0, 236, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addHostel();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateHostel();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteHostel();
    }//GEN-LAST:event_deleteButtonActionPerformed


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
