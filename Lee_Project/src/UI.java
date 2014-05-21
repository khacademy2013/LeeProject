import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

public class UI extends JFrame{
	private static final long serialVersionUID = -8211458032602152709L;
	
	JFrame fAdd , fList;
	JPanel pNorth2, pNorth1,pCenter,pNorth3,pSouth, pView, p1,p2;
	JButton bSearch,bSearchAll,bInsert,bDelete,bUpdate,bDeleteAll,bWrite,bCreate,bContact;
	JLabel lSearch,lName,lCell,lEmail,lCompany,lPosition,lAddr,lGroup,lvName,lvCell,lvEmail,lvCompany,lvPosition,lvAddr, lvAddr2, lnow , ldate;
	Choice cGroup;
	JTextArea area;
	JMenuBar bar;
	JMenu menu;
	JMenuItem addUser, viewUser, exit;
	JDateChooser date;
	Calendar c;
	static JList userList;
	String syear,smonth,sday;
	static String userN, dateText;
	static String tableN;
	static JTextField search, name, cell, email, company,position,addr,userName;
	TableRowSorter trs;
	
	GridBagConstraints con = new GridBagConstraints();
	JTable jt;
	JScrollPane scroll,scroll2;
	static DefaultTableModel dtm;
	
	static int selectRow = -1;
	static int num = -1;
	static Vector<PersonDTO> list;


	
	UI(){
		super("명함관리툴");	
		Toolkit tk= Toolkit.getDefaultToolkit();
		setSize(1200,700);
		setLayout(new GridBagLayout());
		setLocation(tk.getScreenSize().width/2-600,tk.getScreenSize().height/2-370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		userN="사용자를 선택하세요";
		
		date=new JDateChooser();
		
		bar= new JMenuBar();
		menu= new JMenu("메뉴");
		addUser=new JMenuItem("사용자 추가");
		viewUser=new JMenuItem("사용자 목록");
		exit=new JMenuItem("Exit");
	
		menu.add(addUser);
		menu.add(viewUser);
		menu.addSeparator();
		menu.add(exit);
		bar.add(menu);
		bar.setBackground(Color.ORANGE);

		
		pNorth2= new JPanel();
		pNorth2.setLayout(null);
		pNorth3= new JPanel();
		pNorth3.setLayout(null);
		pCenter= new JPanel();	
		pView= new JPanel();
		pView.setLayout(null);
		
		bSearch= new JButton("검색");
		bSearchAll= new JButton("전체보기");
		bInsert= new JButton("등록");
		bDelete= new JButton("삭제");
		bUpdate= new JButton("수정");
		bDeleteAll = new JButton("전체삭제");
		bWrite=new JButton("새로쓰기");
		bCreate=new JButton("만들기");
		bContact=new JButton("선택");
		
		lSearch= new JLabel("              검    색");
		lGroup= new JLabel("분    류");
		lName= new JLabel("이    름");
		lCell= new JLabel("핸드폰");
		lEmail= new JLabel("이메일");
		lCompany= new JLabel("회사명");
		lPosition= new JLabel("직    급");
		lAddr= new JLabel("주    소");
		
		lvName=new JLabel("");
		lvCell= new JLabel("");
		lvEmail= new JLabel("");
		lvCompany= new JLabel("");
		lvPosition= new JLabel("");
		lvAddr= new JLabel("");
		lvAddr2= new JLabel("");
		ldate=new JLabel("등록일");
		
		lnow=new JLabel("User: "+userN);
		
		cGroup= new Choice();
		cGroup.add("NONE");
		cGroup.add("FAMILY");
		cGroup.add("FRIEND");
		cGroup.add("OFFICE");
		cGroup.add("CUSTOMER");
		cGroup.add("VIP");
		cGroup.setSize(10, 100);
		
		search= new JTextField(15);
		name =  new JTextField(10);
		cell =  new JTextField(15);
		email =  new JTextField(15);
		company=  new JTextField(15);
		position=  new JTextField(7);
		addr= new JTextField(30);
		userName=new JTextField(15);
		
	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {"No","등록일","그룹", "이름", "휴대폰","이메일","회사","직급","주소"});
		
		userList=new JList();
		
		jt = new JTable(dtm);
		scroll = new JScrollPane(jt);
		scroll.setVerticalScrollBar(new JScrollBar());
		
		jt.setAutoCreateRowSorter(true);
		trs=new TableRowSorter(jt.getModel());
		jt.setRowSorter(trs);
			
		//-----pNorth3--------------------
		pNorth3.setSize(1000,100);
	
		lnow.setBounds(500,-31,200,100);
		pNorth3.add(lnow);
		lnow.setFont(new Font("고딕체",Font.BOLD,15));
		lnow.setForeground(Color.blue);
		
		lSearch.setBounds(50,-31,100,100);
		pNorth3.add(lSearch);
		
		bar.setBounds(10,7,40,20);
		pNorth3.add(bar);
		
		addUser.addActionListener(new MClick());
		viewUser.addActionListener(new MClick());
		exit.addActionListener(new MClick());
		
		search.setBounds(150,7,130,22);
		pNorth3.add(search);
		search.addActionListener(new FieldEnter());
		
		bDeleteAll.setBounds(1010,4,120,30);
		pNorth3.add(bDeleteAll);
		bDeleteAll.addActionListener(new BClick());
		bDeleteAll.setBackground(Color.black);
		bDeleteAll.setForeground(Color.white);
		
		bSearch.setBounds(290,4,70,30);
		pNorth3.add(bSearch);
		bSearch.addActionListener(new BClick());
		
		bSearchAll.setBounds(370,4,100,30);
		pNorth3.add(bSearchAll);
		bSearchAll.addActionListener(new BClick());
		
		bInsert.setBounds(710,4,70,30);
		pNorth3.add(bInsert);
		bInsert.setBackground(Color.yellow);
		bInsert.addActionListener(new BClick());
		
		bUpdate.setBounds(790,4,70,30);
		pNorth3.add(bUpdate);
		bUpdate.addActionListener(new BClick());

		bDelete.setBounds(870,4,70,30);
		pNorth3.add(bDelete);
		bDelete.setBackground(Color.red);
		bDelete.addActionListener(new BClick());
		
					
		//-----pNorth2--------------------		

		pNorth2.setSize(1000, 400);
		pNorth2.setBackground(new Color(180,150,130));
		
		date.setBounds(560,17,120,23);
		pNorth2.add(date);		
		
		ldate.setBounds(510, 17,100,23);
		pNorth2.add(ldate);
		
		bWrite.setBounds(580,150,90,40);
		pNorth2.add(bWrite);
		bWrite.addActionListener(new BClick());
		
		lName.setBounds(30,-20,100,100);
		pNorth2.add(lName);
		name.setBounds(75,19,120,22);
		pNorth2.add(name);
		
		lGroup.setBounds(280,-20,50,100);
		pNorth2.add(lGroup);
		cGroup.setBounds(330,19,100,100);
		pNorth2.add(cGroup);			
		
		lCell.setBounds(30,25,100,100);
		pNorth2.add(lCell);		
		cell.setBounds(75,65,120,22);
		pNorth2.add(cell);		
		
		lEmail.setBounds(280,25,100,100);
		pNorth2.add(lEmail);		
		email.setBounds(330,65,170,22);
		pNorth2.add(email);
		
		lCompany.setBounds(30,70,100,100);
		pNorth2.add(lCompany);		
		company.setBounds(75,110,170,22);
		pNorth2.add(company);
		
		lPosition.setBounds(280,70,100,100);
		pNorth2.add(lPosition);		
		position.setBounds(330,110,100,22);
		pNorth2.add(position);
		
		lAddr.setBounds(30,115,100,100);
		pNorth2.add(lAddr);	
		addr.setBounds(75,155,425,22);
		pNorth2.add(addr);
		
	
		pView.setBounds(710,8,430,190);
		pNorth2.add(pView);
		
		pCenter.add(jt.getTableHeader());
		
		
		pView.add(lvName);			// 가상 명함에 뿌려질 값들
		pView.add(lvPosition);
		pView.add(lvCell);
		pView.add(lvEmail);
		pView.add(lvAddr);	
		pView.add(lvAddr2);	
		pView.add(lvCompany);
		
		lvName.setBounds(30,0,350,110);
		lvName.setFont(new Font("고딕체",Font.BOLD , 32));

		lvPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lvPosition.setBounds(210,7,200,110);
		lvPosition.setFont(new Font("궁서체",Font.BOLD , 17));
		
		lvCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lvCompany.setBounds(60,-29,350,110);
		lvCompany.setFont(new Font("바탕체",Font.BOLD, 18));
		lvCompany.setForeground(new Color(130,100,53));

		lvCell.setHorizontalAlignment(SwingConstants.RIGHT);
		lvCell.setBounds(60,40,350,110);
		lvCell.setFont(new Font("고딕체",Font.BOLD , 15));
		
		lvEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lvEmail.setBounds(60,60,350,110);
		lvEmail.setFont(new Font("고딕체",Font.BOLD , 15));

		lvAddr.setHorizontalAlignment(SwingConstants.RIGHT);
		lvAddr.setBounds(10,90,400,110);
		lvAddr.setFont(new Font("고딕체",Font.ROMAN_BASELINE, 15));

		lvAddr2.setHorizontalAlignment(SwingConstants.RIGHT);
		lvAddr2.setBounds(10,110,400,110);
		lvAddr2.setFont(new Font("고딕체",Font.ROMAN_BASELINE, 15));
		

		//-----메인--------------------
		con.fill = con.BOTH;	//메인 구성에 필요한 각 패널의 위치
		con.ipadx = con.ipady = 0;
		con.weightx=1.0;
				
		con.weighty=0.2;
		con.gridx=0;
		con.gridy=1;
		add(pNorth3, con);
		
		con.weighty=1.0;
		con.gridx=0;
		con.gridy=2;
		add(pNorth2, con);
		
		con.weighty=0.03;
		con.gridx=0;
		con.gridy=4;
		add(jt.getTableHeader(), con);
		
		con.weighty=0.0;
		con.gridx=0;
		con.gridy=5;
		add(scroll,con);
		
		
		selectRow =jt.getSelectedRow();
		jt.addMouseListener(new TableClick());
		jt.setRowHeight(20);

		jt.getColumnModel().getColumn(0).setPreferredWidth(10);  //컬럼 헤더의 너비
		jt.getColumnModel().getColumn(1).setPreferredWidth(50);  
		jt.getColumnModel().getColumn(2).setPreferredWidth(70);  
		jt.getColumnModel().getColumn(3).setPreferredWidth(130);  
		jt.getColumnModel().getColumn(4).setPreferredWidth(130);  
		jt.getColumnModel().getColumn(5).setPreferredWidth(130);  
		jt.getColumnModel().getColumn(6).setPreferredWidth(130);  
		jt.getColumnModel().getColumn(7).setPreferredWidth(100);  
		jt.getColumnModel().getColumn(8).setPreferredWidth(250); 
		
		
		jt.getTableHeader().setReorderingAllowed(false); 	// 컬럼 헤더 고정
				
		DB_switch db=DB_switch.getInstance();
		DB_Perform go=DB_Perform.getInstance();
		
		
		db.on();
		go.showTable(db.conn);
		db.off();
		
		
		
		bDelete.setEnabled(false);
		bInsert.setEnabled(false);
		bUpdate.setEnabled(false);	
		jt.setGridColor(Color.gray);
		setVisible(true);
	}

