package com.sist.client;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import com.sist.data.MusicVO;
//Jpanel => HTML => <div>
public class MusicCard extends JPanel{
	JLabel poster=new JLabel(); // 한줄 문자 => 제목 등
	JLabel title=new JLabel();
	JLabel singer=new JLabel();
	JLabel album=new JLabel();
	JLabel rank=new JLabel();
	public MusicCard()
	{
		setLayout(null);
		rank.setBounds(10,50,10,10);
		add(rank);
		
		poster.setBounds(30,50,150,150);
		add(poster);
		
		title.setBounds(200,50,350,70);
		title.setFont(new Font("굴림체",Font.BOLD,18));
		add(title);
		
		singer.setBounds(600,50,350,70);
		singer.setFont(new Font("굴림체",Font.BOLD,18));
		add(singer);
		
		album.setBounds(1000,50,350,70);
		album.setFont(new Font("굴림체",Font.BOLD,18));
		add(album);
		
		
	}
	//값을 체운다
	public void musicPrint(MusicVO vo) {
		try 
		{
			//System.out.println("aaa:"+vo.getPoster());
			URL url=new URL(vo.getPoster());
			
			Image img=ClientMainFrame.getImage(new ImageIcon(url),150,150);
			rank.setText(vo.getRank());
			poster.setIcon(new ImageIcon(img));
			title.setText(vo.getTitle());
			singer.setText(vo.getSinger());
			album.setText(vo.getAlbum());
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}