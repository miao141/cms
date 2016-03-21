package com.sqltojava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ReadFile {

	public static void main(String[] args) {
		  File f1 = new File("E:\\personal\\cms\\jeetemp-demo1.1\\WebContent\\WEB-INF\\jsp\\admin\\TbsA\\searchDlg.jsp");
		  File f2 = new File("E:\\personal\\cms\\jeetemp-demo1.1\\WebContent\\WEB-INF\\jsp\\admin\\TbsA\\searchDlgA.jsp");
		  //int b=0;
		  String line="";
		  try {
		   FileReader reader = new FileReader(f1);
		   FileWriter writer = new FileWriter(f2);
		  // BufferedReader br = new BufferedReader(reader);
		   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f1),"UTF-8"));
		   BufferedWriter bw = new BufferedWriter(writer);
		   
		   
		   while((line=br.readLine())!=null){
			System.out.println(line);
			String strLine = "classInfo.append(\""+line+"\\r\\n\");";
		    bw.write(strLine);
		    bw.newLine();
		    
		   }
		   bw.close();
		   br.close();
		   reader.close();
		   writer.close();
		  
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		 }
}
