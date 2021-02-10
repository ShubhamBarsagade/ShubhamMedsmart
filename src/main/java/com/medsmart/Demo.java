package com.medsmart;

public class Demo {

	public static void main(String[] args) {
		
		String a="abc";
		
		String b="ABC";
		
		if(a==b) {
			System.out.println("a = b");
		}
		if(a.equals(b)) {
			System.out.println("a equals b");
			
		}
		if(a.equalsIgnoreCase(b)) {
			System.out.println("a equalEgnoreCase b");
		}

	}

}
