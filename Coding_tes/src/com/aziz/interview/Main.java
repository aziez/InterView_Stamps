package com.aziz.interview;

public class Main {
    public static void main(String[] args) {
	    Integer[] array1 = { 1, 2, 3, 4, 10, 20, 30, 100, 200, 300, 400, 1000, 2000, 3000 };
	    Integer[] array2 = {3,15,5,6, 8,9,11,12,13,4};

        for (Integer integer : array1) {
            for (Integer value : array2) {
                if (integer.equals(value)) {
                    System.out.print(integer);
                }
            }
        }
    }
}
