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
		add("MF",mf);	//car메인
		add("DLF",dlf); //드라마
		add("MLF",mlf); //영화
		add("BLF",blf); //책 
		add("SLF",slf);	//주식
		add("MULF",mulf);//음악

		add("DF",cdf); //car 디테일

		
	}
}
