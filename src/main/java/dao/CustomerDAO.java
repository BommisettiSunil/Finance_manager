package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import util.DBConnection;

public class CustomerDAO {

    public static void addCustomer(Customer c) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO customers(name,phone,address,email,admin_id) VALUES(?,?,?,?,?)"
            );

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getAdminId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Customer> getCustomers(int adminId){

    	List<Customer> customers=new ArrayList<>();

    	try{

    	Connection con=DBConnection.getConnection();

    	PreparedStatement ps=con.prepareStatement(
    	"SELECT * FROM customers WHERE admin_id=?"
    	);

    	ps.setInt(1,adminId);

    	ResultSet rs=ps.executeQuery();

    	while(rs.next()){

    	Customer c=new Customer();

    	c.setCustomerId(rs.getInt("customer_id"));
    	c.setName(rs.getString("name"));
    	c.setPhone(rs.getString("phone"));
    	c.setAddress(rs.getString("address"));
    	c.setEmail(rs.getString("email"));
    	c.setAdminId(rs.getInt("admin_id"));

    	customers.add(c);

    	}

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return customers;

    	}
    public static ResultSet searchCustomer(String name,int adminId){

    	ResultSet rs=null;

    	try{

    	Connection con=DBConnection.getConnection();

    	PreparedStatement ps=con.prepareStatement(
    	"SELECT * FROM customers WHERE name LIKE ? AND admin_id=?"
    	);

    	ps.setString(1,"%"+name+"%");
    	ps.setInt(2,adminId);

    	rs=ps.executeQuery();

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	return rs;

    	}
    public static void deleteCustomer(int id,int adminId){

    	try{

    	Connection con=DBConnection.getConnection();

    	PreparedStatement ps=con.prepareStatement(
    	"DELETE FROM customers WHERE customer_id=? AND admin_id=?"
    	);

    	ps.setInt(1,id);
    	ps.setInt(2,adminId);

    	ps.executeUpdate();

    	}catch(Exception e){
    	e.printStackTrace();
    	}

    	}
    public static int getTotalCustomers(int adminId){

    	int count=0;

    	try{

    	Connection con=DBConnection.getConnection();

    	PreparedStatement ps=con.prepareStatement(
    	"SELECT COUNT(*) FROM customers WHERE admin_id=?"
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
}