package com.employee.controller;

public class TestChangeCode {

	public static void main(String[] args) {
		String code =  "1g3tRIgw";
		char[] forCode = code.toCharArray();
		char[] newCode = new char[code.length()];
		
		for(int i = 0 ; i < code.length(); i++) {
			if(i < (code.length()-2)) {
				newCode[i + 2] = forCode[i]; 
			}else {
				newCode[i - (code.length()-2)] = forCode[i];
			}
		}
		String newPwd = String.valueOf(newCode);
		System.out.println(newPwd);
		
		char[] forShow = newPwd.toCharArray();
		char[] gobackCode = new char[newPwd.length()];
		for(int i = 0 ; i < newPwd.length(); i++) {
			if(i < 2) {
				gobackCode[i + ((newPwd.length()-2))] = forShow[i]; 
			}else {
				gobackCode[i -2] = forShow[i];
			}
		}
		String showPwd = String.valueOf(gobackCode);
		System.out.println(showPwd);
	}
}
