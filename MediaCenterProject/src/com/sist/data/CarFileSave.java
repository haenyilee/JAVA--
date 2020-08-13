package com.sist.data;

import java.awt.Image;
import java.io.FileWriter;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class CarFileSave {
	
	 public void carAllData() 
	    {
	     try {
		    	ArrayList<CarVO> list=
		    			new ArrayList<CarVO>();
		    	
		    	int no=1;
		    	
		    	for(int i=1;i<=5;i++)
		    	{
		    		// 사이트 연결해서 데이터를 한번에 읽기 => 메모리 저장 => Document
		    		Document doc=Jsoup.connect("https://www.bobaedream.co.kr/mycar/mycar_list.php?gubun=K&page="+i+"&order=S11&view_size=20").get();
		    		System.out.println(doc);
		    	    Elements link=doc.select("p.tit a");
		    	    
		    	    for(int j=0;j<link.size();j++)
		    	    {
		    	    	try {
		    	    	String url="https://www.bobaedream.co.kr/"+link.get(j).attr("href");
		    	    	Document doc2=Jsoup.connect(url).get();
		    	    	
		    	    	System.out.println(no);
		    	    	System.out.println(url);
		    	    	

		    	    	
		    	    	Element poster=doc2.selectFirst("li.is-visible img");
		    	    	System.out.println("차사진:"+poster.attr("src"));
		    	    	
		    	    	Element name=doc2.selectFirst("h3.tit");
		    	    	System.out.println("차이름:"+name.text());
		    	    	
		    	    	Element price=doc2.selectFirst("span.price");
		    	    	System.out.println("가격:"+price.text());
		    	    	
		    	    	Element age=doc2.select("td").get(0);
		    	    	System.out.println("연식:"+age.text());

		    	    	Element km=doc2.select("td").get(2);
		    	    	System.out.println("주행:"+km.text());
		    	    	
		    	    	Element trans=doc2.select("td").get(4);
		    	    	System.out.println("변속기:"+trans.text());
		    	    	
		    	    	Element oil=doc2.select("td").get(6);
		    	    	System.out.println("연료:"+oil.text());
		    	    	
		    	    	System.out.println("\n");
		    	    	
		    	    	String msg= no+"|"+ name.text()+"|"+poster.attr("src")+"|"
		    	    				+price.text()+"|"+age.text()+"|" +km.text()+"|"
		    	    				+trans.text()+"|"+oil.text()+"\r\n";
		    	    	
		    	    	FileWriter fw=new FileWriter("c:\\javaDev\\car111.txt",true);
		    	    	fw.write(msg);
		    	    	fw.close();
		    	    	
		    	    	no++;
	
		    	    	}
		    	    	catch (Exception e) {}
		    	    }
		    	}
	      }catch(Exception ex)
	      {
	    	   System.out.println(ex.getMessage());// 에러 메세지 출력 
	      }

	    }
	 
	 public static void main(String[] args) {
			
		 	CarFileSave m=new CarFileSave();
			m.carAllData();
		}
}
