package com.sist.client;
import java.awt.*;
import javax.swing.*;

public class ButtonForm extends JPanel{
	JButton b1,b2,b3,b4,b5,b6,b7;
	//초기화(버튼) ==> 배치(생성자)
	public ButtonForm()
	{
		//setBackground(Color.black);
		
		b1=new JButton("홈");
		b2=new JButton("드라마");
		b3=new JButton("영화");
		b4=new JButton("책");
		b5=new JButton("주식");
		b6=new JButton("음악");
		b7=new JButton("종료");
		
		setLayout(new GridLayout(7,1,5,5)); //버튼 세로로 내리기 위해서
		add(b1);
		add(b2);
		add(b3);	
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		
	}

}
