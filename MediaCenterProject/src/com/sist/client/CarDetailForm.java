package com.sist.client;

import javax.swing.*;
import com.sist.data.CarManager;
import com.sist.data.CarVO;

import java.awt.*;
import java.net.*;


public class CarDetailForm extends JLabel{
	JLabel poster=new JLabel();
	JLabel la1=new JLabel();
	JLabel[] la=new JLabel[5];
	JButton b1,b2;
	
	public CarDetailForm()
	{
		setLayout(null);
		
		//포스터 위치
		poster.setBounds(10, 15, 350, 450);
		poster.setOpaque(true);
		poster.setBackground(Color.red);
		add(poster);
		
		//차 이름
		la1.setBounds(365, 15, 800, 45);
		la1.setFont(new Font("굴림체",Font.BOLD,30));
		add(la1);
		
		//차 세부정보
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(7,1,5,5));
		//String[] str={"가격","연식","주행거리","기어","연료"};
		for (int i = 0; i < 5; i++) 
		{
			la[i]=new JLabel(/*str[i]*/);
			la[i].setFont(new Font("굴림체",Font.BOLD,25));
			p.add(la[i]);
		}
		p.setBounds(365, 80, 500, 300);
		add(p);
		
		//버튼
		b1=new JButton("구입");
		b1.setBackground(Color.orange);
		b2=new JButton("목록");
		b2.setBackground(Color.green);
		JPanel p2=new JPanel();
		p2.add(b1);
		p2.add(b2);
		p2.setBounds(365, 385, 200, 35);
		add(p2);
		
		//setSize(900,670);
		
	}
	
	public void detailPrint(int no)
	{
		CarManager m=new CarManager();
		CarVO vo=m.CarDetailData(no);
		la1.setText(vo.getName());
		try {
				URL url=new URL(vo.getPoster());
				Image img = ClientMainFrame.getImage(new ImageIcon(url), poster.getWidth(), 
						poster.getHeight());
				
				poster.setIcon(new ImageIcon(img));

		} catch (Exception e) {}
		
		la[0].setText("가격:"+vo.getPrice());
		la[1].setText("연식:"+vo.getAge());
		la[2].setText("주행거리:"+vo.getKm());
		la[3].setText("기어:"+vo.getTrans());
		la[4].setText("연료:"+vo.getOil());
		
	}
}