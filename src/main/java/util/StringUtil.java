package util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;



public class StringUtil {
	public static final Logger log = Logger.getLogger(StringUtil.class);
	public static boolean isNull(String arg){
		if(arg==null||arg.length()==0){
			return true;
		}
		return false;
	}
	
	public static boolean stringEqual(String a,String b){
		if(a==null&&b==null){
			return true;
		}
		if(a!=null&&!a.equals(b)){
			return false;
		}
		return true;
	}
	
	/**
	 * 字符持久化到本地文件
	 * @param source
	 * @param savepath
	 * @return 
	 * @return
	 */
	public static void saveFileLocal(String source, String savepath){
		File file = getFile(savepath);
		OutputStream outPut = null;
		OutputStreamWriter writer = null;
		try {
			outPut = new FileOutputStream(file);
			writer = new OutputStreamWriter(outPut,"UTF-8");
			writer.write(source);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
				outPut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 每次写入都是另起一行追加
	 * @param source
	 * @param savepath
	 */
	public static void saveFileLocalLine(List<String> sourceList, String savepath){
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		try {
			fileWriter = new FileWriter(savepath, true);
			writer = new BufferedWriter(fileWriter);
			Iterator<String> iterator = sourceList.iterator();
			while(iterator.hasNext()){
				writer.newLine();
				writer.write(iterator.next().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static File getFile(String savePath){
		File file = new File(savePath);
		if( !file.getParentFile().exists() ){
			file.getParentFile().mkdirs();
		}
		return file;
	}
	
	/**
	 * byte数组写入文件
	 * @param filePath
	 * @param sources
	 */
	public static String saveFileWithByte(String filePath, byte[] sources){
		File file = getFile(filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(sources);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != fos){
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		return filePath;
	}
	
	/**
	 * 读取本地文件
	 * @param localFilePath
	 * @return
	 */
	public static String getFileLocal(String localFilePath){
	    File file = new File(localFilePath);
	    if(!file.exists() || !file.isFile()){
	        return null;
	    }
		BufferedReader reader = null;
		StringBuffer buffer =new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(localFilePath), "UTF-8"));
			while(reader.ready()){
				buffer.append(reader.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
	
	/**
	 * 产生本地文件路径
	 */
	public static String getLocalPath(String sourceUrl, String dirPath){
		StringBuilder strBuilder = new StringBuilder(dirPath);
		String fileDir = JavaMd5.getMD5Str(sourceUrl);
		strBuilder.append(fileDir.substring(0,2)).append("/");
		strBuilder.append(fileDir.substring(2,4)).append("/");
		strBuilder.append(fileDir);
		return strBuilder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getLocalPath("http://www.yooli.com/dingcunbaoV/detail/6.html",""));
	}
	
	/**
	 * 读取文件
	 * @param filePath
	 * @return
	 */
	public static byte[] readFileWithByte(String filePath){
		File file = new File(filePath);
		if(null == file || !file.exists()){
			return null;
		}
		byte[] bytes = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = input.read(temp)) != -1) {
				output.write(temp, 0, size);
			}
			input.close();
			bytes = output.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
