/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import BeanClasses.AllotmentBean;
import BeanClasses.BatchBean;
import BeanClasses.DepartmentBean;
import BeanClasses.FacultyBean;
import BeanClasses.HostelsBean;
import BeanClasses.ProgramBean;
import BeanClasses.StudentBean;
import BeanClasses.WardenBean;
import java.awt.FileDialog;
import java.io.FileOutputStream;
import java.io.PrintStream;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author Danish
 */
public class DatabaseManager {
   

    
    //usindh connection starts
	private static Connection con;
    private static String fac_id;
	static{
		try{
			init();
		}catch(Exception e){
		}
	}
	private static void init()throws Exception{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:hostelSystem");
//            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//            String path="D:\\Hostel Management System\\Hostel Access Database\\hostel.accdb";
//            String url = "jdbc:ucanaccess://"+path;  
//            con=DriverManager.getConnection(url);
//            System.out.println("Connection Successful");
            /*		
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String path="D:\\Library Prooject\\Library Access Database\\library.accdb";
                String url = "jdbc:ucanaccess://"+path;      
                con=DriverManager.getConnection(url);
                System.out.println("Connection Successful");*/
	}
     // usindh connection ends
        
	public static int addFaculty(String facName,String remarks)throws Exception{
		String query="INSERT into faculty (fac_name,remarks) values ('"+facName+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addDepartment(int facId,String deptName,String remarks)throws Exception{
		String query="INSERT into department (fac_id,dept_name,remarks) values ("+facId+",'"+deptName+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addProgram(int deptId,String progName,int semDuration,String remarks)throws Exception{
		String query="INSERT into program (dept_id,prog_name,duration_in_sem,remarks) values ('"+deptId+"','"+progName+"',"+semDuration+",'"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addBatch(int progId,int batchYear,String shift,String groupDesc,String remarks)throws Exception{
		String query="INSERT into batch (prog_id,batch_year,shift,group_desc,remarks) values ("+progId+","+batchYear+",'"+shift+"','"+groupDesc+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addStudent(int batchId,String stdName,String fName,String surname,String rollNo,String gender,String remarks)throws Exception{
		String query="INSERT into student (batch_id,std_name,fname,surname,roll_no,gender,remarks) values ("+batchId+",'"+stdName+"','"+fName+"','"+surname+"','"+rollNo+"','"+gender+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addHostel(String hostelName,int numOfRooms,int numOfBathrooms,int numOfStories,String mess,int stdPerRoom,String remarks)throws Exception{
		String query="INSERT into hostels (hostel_name,num_of_rooms,num_of_bathrooms,num_of_stories,mess,std_per_room,remarks) values ('"+hostelName+"',"+numOfRooms+","+numOfBathrooms+","+numOfStories+",'"+mess+"',"+stdPerRoom+",'"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int addWarden(int deptId,int hostelId,String wardenName,String appointmentDate,int cellNo,String remarks)throws Exception{
		String query="INSERT into warden (dept_id,hostel_id,warden_name,appointment_date,cell_num,remarks) values ("+deptId+","+hostelId+",'"+wardenName+"','"+appointmentDate+"',"+cellNo+",'"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}  
	public static int addAllotment(int stdId,int hostelId,int roomNo,int challanNo,String challanDate,int challanAmount,String leavingDate,String reasonOfLeaving,String remarks)throws Exception{
 
            String query="INSERT into allotment (std_id,hostel_id,room_no,challan_no,challan_date,challan_amount,leaving_date,reason_of_leaving,remarks) values ("+stdId+","+hostelId+","+roomNo+","+challanNo+","+challanDate+","+challanAmount+","+leavingDate+",'"+reasonOfLeaving+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}         
       
	public static int deleteFaculty(int facId)throws Exception{
		String query="DELETE from faculty where fac_id="+facId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteDepartment(int deptId)throws Exception{
		String query="DELETE from department where dept_id="+deptId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteProgram(int progId)throws Exception{
		String query="DELETE from program where prog_id="+progId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteBatch(int batchId)throws Exception{
		String query="DELETE from batch where batch_id="+batchId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteStudent(int StdId)throws Exception{
		String query="DELETE from student where std_id="+StdId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}

	public static int deleteHostel(String hostelId)throws Exception{
		String query="DELETE from hostels where hostel_id="+hostelId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}  
	public static int deleteWarden(String wardenId)throws Exception{
		String query="DELETE from warden where warden_id="+wardenId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	} 
	public static int deleteAllotment(String allotId)throws Exception{
		String query="DELETE from allotment where allot_id="+allotId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}              	
        
	public static int updateFaculty(int facId,String facName,String remarks)throws Exception{
		String query="UPDATE faculty set fac_name='"+facName+"' , remarks='"+remarks+"' where fac_id="+facId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int updateDepartment(int deptId,String deptName,String remarks)throws Exception{
		String query="UPDATE department set dept_name='"+deptName+"' , remarks='"+remarks+"' where dept_id="+deptId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int updateProgram(int progId,String progName,int semDuration,String remarks)throws Exception{
		String query="UPDATE program set prog_name='"+progName+"' , duration_in_sem="+semDuration+" , remarks='"+remarks+"' where prog_id="+progId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int updateBatch(int batchId,int batchYear,String shift,String groupDesc,String remarks)throws Exception{
		String query="UPDATE batch set batch_year='"+batchYear+"' ,shift='"+shift+"' ,group_desc='"+groupDesc+"' , remarks='"+remarks+"' where batch_id="+batchId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int updateStudent(int stdId,String stdName,String fName,String surname,String rollNo,String remarks)throws Exception{
		String query="UPDATE student set std_name='"+stdName+"' ,fname='"+fName+"' ,surname='"+surname+"' ,roll_no='"+rollNo+"' , remarks='"+remarks+"' where std_id="+stdId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
    public static int updateHostel(String hostelId,String hostelName,int numOfRooms,int numOfBathrooms,int numOfStories,String mess,int stdPerRoom,String remarks)throws Exception{
                String query="Update hostels set hostel_name='"+hostelName+"' , num_of_rooms = "+numOfRooms+" , num_of_bathrooms = "+numOfBathrooms+", num_of_stories = "+numOfStories+" , mess = '"+mess+"',std_per_room = "+stdPerRoom+" , remarks = '"+remarks+"' where hostel_id = "+hostelId; 
                System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
  } 
     public static int updateWarden(int deptId,int hostelId,String wardenId,String wardenName,String appointmentDate,int cellNo,String remarks)throws Exception{
                String query="Update warden set dept_id="+deptId+",hostel_id="+hostelId+" , warden_name='"+wardenName+"' , appointment_date = '"+appointmentDate+"' , cell_num = "+cellNo+",  remarks = '"+remarks+"' where warden_id = "+wardenId; 
                System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
  }    
     public static int updateAllotment(int stdId,int hostelId,String allotId,int roomNo,int challanNo,String challanDate,int challanAmount,String leavingDate,String reasonOfLeaving,String remarks)throws Exception{
                String query="Update allotment set std_id="+stdId+", hostel_id="+hostelId+" , room_no="+roomNo+" , challan_no = "+challanNo+" , challan_date = "+challanDate+", challan_amount="+challanAmount+" , leaving_date="+leavingDate+" , reason_of_leaving='"+reasonOfLeaving+"' , remarks = '"+remarks+"' where allot_id = "+allotId; 
                System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
  }     
   
   //subint.karbi@outlook.com 1111

	public static Vector getFaculty()throws Exception{
		String query="select * from faculty";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				FacultyBean bean=new FacultyBean();	
				bean.setFacId( result.getInt("fac_id") );
				bean.setFacName( result.getString("fac_name") );
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
	public static Vector getDepartment(int facId)throws Exception{
		String query="select * from department where fac_id= "+facId ;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				DepartmentBean bean=new DepartmentBean();	
				bean.setDeptId( result.getInt("dept_id") );
				bean.setFacId( result.getInt("fac_id") );
				bean.setDeptName( result.getString("dept_name") );
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
	public static Vector getProgram(int deptId)throws Exception{
		String query="select * from program where dept_id="+deptId;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				ProgramBean bean=new ProgramBean();	
				bean.setProgId( result.getInt("prog_id") );
				bean.setDeptId( result.getInt("dept_id") );
				bean.setProgName( result.getString("prog_name") );
				bean.setSemDuration( result.getInt("duration_in_sem"));
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
	public static Vector getBatch(int progId)throws Exception{
		String query="select * from batch where prog_id="+progId;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				BatchBean bean=new BatchBean();	
				bean.setBatchId( result.getInt("batch_id") );
				bean.setProgId( result.getInt("prog_id") );
				bean.setBatchYear( result.getInt("batch_year") );
				bean.setShift( result.getString("shift") );
				bean.setGroupDesc( result.getString("group_desc") );
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
        	public static Vector getBatch()throws Exception{
		String query="select * from batch ";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				BatchBean bean=new BatchBean();	
				bean.setBatchId( result.getInt("batch_id") );
				bean.setProgId( result.getInt("prog_id") );
				bean.setBatchYear( result.getInt("batch_year") );
				bean.setShift( result.getString("shift") );
				bean.setGroupDesc( result.getString("group_desc") );
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
	public static Vector getStudent(int batchId)throws Exception{
		String query="select * from student where batch_id= "+batchId;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				StudentBean bean=new StudentBean();	
				bean.setStdId( result.getInt("std_id") );
				bean.setBatchId( result.getInt("batch_id") );
				bean.setName( result.getString("std_name") );
				bean.setFname( result.getString("fname") );
				bean.setSurname( result.getString("surname") );
				bean.setRollNo( result.getString("roll_no") );
                                bean.setGender(result.getString("gender"));
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}    
  public static Vector getStudent()throws Exception{
		String query="select * from student ";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
                       // int sno=0;
			while(result.next()){
                           // sno++;
				StudentBean bean=new StudentBean();	
				//bean.setSerialNum(sno);
                                
                                bean.setStdId( result.getInt("std_id") );
				bean.setBatchId( result.getInt("batch_id") );
				bean.setName( result.getString("std_name") );
				bean.setFname( result.getString("fname") );
				bean.setSurname( result.getString("surname") );
				bean.setRollNo( result.getString("roll_no") );
                                bean.setGender(result.getString("gender"));
				bean.setRemarks( result.getString("remarks") );	
                                //bean.setSerialNum(result.getInt("serial_num"));
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}          

     public static Vector getHostel()throws Exception{   
 
      String query="select * from hostels";
      System.out.println(query);
   
      Statement st=null;
      ResultSet result=null;
      try{
          	st=con.createStatement();
	        result=st.executeQuery(query);
		Vector v=new Vector();
              
                while(result.next()){
                   HostelsBean bean = new HostelsBean ();
                  
                    bean.setHotelId(result.getInt("hostel_id"));
                    bean.setHostelName(result.getString("hostel_name"));
                    bean.setNumOfRooms(result.getInt("num_of_rooms"));
                    bean.setNumOfBathrooms(result.getInt("num_of_bathrooms"));
                    bean.setNumOfStories(result.getInt("num_of_stories"));
                    bean.setMess(result.getString("mess"));
                    bean.setStdPerRoom(result.getInt("std_per_room"));
                    bean.setRemarks(result.getString("remarks"));
                    v.addElement(bean);
               }
          return v;
      }
      finally{
              if(result!=null)result.close();;
              if(st!=null)st.close();;
      }        
}
        public static HostelsBean getHostelById(String hostelId){
        
            HostelsBean bean = null;
            ResultSet result = null;
            try{
                
                String query = "select * from hostels where hostel_id = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, hostelId);
                
                result = pstmt.executeQuery();
                
                if(result.next()){
                
                    bean = new HostelsBean();
                    bean.setHotelId(result.getInt("hostel_id"));
                    bean.setHostelName(result.getString("hostel_name"));
                    bean.setNumOfRooms(result.getInt("num_of_rooms"));
                    bean.setNumOfBathrooms(result.getInt("num_of_bathrooms"));
                    bean.setNumOfStories(result.getInt("num_of_stories"));
                    bean.setMess(result.getString("mess"));
                    bean.setStdPerRoom(result.getInt("std_per_room"));
                    bean.setRemarks(result.getString("remarks"));
                }
                
                
            }catch(Exception e){
            
                e.printStackTrace();
                
            }
            return bean;
 }  
 
     public static Vector getWarden(int deptId)throws Exception{   
 
      String query="select * from warden where dept_id="+deptId;
      System.out.println(query);
   
      Statement st=null;
      ResultSet result=null;
      try{
          	st=con.createStatement();
	        result=st.executeQuery(query);
		Vector v=new Vector();
                WardenBean  bean =  null;
                while(result.next()){
                    bean = new WardenBean();
                    bean.setDeptId(result.getInt("dept_id"));
                    bean.setHostelId(result.getInt("hostel_id"));
                    bean.setWardenId(result.getInt("warden_id"));
                    bean.setWardenName(result.getString("warden_name"));
                    bean.setAppointmentDate(result.getDate("appointment_date"));
                    bean.setCellNum(result.getInt("cell_num"));
                    bean.setRemarks(result.getString("remarks"));
                    v.addElement(bean);
               }
          return v;
      }
      finally{
              if(result!=null)result.close();;
              if(st!=null)st.close();;
      }   
      
}        
       public static WardenBean getWardenById(String wardenId){
        
            WardenBean bean = null;
            ResultSet result = null;
            try{
                
                String query = "select * from warden where warden_id = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, wardenId);
                
                result = pstmt.executeQuery();
                
                if(result.next()){
                
                    bean = new WardenBean();
                    bean.setWardenId(result.getInt("warden_id"));
                    bean.setWardenName(result.getString("warden_name"));
                    bean.setAppointmentDate(result.getDate("appointment_date"));
                    bean.setCellNum(result.getInt("cell_num"));
                    bean.setRemarks(result.getString("remarks"));
                }
                
                
            }catch(Exception e){
            
                e.printStackTrace();
                
            }
         return bean;   
 }          
     public static Vector getAllotment(int hostelId)throws Exception{   
 
      String query="select * from allotment where hostel_id="+hostelId;
      System.out.println(query);
   
      Statement st=null;
      ResultSet result=null;
      try{
          	st=con.createStatement();
	        result=st.executeQuery(query);
		Vector v=new Vector();
                AllotmentBean  bean =  null;
                while(result.next()){
                    bean = new AllotmentBean();
                    bean.setStdId(result.getInt("std_id"));
                    bean.setHostelId(result.getInt("hostel_id"));
                    bean.setAllotId(result.getInt("allot_id"));
                    bean.setRoomNo(result.getInt("room_no"));
                    bean.setChallanNo(result.getInt("challan_no"));
                    bean.setChallanDate(result.getDate("challan_date"));
                    bean.setChallanAmount(result.getInt("challan_amount"));
                    bean.setLeavingDate(result.getDate("leaving_date"));                    
                    bean.setReasonOfLeaving(result.getString("reason_of_leaving"));
                    bean.setRemarks(result.getString("remarks"));
                    v.addElement(bean);
               }
          return v;
      }
      finally{
              if(result!=null)result.close();;
              if(st!=null)st.close();;
      }    
}    

       public static AllotmentBean getAllotmentById(String allotId){
        
            AllotmentBean bean = null;
            ResultSet result = null;
            try{
                
                String query = "select * from allotment where allot_id = ?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, allotId);
                
                result = pstmt.executeQuery();
                
                if(result.next()){
                    bean= new AllotmentBean();
                    bean.setStdId(result.getInt("std_id"));
                    bean.setHostelId(result.getInt("hostel_id"));
                    bean.setAllotId(result.getInt("allot_id"));
                    bean.setRoomNo(result.getInt("room_no"));
                    bean.setChallanNo(result.getInt("challan_no"));
                    bean.setChallanDate(result.getDate("challan_date"));
                    bean.setChallanAmount(result.getInt("challan_amount"));
                    bean.setLeavingDate(result.getDate("leaving_date"));                    
                    bean.setReasonOfLeaving(result.getString("reason_of_leaving"));
                    bean.setRemarks(result.getString("remarks"));
                }
                
                
            }catch(Exception e){
            
                e.printStackTrace();
                
            }
         return bean;   
 }     
     
}//DATABASE CLASS END

//     public static Vector getDefaulterList(String dateOfIssue1,String dateOfIssue2)throws Exception{
//            if(dateOfIssue1.trim().equals(""))
//                     dateOfIssue1=null;
//            else
//                dateOfIssue1="#"+dateOfIssue1+"#";
//            if(dateOfIssue2.trim().equals(""))
//                     dateOfIssue2=null;
//            else
//                dateOfIssue2="#"+dateOfIssue2+"#";    
//            
//      String query="select roll_no,std_name,fname,surname,b.book_title,b.author,b.publisher,b.price from book_issue bi,book b,student std where bi.std_id=std.std_id and bi.book_id=b.book_id and date_of_issue>"+dateOfIssue1+" and date_of_issue<"+dateOfIssue2+" ";
//      System.out.println(query);
//   
//      Statement st=null;
//      ResultSet result=null;
//      try{
//          	st=con.createStatement();
//	        result=st.executeQuery(query);
//		Vector v=new Vector();
//                BookIssueBean  bean =  null;
//                while(result.next()){
//                    bean = new BookIssueBean ();
//                  // Vector vector=new Vector();
//                    bean.setRollNo(result.getString("roll_no"));
//                    bean.setName(result.getString("std_name"));
//                    bean.setFname(result.getString("fname"));
//                    bean.setSurname(result.getString("surname"));
//                    bean.setBookTitle(result.getString("book_title"));
//                    bean.setAuthor(result.getString("author"));
//                    bean.setPublisher(result.getString("publisher"));
//                    bean.setPrice(result.getInt("price"));
//                    v.addElement(bean);
//      
//                }
//          return v;
//      }
//      finally{
//              if(result!=null)result.close();;
//              if(st!=null)st.close();;
//      }
//            
//}      
   



//        public static HostelsBean getHostelById(String hostelId){
//                String query = "select * from hostels where hostel_id = "+hostelId+"'";
//                System.out.println(query);
//   
//      Statement st=null;
//      ResultSet result=null;
//      try{
//          	st=con.createStatement();
//	        result=st.executeQuery(query);
//		
//                HostelsBean  bean =  null;
//                while(result.next()){
//                    bean = new HostelsBean ();
//                  
//                    bean.setHotelId(result.getInt("hostel_id"));
//                    bean.setHostel_name(result.getString("hostel_name"));
//                    bean.setNumOfRooms(result.getInt("num_of_rooms"));
//                    bean.setNumOfBathrooms(result.getInt("num_of_bathrooms"));
//                    bean.setNumOfStories(result.getInt("num_of_stories"));
//                    bean.setMess(result.getString("mess"));
//                    bean.setStdPerRoom(result.getInt("std_per_room"));
//                    bean.setRemarks(result.getString("remarks"));
//                }
//                return bean;
//                
//             }
//      finally{
//              if(result!=null)result.close();
//              if(st!=null)st.close();
//      }  
//           
//     }