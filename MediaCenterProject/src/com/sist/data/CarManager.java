package com.sist.data;
import java.util.*;
import java.io.*;

public class CarManager {
	private static ArrayList<CarVO> list //CarVO ��ü(�޼���)�� ���
			=new ArrayList<CarVO>();
	static
	{
		try
		{

			//����� ���� �б�
			FileInputStream fr=new FileInputStream("C:\\JavaDev\\ProjectData\\car.txt");
			
			// �޸𸮿� ��ü�����͸� ��Ƽ� ���� 
			BufferedReader in=new BufferedReader(new InputStreamReader(fr));
			
			while(true)
			{
				String car=in.readLine(); // readLine() => \n
				if(car==null) break; // ���� => ������ �� ���� ��� , ������ ������ null �ǵ�����
				/*
				 * 1
				 * |��� �� K9 3.3 T-GDI AWD �������� ��
				 * |http://image.xn--289av8kwmfx2cf8r66ai5r.com/FileData/product/car25/20200531_58E5E37F4587FA95.jpg
				 * |929����
				 * |2019/06
				 * |8,895km
				 * |�ڵ�
				 * |���ָ�
				 */
				StringTokenizer st=new StringTokenizer(car,"|");
				while(st.hasMoreTokens())
				{
					CarVO vo=new CarVO();
					vo.setNo(Integer.parseInt(st.nextToken())); //��ȯ�� string�̶� ����ȯ �ʼ�
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
	
	//������������
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
					i++;// 10���� �����ִ� ���� 
				}
				j++;// for���ư��� Ƚ��
			}
			return Cars;
		}
		
	//�󼼺���
	public CarVO CarDetailData(int no)
	{
		return list.get(no-1); //get�� �ε��� ��ȣ
	}
}
