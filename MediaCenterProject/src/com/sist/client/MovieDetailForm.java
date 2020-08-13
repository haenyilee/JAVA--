package com.sist.client;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import com.sist.data.MovieManager;
import com.sist.data.MovieVO;

import java.util.*;

public class MovieDetailForm extends JPanel {
	
	JLabel poster=new JLabel();
	JLabel la1=new JLabel();
	JLabel[] la=new JLabel[4];
	JTextPane ta=new JTextPane(); 
	JButton b1,b2;
	
	public MovieDetailForm() {
		
		setLayout(null);
		
		poster.setBounds(10, 15, 250,350);
		add(poster);
		
		la1.setBounds(265, 15, 500, 45);
		la1.setFont(new Font("고딕체",Font.BOLD,25));
		add(la1);
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(6,1,5,5));
		String[] str= {"개봉","출연","평점","평론"};
		
		
		for(int i=0; i<4; i++) {
			la[i]=new JLabel(str[i]);
			la[i].setFont(new Font("고딕체",Font.BOLD,15));
			p.add(la[i]);
		}
		
		p.setBounds(265, 80, 450, 300);
		add(p);
		
		ta.setEditable(false); // 비활성화
		JScrollPane js=new JScrollPane(ta);
		js.setBounds(10, 390, 730, 150);
		add(js);
		
		b1=new JButton("시청");
		b2=new JButton("목록");	
		b1.setBackground(Color.red);
		b2.setBackground(Color.green);
		
		JPanel p2=new JPanel();
		p2.add(b1);
		p2.add(b2);
		
		p2.setBounds(365, 385, 200, 35);
		add(p2);
		
	}
	
	public void detailPrint(int mno) {
		MovieManager m=new MovieManager();
		MovieVO vo=m.movieDatailData(mno);
		la1.setText(vo.getTitle());
		
		try {
			URL url=new URL(vo.getPoster());
			Image img=ClientMainFrame.getImage(new ImageIcon(url), poster.getWidth(), poster.getHeight()); 
			
			poster.setIcon(new ImageIcon(img));
		} catch (Exception e) {

		}
		
		la[0].setText("개봉:"+vo.getRegyear());
		la[1].setText(""+vo.getActor());
		la[2].setText("평점:"+vo.getScore());
		la[3].setText(""+vo.getStory());
		
		ta.setText(vo.getCritics());
	}
}
