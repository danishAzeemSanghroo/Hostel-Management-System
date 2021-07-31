/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.DepartmentBean;
import BeanClasses.FacultyBean;
import BeanClasses.HostelsBean;
import BeanClasses.WardenBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danish
 */
public class WardenPanel extends javax.swing.JPanel {

    /**
     * Creates new form WardenPanel
     */
    public WardenPanel() {
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
        jScrollPane3 = new javax.swing.JScrollPane();
        wardenTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cellNoTextField = new javax.swing.JTextField();
        appointmentDateChooser = new com.toedter.calendar.JDateChooser();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 153));

        wardenLabel.setFont(new java.awt.Font("Times New Roman", 0, 60)); // NOI18N
        wardenLabel.setText("WARDEN");

        facultyLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        facultyLabel.setText("FACULTY");

        deptLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        deptLabel.setText("DEPARTMENT");

        hostelLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        hostelLabel.setText("HOSTEL");

        wardenIdLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        wardenIdLabel.setText("WARDEN ID");

        wardenNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        wardenNameLabel.setText("WARDEN NAME");

        appointmentDateLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        appointmentDateLabel.setText("APPOINTMENT DATE");

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        remarksLabel.setText("REMARKS");

        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });

        deptComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptComboBoxActionPerformed(evt);
            }
        });

        hostelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostelComboBoxActionPerformed(evt);
            }
        });

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

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
            wardenTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            wardenTable.getColumnModel().getColumn(1).setPreferredWidth(10);
            wardenTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            wardenTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            wardenTable.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CELL NO.");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(680, 680, 680)
                        .addComponent(wardenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(facultyLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(deptLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(hostelLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(wardenIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(wardenNameLabel))
                            .addComponent(appointmentDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(remarksLabel)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(wardenIdTextField)
                                    .addComponent(wardenNameTextField)
                                    .addComponent(appointmentDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                    .addComponent(cellNoTextField)
                                    .addComponent(hostelComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deptComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(facultyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(wardenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(facultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(deptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(hostelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(wardenIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(wardenNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(appointmentDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel1)
                            .addGap(41, 41, 41)
                            .addComponent(remarksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(deptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(hostelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(wardenIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(wardenNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(appointmentDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(cellNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addWarden();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateWarden();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteWarden();
    }//GEN-LAST:event_deleteButtonActionPerformed


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