	public String currentDate() {
		c= Calendar.getInstance();
		c.setTime((Date)date.getSpinner().getValue());
		syear=c.get(Calendar.YEAR)+"";		
		smonth = c.get(Calendar.MONTH)+1<10 ? "0" +(c.get(Calendar.MONTH)+1) : c.get(Calendar.MONTH)+1+""; 
		sday=c.get(Calendar.DATE) < 10 ? "0" + c.get(Calendar.DATE) : c.get(Calendar.DATE)+"";	
		dateText=syear+smonth+sday;
		return dateText;
	}
	
	void formInit() {
		cGroup.select("NONE");
		name.setText("");
		cell.setText("");
		email.setText("");
		company.setText("");
		position.setText("");
		addr.setText("");
		date.setDate(new Date());
		
	}
	
	public static void main(String[] args) {		
		new UI();
	}

	class BClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj=e.getSource();
			DB_switch db= DB_switch.getInstance();
			DB_Perform go=DB_Perform.getInstance();
			db.on();
		
			if(bInsert==(JButton)obj){	// 사용자의 테이블에 새로운 데이터 추가버튼
				if(!name.getText().equals("")){					
					go.insert(db.conn,currentDate() ,cGroup.getSelectedItem(), name.getText(), cell.getText(), email.getText(),
							company.getText(), position.getText(), addr.getText());				
					go.select(db.conn);
					formInit();
				}else{
					JOptionPane.showMessageDialog(null, "이름이 비어있습니다.");
				} 
			}	
			if(bUpdate==(JButton)obj){	// 선택 셀의 데이터를 수정할 때 버튼
				selectRow = jt.convertRowIndexToModel(jt.getSelectedRow());
				PersonDTO dto = list.get(selectRow);
				num = dto.getNo();
				;
				String c[]={"date","groups","name", "cell", "email","company","position","addr"};
				String t[]={currentDate(),cGroup.getSelectedItem(),name.getText(),cell.getText(),email.getText(), company.getText(), 
						position.getText(), addr.getText()};
				
				for(int i=0; i<8; i++){
					go.update(db.conn, c[i], t[i], num);
				}
				go.select(db.conn);
				
			}
			
