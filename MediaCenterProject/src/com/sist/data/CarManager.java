package com.sist.data;
import java.util.*;
import java.io.*;

public class CarManager {
	private static ArrayList<CarVO> list //CarVO 객체(메서드)만 사용
			=new ArrayList<CarVO>();
	static
	{
		try
		{

			//저장된 파일 읽기
			FileInputStream fr=new FileInputStream("C:\\JavaDev\\ProjectData\\car.txt");
			
			// 메모리에 전체데이터를 모아서 관리 
			BufferedReader in=new BufferedReader(new InputStreamReader(fr));
			
			while(true)
			{
				String car=in.readLine(); // readLine() => \n
				if(car==null) break; // 종료 => 파일을 다 읽은 경우 , 읽을게 없으면 null 되돌려줌
				/*
				 * 1
				 * |기아 더 K9 3.3 T-GDI AWD 마스터즈 Ⅲ
				 * |http://image.xn--289av8kwmfx2cf8r66ai5r.com/FileData/product/car25/20200531_58E5E37F4587FA95.jpg
				 * |929만원
				 * |2019/06
				 * |8,895km
				 * |자동
				 * |가솔린
				 */
				StringTokenizer st=new StringTokenizer(car,"|");
				while(st.hasMoreTokens())
				{
					CarVO vo=new CarVO();
					vo.setNo(Integer.parseInt(st.nextToken())); //반환이 string이라 형변환 필수
					vo.setName(st.nextToken());
					vo.setPoster("https:"+st.nextToken());
					vo.setPrice(st.nextToken());
					vo.setAge(st.nextToken());
					vo.setKm(st.nextToken());
					vo.setTrans(st.nextToken());
					vo.setOil(st.nextToken());
				
					list.add(vo);
				}
			}
			
		}catch(Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
	
	//페이지나누기
		public ArrayList<CarVO> carListData(int page)
		{
			ArrayList<CarVO> Cars=new ArrayList<CarVO>();
			int i=0;
			int j=0;
			int pagecnt=(page*10)-10;

			for(CarVO vo:list)
			{
				if(i<10 && j>=pagecnt)
				{
					Cars.add(vo);
					i++;// 10개씩 나눠주는 변수 
				}
				j++;// for돌아가는 횟수
			}
			return Cars;
		}
		
	//상세보기
	public CarVO CarDetailData(int no)
	{
		return list.get(no-1); //get은 인덱스 번호
	}
}
