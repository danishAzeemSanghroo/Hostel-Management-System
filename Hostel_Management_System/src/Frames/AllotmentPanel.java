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
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danish
 */
public class AllotmentPanel extends javax.swing.JPanel {

    /**
     * Creates new form AllotmentPanel
     */
    public AllotmentPanel() {
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
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 0, 153));

        allotmentLabel.setFont(new java.awt.Font("Times New Roman", 1, 60)); // NOI18N
        allotmentLabel.setText("ALLOTMENT");

        facultyLabel.setText("FACULTY");

        deptLabel.setText("DEPARTMENT");

        progLabel.setText("PROGRAM");

        batchLabel.setText("BATCH");

        stdLabel.setText("STUDENT");

        hostelLabel.setText("HOSTEL");

        allotIdLabel.setText("ALLOT ID");

        roomNoLabel.setText("ROOM NO.");

        challanNoLabel.setText("CHALLAN NO.");

        challanDateLabel.setText("CHALLAN DATE");

        challanAmountLabel.setText("CHALLAN AMOUNT");

        leavingDateLabel.setText("LEAVING DATE");

        reasonOfLeavingLabel.setText("REASON OF LEAVING");

        remarksLabel.setText("REMARKS");

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

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
            allotmentTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            allotmentTable.getColumnModel().getColumn(1).setPreferredWidth(5);
            allotmentTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            allotmentTable.getColumnModel().getColumn(5).setPreferredWidth(10);
            allotmentTable.getColumnModel().getColumn(6).setPreferredWidth(10);
            allotmentTable.getColumnModel().getColumn(7).setPreferredWidth(5);
        }

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

        progComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                progComboBoxActionPerformed(evt);
            }
        });

        batchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchComboBoxActionPerformed(evt);
            }
        });

        stdComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stdComboBoxActionPerformed(evt);
            }
        });

        hostelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostelComboBoxActionPerformed(evt);
            }
        });

        challanDateChooser.setDateFormatString("dd-MMM-YYYY");

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

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add_1.jpg"))); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(570, 570, 570)
                        .addComponent(allotmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(facultyLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(deptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(progLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(batchLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(stdLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(hostelLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(allotIdLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(roomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(challanNoLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(challanDateLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(challanAmountLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(leavingDateLabel))
                            .addComponent(reasonOfLeavingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(remarksLabel)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(progComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hostelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(allotIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(challanNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(challanDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(challanAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(leavingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reasonOfLeavingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(allotmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(facultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(deptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(progLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(batchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(stdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(hostelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(allotIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(roomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(leavingDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(reasonOfLeavingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(remarksLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(facultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(deptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(progComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(batchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(stdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(hostelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(allotIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(roomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(challanAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(leavingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(reasonOfLeavingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(281, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void hostelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostelComboBoxActionPerformed
        getAllotment();
    }//GEN-LAST:event_hostelComboBoxActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateAllotment();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteAllotment();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addAllotment();
    }//GEN-LAST:event_addButtonActionPerformed


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
