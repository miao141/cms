package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class JavaMd5 {
	
	private static final Logger log = Logger.getLogger(JavaMd5.class);
	
	  public static String getMD5Str(String str) { 
		  
			  if(StringUtils.isBlank(str)){
				  return null;
			  }
			  MessageDigest messageDigest = null;  
	  
	        try {  
	            messageDigest = MessageDigest.getInstance("MD5");  
	  
	            messageDigest.reset();  
	  
	            messageDigest.update(str.getBytes("UTF-8"));  
	        } catch (NoSuchAlgorithmException e) {  
	        	log.info("NoSuchAlgorithmException caught!",e);  
	            System.exit(-1);  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	  
	        byte[] byteArray = messageDigest.digest();  
	  
	        StringBuffer md5StrBuff = new StringBuffer();  
	  
	        for (int i = 0; i < byteArray.length; i++) {              
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
	            else  
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
	        }  
	  
	        return md5StrBuff.toString();  
	    }  
	  
	  public static void main(String []args){
		  System.out.println(JavaMd5.getMD5Str("http://www.iqianjin.com/plan/160/2a7a9eaa23.html"));
	  }
}
