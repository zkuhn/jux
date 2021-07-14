package db;
import java.sql.*;

public class DbSingleton {
    public DbSingleton(){
        String url = "jdbc:mysql://localhost:3306";
        try{
            Connection conn = DriverManager.getConnection(url);
        } catch(Exception e) {
            System.out.println("db conn failed");
        }
    }
}
