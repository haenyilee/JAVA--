package com.sist.client;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CarCard extends JPanel{
	String name, poster;
	Image img;
	
	public CarCard(String name, String poster)
	{
		this.name=name;
		this.poster=poster;
		try 
		{
			img=Toolkit.getDefaultToolkit().getImage(new URL(poster));
		} catch (Exception ex) {}
		setToolTipText(name); //ÅøÆÁ
	
	
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);	
	}
}
