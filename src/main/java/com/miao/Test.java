package com.miao;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File file = new File("F:\\u\\dao\\mapper\\Order_shippingMapper.xml");
		ChangeFileCode changeFileCode = new ChangeFileCode();  
        changeFileCode.setFileIn(file.getPath(), "GBK");//如果文件编码为ANSI用GBK来读，如果是UTF-8用UTF-8来读  
        changeFileCode.setFileOut(file.getPath(), "UTF-8");//UTF-8则文件编码为UTF-8， 如果为GBK，编码为ANSI  
        changeFileCode.start();  

	}

}
