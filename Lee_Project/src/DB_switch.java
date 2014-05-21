import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class DB_switch { //싱글톤으로 만든 데이터베이스 연동 온/오프 기능을 담당할 클래스
	
	public Connection conn=null;
	private DB_switch() {
		
	}
		
	public static DB_switch getInstance(){
		return new DB_switch();
		
	}
	
	public Connection on(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/namecard","root","0000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return conn;
	}	
	
	public void off(){
		try { 							
			if(conn!=null){
				conn.close();
				System.out.println("connOFF");				
			}	
			if(DB_Perform.stmt!=null){
				DB_Perform.stmt.close(); 
					System.out.println("stmtOFF");
			}
			
			if(DB_Perform.rs!=null){
				DB_Perform.rs.close(); 
					System.out.println("rsOFF");
			}	
											
		} catch(SQLException ex){;}		
	}
	
}


