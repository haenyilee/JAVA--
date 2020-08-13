package com.sist.data;

import java.util.*;
import java.io.*;


public class MovieManager {
	
private static ArrayList<MovieVO> list=new ArrayList<MovieVO>();
	
	static {
try {
			
			FileInputStream fr=new FileInputStream("C:\\JavaDev\\ProjectData\\Best_movie.txt");
			
			BufferedReader in=new BufferedReader(new InputStreamReader(fr));
			
			while(true) {
				
				String movie=in.readLine();
				if(movie==null) break;
				
						
						String[] data=movie.split("\\|");
						
						MovieVO vo=new MovieVO();
						
						vo.setMno(Integer.parseInt(data[0]));
						vo.setCno(Integer.parseInt(data[1]));
						vo.setTitle(data[2]);
						
						vo.setRegyear(data[3]);
						
						vo.setScore(data[4]);
						
						String actor=data[5];
					    vo.setActor(actor);
					    
					    String critics=data[6];
					    vo.setCritics(critics);
					    
					    String poster=data[7];
					    vo.setPoster(poster);
					    
					    String story=data[8];
					    vo.setStory(story);
					
						
						list.add(vo);
					}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<MovieVO> movieListData(int page) {
		
		ArrayList<MovieVO> movies=new ArrayList<MovieVO>();
		
		int i=0;
		int j=0;
		int pagecnt=(page*10)-10;
		
		for(MovieVO vo:list) {
			
			if(i<10 && j>pagecnt) {
				
				movies.add(vo);
				i++;
			}
			j++;
		}
		return movies;
	}
	
	public int movieTotalPage() {
		
		return (int)(Math.ceil(list.size()/10.0));
	}
	
	public MovieVO movieDatailData(int mno) {
		
		return list.get(mno-1);
	}
	
	public ArrayList<MovieVO> movieAllData(int cno){
		
		ArrayList<MovieVO> movies=new ArrayList<MovieVO>();
		
		for(MovieVO vo:list) {
			if(vo.getCno()==cno) {
				movies.add(vo);
			}
		}
		return movies;
	}
	
	public ArrayList<MovieVO> movieFindData(String ss){
		
		ArrayList<MovieVO> movies=new ArrayList<MovieVO>(); 
		// list
		
		for(MovieVO vo:list) {
			if(vo.getTitle().contains(ss)||vo.getTitle().toUpperCase().contains(ss)) {
				movies.add(vo); 
			}
		}
		
		return movies;
	}
	
	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		System.out.print("페이지:");
		int page=scan.nextInt();
		
		MovieManager m=new MovieManager();
		System.out.println(page+" page / "+m.movieTotalPage()+" pages");
		
		// 데이터 읽기
		
		ArrayList<MovieVO> list = m.movieListData(page);
		
		for(MovieVO vo:list) {
			System.out.println(vo.getTitle());
		}
	}

}
