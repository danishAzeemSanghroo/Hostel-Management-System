package Frames;

import BeanClasses.DepartmentBean;
import BeanClasses.FacultyBean;
import DatabaseManager.DatabaseManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
//import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Danish
 */
public class DepartmentFrame extends javax.swing.JFrame implements ActionListener {
 //   private Object deptList;

    /**
     * Creates new form DepartmentFrame
     */
    public DepartmentFrame() {
        initComponents();
        facultyComboBox.addActionListener((ActionListener) this);
        getFaculty();
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
public void actionPerformed(ActionEvent e){
	if(facultyComboBox==e.getSource())
		getDepartment();
}


private void getDepartment(){
	FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
	if(bean==null)return;

	try{
	          Vector v=DatabaseManager.getDepartment( bean.getFacId() );
	           deptList.setListData(v); 		
	}catch(Exception e){
		e.printStackTrace();
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify  this code. The content of this method is always
     * regenerated by the Form Editor.
     */ 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deptLabel = new javax.swing.JLabel();
        facultyLabel = new javax.swing.JLabel();
        deptIdLabel = new javax.swing.JLabel();
        deptNameLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        facultyComboBox = new javax.swing.JComboBox();
        deptIdTextField = new javax.swing.JTextField();
        deptNameTextField = new javax.swing.JTextField();
        deptListLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        deptList = new javax.swing.JList();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        deptLabel.setFont(new java.awt.Font("Stencil", 1, 48)); // NOI18N
        deptLabel.setText("DEPARTMENT");
        getContentPane().add(deptLabel);
        deptLabel.setBounds(390, 30, 370, 90);

        facultyLabel.setText("FACULTY");
        getContentPane().add(facultyLabel);
        facultyLabel.setBounds(90, 170, 80, 20);

        deptIdLabel.setText("DEPARTMENT ID");
        getContentPane().add(deptIdLabel);
        deptIdLabel.setBounds(90, 220, 140, 30);

        deptNameLabel.setText("DEPARTMENT NAME");
        getContentPane().add(deptNameLabel);
        deptNameLabel.setBounds(90, 270, 150, 30);

        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(90, 330, 80, 20);

        getContentPane().add(facultyComboBox);
        facultyComboBox.setBounds(260, 170, 360, 26);

        deptIdTextField.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                deptIdTextFieldComponentHidden(evt);
            }
        });
        getContentPane().add(deptIdTextField);
        deptIdTextField.setBounds(260, 220, 100, 26);
        getContentPane().add(deptNameTextField);
        deptNameTextField.setBounds(260, 270, 360, 26);

        deptListLabel.setText("DEPARTMENTS");
        getContentPane().add(deptListLabel);
        deptListLabel.setBounds(680, 280, 130, 30);

        deptList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                deptListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(deptList);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(660, 330, 370, 180);

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(260, 540, 93, 50);

        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(360, 540, 93, 50);

        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton);
        clearButton.setBounds(460, 540, 81, 50);

        backButton.setText("BACK");
        backButton.setPreferredSize(new java.awt.Dimension(81, 29));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(550, 540, 81, 50);

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(940, 540, 90, 40);

        remarksTextArea.setColumns(20);
        remarksTextArea.setRows(5);
        jScrollPane2.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(260, 330, 360, 180);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        if(updateButton==evt.getSource())  
                updateDepartment();// TODO add your handling code here:
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteDepartment();        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void deptIdTextFieldComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_deptIdTextFieldComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_deptIdTextFieldComponentHidden

    private void deptListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_deptListValueChanged
        DepartmentBean bean=(DepartmentBean)deptList.getSelectedValue();
   if(bean==null)return;

   deptIdTextField.setText( ""+bean.getDeptId());
   deptNameTextField.setText( bean.getDeptName());
   remarksTextArea.setText( bean.getRemarks());  // TODO add your handling code here:
    }//GEN-LAST:event_deptListValueChanged

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
       if(addButton==evt.getSource()) 
           addDepartment();        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
    clear();        // TODO add your handling code here:
    }//GEN-LAST:event_clearButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
//          new MainFrame().setVisible(true);
//          dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

private void addDepartment(){
     FacultyBean bean=(FacultyBean)facultyComboBox.getSelectedItem();
     if(bean==null)return;

    String deptName=deptNameTextField.getText();
    String remarks=remarksTextArea.getText();

    try{
               	int rows=DatabaseManager.addDepartment(bean.getFacId(),deptName,remarks);
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getDepartment();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end
private void deleteDepartment(){
    DepartmentBean bean = (DepartmentBean)deptList.getSelectedValue();
    if(bean==null)return;
    try{
           int rows = DatabaseManager.deleteDepartment(bean.getDeptId());
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+"Record Removed.");
           getDepartment();
           clear();
           }
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}

private void updateDepartment(){
     DepartmentBean bean=(DepartmentBean)deptList.getSelectedValue();
     if(bean==null)return;

     String deptName=deptNameTextField.getText();
     String remarks=remarksTextArea.getText();

     try{
	int rows=DatabaseManager.updateDepartment(bean.getDeptId(),deptName,remarks);
	if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getDepartment();
	    clear();
        	}
     }catch(Exception e){
         e.printStackTrace();
         javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
     } 
}

private void clear(){
    deptIdTextField.setText("");
    deptNameTextField.setText("");
    remarksTextArea.setText("");
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
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel deptIdLabel;
    private javax.swing.JTextField deptIdTextField;
    private javax.swing.JLabel deptLabel;
    private javax.swing.JList deptList;
    private javax.swing.JLabel deptListLabel;
    private javax.swing.JLabel deptNameLabel;
    private javax.swing.JTextField deptNameTextField;
    private javax.swing.JComboBox facultyComboBox;
    private javax.swing.JLabel facultyLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

   /* public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
