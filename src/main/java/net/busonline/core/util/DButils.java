package net.busonline.core.util;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;  
  
  

public class DButils {
	 private static String url = "jdbc:mysql://192.168.109.227:3306/cctv";  
	    private static String user = "baidul";  
	    private static String psw = "1wq2a3sz";  
	    private static  Connection conn;  
	    static {  
	        try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (ClassNotFoundException e) {  
	            e.printStackTrace();  
	            throw new RuntimeException(e);  
	        }  
	    }
	    public static Connection getConnection() {  
	        if(null == conn) {  
	            try {  
	                conn = DriverManager.getConnection(url, user, psw);  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            }  
	        }  
	        return conn;  
	    }  
	    public static void closeResources(Connection conn,Statement pstmt,ResultSet rs) {  
	        if(null != rs) {  
	            try {  
	                rs.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } finally {  
	                if(null != pstmt) {  
	                    try {  
	                        pstmt.close();  
	                    } catch (SQLException e) {  
	                        e.printStackTrace();  
	                        throw new RuntimeException(e);  
	                    } finally {  
	                        if(null != conn) {  
	                            try {  
	                                conn.close();  
	                            } catch (SQLException e) {  
	                                e.printStackTrace();  
	                                throw new RuntimeException(e);  
	                            }  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	    } 
	    
}
