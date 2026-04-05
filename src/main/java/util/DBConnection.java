package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    public static Connection getConnection(){

        Connection con = null;

        try{

            Properties props = new Properties();

            InputStream is = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            props.load(is);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, pass);

        }catch(Exception e){
            e.printStackTrace();
        }

        return con;
    }
}