			if(bSearchAll==(JButton)obj){ // 사용자의 모든 데이터 보여주기
				go.select(db.conn);
				jt.setGridColor(Color.gray);
			}
			
			if(bDelete==(JButton)obj){ // 선택 셀의 행 지움
				int a=JOptionPane.showConfirmDialog(null,list.get(selectRow).getName()+"님을 삭제할까요?");
				if(a==0){
					selectRow = jt.convertRowIndexToModel(jt.getSelectedRow());
					PersonDTO dto = list.get(selectRow);
					num = dto.getNo();
					go.delete(db.conn, num);
					go.select(db.conn);
					formInit();
					cGroup.select("NONE");
					date.setDate(new Date());
					lvName.setText("");
					lvCell.setText("");
					lvEmail.setText("");
					lvCompany.setText("");
					lvPosition.setText("");
					lvAddr.setText("");
					lvAddr.setText("");
					lvAddr2.setText("");
						
				}else ;		
			}
			
			if(bSearch==(JButton)obj){	// 검색기능을 통해 검색버튼을 눌렀을 때
				go.search(db.conn);
				jt.setGridColor(Color.blue);
				
			}
			
			if(bDeleteAll==(JButton)obj){  // 해당사용자 데이터 모두 비우기
				go.deleteAll(db.conn);
				go.select(db.conn);	
				formInit();
				cGroup.select("NONE");
				date.setDate(new Date());
				lvName.setText("");
				lvCell.setText("");
				lvEmail.setText("");
				lvCompany.setText("");
				lvPosition.setText("");
				lvAddr.setText("");
				lvAddr.setText("");
				lvAddr2.setText("");
					
			}
			
