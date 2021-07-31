/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.DepartmentBean;
import BeanClasses.FacultyBean;
import BeanClasses.HostelsBean;
import BeanClasses.ListItem;
import BeanClasses.WardenBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;
import EnDeCoder.Encoder;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danish
 */
public class WardenFrame extends javax.swing.JFrame {

    /**
     * Creates new form WardenFrame
     */
    public WardenFrame() {
        initComponents();
        getFaculty();
        getHostel();
      
    }
      private void getFaculty(){
	try{
		Vector v=DatabaseManager.getFaculty();
		facultyComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			facultyComboBox.addItem(v.elementAt(i));

	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end method 
       private void getDepartment(){
	try{
                FacultyBean bean =(FacultyBean)facultyComboBox.getSelectedItem();
                //System.out.println(bean);
                if(bean==null)return;
                Vector v=DatabaseManager.getDepartment(bean.getFacId());
		deptComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			deptComboBox.addItem(v.elementAt(i));

	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end method  
 private void getHostel(){
	try{
                Vector v=DatabaseManager.getHostel();
		hostelComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++){
			hostelComboBox.addItem(v.elementAt(i));
                }
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end method        
private void getWarden(){
        DepartmentBean deptBean=(DepartmentBean)deptComboBox.getSelectedItem();
        //HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();
	try{
		java.util.Vector v=DatabaseManager.getWarden(deptBean.getDeptId());		
                        DefaultTableModel model=(DefaultTableModel)wardenTable.getModel();
                   tableClear();
                Vector vector =null;
               for(int i=0; i<v.size(); i++){
                   WardenBean wardenBean= (WardenBean)v.elementAt(i);
                     vector = new Vector();
                     
                     vector.addElement(wardenBean.getWardenId());
                     vector.addElement(wardenBean.getWardenName());
                     vector.addElement(wardenBean.getAppointmentDate());
                     vector.addElement(wardenBean.getCellNum());
                     vector.addElement(wardenBean.getRemarks());
                     model.addRow(vector);
                     
                }   
      }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
    }    
	
}//end Method 
   private void addWarden(){
        DepartmentBean deptBean=(DepartmentBean)deptComboBox.getSelectedItem();
        if(deptBean==null)return;
        HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();  
        if(hostelBean==null)return;
    String wardenName=wardenNameTextField.getText();
    //String appointmentDate= Decoder.getDateFormat(appointmentDateTextField.getText());
    String appointmentDate=Decoder.getDateFormat(appointmentDateChooser.getDate());
    int cellNo=Integer.parseInt(""+cellNoTextField.getText());
    String remarks=remarksTextArea.getText();

    try{
               	int rows=DatabaseManager.addWarden(deptBean.getDeptId(),hostelBean.getHotelId(),wardenName,appointmentDate,cellNo,remarks);
                
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getWarden();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end 
private void deleteWarden(){
     int row =wardenTable.getSelectedRow();
     String wardenId=wardenTable.getModel().getValueAt(row, 0).toString();
    try{
           int rows = DatabaseManager.deleteWarden(wardenId);
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+" Rrecord Removed.");
           getWarden();
           clear();
           }
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }
}   
private void updateWarden(){
        DepartmentBean deptBean=(DepartmentBean)deptComboBox.getSelectedItem();
        if(deptBean==null)return;
        HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();  
        if(hostelBean==null)return;
        
     int row =wardenTable.getSelectedRow();
     String wardenId=wardenTable.getModel().getValueAt(row, 0).toString(); 
     
     String wardenName=wardenNameTextField.getText();
    //String appointmentDate= Decoder.getDateFormat(appointmentDateTextField.getText());
    String appointmentDate=Decoder.getDateFormat(appointmentDateChooser.getDate());
    int cellNo=Integer.parseInt(""+cellNoTextField.getText());
    String remarks=remarksTextArea.getText();

     try{
	int rows=DatabaseManager.updateWarden(deptBean.getDeptId(),hostelBean.getHotelId(),wardenId,wardenName,appointmentDate,cellNo,remarks);
	if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getWarden();
	    clear();
        	}
     }catch(Exception e){
         e.printStackTrace();
         javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
     } 
}
private void clear(){
        wardenIdTextField.setText("");
        wardenNameTextField.setText("");
        appointmentDateChooser.setDateFormatString("");
        remarksTextArea.setText("");
}
private void tableClear(){
    DefaultTableModel model=(DefaultTableModel)wardenTable.getModel();
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

        wardenLabel = new javax.swing.JLabel();
        facultyLabel = new javax.swing.JLabel();
        deptLabel = new javax.swing.JLabel();
        hostelLabel = new javax.swing.JLabel();
        wardenIdLabel = new javax.swing.JLabel();
        wardenNameLabel = new javax.swing.JLabel();
        appointmentDateLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        deptComboBox = new javax.swing.JComboBox();
        hostelComboBox = new javax.swing.JComboBox();
        wardenIdTextField = new javax.swing.JTextField();
        wardenNameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        wardenTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cellNoTextField = new javax.swing.JTextField();
        appointmentDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        wardenLabel.setFont(new java.awt.Font("Times New Roman", 0, 60)); // NOI18N
        wardenLabel.setText("WARDEN");
        getContentPane().add(wardenLabel);
        wardenLabel.setBounds(690, 20, 270, 80);

        facultyLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        facultyLabel.setText("FACULTY");
        getContentPane().add(facultyLabel);
        facultyLabel.setBounds(150, 130, 109, 40);

        deptLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        deptLabel.setText("DEPARTMENT");
        getContentPane().add(deptLabel);
        deptLabel.setBounds(90, 220, 167, 40);

        hostelLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelLabel.setText("HOSTEL");
        getContentPane().add(hostelLabel);
        hostelLabel.setBounds(160, 300, 95, 40);

        wardenIdLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        wardenIdLabel.setText("WARDEN ID");
        getContentPane().add(wardenIdLabel);
        wardenIdLabel.setBounds(110, 380, 150, 40);

        wardenNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        wardenNameLabel.setText("WARDEN NAME");
        getContentPane().add(wardenNameLabel);
        wardenNameLabel.setBounds(70, 440, 188, 40);

        appointmentDateLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        appointmentDateLabel.setText("APPOINTMENT DATE");
        getContentPane().add(appointmentDateLabel);
        appointmentDateLabel.setBounds(10, 520, 260, 40);

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(140, 660, 118, 40);

        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(facultyComboBox);
        facultyComboBox.setBounds(300, 120, 620, 50);

        deptComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(deptComboBox);
        deptComboBox.setBounds(300, 210, 620, 50);

        hostelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostelComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(hostelComboBox);
        hostelComboBox.setBounds(300, 300, 620, 50);
        getContentPane().add(wardenIdTextField);
        wardenIdTextField.setBounds(300, 370, 330, 50);
        getContentPane().add(wardenNameTextField);
        wardenNameTextField.setBounds(300, 440, 330, 50);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(300, 650, 620, 150);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(300, 860, 140, 60);

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(470, 860, 160, 60);

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        clearButton.setText("CLEAR");
        getContentPane().add(clearButton);
        clearButton.setBounds(660, 860, 160, 60);

        backButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        backButton.setText("BACK");
        getContentPane().add(backButton);
        backButton.setBounds(850, 860, 150, 60);

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(1030, 860, 160, 60);

        wardenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "WARDEN ID", "WARDEN NAME", "APPOINTMENT DATE", "CELL NO", "REMARKS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        wardenTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wardenTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(wardenTable);
        if (wardenTable.getColumnModel().getColumnCount() > 0) {
            wardenTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            wardenTable.getColumnModel().getColumn(3).setResizable(false);
            wardenTable.getColumnModel().getColumn(4).setResizable(false);
            wardenTable.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(940, 120, 840, 680);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CELL NO.");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 590, 120, 29);
        getContentPane().add(cellNoTextField);
        cellNoTextField.setBounds(300, 580, 330, 50);
        getContentPane().add(appointmentDateChooser);
        appointmentDateChooser.setBounds(300, 510, 330, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        getDepartment();        
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void deptComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptComboBoxActionPerformed
            getWarden();
    }//GEN-LAST:event_deptComboBoxActionPerformed

    private void hostelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostelComboBoxActionPerformed
        //getWarden();
    }//GEN-LAST:event_hostelComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addWarden();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateWarden();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
       deleteWarden();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void wardenTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wardenTableMouseClicked
     int row =wardenTable.getSelectedRow();
     String wardenId=wardenTable.getModel().getValueAt(row, 0).toString();
             
     WardenBean bean=DatabaseManager.getWardenById(wardenId);
         wardenIdTextField.setText(""+bean.getWardenId());
         wardenNameTextField.setText(bean.getWardenName());
         appointmentDateChooser.setDate(bean.getAppointmentDate());
         cellNoTextField.setText(""+bean.getCellNum());
         remarksTextArea.setText(bean.getRemarks());
         

   
    }//GEN-LAST:event_wardenTableMouseClicked

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
            java.util.logging.Logger.getLogger(WardenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WardenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WardenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WardenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WardenFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private com.toedter.calendar.JDateChooser appointmentDateChooser;
    private javax.swing.JLabel appointmentDateLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField cellNoTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox deptComboBox;
    private javax.swing.JLabel deptLabel;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JLabel facultyLabel;
    private javax.swing.JComboBox hostelComboBox;
    private javax.swing.JLabel hostelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel wardenIdLabel;
    private javax.swing.JTextField wardenIdTextField;
    private javax.swing.JLabel wardenLabel;
    private javax.swing.JLabel wardenNameLabel;
    private javax.swing.JTextField wardenNameTextField;
    private javax.swing.JTable wardenTable;
    // End of variables declaration//GEN-END:variables
}
