package com.sist.client;

import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sist.data.StockManager;
import com.sist.data.StockVO;

public class StockListForm extends JPanel implements MouseListener{
	JButton b1,b2,b3,b4,b5;
	JTextField tf;
	JTable table;
	DefaultTableModel model;
	StockDetailForm sdf = new StockDetailForm();
	
	public StockListForm()
	{
		setLayout(null);
		   b1=new JButton("½ÃÃÑ 1 ~ 50À§");
		   b2=new JButton("½ÃÃÑ 51 ~ 100À§");
		   b3=new JButton("½ÃÃÑ 101 ~ 150À§");
		   b4=new JButton("½ÃÃÑ 151 ~ 200À§");
		   b5=new JButton("°Ë»ö");
		   tf=new JTextField(20);
		   JPanel p=new JPanel();
		   p.add(b1);p.add(b2);p.add(b3);p.add(b4);p.add(b5);p.add(tf);
		   p.setBounds(10, 25, 900, 35);
		   add(p);
		   
		   String[] col={"¹øÈ£","","Á¾¸ñ¸í","ÇöÀç°¡","½Ã°¡ÃÑ¾×","µî¶ô·ü"};
		   Object[][] row=new Object[0][5];
		   
		   model=new DefaultTableModel(row,col) {
		        // ?????? ????? 
					@Override
					public boolean isCellEditable(int row, int column) {
						// TODO Auto-generated method stub
						return false;
					}
			        // ????? ¡À?? 
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
			   
			   sdf.setBounds(715,70 , 800, 650);
			   add(sdf);
			   StockAllData(1);
			   sdf.stockDetailPrintint(1); // ??? ?©ø?,,,
			   
			   table.addMouseListener(this);
		   
	}

	public void StockAllData(int cno) {
		StockManager dm2=new StockManager();
		ArrayList<StockVO> list = dm2.stockAllData(cno);
		
		
		for(int i=model.getRowCount()-1;i>=0;i--)
		{
			model.removeRow(i);
		}
		
	
		for(StockVO vo:list)
		{
			try {
				URL url = new URL(vo.getPoster());
//				BufferedImage image = ImageIO.read(new File(vo.getPoster()));
				Image img = ClientMainFrame.getImage(new ImageIcon(url), 50, 50);
				
				
				Object[] data = {
						vo.getMno(),
						new ImageIcon(img),
						vo.getTitle(),
						vo.getValue(),
						vo.getTotal(),
						vo.getPercent()
				};
				model.addRow(data);
				
			} catch (Exception e) {e.printStackTrace();}
		}
		
	}
	
	//
	public void stockFindData(String ss)
	{
		
		StockManager dm2=new StockManager();
		ArrayList<StockVO> list = dm2.stockFindData(ss);
		
		for(int i=model.getRowCount()-1;i>=0;i--)
		{
			model.removeRow(i);
		}
		

		for(StockVO vo:list)
		{
			try {
				URL url = new URL(vo.getPoster());
//				BufferedImage image = ImageIO.read(new File(vo.getPoster()));
				Image img=ClientMainFrame.getImage(new ImageIcon(url),50, 50);
				 
				 Object[] data = {
							vo.getMno(),
							new ImageIcon(img),
							vo.getTitle(),
							vo.getValue(),
							vo.getTotal(),
							vo.getPercent()
					};
					model.addRow(data);
					
				} catch (Exception e) {e.printStackTrace();}
		}
		
		
	}





	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource()==table)
		{
			if(e.getClickCount()==2)
			{
				int row = table.getSelectedRow();
				String mno= model.getValueAt(row, 0).toString();
				sdf.stockDetailPrintint(Integer.parseInt(mno));
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
