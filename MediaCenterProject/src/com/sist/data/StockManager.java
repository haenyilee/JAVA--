package com.sist.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StockManager {

	
	private static ArrayList<StockVO> list = new ArrayList<StockVO>();
	
	static {
		try {
			
			FileInputStream fr = new FileInputStream("C:\\JavaDev\\ProjectData\\stock.txt");
			
			BufferedReader in=new BufferedReader(new InputStreamReader(fr));
			
			while(true) {
				
				String stock=in.readLine();
				if(stock==null) break;
				
						
						String[] data=stock.split("\\|");
						
						StockVO vo=new StockVO();
						
						vo.setMno(Integer.parseInt(data[0]));
						vo.setCno(Integer.parseInt(data[1]));
						vo.setTitle(data[2]);
						vo.setPoster(data[3]);
						vo.setValue(data[4]);
						vo.setChangeValue(data[5]);
						vo.setPercent(data[6]);
						vo.setTotal(data[7]);
						vo.setForeigner(data[8]);
						vo.setExchange(data[9]);
						
						list.add(vo);
					
			}
			
			
		} catch (Exception e) { 
			System.out.println(e.getMessage());
		}
	}
	
	
	public ArrayList<StockVO> stockListData(int page)
	{
		ArrayList<StockVO> stocks = new ArrayList<StockVO>();
		// ?????? ??????
				int i = 0;
				int j = 0;
				int pagecnt=(page*10)-10;
				/*
				 * 1page = 0~9
				 * 2page = 10~19
				 */
				for(StockVO vo:list)
				{
					// pagecnt 
					if(i<10 && j>=pagecnt)
					{
						stocks.add(vo);
						i++; 
					} 
					j++; 
				}
				return stocks;
	}
	
	// ??? a??(????????)
	public ArrayList<StockVO> stockFindData(String ss)
	{
		ArrayList<StockVO> stocks = new ArrayList<StockVO>();
		
		for(StockVO vo:list)
		{
			if(vo.getTitle().contains(ss))
			{
				stocks.add(vo);
			}
		}
		return stocks;
	}
	
	
	// ??? ??u ??????
	public int dramaTotalPage() {
		return (int) (Math.ceil(list.size()/10.0));
	}
	
	
	// ??? ??
	public StockVO stockDetailData(int mno)
	{
		return list.get(mno-1);
	}
	
	
	// ??? ???
	public ArrayList<StockVO> stockAllData(int cno)
	{
		
		ArrayList<StockVO> stocks = new ArrayList<StockVO>();
		for(StockVO vo : list)
		{
			if(vo.getCno()==cno)
			{
				stocks.add(vo);
			}
		}
		return stocks;
	}
	
	
	
	public static void main(String[] args) {
		StockManager s = new StockManager();
//		ArrayList<StockVO> list = s.sto
	}
	
	
	
	
	
	
}
