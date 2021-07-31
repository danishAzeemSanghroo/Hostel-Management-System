/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.AllotmentBean;
import BeanClasses.BatchBean;
import BeanClasses.DepartmentBean;
import BeanClasses.FacultyBean;
import BeanClasses.HostelsBean;
import BeanClasses.ProgramBean;
import BeanClasses.StudentBean;
import BeanClasses.WardenBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;
import EnDeCoder.Encoder;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danish
 */
public class AllotmentFrame extends javax.swing.JFrame {

    /**
     * Creates new form AllotmentFrame
     */
    public AllotmentFrame() {
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
	FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
	if(bean==null)return;

	try{
	          Vector v=DatabaseManager.getDepartment( bean.getFacId() );
	      deptComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			deptComboBox.addItem(v.elementAt(i));	
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}
  private void getProgram(){
	DepartmentBean bean=(DepartmentBean)deptComboBox.getSelectedItem();
	if(bean==null)return;

	try{
	          Vector v=DatabaseManager.getProgram( bean.getDeptId() );
	          progComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			progComboBox.addItem(v.elementAt(i));	
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}  
  private void getBatch(){
	ProgramBean bean=(ProgramBean)progComboBox.getSelectedItem();
	if(bean==null)return;

	try{
	          Vector v=DatabaseManager.getBatch( bean.getProgId() );
	        batchComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			batchComboBox.addItem(v.elementAt(i));		
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}     

 private void getStudent(){
	BatchBean bean=(BatchBean)batchComboBox.getSelectedItem();
	if(bean==null)return;

	try{
	          Vector v=DatabaseManager.getStudent( bean.getBatchId() );
	   	        stdComboBox.removeAllItems();
		for(int i=0; i<v.size(); i++)
			stdComboBox.addItem(v.elementAt(i));		
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}    
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
private void getAllotment(){
        //StudentBean stdBean=(StudentBean)stdComboBox.getSelectedItem();
        HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();
        if(hostelBean==null)return;
	try{
		java.util.Vector v=DatabaseManager.getAllotment(hostelBean.getHotelId());		
                        DefaultTableModel model=(DefaultTableModel)allotmentTable.getModel();
                   tableClear();
                Vector vector =null;
               for(int i=0; i<v.size(); i++){
                   AllotmentBean allotBean= (AllotmentBean)v.elementAt(i);
                     vector = new Vector();
                     vector.addElement(allotBean.getAllotId());
                     vector.addElement(allotBean.getRoomNo());
                     vector.addElement(allotBean.getChallanNo());
                     vector.addElement(allotBean.getChallanDate());
                     vector.addElement(allotBean.getChallanAmount());
                     vector.addElement(allotBean.getLeavingDate());
                     vector.addElement(allotBean.getReasonOfLeaving());
                     vector.addElement(allotBean.getRemarks());
                     model.addRow(vector);   
                }   
      }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
    }    
	
}//end Method
   private void addAllotment(){
        StudentBean stdBean=(StudentBean)stdComboBox.getSelectedItem();
        if(stdBean==null)return;
        HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();  
        if(hostelBean==null)return;
        
    int roomNo=Integer.parseInt(""+roomNoTextField.getText());
    int challanNo=Integer.parseInt(""+challanNoTextField.getText());
    String challanDate=Decoder.getDateFormat(challanDateChooser.getDate());
    int challanAmount=Integer.parseInt(""+challanAmountTextField.getText());
    String leavingDate=Decoder.getDateFormat(leavingDateChooser.getDate());
    String reasonOfLeaving=reasonOfLeavingTextField.getText();
    String remarks=remarksTextArea.getText();

    try{
            if(challanDate.trim().equals(""))
                challanDate=null;
            else
                challanDate="#"+challanDate+"#";
            if(leavingDate.trim().equals(""))
                     leavingDate=null;
            else
               leavingDate="#"+leavingDate+"#";
               	int rows=DatabaseManager.addAllotment(stdBean.getStdId(),hostelBean.getHotelId(),roomNo,challanNo,challanDate,challanAmount,leavingDate,reasonOfLeaving,remarks);
                
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getAllotment();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end 
private void deleteAllotment(){
     int row =allotmentTable.getSelectedRow();
     String allotId=allotmentTable.getModel().getValueAt(row, 0).toString();
    try{
           int rows = DatabaseManager.deleteAllotment(allotId);
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+" Rrecord Removed.");
           getAllotment();
           clear();
           }
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }
}
private void updateAllotment(){
        StudentBean stdBean=(StudentBean)stdComboBox.getSelectedItem();
        if(stdBean==null)return;
        HostelsBean hostelBean=(HostelsBean)hostelComboBox.getSelectedItem();  
        if(hostelBean==null)return;
    
     int row =allotmentTable.getSelectedRow();
     String allotId=allotmentTable.getModel().getValueAt(row, 0).toString();        
        
    int roomNo=Integer.parseInt(""+roomNoTextField.getText());
    int challanNo=Integer.parseInt(""+challanNoTextField.getText());
    String challanDate=Decoder.getDateFormat(challanDateChooser.getDate());
    int challanAmount=Integer.parseInt(""+challanAmountTextField.getText());
    String leavingDate=Decoder.getDateFormat(leavingDateChooser.getDate());
    String reasonOfLeaving=reasonOfLeavingTextField.getText();
    String remarks=remarksTextArea.getText();

     try{
            if(challanDate.trim().equals(""))
                challanDate=null;
            else
                challanDate="#"+challanDate+"#";
            if(leavingDate.trim().equals(""))
                     leavingDate=null;
            else
               leavingDate="#"+leavingDate+"#";
               	int rows=DatabaseManager.updateAllotment(stdBean.getStdId(),hostelBean.getHotelId(),allotId,roomNo,challanNo,challanDate,challanAmount,leavingDate,reasonOfLeaving,remarks);
                if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getAllotment();
	    clear();
        	}
     }catch(Exception e){
         e.printStackTrace();
         javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
     } 
}
private void clear(){
        allotIdTextField.setText("");
        roomNoTextField.setText("");
        challanNoTextField.setText("");
        challanDateChooser.setDateFormatString("");
        challanAmountTextField.setText("");
        leavingDateChooser.setDateFormatString("");
        reasonOfLeavingTextField.setText("");
        remarksTextArea.setText("");
}
private void tableClear(){
    DefaultTableModel model=(DefaultTableModel)allotmentTable.getModel();
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

        allotmentLabel = new javax.swing.JLabel();
        facultyLabel = new javax.swing.JLabel();
        deptLabel = new javax.swing.JLabel();
        progLabel = new javax.swing.JLabel();
        batchLabel = new javax.swing.JLabel();
        stdLabel = new javax.swing.JLabel();
        hostelLabel = new javax.swing.JLabel();
        allotIdLabel = new javax.swing.JLabel();
        roomNoLabel = new javax.swing.JLabel();
        challanNoLabel = new javax.swing.JLabel();
        challanDateLabel = new javax.swing.JLabel();
        challanAmountLabel = new javax.swing.JLabel();
        leavingDateLabel = new javax.swing.JLabel();
        reasonOfLeavingLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        allotmentTable = new javax.swing.JTable();
        facultyComboBox = new javax.swing.JComboBox();
        deptComboBox = new javax.swing.JComboBox();
        progComboBox = new javax.swing.JComboBox();
        batchComboBox = new javax.swing.JComboBox();
        stdComboBox = new javax.swing.JComboBox();
        hostelComboBox = new javax.swing.JComboBox();
        allotIdTextField = new javax.swing.JTextField();
        roomNoTextField = new javax.swing.JTextField();
        challanNoTextField = new javax.swing.JTextField();
        challanAmountTextField = new javax.swing.JTextField();
        reasonOfLeavingTextField = new javax.swing.JTextField();
        challanDateChooser = new com.toedter.calendar.JDateChooser();
        leavingDateChooser = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        allotmentLabel.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        allotmentLabel.setText("ALLOTMENT");
        getContentPane().add(allotmentLabel);
        allotmentLabel.setBounds(630, 20, 400, 60);

        facultyLabel.setText("FACULTY");
        getContentPane().add(facultyLabel);
        facultyLabel.setBounds(150, 140, 68, 30);

        deptLabel.setText("DEPARTMENT");
        getContentPane().add(deptLabel);
        deptLabel.setBounds(120, 180, 110, 30);

        progLabel.setText("PROGRAM");
        getContentPane().add(progLabel);
        progLabel.setBounds(140, 230, 75, 30);

        batchLabel.setText("BATCH");
        getContentPane().add(batchLabel);
        batchLabel.setBounds(160, 270, 51, 30);

        stdLabel.setText("STUDENT");
        getContentPane().add(stdLabel);
        stdLabel.setBounds(140, 320, 71, 30);

        hostelLabel.setText("HOSTEL");
        getContentPane().add(hostelLabel);
        hostelLabel.setBounds(150, 370, 59, 30);

        allotIdLabel.setText("ALLOT ID");
        getContentPane().add(allotIdLabel);
        allotIdLabel.setBounds(140, 410, 71, 30);

        roomNoLabel.setText("ROOM NO.");
        getContentPane().add(roomNoLabel);
        roomNoLabel.setBounds(140, 450, 80, 30);

        challanNoLabel.setText("CHALLAN NO.");
        getContentPane().add(challanNoLabel);
        challanNoLabel.setBounds(120, 490, 103, 30);

        challanDateLabel.setText("CHALLAN DATE");
        getContentPane().add(challanDateLabel);
        challanDateLabel.setBounds(100, 530, 116, 30);

        challanAmountLabel.setText("CHALLAN AMOUNT");
        getContentPane().add(challanAmountLabel);
        challanAmountLabel.setBounds(80, 570, 142, 30);

        leavingDateLabel.setText("LEAVING DATE");
        getContentPane().add(leavingDateLabel);
        leavingDateLabel.setBounds(110, 610, 112, 30);

        reasonOfLeavingLabel.setText("REASON OF LEAVING");
        getContentPane().add(reasonOfLeavingLabel);
        reasonOfLeavingLabel.setBounds(60, 650, 160, 30);

        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(140, 710, 70, 20);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(260, 700, 430, 130);

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(1010, 860, 150, 50);

        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(450, 860, 150, 50);

        backButton.setText("BACK");
        getContentPane().add(backButton);
        backButton.setBounds(820, 860, 160, 50);

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(260, 860, 160, 50);

        clearButton.setText("CLEAR");
        getContentPane().add(clearButton);
        clearButton.setBounds(630, 860, 160, 50);

        allotmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ALLOT ID", "ROOM NO", "CHALLAN NO", "CHALLAN DATE", "CHALLAN AMOUNT", "LEAVING DATE", "REASON OF LEAVING", "REMARKS"
            }
        ));
        allotmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allotmentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(allotmentTable);
        if (allotmentTable.getColumnModel().getColumnCount() > 0) {
            allotmentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            allotmentTable.getColumnModel().getColumn(1).setPreferredWidth(20);
            allotmentTable.getColumnModel().getColumn(6).setResizable(false);
            allotmentTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            allotmentTable.getColumnModel().getColumn(7).setResizable(false);
            allotmentTable.getColumnModel().getColumn(7).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(700, 140, 1230, 690);

        facultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(facultyComboBox);
        facultyComboBox.setBounds(260, 140, 420, 30);

        deptComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(deptComboBox);
        deptComboBox.setBounds(260, 180, 420, 30);

        progComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                progComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(progComboBox);
        progComboBox.setBounds(260, 220, 420, 30);

        batchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(batchComboBox);
        batchComboBox.setBounds(260, 260, 420, 30);

        stdComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(stdComboBox);
        stdComboBox.setBounds(260, 310, 420, 30);

        hostelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostelComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(hostelComboBox);
        hostelComboBox.setBounds(260, 360, 420, 30);
        getContentPane().add(allotIdTextField);
        allotIdTextField.setBounds(260, 410, 250, 30);
        getContentPane().add(roomNoTextField);
        roomNoTextField.setBounds(260, 450, 250, 30);
        getContentPane().add(challanNoTextField);
        challanNoTextField.setBounds(260, 490, 250, 30);
        getContentPane().add(challanAmountTextField);
        challanAmountTextField.setBounds(260, 570, 250, 30);
        getContentPane().add(reasonOfLeavingTextField);
        reasonOfLeavingTextField.setBounds(260, 650, 250, 30);

        challanDateChooser.setDateFormatString("dd-MMM-YYYY");
        getContentPane().add(challanDateChooser);
        challanDateChooser.setBounds(260, 530, 250, 30);
        getContentPane().add(leavingDateChooser);
        leavingDateChooser.setBounds(260, 610, 250, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultyComboBoxActionPerformed
        getDepartment();
    }//GEN-LAST:event_facultyComboBoxActionPerformed

    private void deptComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptComboBoxActionPerformed
        getProgram(); 
    }//GEN-LAST:event_deptComboBoxActionPerformed

