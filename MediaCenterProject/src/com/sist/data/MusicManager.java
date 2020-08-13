package com.sist.data;
import java.util.*;
import java.io.*;
public class MusicManager {
	private static ArrayList<MusicVO> list= new ArrayList<MusicVO>();
	// 프로그램 => 시작 => 데이터 읽어서 => ArrayList에 저장
	/*
	 * 	 	파일
	 * 		데이터베이스
	 * 		========== 한번만 수행
	 */
	static
	{
		try {
			FileInputStream fis=new FileInputStream("C:\\JavaDev\\ProjectData\\genie_music.txt");
			BufferedReader in=new BufferedReader(new InputStreamReader(fis));
			while(true)
			{
				String data=in.readLine();
				if(data==null) break;
				
				StringTokenizer st=new StringTokenizer(data, "|");
				MusicVO vo=new MusicVO();
				vo.setRank(st.nextToken());
				vo.setPoster("http:"+st.nextToken());
				vo.setTitle(st.nextToken()); 	
				vo.setSinger(st.nextToken());
				vo.setAlbum(st.nextToken());
				
				list.add(vo);
				
			}
			fis.close();
		} catch (Exception ex) {}
	}
	public ArrayList<MusicVO> musicListData(int page)
	{
		ArrayList<MusicVO> music=new ArrayList<MusicVO>();
		int i=0; // i => 5개씩 나눠주는 변수
		int j=0; // j => for가 수행되는 횟수
		int pagecnt=(page*5)-5; // 출력할 시작위치
		// if(i<5 && j<=pagecnt)
		for(MusicVO vo:list)
		{
			if(i<5 && j>=pagecnt)
			{
				music.add(vo);
				i++;
			}
			j++;
		}
		return music;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusicManager m=new MusicManager();
		ArrayList<MusicVO> list=m.musicListData(1);
		int i=1;
		for(MusicVO vo:list)
		{
			System.out.println(i+"."+vo.getTitle());
			i++;
		}

	}

}