package com.java.routes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.java.controller.DatasetController;
import com.java.model.Dataset;

/**
 * Servlet implementation class Dataset_Routes
 */
@WebServlet("/")
public class Dataset_Routes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Gson gson=new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dataset_Routes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//CORS prevention
				response.addHeader("Access-Control-Allow-Origin","*");
				response.setContentType("application/json");
		String action=request.getServletPath();
		switch(action)
		{
		case "/get-all-data":
			getAllData(request,response);
			break;
		case "/add-data":
			addData(request,response);
			break;
		case "/update-data":
			updateData(request,response);
			break;
		case "/delete-data":
			deleteData(request,response);
			break;
		case "/advance-search":
			advanceSearch(request,response);
			break;
		}
	}
	
	private void getAllData(HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Dataset> d1=DatasetController.getAllData();
			String JSONresponse=gson.toJson(d1);
			response.getWriter().append(JSONresponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addData(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		  if ("POST".equalsIgnoreCase(request.getMethod())) {
//				String requestType=request.getMethod();
//				System.out.println(request.getReader().lines());
//				String JSONresponse=gson.toJson(requestType);
//				try {
//					response.getWriter().append(JSONresponse);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
		
		String business_code=request.getParameter("business_code");
		String cust_number=request.getParameter("cust_number");
		String clear_date=request.getParameter("clear_date");
		String business_year=request.getParameter("business_year");
		String doc_id=request.getParameter("doc_id");
		String posting_date=request.getParameter("posting_date");
		String document_create_date=request.getParameter("document_create_date");
		String document_create_date1=request.getParameter("document_create_date1");
		String due_in_date=request.getParameter("due_in_date");
		String invoice_currency=request.getParameter("invoice_currency");
		String document_type=request.getParameter("document_type");
		String posting_id=request.getParameter("posting_id");
		String area_business=request.getParameter("area_business");
		String total_open_amount=request.getParameter("total_open_amount");
		String baseline_create_date=request.getParameter("baseline_create_date");
		String cust_payment_terms=request.getParameter("cust_payment_terms");
		String invoice_id=request.getParameter("invoice_id");
		Dataset d1=new Dataset(business_code,cust_number,clear_date,business_year,doc_id,posting_date,document_create_date,document_create_date1,due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id);
		int result=DatasetController.addData(d1);
		String JSONresponse;
		if(result==1)
			JSONresponse=gson.toJson("Data inserted Successfully");
		else
			JSONresponse=gson.toJson("Error in inserting data");
		
		try {
			response.getWriter().append(JSONresponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
	}
	
	public void updateData(HttpServletRequest request,HttpServletResponse response) {
		try {
			int s_no=Integer.parseInt(request.getParameter("s_no"));
			String invoice_curr=request.getParameter("invoice_curr");
			String cust_payment_terms=request.getParameter("cust_payment_terms");
			int result=DatasetController.updateData(s_no, invoice_curr, cust_payment_terms);
			String JSONresponse;
			if(result==1)
				JSONresponse="Data Updated Successfully";
			else
				JSONresponse="Error in updating data";
				response.getWriter().append(JSONresponse);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteData(HttpServletRequest request,HttpServletResponse response) {
		try {
			int s_no=Integer.parseInt(request.getParameter("s_no"));
			int result=DatasetController.deleteData(s_no);
			String JSONresponse;
			if(result==1)
				JSONresponse="Data Deleted Successfully";
			else
				JSONresponse="Error in deleting data";
				response.getWriter().append(JSONresponse);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void advanceSearch(HttpServletRequest request,HttpServletResponse response) {
		try {
			int cust_number=Integer.parseInt(request.getParameter("cust_number"));
			int invoice_id=Integer.parseInt(request.getParameter("invoice_id"));
			int doc_id=Integer.parseInt(request.getParameter("doc_id"));
			int business_year=Integer.parseInt(request.getParameter("business_year"));
			System.out.print(cust_number);
			List<Dataset> d1=DatasetController.getFilteredData(cust_number,invoice_id,doc_id,business_year);
			String JSONresponse=gson.toJson(d1);
			response.getWriter().append(JSONresponse);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	

}