package com.aziz.interview;

public class Main {

    public static void main(String[] args) {
	int batas = 100;
	String[] temp = new String[101];

	for (int a = 1; a <= batas; a++){

	    if (a % 3 == 0 && a % 5 == 0){
	    	temp[a] = "ApaBole";
		}else if(a % 5 == 0){
			temp[a] = "Bole";
		}else if(a % 3 == 0){
	    	temp[a] = "Apa";
		}else{
			temp[a] = String.valueOf(a);
		}
		System.out.print(temp[a]+", ");
    }
    }
}
