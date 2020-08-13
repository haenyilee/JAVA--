package com.sist.client;
import javax.swing.*; 	// 버튼 등
import com.sist.data.CarManager;
import com.sist.data.CarVO;
import java.awt.*; 		// Color, Layout 배치 등
import java.awt.event.*;// 인터페이스
import java.util.ArrayList;
import javax.print.DocFlavor.STRING;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import com.sist.common.Function;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.net.*;

public class ClientMainFrame extends JFrame implements ActionListener,MouseListener,Runnable{
	
	JLabel title=new JLabel("3조 프로젝트",JLabel.CENTER);
	Login login = new Login();
	ControlPanel cp;
	ButtonForm bf=new ButtonForm();
	ChatForm cf=new ChatForm();
	
	int curpage=1;
	int totalpage=10;
	
	
	// 네트워크 관련 프로그램
	Socket s;// 연결 기계
	OutputStream out; // 서버로 요청값 전송 => 본인? => 로그인, 채탱 문자열, 종료...
	BufferedReader in; // 서버로부터 값 받아오는 클래스 => 쓰레드
	
	public ClientMainFrame()
	{
		
		cp = new ControlPanel(this);
		//새로운창이 아니라 this가 가르키는건 현재 실행중인 윈도우창을 가르킨다.
		
		
		//윈도우창 지정
		setSize(1600, 1000);
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//제목
		title.setFont(new Font("굴림체",Font.BOLD,55));
		title.setOpaque(true);
		setLayout(null);
		title.setBounds(5, 15, 1575, 50);
		title.setOpaque(false);
		add(title);
		
		//컨트롤패널
		cp.setBounds(115, 80, 1465, 680);
		add(cp); 
		
		// 채팅 패널 배치
		cf.setBounds(115,760,1465,200);
		add(cf);
		
		//메인 왼쪽 메뉴 폼
		bf.setBounds(10, 120, 100, 300);
		add(bf);
		
		//메인 왼쪽 메뉴
		bf.b1.addActionListener(this); //홈
		bf.b2.addActionListener(this); //드라마
		bf.b3.addActionListener(this); //영화
		bf.b4.addActionListener(this); //책
		bf.b5.addActionListener(this); //주식
		bf.b6.addActionListener(this); //음악
		bf.b7.addActionListener(this); //종료
		
		//메인
		cp.mf.b1.addActionListener(this); //이전
		cp.mf.b2.addActionListener(this); //다음
		
		//카 디테일
		cp.cdf.b1.addActionListener(this); //예매
		cp.cdf.b2.addActionListener(this); //목록
		
		
		
		// 드라마
		cp.dlf.b1.addActionListener(this); // 넷플릭스
		cp.dlf.b2.addActionListener(this); // HBO
		cp.dlf.b3.addActionListener(this); // Amazon Prime
		cp.dlf.b4.addActionListener(this); // DISNEY+
		cp.dlf.b5.addActionListener(this); // 검색버튼
		cp.dlf.tf.addActionListener(this); // 검색창 : 엔터치면 검색되는 기능 추가
		
		// 영화
		cp.mlf.b1.addActionListener(this);
		cp.mlf.b2.addActionListener(this);
		cp.mlf.b3.addActionListener(this);
		cp.mlf.b4.addActionListener(this);
		cp.mlf.b5.addActionListener(this);
		cp.mlf.tf.addActionListener(this);
		
		// 책
		cp.blf.b1.addActionListener(this);
		cp.blf.b2.addActionListener(this);
		cp.blf.b3.addActionListener(this);
		cp.blf.b4.addActionListener(this);
		cp.blf.b5.addActionListener(this);
		cp.blf.b6.addActionListener(this);
		cp.blf.tf.addActionListener(this);
		
		// 책
		cp.slf.b1.addActionListener(this);
		cp.slf.b2.addActionListener(this);
		cp.slf.b3.addActionListener(this);
		cp.slf.b4.addActionListener(this);
		cp.slf.b5.addActionListener(this);
		cp.slf.tf.addActionListener(this);
		
		// 로그인 처리
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);
		
