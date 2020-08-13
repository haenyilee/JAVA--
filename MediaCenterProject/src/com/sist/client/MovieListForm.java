package com.sist.client;
import com.sist.data.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.sist.data.MovieVO;

import java.net.*; //url


public class MovieListForm extends JPanel implements MouseListener{
	
	JButton b1,b2,b3,b4,b5;
	JTextField tf;
	JTable table;
	DefaultTableModel model;
	MovieDetailForm mdf=new MovieDetailForm();
	
	
	public MovieListForm() {
		
		setLayout(null); 
		
		b1=new JButton("BEST MOVIES ON NETFLIX");
		b2=new JButton("THE 150 BEST MOVIES ON DISNEY");
		b3=new JButton("100 BEST MOVIES ON AMAZON PRIME");
		b4=new JButton("THE 95 BEST MOVIES 3 HOURS OR LONGER");
		b5=new JButton("검색");
		
		tf=new JTextField(20);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2); 
		p.add(b3);
		p.add(b4);
		p.add(tf);
		p.add(b5);
		p.setBounds(10, 25, 1300, 35);
		add(p);
		
		String[] col= {"번호","","제목","개봉년도","평점"};
		Object[][] row=new Object[0][5];
		
		
		model=new DefaultTableModel(row,col) {

			// 편집방지
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

			// 이미지첨부 
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				return getValueAt(0, columnIndex).getClass();
			}
			
		};
		
		table=new JTable(model);
		table.setRowHeight(50);
		
		JScrollPane js=new JScrollPane(table);
		
		
		
		js.setBounds(10, 70, 700, 550);
		add(js);
		
		mdf.setBounds(715, 70, 800, 650);
		add(mdf);
		
		movieAllData(1);
		
		mdf.detailPrint(1);
		
		table.addMouseListener(this);
	}
	
	
	public void movieAllData(int cno) {
		
		
		MovieManager m=new MovieManager(); 
		ArrayList<MovieVO> list=m.movieAllData(cno);
		
		for(int i=model.getRowCount()-1; i>=0; i--) {
		
			model.removeRow(i);}
		
		
		
		for(MovieVO vo:list) {
			try {
				
			URL url=new URL(vo.getPoster()); 
		
			Image img=ClientMainFrame.getImage(new ImageIcon(url), 50, 50);
			
			Object[] data= {
					
					vo.getMno(),
					new ImageIcon(img),
					vo.getTitle(),
					vo.getRegyear(),
					vo.getScore(),
			};
			model.addRow(data);
			}
			catch (Exception e) {
				
			}
		}
	}
	
public void movieFindData(String ss) {
		
		
		MovieManager m=new MovieManager(); 
		ArrayList<MovieVO> list=m.movieFindData(ss);
		
		
		for(int i=model.getRowCount()-1; i>=0; i--) {
		
			model.removeRow(i);}
		
		
		
		for(MovieVO vo:list) {
			try {
				
			URL url=new URL(vo.getPoster()); 
			Image img=ClientMainFrame.getImage(new ImageIcon(url), 50, 50);
			
			Object[] data= {
					
					vo.getMno(),
					new ImageIcon(img),
					vo.getTitle(),
					vo.getRegyear(),
					vo.getScore(),
			};
			model.addRow(data);
			}
			catch (Exception e) {
				
			}
		}
	}


@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==table) {
		if(e.getClickCount()==2) {
			int row=table.getSelectedRow(); 
			String mno=model.getValueAt(row, 0).toString();
			mdf.detailPrint(Integer.parseInt(mno));
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
		
	
}