    private void progComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_progComboBoxActionPerformed
        getBatch();
    }//GEN-LAST:event_progComboBoxActionPerformed

    private void batchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchComboBoxActionPerformed
        getStudent();
    }//GEN-LAST:event_batchComboBoxActionPerformed

    private void stdComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stdComboBoxActionPerformed
               
    }//GEN-LAST:event_stdComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addAllotment();
    }//GEN-LAST:event_addButtonActionPerformed

    private void hostelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostelComboBoxActionPerformed
        getAllotment();
    }//GEN-LAST:event_hostelComboBoxActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteAllotment();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
         updateAllotment();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void allotmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allotmentTableMouseClicked
     int row =allotmentTable.getSelectedRow();
     String allotId=allotmentTable.getModel().getValueAt(row, 0).toString();
             
     AllotmentBean bean=DatabaseManager.getAllotmentById(allotId);
      
        stdComboBox.setSelectedItem(bean.getStdId());
        hostelComboBox.setSelectedItem(bean.getHostelId());
        allotIdTextField.setText(""+bean.getAllotId());
        roomNoTextField.setText(""+bean.getRoomNo());
        challanNoTextField.setText(""+bean.getChallanNo());
        challanDateChooser.setDate(bean.getChallanDate());
        challanAmountTextField.setText(""+bean.getChallanAmount());
        leavingDateChooser.setDate(bean.getLeavingDate());
        reasonOfLeavingTextField.setText(""+bean.getReasonOfLeaving());
        remarksTextArea.setText(bean.getRemarks());
    }//GEN-LAST:event_allotmentTableMouseClicked

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
            java.util.logging.Logger.getLogger(AllotmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllotmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllotmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllotmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllotmentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel allotIdLabel;
    private javax.swing.JTextField allotIdTextField;
    private javax.swing.JLabel allotmentLabel;
    private javax.swing.JTable allotmentTable;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox batchComboBox;
    private javax.swing.JLabel batchLabel;
    private javax.swing.JLabel challanAmountLabel;
    private javax.swing.JTextField challanAmountTextField;
    private com.toedter.calendar.JDateChooser challanDateChooser;
    private javax.swing.JLabel challanDateLabel;
    private javax.swing.JLabel challanNoLabel;
    private javax.swing.JTextField challanNoTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox deptComboBox;
    private javax.swing.JLabel deptLabel;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JLabel facultyLabel;
    private javax.swing.JComboBox hostelComboBox;
    private javax.swing.JLabel hostelLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser leavingDateChooser;
    private javax.swing.JLabel leavingDateLabel;
    private javax.swing.JComboBox progComboBox;
    private javax.swing.JLabel progLabel;
    private javax.swing.JLabel reasonOfLeavingLabel;
    private javax.swing.JTextField reasonOfLeavingTextField;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JLabel roomNoLabel;
    private javax.swing.JTextField roomNoTextField;
    private javax.swing.JComboBox stdComboBox;
    private javax.swing.JLabel stdLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
