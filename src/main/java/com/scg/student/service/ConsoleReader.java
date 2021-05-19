package main.java.com.scg.student.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleReader {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	public static int readInt() throws Exception {
		int i=Integer.parseInt(br.readLine());
		return i;
	}
	
	public static String readString() throws Exception {
		String s=br.readLine();
		return s;
	}

}
