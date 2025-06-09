package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String HOST = "localhost:3306";
    private static final String DATABASE = "db_billing";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;

    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
                connection = null;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
