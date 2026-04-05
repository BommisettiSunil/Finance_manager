package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Loan;
import util.DBConnection;

public class LoanDAO {

    public static void addLoan(Loan loan){

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO loans(customer_id,loan_amount,interest_rate,duration,emi,start_date,interest_type)\r\n"
            + "VALUES(?,?,?,?,?,?,?)"
            );

            ps.setInt(1, loan.getCustomerId());
            ps.setDouble(2, loan.getLoanAmount());
            ps.setDouble(3, loan.getInterestRate());
            ps.setInt(4, loan.getDuration());
            ps.setDouble(5, loan.getEmi());
            ps.setDate(6, loan.getStartDate());
            ps.setString(7, loan.getInterestType());

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


//    public static ResultSet getLoansByCustomer(int customerId){
//
//        ResultSet rs=null;
//
//        try{
//
//            Connection con = DBConnection.getConnection();
//
//            PreparedStatement ps = con.prepareStatement(
//            "SELECT * FROM loans WHERE customer_id=?"
//            );
//
//            ps.setInt(1, customerId);
//
//            rs = ps.executeQuery();
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return rs;
//    }
    public static ResultSet getCustomerLoans(int customerId){

    	ResultSet rs=null;

    	try{

    	Connection con = DBConnection.getConnection();

    	PreparedStatement ps = con.prepareStatement(
    	"SELECT * FROM loans WHERE customer_id=?"
    	);

    	ps.setInt(1, customerId);

    	rs = ps.executeQuery();

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return rs;

    	}
    public static Loan getLoanById(int loanId){

        Loan loan = null;

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM loans WHERE loan_id=?")){

            ps.setInt(1, loanId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                loan = new Loan();

                loan.setLoanId(rs.getInt("loan_id"));
                loan.setCustomerId(rs.getInt("customer_id"));
                loan.setLoanAmount(rs.getDouble("loan_amount"));
                loan.setInterestRate(rs.getDouble("interest_rate"));
                loan.setDuration(rs.getInt("duration"));
                loan.setEmi(rs.getDouble("emi"));
                loan.setStartDate(rs.getDate("start_date"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return loan;
    }
    public static int getTotalLoans(int adminId){

    	int count=0;

    	try{

    	Connection con=DBConnection.getConnection();

    	PreparedStatement ps=con.prepareStatement(
    	"SELECT COUNT(*) FROM loans l "+
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
    public static ResultSet getAllLoans(int adminId){

    	ResultSet rs = null;

    	try{

    	Connection con = DBConnection.getConnection();

    	PreparedStatement ps = con.prepareStatement(
    	"SELECT l.* FROM loans l JOIN customers c ON l.customer_id=c.customer_id WHERE c.admin_id=?"
    	);

    	ps.setInt(1, adminId);

    	rs = ps.executeQuery();

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return rs;

    	}
    public static ResultSet getReminderDetails(int loanId){

    	ResultSet rs = null;

    	try{

    	Connection con = DBConnection.getConnection();

    	PreparedStatement ps = con.prepareStatement(
    	"SELECT c.name,c.email,l.emi FROM customers c JOIN loans l ON c.customer_id=l.customer_id WHERE l.loan_id=?"
    	);

    	ps.setInt(1, loanId);

    	rs = ps.executeQuery();

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return rs;

    	}
   
}
