package com.example.devDemo;

public class QuoteDemo {

    public static void main(String[] args) {
        // write your code here
        int i = 10;
        getNumberCount(1);
        System.out.println("onece i = " + i);

        i = getNumberCount(i, 0);
        System.out.println("twince i = " + i);

    }


    private static void getNumberCount(int i) {
        i += 1;
    }

    public static int getNumberCount(int i, int j) {
        return i += 1;
    }
}
