package com.sist.client;
import java.awt.*;
import javax.swing.*;
import com.sist.data.*;
import java.util.*; //Arrylist

public class CarMainForm extends JPanel{
	
	JButton b1,b2;
	JPanel p=new JPanel();
	CarCard[] mc=new CarCard[10];
	ClientMainFrame c;
	
	public CarMainForm(ClientMainFrame c)
	{
		this.c=c;
		setLayout(new BorderLayout());
		b1=new JButton("◀");
		//b1.setSize(50, 50);
		b2=new JButton("▶");
		
		p.setLayout(new GridLayout(2,5,5,5));
		
		CarPrint(1);
		add("West",b1); add("Center",p); add("East",b2);
		
		
	}
	
	// carListData 에서 저장한 데이터 MainForm에 출력
	public void CarPrint(int page)
	{
		CarManager m=new CarManager();
		ArrayList<CarVO> list=m.carListData(page);
		
		int i = 0;
		for(CarVO vo:list)
		{
			mc[i]=new CarCard(vo.getName(),vo.getPoster());
			p.add(mc[i]);
			mc[i].addMouseListener(c);
			i++;
		}
	}
}