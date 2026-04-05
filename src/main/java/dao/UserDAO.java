package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class UserDAO {

public static ResultSet login(String username,String password){

ResultSet rs=null;

try{

Connection con=DBConnection.getConnection();

PreparedStatement ps=con.prepareStatement(
"SELECT * FROM users WHERE username=? AND password=?"
);

ps.setString(1,username);
ps.setString(2,password);

rs=ps.executeQuery();

}catch(Exception e){
e.printStackTrace();
}

return rs;

}

public static void signup(String name,String username,String password){

try{

Connection con=DBConnection.getConnection();

PreparedStatement ps=con.prepareStatement(
"INSERT INTO users(name,username,password) VALUES(?,?,?)"
);

ps.setString(1,name);
ps.setString(2,username);
ps.setString(3,password);

ps.executeUpdate();

}catch(Exception e){
e.printStackTrace();
}

}

}
