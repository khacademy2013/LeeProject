
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DB_Perform{ // 싱글톤으로 만든 DB 수행문(select, insert, delete, update) 4가지를 수행할 클래스
	
	public static Statement stmt=null;
	static ResultSet rs=null;
	int n;
	int count=0;
	Vector<String> v=new Vector<>();
	private DB_Perform() {		
	}
	
	public static DB_Perform getInstance(){		
		return new DB_Perform();
		
	}
	
	public synchronized int insert(Connection conn,String...value){ // ---------------추가기능
		
		try {
			String s=Arrays.toString(value);
			s=s.replace("[","");
			s=s.replace("]","");
			s=s.replace(", ",",");
			s=s.replace(",","','");
			
			stmt=conn.createStatement();			
			n=stmt.executeUpdate("insert into "+UI.userN+" (date,groups,name,cell,email,company,position,addr) values('"+s+"')");
			if(n==1){
				JOptionPane.showMessageDialog(null, "성공적으로 추가 되었습니다.");
			}else{
				JOptionPane.showMessageDialog(null, "추가 실패");
			}
			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	public synchronized int delete(Connection conn,int where){ // ---------------삭제기능
		
		try {
			stmt=conn.createStatement();			
			n=stmt.executeUpdate("delete from "+UI.userN+" where no="+where);
			
			if(n>0){
				JOptionPane.showMessageDialog(null, "성공적으로 삭제 되었습니다.");
			}else{
				JOptionPane.showMessageDialog(null, "삭제 실패");
			}			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	public synchronized int deleteAll(Connection conn){ // --------------- 유저삭제기능
		
		try {
			stmt=conn.createStatement();			
			n=stmt.executeUpdate("delete from "+UI.userN);
			
			if(n==UI.list.size()){
				JOptionPane.showMessageDialog(null, "성공적으로 삭제 되었습니다.");
			}else{
				JOptionPane.showMessageDialog(null, "삭제 실패");
			}			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	public synchronized int update(Connection conn,String cul,String newV, int no){ // --------------- 수정기능
		
		try {

			stmt=conn.createStatement();			
			n=stmt.executeUpdate("UPDATE "+UI.userN+" SET "+cul+"='"+newV+"' WHERE NO="+no);
			
			if(n==1){
				count++;
				if(count==8){
					JOptionPane.showMessageDialog(null, "성공적으로 변경 되었습니다.");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "변경 실패");
			}
			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
				//e.printStackTrace();
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	public synchronized int select(Connection conn){ // --------------- 전체조회기능
		UI.dtm.setRowCount(0); 
		UI.list = new Vector<PersonDTO>();
	
		try {

			stmt=conn.createStatement();
			rs=stmt.executeQuery("select * from "+UI.userN);
			int countN=1;
			while(rs.next()){				
				PersonDTO dto=new PersonDTO();
				dto.setCount(countN);
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setGroup(rs.getString("groups"));
				dto.setName(rs.getString("name"));
				dto.setCell(rs.getString("cell"));
				dto.setEmail(rs.getString("email"));
				dto.setCompany(rs.getString("company"));
				dto.setPosition(rs.getString("position"));
				dto.setAddr(rs.getString("addr"));
				countN++;
				UI.list.add(dto);				
			}
			
			for(int i=0; i<UI.list.size(); i++){
				Vector<String> data = new Vector<String>();
				PersonDTO dto = UI.list.get(i);	
				data.add(dto.getCount()+"");
				data.add(dto.getDate());
				data.add(dto.getGroup());
				data.add(dto.getName());
				data.add(dto.getCell());
				data.add(dto.getEmail());
				data.add(dto.getCompany());
				data.add(dto.getPosition());
				data.add(dto.getAddr());
				UI.dtm.addRow(data);
			}
			
			
		} catch (SQLException e) {			
				JOptionPane.showMessageDialog(null, "사용자를 선택하세요");
				//e.printStackTrace();
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	
	public synchronized int search(Connection conn){
		UI.dtm.setRowCount(0); 
		UI.list = new Vector<PersonDTO>();
		String word= UI.search.getText();	
		try {

			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM "+UI.userN+" WHERE groups LIKE '%"+word+"%' OR name LIKE '%"+word+"%' "+ 
					"OR cell LIKE '%"+word+"%' OR email LIKE '%"+word+"%' OR company LIKE '%"+word+"%' OR position LIKE '%"+word+
					"%'OR addr LIKE '%"+word+"%'");
			int countN=1;
			while(rs.next()){				
				PersonDTO dto=new PersonDTO();
				dto.setCount(countN);
				dto.setNo(rs.getInt("no"));
				dto.setDate(rs.getString("date"));
				dto.setGroup(rs.getString("groups"));
				dto.setName(rs.getString("name"));
				dto.setCell(rs.getString("cell"));
				dto.setEmail(rs.getString("email"));
				dto.setCompany(rs.getString("company"));
				dto.setPosition(rs.getString("position"));
				dto.setAddr(rs.getString("addr"));
				countN++;
				UI.list.add(dto);				
			}
			
			for(int i=0; i<UI.list.size(); i++){
				Vector<String> data = new Vector<String>();
				PersonDTO dto = UI.list.get(i);	
				data.add(dto.getCount()+"");
				data.add(dto.getDate());
				data.add(dto.getGroup());
				data.add(dto.getName());
				data.add(dto.getCell());
				data.add(dto.getEmail());
				data.add(dto.getCompany());
				data.add(dto.getPosition());
				data.add(dto.getAddr());
				UI.dtm.addRow(data);
			}
			
			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
				//e.printStackTrace();
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}	
	
	public synchronized int create(Connection conn,String username){ // --------------- 추가기능
		
		try {
			stmt=conn.createStatement();			
			
			boolean a=stmt.execute("CREATE TABLE "+UI.userN+" (no INT(250) NOT NULL AUTO_INCREMENT,DATE date,"
					+ "groups VARCHAR(10) DEFAULT NULL,name VARCHAR(20) NOT NULL,cell VARCHAR(20) DEFAULT NULL,"
					+ "email VARCHAR(25) DEFAULT NULL,company VARCHAR(25) DEFAULT NULL,"
					+ "position VARCHAR(20) DEFAULT NULL,addr VARCHAR(60) DEFAULT NULL,"
					+ "PRIMARY KEY (no)) ENGINE=INNODB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8");
		
			if(a==false){			
					JOptionPane.showMessageDialog(null, "성공적으로 생성 되었습니다.");				
			}else{
				JOptionPane.showMessageDialog(null, "생성 실패");
			}
			
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "");
				e.printStackTrace();
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;		
	}
	
	public synchronized int showTable(Connection conn){

		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SHOW TABLES");
			UI.userList.removeAll();
			while(rs.next()){				
				v.add(rs.getString("Tables_in_namecard"));							
			}
			UI.userList.setListData(v);
		} catch (SQLException e) {			
				//JOptionPane.showMessageDialog(null, "명령어 비정상적 접근입니다");
				//e.printStackTrace();
		} catch (Exception me){
				//JOptionPane.showMessageDialog(null, "비정상적 접근입니다");
		} 
		return n;
			
	}	
}

