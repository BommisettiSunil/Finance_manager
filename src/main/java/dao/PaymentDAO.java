package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class PaymentDAO {

	public static double getTotalPaid(int loanId){

		double paid=0;

		try{

			Connection con=DBConnection.getConnection();

			PreparedStatement ps=con.prepareStatement(
					"SELECT COALESCE(SUM(amount),0) FROM payments WHERE loan_id=? AND type='EMI'"
					);

			ps.setInt(1,loanId);

			ResultSet rs=ps.executeQuery();

			if(rs.next()){
				paid=rs.getDouble(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return paid;

	}
	public static void addPayment(int loanId,double amount,String paymentDate,String type){

	    try{

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(
	        "INSERT INTO payments(loan_id,amount,payment_date,type) VALUES(?,?,?,?)"
	        );

	        ps.setInt(1,loanId);
	        ps.setDouble(2,amount);
	        ps.setDate(3,java.sql.Date.valueOf(paymentDate));
	        ps.setString(4,type);

	        ps.executeUpdate();

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
	public static int getTotalPayments(int adminId){

		int count=0;

		try{

		Connection con=DBConnection.getConnection();

		PreparedStatement ps=con.prepareStatement(
		"SELECT COUNT(*) FROM payments p "+
		"JOIN loans l ON p.loan_id=l.loan_id "+
		"JOIN customers c ON l.customer_id=c.customer_id "+
		"WHERE c.admin_id=?"
		);

		ps.setInt(1,adminId);

		ResultSet rs=ps.executeQuery();

		if(rs.next()){
		count=rs.getInt(1);
		}

		}catch(Exception e){
		e.printStackTrace();
		}

		return count;

		}
	public static double getTotalEMI(int adminId){

		double total=0;

		try{

		Connection con=DBConnection.getConnection();

		PreparedStatement ps=con.prepareStatement(
		"SELECT COALESCE(SUM(p.amount),0) FROM payments p "+
		"JOIN loans l ON p.loan_id=l.loan_id "+
		"JOIN customers c ON l.customer_id=c.customer_id "+
		"WHERE c.admin_id=?"
		);

		ps.setInt(1,adminId);

		ResultSet rs=ps.executeQuery();

		if(rs.next()){
		total=rs.getDouble(1);
		}

		}catch(Exception e){
		e.printStackTrace();
		}

		return total;

		}
	public static ResultSet getPayments(int adminId){

		ResultSet rs = null;

		try{

		Connection con = DBConnection.getConnection();

		PreparedStatement ps = con.prepareStatement(
		"SELECT p.* FROM payments p " +
		"JOIN loans l ON p.loan_id=l.loan_id " +
		"JOIN customers c ON l.customer_id=c.customer_id " +
		"WHERE c.admin_id=?"
		);

		ps.setInt(1, adminId);

		rs = ps.executeQuery();

		}catch(Exception e){
		e.printStackTrace();
		}

		return rs;

		}
	
	public static int getPaidInstallments(int loanId){

	    int count = 0;

	    try{

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(
	        "SELECT COUNT(*) FROM payments WHERE loan_id=? AND type='EMI'"
	        );

	        ps.setInt(1, loanId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()){
	            count = rs.getInt(1);
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return count;
	}


}