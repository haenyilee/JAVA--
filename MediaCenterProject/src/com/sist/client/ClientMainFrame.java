package com.sist.client;
import javax.swing.*; 	// ��ư ��
import com.sist.data.CarManager;
import com.sist.data.CarVO;
import java.awt.*; 		// Color, Layout ��ġ ��
import java.awt.event.*;// �������̽�
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
	
	JLabel title=new JLabel("3�� ������Ʈ",JLabel.CENTER);
	Login login = new Login();
	ControlPanel cp;
	ButtonForm bf=new ButtonForm();
	ChatForm cf=new ChatForm();
	
	int curpage=1;
	int totalpage=10;
	
	
	// ��Ʈ��ũ ���� ���α׷�
	Socket s;// ���� ���
	OutputStream out; // ������ ��û�� ���� => ����? => �α���, ä�� ���ڿ�, ����...
	BufferedReader in; // �����κ��� �� �޾ƿ��� Ŭ���� => ������
	
	public ClientMainFrame()
	{
		
		cp = new ControlPanel(this);
		//���ο�â�� �ƴ϶� this�� ����Ű�°� ���� �������� ������â�� ����Ų��.
		
		
		//������â ����
		setSize(1600, 1000);
		//setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//����
		title.setFont(new Font("����ü",Font.BOLD,55));
		title.setOpaque(true);
		setLayout(null);
		title.setBounds(5, 15, 1575, 50);
		title.setOpaque(false);
		add(title);
		
		//��Ʈ���г�
		cp.setBounds(115, 80, 1465, 680);
		add(cp); 
		
		// ä�� �г� ��ġ
		cf.setBounds(115,760,1465,200);
		add(cf);
		
		//���� ���� �޴� ��
		bf.setBounds(10, 120, 100, 300);
		add(bf);
		
		//���� ���� �޴�
		bf.b1.addActionListener(this); //Ȩ
		bf.b2.addActionListener(this); //���
		bf.b3.addActionListener(this); //��ȭ
		bf.b4.addActionListener(this); //å
		bf.b5.addActionListener(this); //�ֽ�
		bf.b6.addActionListener(this); //����
		bf.b7.addActionListener(this); //����
		
		//����
		cp.mf.b1.addActionListener(this); //����
		cp.mf.b2.addActionListener(this); //����
		
		//ī ������
		cp.cdf.b1.addActionListener(this); //����
		cp.cdf.b2.addActionListener(this); //���
		
		
		
		// ���
		cp.dlf.b1.addActionListener(this); // ���ø���
		cp.dlf.b2.addActionListener(this); // HBO
		cp.dlf.b3.addActionListener(this); // Amazon Prime
		cp.dlf.b4.addActionListener(this); // DISNEY+
		cp.dlf.b5.addActionListener(this); // �˻���ư
		cp.dlf.tf.addActionListener(this); // �˻�â : ����ġ�� �˻��Ǵ� ��� �߰�
		
		// ��ȭ
		cp.mlf.b1.addActionListener(this);
		cp.mlf.b2.addActionListener(this);
		cp.mlf.b3.addActionListener(this);
		cp.mlf.b4.addActionListener(this);
		cp.mlf.b5.addActionListener(this);
		cp.mlf.tf.addActionListener(this);
		
		// å
		cp.blf.b1.addActionListener(this);
		cp.blf.b2.addActionListener(this);
		cp.blf.b3.addActionListener(this);
		cp.blf.b4.addActionListener(this);
		cp.blf.b5.addActionListener(this);
		cp.blf.b6.addActionListener(this);
		cp.blf.tf.addActionListener(this);
		
		// å
		cp.slf.b1.addActionListener(this);
		cp.slf.b2.addActionListener(this);
		cp.slf.b3.addActionListener(this);
		cp.slf.b4.addActionListener(this);
		cp.slf.b5.addActionListener(this);
		cp.slf.tf.addActionListener(this);
		
		// �α��� ó��
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);
		
		// ä���Է�
		cf.tf.addActionListener(this);

		
	}
	
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		new ClientMainFrame();
		
		//System.out.println(CarManager.list.get(1).getPoster());

	}
	
	public static Image getImage(ImageIcon ii,int w,int h) //�̹��� ���(����)
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
		// �α���, ä��================================================================
		if(e.getSource()==login.b1)
		{
			String id = login.tf1.getText();
			if(id.length()<1) // id�� �Է����� ���� ���
			{
				JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
				login.tf1.requestFocus();
				return;
			}
			String name=login.tf2.getText();
			if(name.length()<1) // ��ȭ�� �Է����� ���� ���
			{
				JOptionPane.showMessageDialog(this, "��ȭ�� �Է��ϼ���");
				login.tf2.requestFocus();
				return;
			}
			String sex="";
			if(login.rb1.isSelected()) {
				sex="����";}
			else {
				sex="����";}
			
			// ������ �Է¹��� ������ ����
			try
			{
				// ����
				s=new Socket("localhost",3355);
				// �ۼ��� ��ġ Ȯ��
				in =new BufferedReader(new InputStreamReader(s.getInputStream()));
				out=s.getOutputStream();
				
				// �α��� ��û(������ ���۵Ǵ� �κ�)
				out.write((Function.LOGIN+"|"+id+"|"+name+"|"+sex+"\n").getBytes());
				
			}catch (Exception e2) {}
			// �������� �����ϴ� �����͸� �о ���
			new Thread(this).start();
			// run�� ��ġ Ȯ��=> �ڽ��� Ŭ���� �ȿ� �����ϱ� ������ (this)�ۼ�
		}
		
		else if(e.getSource()==login.b2)
		{
			System.exit(0);
		}
		
		// �׸� �޴� ��ư=======================================================================
		else if(e.getSource()==bf.b1) //Ȩ
		{
			cp.card.show(cp, "MF");
		}
		else if(e.getSource()==bf.b2) //���
		{
			cp.card.show(cp, "DLF");
		}
		else if(e.getSource()==bf.b3) //��ȭ
		{
			cp.card.show(cp, "MLF");
		}
		else if(e.getSource()==bf.b4) //å
		{
			cp.card.show(cp, "BLF");
		}
		else if(e.getSource()==bf.b5) //�ֽ�
		{
			cp.card.show(cp, "SLF");
		}
		else if(e.getSource()==bf.b6) //����
		{
			cp.card.show(cp, "MULF");
		}
		else if(e.getSource()==bf.b7)
		{try {
			out.write((Function.EXIT+"|\n").getBytes());
			System.exit(0);
		} catch (Exception e2) {}
			
		}
		
		
		// ���� Ȩ ����, ���������� ���� ��ư=========================================================
		else if(e.getSource()==cp.mf.b1) //����������
		{
			if(curpage>1)
			{
				curpage--;
				cp.mf.p.removeAll();// JPanel�� ��� ����� ����
				cp.mf.CarPrint(curpage);
				cp.mf.repaint();
				cp.mf.p.validate();// JPanel�� ���� ����� ���ġ
			}
		}
		else if(e.getSource()==cp.mf.b2) //����������
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
		
		
		// Car ������ ����, ������� ���ư���========================================================
		else if(e.getSource()==cp.cdf.b1) //����
		{
			{
				JOptionPane.showMessageDialog(this,"������ "+cp.cdf.la[0].getText()+" �Դϴ�");
			}
		}
		else if(e.getSource()==cp.cdf.b2) //���
		{
			cp.card.show(cp, "MF");
		}
		
		
		// ��� ��۱����� �Ѿ��================================================================
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
			//1. �Էµ� �� �б�
			String ss= cp.dlf.tf.getText();
			if(ss.length()<1)
			{
				JOptionPane.showMessageDialog(this, "�˻�� �Է����ּ���");
				cp.dlf.tf.requestFocus(); // Ŀ�� �����̰� ���ֱ�
				return; // �޼ҵ� ����
			}
			else 
			{
				cp.dlf.dramaFindData(ss);
			}
			
		}
		
		
		// ��ȭ ��Ʈ���� �Ѿ��================================================================
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
			
			// 1.�Էµ� �� �б�
			String ss=cp.mlf.tf.getText();
			if(ss.length()<1) {
				JOptionPane.showMessageDialog(this, "�˻�� �Է��ϼ���");
				cp.mlf.tf.requestFocus(); // Ŀ�� �÷�����
				return; // �޼ҵ� ����
			}
			cp.mlf.movieFindData(ss);
			
		}
		
		
		
		// å ��Ʈ���� �Ѿ��================================================================
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
				JOptionPane.showMessageDialog(this, "�˻�� �Է��ϼ���");
				cp.blf.tf.requestFocus();
				return;
			}
			cp.blf.movieFindData(ss); 
		}
		
		
		// �ֽ� �������� �Ѿ��================================================================
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
			//1. �Էµ� �� �б�
			String ss= cp.slf.tf.getText();
			if(ss.length()<1)
			{
				JOptionPane.showMessageDialog(this, "�˻�� �Է����ּ���");
				cp.slf.tf.requestFocus(); // Ŀ�� �����̰� ���ֱ�
				return; // �޼ҵ� ����
			}
			else 
			{
				cp.slf.stockFindData(ss);
			}
			
		}
		
		
		// ä�� �ѱ�� ================================================================
		else if(e.getSource()==cf.tf)
		{
			//�Էµ� ������ �б�
			String msg=cf.tf.getText();
			
			if(msg.length()<1) return; //ä���Է� �������� ����
			
			try {
				out.write((Function.CHAT+"|"+msg+"\n").getBytes()); //���ں���
			} catch (Exception e2) {}
			
			cf.tf.setText(""); //����ġ�� �Է�â �������� ���
			
		}
		
	}

	@Override
	public void run() {
		// �������� ������ �����͸� �޾Ƽ� ó���ϴ� �κ�
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
					login.setVisible(true);// �α��� â
					setVisible(true); // ��ȭâ(Main)
				}
				break;
				case Function.LOGIN:
				{
					// ���̺� ��¸� �ϸ� ����
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
					dispose(); // ������ �޸� ����
					System.exit(0);
				}
				break;

				}
			}
		} catch (Exception e) {}
		
	}

}