			if(bWrite==(JButton)obj){  // 입력폼 리셋하기
				formInit();		
			}
			
			if(bCreate==(JButton)obj){  // 사용자생성에서 생성버튼 눌렀을 때
				formInit();
				bInsert.setEnabled(true);
				userN=userName.getText();
				if (userN.contains(" ")) {
					JOptionPane.showMessageDialog(null, "ID에 공백이 포함될 수 없습니다.");
					userName.setText("");
				}
				else if(userN.length()>14){
					JOptionPane.showMessageDialog(null, "14자 이하로 입력");
					userName.setText("");
				}
				else if(userN.charAt(0)>='0'&&userN.charAt(0)<='9'){
					userN='u'+userName.getText();
					lnow.setText("User: "+userN);
					go.create(db.conn, userN);
					go.select(db.conn);
					go.showTable(db.conn);
					fAdd.dispose();
				}else{
					lnow.setText("User: "+userN);
					go.create(db.conn, userN);
					go.select(db.conn);
					go.showTable(db.conn);
					fAdd.dispose();
				}
							
			}
			
			if(bContact==(JButton)obj){  // 사용자선택에서 선택버튼 눌렀을 때
				bInsert.setEnabled(true);
				userN=userList.getSelectedValue()+"";
				go.select(db.conn);
				lnow.setText("User: "+userN);
				fList.dispose();
				formInit();
			}
			db.off();
		}
	
	}
	
	class TableClick extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {	//셀의 내용을 마우스로 클릭했을 때 실행될 실행문
			bUpdate.setEnabled(true);
			bDelete.setEnabled(true);
			selectRow = jt.convertRowIndexToModel(jt.getSelectedRow());	// 선택한 셀이 몇번째인지 알려줌			
			String form = list.get(selectRow).getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			try {
				d = sdf.parse(form);
				date.setDate(d);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			cGroup.select(list.get(selectRow).getGroup());
			name.setText(list.get(selectRow).getName());
			cell.setText(list.get(selectRow).getCell());
			email.setText(list.get(selectRow).getEmail());
			company.setText(list.get(selectRow).getCompany());
			position.setText(list.get(selectRow).getPosition());
			addr.setText(list.get(selectRow).getAddr());	
			
			lvName.setText(list.get(selectRow).getName());
			lvCell.setText(list.get(selectRow).getCell());
			lvEmail.setText(list.get(selectRow).getEmail());
			lvCompany.setText(list.get(selectRow).getCompany());
			lvPosition.setText(list.get(selectRow).getPosition());		
			if(list.get(selectRow).getAddr().length()>23){
				lvAddr.setText(list.get(selectRow).getAddr().substring(0,23));
				lvAddr2.setText(list.get(selectRow).getAddr().substring(23));
			}else{
				lvAddr.setText(list.get(selectRow).getAddr());
				lvAddr2.setText("");
			}
		
			if(cGroup.getSelectedItem().equals("NONE")){
				pView.setBackground(Color.white);
			}
			if(cGroup.getSelectedItem().equals("FAMILY")){
				pView.setBackground(new Color(255,227,250));
			}
			if(cGroup.getSelectedItem().equals("FRIEND")){
				pView.setBackground(new Color(216,251,211));
			}
			if(cGroup.getSelectedItem().equals("OFFICE")){
				pView.setBackground(new Color(227,239,255));
			}
			if(cGroup.getSelectedItem().equals("CUSTOMER")){
				pView.setBackground(new Color(178,235,244));
			}
			if(cGroup.getSelectedItem().equals("VIP")){
				pView.setBackground(new Color(250,247,135));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			jt.setSelectionBackground(Color.yellow);
		}
		
	}
	
	class FieldEnter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ee)  {
			DB_switch db= DB_switch.getInstance();
			DB_Perform go=DB_Perform.getInstance();
			db.on();
			go.search(db.conn);
			jt.setGridColor(Color.blue);		
			db.off();
		}	
	}
	
	class MClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ee)  {
			String obj=ee.getActionCommand();
			if(obj.equals("사용자 추가")){
				userName.setText("");
				fAdd=new JFrame("사용자 생성");
				fAdd.setLayout(new GridLayout(3,3));
				fAdd.setBounds(300,300,300,200);
				fAdd.add(new JLabel("    사용자명 ( 14자 이내, 특수문자 제외 )"));
				fAdd.add(userName);
				fAdd.add(bCreate);
				bCreate.addActionListener(new BClick());							
				fAdd.setVisible(true);
			}
			
			if(obj.equals("사용자 목록")){
				fList=new JFrame("사용자 목록");
				fList.setLayout(new GridLayout(2,2));
				fList.setBounds(300,300,300,200);	
				scroll2=new JScrollPane(userList);			
				fList.add(scroll2);
				fList.add(bContact);
				bContact.addActionListener(new BClick());							
				fList.setVisible(true);
			}			
			
			if(obj.equals("Exit")){
				System.exit(0);
			}

		}	
	}
	
	
}

