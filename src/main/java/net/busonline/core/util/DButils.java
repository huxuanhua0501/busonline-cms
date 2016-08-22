package net.busonline.core.util;
import java.io.InputStream;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import ch.qos.logback.core.db.dialect.DBUtil;  
  
  

public class DButils {
	 private static String url = "jdbc:mysql://192.168.109.227:3306/cctv";  
	    private static String user = "baidul";  
	    private static String psw = "1wq2a3sz";  
	    private static  Connection conn;  
	    private static DataSource dataSource = null;	
	    public static ThreadLocal<Connection> container = new ThreadLocal<Connection>();
	    /*static {  
	        try {  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (ClassNotFoundException e) {  
	            e.printStackTrace();  
	            throw new RuntimeException(e);  
	        }  
	    }*/
	    static {
			try{
				InputStream in = DBUtil.class.getClassLoader()
						               .getResourceAsStream("ds.properties");
	            Properties props = new Properties();
				props.load(in);
				dataSource = DruidDataSourceFactory.createDataSource(props);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	    /*public static Connection getConnection() {  
	        if(null == conn) {  
	            try {  
	                conn = DriverManager.getConnection(url, user, psw);  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            }  
	        }  
	        return conn;  
	    }  */
	    public static Connection getConnection(){
	        Connection conn =null;
	        try{
	            conn = dataSource.getConnection();
	            System.out.println(Thread.currentThread().getName()+"连接已经开启......");
	            container.set(conn);
	        }catch(Exception e){
	            System.out.println("连接获取失败");
	            e.printStackTrace();
	        }
	        return conn;
	    }
	    
	    public static void close(){
	        try{
	            Connection conn=container.get();
	            if(conn!=null){
	                conn.close();
	                System.out.println(Thread.currentThread().getName()+"连接关闭");
	            }
	        }catch(SQLException e){
	            throw new RuntimeException(e.getMessage(),e);
	        }finally{
	            try {
	                container.remove();//从当前线程移除连接切记
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
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
