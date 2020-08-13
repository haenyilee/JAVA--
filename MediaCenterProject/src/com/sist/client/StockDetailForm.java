package com.sist.client;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.sist.data.StockManager;
import com.sist.data.StockVO;

public class StockDetailForm extends JPanel {
	JLabel poster=new JLabel();
	JLabel la1 = new JLabel("",JLabel.CENTER);
	JLabel[] la = new JLabel[7];
	JTextPane ta = new JTextPane();
	
	
	public StockDetailForm() {
		setLayout(null);
		poster.setBounds(10,15,250,350);
		add(poster);
		
		
		la1.setBounds(265,15,500,45);
		la1.setFont(new Font("���ü", Font.BOLD, 25));
		add(la1);
		
		JPanel p =new JPanel();
		p.setLayout(new GridLayout(7,1,5,5));
		String[] str = {"����","���簡","���Ϻ�","�����","�ð��Ѿ�","�ܱ��� ����","�ŷ���"};
		for(int i=0;i<7;i++)
		{
			la[i]=new JLabel(str[i]);
			la[i].setFont(new Font("���ü",Font.BOLD,15));
			p.add(la[i]);
		}
		
		p.setBounds(265,80,450,300);
		add(p);
		
		ta.setEditable(false);
		JScrollPane js = new JScrollPane(ta);
		js.setBounds(10,390,730,150);
		add(js);
		
		
	}
	
	public void stockDetailPrintint(int mno) {
		StockManager s = new StockManager();
		StockVO vo = s.stockDetailData(mno);
		la1.setText(vo.getTitle());
		
		try {
			URL url=new URL(vo.getPoster());
			Image img = ClientMainFrame.getImage(new ImageIcon(url), poster.getWidth(),poster.getHeight());
			poster.setIcon(new ImageIcon(img));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		la[0].setText("����:"+vo.getTitle());
		la[1].setText("���簡:"+vo.getValue());
		la[2].setText("���Ϻ�:"+vo.getChangeValue());
		la[3].setText("�����:"+vo.getPercent());
		la[4].setText("�ð��Ѿ�:"+vo.getTotal());
		la[5].setText("�ܱ��� ����:"+vo.getForeigner());
		la[6].setText("�ŷ���:"+vo.getExchange());
		ta.setText(vo.getTitle()); //�����̾�� ���� �ѹ����ֱ�
	}
	
	
	
	
}
