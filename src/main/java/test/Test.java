package test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.text.SimpleDateFormat sdfLongTimePlus = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String  create_time = sdfLongTimePlus.format("2013-08-01 10:40:01");
		System.out.println(create_time);
	}

}