		// 채팅입력
		cf.tf.addActionListener(this);

		
	}
	
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		new ClientMainFrame();
		
		//System.out.println(CarManager.list.get(1).getPoster());

	}
	
	public static Image getImage(ImageIcon ii,int w,int h) //이미지 축소(조정)
    {
    	Image dimg = ii.getImage().getScaledInstance(w, h,
    	        Image.SCALE_SMOOTH);
    	return dimg;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0;i<10;i++)
		{
			if(e.getSource()==cp.mf.mc[i])
			{
				if(e.getClickCount()==2)
				{
					int a=(i+1)+((curpage*10)-10);
					cp.cdf.detailPrint(a);
					cp.card.show(cp, "DF");
					break;
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인, 채팅================================================================
		if(e.getSource()==login.b1)
		{
			String id = login.tf1.getText();
			if(id.length()<1) // id를 입력하지 않은 경우
			{
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				login.tf1.requestFocus();
				return;
			}
			String name=login.tf2.getText();
			if(name.length()<1) // 대화명를 입력하지 않은 경우
			{
				JOptionPane.showMessageDialog(this, "대화명를 입력하세요");
				login.tf2.requestFocus();
				return;
			}
			String sex="";
			if(login.rb1.isSelected()) {
				sex="남자";}
			else {
				sex="여자";}
			
			// 서버로 입력받은 데이터 전송
			try
			{
				// 연결
				s=new Socket("localhost",3355);
				// 송수신 위치 확인
				in =new BufferedReader(new InputStreamReader(s.getInputStream()));
				out=s.getOutputStream();
				
				// 로그인 요청(서버로 전송되는 부분)
				out.write((Function.LOGIN+"|"+id+"|"+name+"|"+sex+"\n").getBytes());
				
			}catch (Exception e2) {}
			// 서버에서 전송하는 데이터를 읽어서 출력
			new Thread(this).start();
			// run의 위치 확인=> 자신의 클래스 안에 존재하기 때문에 (this)작성
		}
		
		else if(e.getSource()==login.b2)
		{
			System.exit(0);
		}
		
		// 항목별 메뉴 버튼=======================================================================
		else if(e.getSource()==bf.b1) //홈
		{
			cp.card.show(cp, "MF");
		}
		else if(e.getSource()==bf.b2) //드라마
		{
			cp.card.show(cp, "DLF");
		}
		else if(e.getSource()==bf.b3) //영화
		{
			cp.card.show(cp, "MLF");
		}
		else if(e.getSource()==bf.b4) //책
		{
			cp.card.show(cp, "BLF");
		}
		else if(e.getSource()==bf.b5) //주식
		{
			cp.card.show(cp, "SLF");
		}
		else if(e.getSource()==bf.b6) //음악
		{
			cp.card.show(cp, "MULF");
		}
		else if(e.getSource()==bf.b7)
		{try {
			out.write((Function.EXIT+"|\n").getBytes());
			System.exit(0);
		} catch (Exception e2) {}
			
		}
		
		
		// 메인 홈 왼쪽, 오른쪽으로 가기 버튼=========================================================
		else if(e.getSource()==cp.mf.b1) //이전페이지
		{
			if(curpage>1)
			{
				curpage--;
				cp.mf.p.removeAll();// JPanel의 모든 기능을 삭제
				cp.mf.CarPrint(curpage);
				cp.mf.repaint();
				cp.mf.p.validate();// JPanel의 원래 기능을 재배치
			}
		}
		else if(e.getSource()==cp.mf.b2) //다음페이지
		{
			if(curpage<totalpage)
			{
				curpage++;
				cp.mf.p.removeAll();
				cp.mf.CarPrint(curpage);
				cp.mf.repaint();
				cp.mf.p.validate();
			}
		}
		
		
		// Car 디테일 구입, 목록으로 돌아가기========================================================
		else if(e.getSource()==cp.cdf.b1) //구입
		{
			{
				JOptionPane.showMessageDialog(this,"이차의 "+cp.cdf.la[0].getText()+" 입니다");
			}
		}
		else if(e.getSource()==cp.cdf.b2) //목록
		{
			cp.card.show(cp, "MF");
		}
		
		
		// 드라마 방송국별로 넘어가기================================================================
		else if(e.getSource()==cp.dlf.b1)
		{
			cp.dlf.dramaAllData(1);
		}
		else if(e.getSource()==cp.dlf.b2)
		{
			cp.dlf.dramaAllData(2);
		}
		else if(e.getSource()==cp.dlf.b3)
		{
			cp.dlf.dramaAllData(3);
		}
		else if(e.getSource()==cp.dlf.b4)
		{
			cp.dlf.dramaAllData(4);
		}
		
		else if(e.getSource()==cp.dlf.b5 || e.getSource()==cp.dlf.tf)
		{
			//1. 입력된 값 읽기
			String ss= cp.dlf.tf.getText();
			if(ss.length()<1)
			{
				JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
				cp.dlf.tf.requestFocus(); // 커서 깜빡이게 해주기
				return; // 메소드 종료
			}
			else 
			{
				cp.dlf.dramaFindData(ss);
			}
			
		}
		
		
		// 영화 차트별로 넘어가기================================================================
		else if(e.getSource()==cp.mlf.b1) {
			cp.mlf.movieAllData(1);
		}
		else if(e.getSource()==cp.mlf.b2) {
			cp.mlf.movieAllData(2);
		}
		else if(e.getSource()==cp.mlf.b3) {
			cp.mlf.movieAllData(3);
		}
		else if(e.getSource()==cp.mlf.b4) {
			cp.mlf.movieAllData(4);
		}
		
		else if(e.getSource()==cp.mlf.b5 || e.getSource()==cp.mlf.tf) {
			
			// 1.입력된 값 읽기
			String ss=cp.mlf.tf.getText();
			if(ss.length()<1) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.mlf.tf.requestFocus(); // 커서 올려놓기
				return; // 메소드 종료
			}
			cp.mlf.movieFindData(ss);
			
		}
		
		
		
		// 책 차트별로 넘어가기================================================================
		else if(e.getSource()==cp.blf.b1)
		{
			cp.blf.movieAllData(1);
		}
		else if(e.getSource()==cp.blf.b2)
		{
			cp.blf.movieAllData(2);
		}
		else if(e.getSource()==cp.blf.b3)
		{
			cp.blf.movieAllData(3);
		}
		else if(e.getSource()==cp.blf.b4)
		{
			cp.blf.movieAllData(4);
		}
		else if(e.getSource()==cp.blf.b5)
		{
			cp.blf.movieAllData(5);
		}
		else if(e.getSource()==cp.blf.b6 || e.getSource()==cp.blf.tf)
		{
			
			String ss=cp.blf.tf.getText();
			if(ss.length()<1) 
			{
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				cp.blf.tf.requestFocus();
				return;
			}
			cp.blf.movieFindData(ss); 
		}
		
		
		// 주식 순위별로 넘어가기================================================================
		else if(e.getSource()==cp.slf.b1)
		{
			cp.slf.StockAllData(1);
		}
		else if(e.getSource()==cp.slf.b2)
		{
			cp.slf.StockAllData(2);
		}
		else if(e.getSource()==cp.slf.b3)
		{
			cp.slf.StockAllData(3);
		}
		else if(e.getSource()==cp.slf.b4)
		{
			cp.slf.StockAllData(4);
		}
		
		else if(e.getSource()==cp.slf.b5 || e.getSource()==cp.slf.tf)
		{
			//1. 입력된 값 읽기
			String ss= cp.slf.tf.getText();
			if(ss.length()<1)
			{
				JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
				cp.slf.tf.requestFocus(); // 커서 깜빡이게 해주기
				return; // 메소드 종료
			}
			else 
			{
				cp.slf.stockFindData(ss);
			}
			
		}
		
		
		// 채팅 넘기기 ================================================================
		else if(e.getSource()==cf.tf)
		{
			//입력된 데이터 읽기
			String msg=cf.tf.getText();
			
			if(msg.length()<1) return; //채팅입력 안했으면 리턴
			
			try {
				out.write((Function.CHAT+"|"+msg+"\n").getBytes()); //문자보냄
			} catch (Exception e2) {}
			
			cf.tf.setText(""); //엔터치면 입력창 지워지는 기능
			
		}
		
	}

	@Override
	public void run() {
		// 서버에서 들어오는 데이터를 받아서 처리하는 부분
		try {
			while(true)
			{
				String msg=in.readLine();
				System.out.println("server=>"+msg);
				StringTokenizer st = new StringTokenizer(msg,"|");
				int protocol = Integer.parseInt(st.nextToken());
				
				switch (protocol) 
				{
				case Function.MYLOG:
				{
					setTitle(st.nextToken());
					login.setVisible(true);// 로그인 창
					setVisible(true); // 영화창(Main)
				}
				break;
				case Function.LOGIN:
				{
					// 테이블에 출력만 하면 끝남
					String[] data= {
							st.nextToken(), // id
							st.nextToken(), // name
							st.nextToken()  // sex
					};
					cf.model.addRow(data);
				}
				break;
				case Function.CHAT:
				{
					cf.bar.setValue(cf.bar.getMaximum());
					cf.ta.append(st.nextToken()+"\n");
				}
				break;
				case Function.EXIT:
				{
					String id=st.nextToken();
					for(int i=0;i<cf.model.getRowCount();i++)
					{
						String mid = cf.model.getValueAt(i, 0).toString();
						if(id.equals(mid))
						{
							cf.model.removeRow(i);
							break;
						}
					}
				}
				break;
				case Function.MYEXIT:
				{
					dispose(); // 윈도우 메모리 해제
					System.exit(0);
				}
				break;

				}
			}
		} catch (Exception e) {}
		
	}

}
