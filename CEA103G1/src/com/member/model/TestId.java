package com.member.model;

public class TestId {

	public static void main(String[] args) {

		String check = testId("G221989078");
		System.out.println(check);
	}

	public static String testId(String id) {
		char[] idCode = id.toCharArray();
		char[] local = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'W', 'Z', 'I', 'O'};
		int num = 10;
		for(int i = 0; i < local.length; i++){
			if(idCode[0] == (local[i])){
				num += i;
			}
		}
		String[] nums = new Integer(num).toString().split("");
		int checkNum = new Integer(nums[0]) * 1 + new Integer(nums[1]) * 9;
		for(int i = 1; i < 9; i++){
			checkNum = checkNum + new Integer(idCode[i]) * (Math.abs(i - 9));
		}
		checkNum = 10 - (checkNum % 10);
		if(new Integer(idCode[9]) == checkNum){
			return "true";
		}else{
			return "false";
		}
	}
}
