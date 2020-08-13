package com.sist.client;
import java.awt.*;
import javax.swing.*;

public class ControlPanel extends JPanel{
	
	CardLayout card=new CardLayout();

	CarMainForm mf;
	ClientMainFrame c;
	DramaListForm dlf= new DramaListForm();
	CarDetailForm cdf=new CarDetailForm();
	MovieListForm mlf=new MovieListForm();
	BookListForm blf=new BookListForm();
	StockListForm slf=new StockListForm();
	MusicForm mulf=new MusicForm();

	public ControlPanel(ClientMainFrame c)
	{
		this.c=c;
		mf=new CarMainForm(c);
		setLayout(card);
		add("MF",mf);	//car����
		add("DLF",dlf); //���
		add("MLF",mlf); //��ȭ
		add("BLF",blf); //å 
		add("SLF",slf);	//�ֽ�
		add("MULF",mulf);//����

		add("DF",cdf); //car ������

		
	}
}
