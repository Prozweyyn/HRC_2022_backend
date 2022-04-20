package com.java.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Dataset;

public class DatasetController {
	private static String jdbcURL="jdbc:mysql://127.0.0.1:3306/grey_goose";
	private static String username="root";
	private static String password="1234";
	
	//Setting up the connection
	protected static Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,username,password);
		}catch(Error | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//Getting the entire dataset from the mySQL
	public static List<Dataset> getAllData(){
		List<Dataset> allData=new ArrayList<>();
		
		Connection connection=getConnection();
		String GET_DATA="SELECT * from winter_internship";
		try {
			PreparedStatement ps=connection.prepareStatement(GET_DATA);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int sl_no = rs.getInt("sl_no");
			 	String business_code = rs.getString("business_code");
				String cust_number = rs.getString("cust_number");
				String clear_date = rs.getString("clear_date");
				String buisness_year = rs.getString("buisness_year");
				String doc_id = rs.getString("doc_id");
				String posting_date = rs.getString("posting_date");
				String document_create_date = rs.getString("document_create_date");
				String document_create_date1 = rs.getString("document_create_date1");
				String due_in_date = rs.getString("due_in_date");
				String invoice_currency = rs.getString("invoice_currency");
				String document_type = rs.getString("document_type");
				String posting_id = rs.getString("posting_id");
				String area_business = rs.getString("area_business");
				String total_open_amount = rs.getString("total_open_amount");
				String baseline_create_date = rs.getString("baseline_create_date");
				String cust_payment_terms = rs.getString("cust_payment_terms");
				String invoice_id = rs.getString("invoice_id");
				String isOpen = rs.getString("isOpen");
				String aging_bucket = rs.getString("aging_bucket");
				String is_deleted = rs.getString("is_deleted");
				
				Dataset d1=new Dataset(sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,document_create_date1,due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,isOpen,aging_bucket,is_deleted);
				allData.add(d1);
				System.out.println(allData.size());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allData;
		
	}
	
	
	//Add a data to the mySQL
	public static int addData(Dataset d1) {
		try {
			Connection connection=getConnection();
			String INSERT_DATA="insert  into `winter_internship`(`business_code`,`cust_number`,`clear_date`,`buisness_year`,`doc_id`,`posting_date`,`document_create_date`,`document_create_date1`,`due_in_date`,`invoice_currency`,`document_type`,`posting_id`,`area_business`,`total_open_amount`,`baseline_create_date`,`cust_payment_terms`,`invoice_id`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt=connection.prepareStatement(INSERT_DATA);
			psmt.setString(1,d1.getBusiness_code());
			psmt.setString(2, d1.getCust_number());
			psmt.setString(3, d1.getClear_date());
			psmt.setString(4, d1.getBuisness_year());
			psmt.setString(5, d1.getDoc_id());
			psmt.setString(6, d1.getPosting_date());
			psmt.setString(7, d1.getDocument_create_date());
			psmt.setString(8, d1.getDocument_create_date1());
			psmt.setString(9, d1.getDue_in_date());
			psmt.setString(10, d1.getInvoice_currency());
			psmt.setString(11, d1.getDocument_type());
			psmt.setString(12, d1.getPosting_id());
			psmt.setString(13, d1.getArea_business());
			psmt.setString(14, d1.getTotal_open_amount());
			psmt.setString(15, d1.getBaseline_create_date());
			psmt.setString(16, d1.getCust_payment_terms());
			psmt.setString(17, d1.getInvoice_id());
			
			if(psmt.executeUpdate()>0)
			{
				return 1;
			}
			else
				return 0;
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		
		
		
	}
	
	public static int updateData(int s_no,String invoice_currency,String cust_payment_terms) {
		try {
			Connection connection=getConnection();
			String UPDATE_DATA="UPDATE winter_internship SET invoice_currency=?,cust_payment_terms=? WHERE sl_no="+s_no;
			PreparedStatement psmt=connection.prepareStatement(UPDATE_DATA);
			psmt.setString(1,invoice_currency);
			psmt.setString(2, cust_payment_terms);
			if(psmt.executeUpdate()>0)
				return 1;
			else
				return 0;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		
	}
	
	
	public static int deleteData(int s_no) {
		try {
			Connection connection=getConnection();
			String DELETE_DATA="DELETE FROM winter_internship WHERE sl_no = "+s_no;
			PreparedStatement psmt=connection.prepareStatement(DELETE_DATA);
			if(psmt.executeUpdate()>0)
				return 1;
			else
				return 0;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		
	}
	
	public static List<Dataset> getFilteredData(int cust_number,int invoice_id,int doc_id,int business_year){
		List<Dataset> allData=new ArrayList<>();
		try {
			Connection connection=getConnection();
			String str="";
			if(cust_number>0)
				str=str.concat("cust_number = "+cust_number+",");
			if(invoice_id>0)
				str=str.concat("invoice_id = "+invoice_id+",");
			if(doc_id>0)
				str=str.concat("doc_id ="+doc_id+",");
			if(business_year>0)
				str=str.concat("business_year = "+business_year+",");
			
			str=str.substring(0, str.length() - 1); 
			System.out.println(str);
			String ADVANCE_FILTER="SELECT * FROM winter_internship WHERE "+str;
			PreparedStatement psmt=connection.prepareStatement(ADVANCE_FILTER);
			ResultSet rs=psmt.executeQuery();
			while(rs.next())
			{
				int sl_no = rs.getInt("sl_no");
			 	String business_code = rs.getString("business_code");
				String cust_number1 = rs.getString("cust_number");
				String clear_date = rs.getString("clear_date");
				String buisness_year = rs.getString("buisness_year");
				String doc_id1 = rs.getString("doc_id");
				String posting_date = rs.getString("posting_date");
				String document_create_date = rs.getString("document_create_date");
				String document_create_date1 = rs.getString("document_create_date1");
				String due_in_date = rs.getString("due_in_date");
				String invoice_currency = rs.getString("invoice_currency");
				String document_type = rs.getString("document_type");
				String posting_id = rs.getString("posting_id");
				String area_business = rs.getString("area_business");
				String total_open_amount = rs.getString("total_open_amount");
				String baseline_create_date = rs.getString("baseline_create_date");
				String cust_payment_terms = rs.getString("cust_payment_terms");
				String invoice_id1 = rs.getString("invoice_id");
				String isOpen = rs.getString("isOpen");
				String aging_bucket = rs.getString("aging_bucket");
				String is_deleted = rs.getString("is_deleted");
				
				Dataset d1=new Dataset(sl_no,business_code,cust_number1,clear_date,buisness_year,doc_id1,posting_date,document_create_date,document_create_date1,due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id1,isOpen,aging_bucket,is_deleted);
				allData.add(d1);				
			}
			
			return allData;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return allData;
		}
		
	}
	
	
	

}