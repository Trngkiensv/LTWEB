package config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionSQL {	
    private static String url = "jdbc:mysql://localhost:3306/webshop";
    private static String user = "root";
    private static String password = "1234";
    public static Connection getConnection() {
    	Connection connection = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(connection==null) System.out.println("Connect failed!");

        return connection;
    }
    public static List<Integer> getAllCartIDs() {
        List<Integer> cartIDs = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            connection = ConnectionSQL.getConnection();
            if (connection != null) {
                // Tạo Statement
                statement = connection.createStatement();

                // Thực thi truy vấn SQL
                String query = "SELECT cartID FROM cart";
                resultSet = statement.executeQuery(query);

                // Lấy dữ liệu từ ResultSet
                while (resultSet.next()) {
                    int cartID = resultSet.getInt("cartID");
                    cartIDs.add(cartID); // Thêm vào danh sách
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartIDs;
    }
    public static void main(String[] args) {
		ConnectionSQL son = new ConnectionSQL();
		son.getConnection();
		System.out.println(son.getAllCartIDs());
	}
}
