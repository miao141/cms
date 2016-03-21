package com.sqltojava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 根据sql文件生成javabean
 * @author yun
 *
 */
public class Stob {
	
	public static void main(String[] args) {

		 SqlUtil.create("D:\\1.sql","F:\\u");//myapproval
		/*StringBuffer classInfo = new StringBuffer();
		
		File tempFile =new File( "F:\\u"+ "\\a\\Mapper1"+ ".xml".trim());  
		  
        String fileName = tempFile.getName();  
        String filePath = tempFile.getParent(); 
        System.out.println("fileName = " + fileName+"  "+filePath);  */
		
	 
		//目前仅支持已经存在的目录
	}
	
	public static void createNewFile(String fileDirectoryAndName,String fileContent){
		  try{
		   String fileName = fileDirectoryAndName;
		   //创建File对象，参数为String类型，表示目录名
		   File myFile = new File(fileName);
		   //判断文件是否存在，如果不存在则调用createNewFile()方法创建新目录，否则跳至异常处理代码
		   if(!myFile.exists())
		    myFile.createNewFile();
		   else  //如果不存在则扔出异常
		    throw new Exception("The new file already exists!");
		   //下面把数据写入创建的文件，首先新建文件名为参数创建FileWriter对象
		   FileWriter resultFile = new FileWriter(myFile);
		   //把该对象包装进PrinterWriter对象
		   PrintWriter myNewFile = new PrintWriter(resultFile);
		   //再通过PrinterWriter对象的println()方法把字符串数据写入新建文件
		   myNewFile.println(fileContent);
		   resultFile.close();   //关闭文件写入流
		  }catch(Exception ex){
		   System.out.println("无法创建新文件！");
		   ex.printStackTrace();
		  }
		 }

}
