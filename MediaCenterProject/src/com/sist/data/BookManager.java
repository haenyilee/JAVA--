package com.sist.data;

import java.util.*;

import java.io.*;



public class BookManager {
	
	private static ArrayList<BookVO> list=new ArrayList<BookVO>();
	
	static
	{
		try
		{
			
			FileInputStream fr=new FileInputStream("C:\\JavaDev\\ProjectData\\BookBest100.txt");
	
			BufferedReader in=new BufferedReader(new InputStreamReader(fr));
			
			while(true)
			{
				String movie=in.readLine(); 
				if(movie==null) break; 
				String[] datas=movie.split("\\|");
				
				
					BookVO vo=new BookVO();
					vo.setMno(Integer.parseInt(datas[0]));
					
					vo.setCno(Integer.parseInt(datas[1]));
					
					vo.setTitle(datas[2]);
					
					vo.setPoster(datas[3]);
					
					vo.setPrice(datas[4]);
					
					vo.setWriter(datas[5]);
					
					vo.setPublisher(datas[6]);
					
					vo.setPublish(datas[7]);
					
					vo.setSubtitle(datas[8]);
			
					vo.setStory(datas[9]);
					
					
					
					list.add(vo);
				}
			
			
		}catch(Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<BookVO> movieListData(int page)
	{
		ArrayList<BookVO> movies=new ArrayList<BookVO>();
		int i=0;
		int j=0;
		int pagecnt=(page*10)-10;
	
		for(BookVO vo:list)
		{
			if(i<10 && j>=pagecnt) 
			{
				movies.add(vo);
				i++; 
			}
			j++; 
		}
		return movies;
	}
	
	public int movieTotalPage()
	{
		
		return (int)(Math.ceil(list.size()/10.0));
						 
	}
	
	
	public BookVO movieDetailData(int mno)
	{
		return list.get(mno-1); 
	}
	
	
	public ArrayList<BookVO> movieAllData(int cno) 
	{
		ArrayList<BookVO> movies=new ArrayList<BookVO>();
		for(BookVO vo:list)
		{
			if(vo.getCno()==cno)
			{
				movies.add(vo);
			}
		}
		return movies;
		
	}
	
	public ArrayList<BookVO> movieFindData(String ss)
	{
		ArrayList<BookVO> movies=new ArrayList<BookVO>(); 
		
		for(BookVO vo:list)
		{
			if(vo.getTitle().contains(ss)) 
			{
				movies.add(vo);
			}
		}
		return movies;
	}
	
//	public static void main(String[] args) 
//	{
//		Scanner scan=new Scanner(System.in);
//		System.out.print("∆‰¿Ã¡ˆ:");
//		int page=scan.nextInt();
//		
//		BookManager m=new BookManager();
//		System.out.println(page+" page / "+m.movieTotalPage()+" pages");
//		
//		
//		ArrayList<BookVO> list=m.movieListData(page);
//		for(BookVO vo:list) 
//		{
//			System.out.println(vo.getTitle());
//		}
//		
//	}
}
