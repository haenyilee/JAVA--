package com.sist.client;
import java.awt.*;
import javax.swing.*;

public class ButtonForm extends JPanel{
	JButton b1,b2,b3,b4,b5,b6,b7;
	//�ʱ�ȭ(��ư) ==> ��ġ(������)
	public ButtonForm()
	{
		//setBackground(Color.black);
		
		b1=new JButton("Ȩ");
		b2=new JButton("���");
		b3=new JButton("��ȭ");
		b4=new JButton("å");
		b5=new JButton("�ֽ�");
		b6=new JButton("����");
		b7=new JButton("����");
		
		setLayout(new GridLayout(7,1,5,5)); //��ư ���η� ������ ���ؼ�
		add(b1);
		add(b2);
		add(b3);	
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		
	}

}
