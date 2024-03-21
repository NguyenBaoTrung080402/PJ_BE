package com.DSTA.PJ_BE;

import java.util.Scanner;

public class test {
    public static void checkNumber1(int n){
        if(n %2 != 0 ){
            System.out.println("Weird");
        } else{
            if ( n >= 2 && n<=5) {
                System.out.println("Not Weird");
            } else if (n >= 6 && n <= 20) {
                System.out.println("Weird");
            } else {
                System.out.println("Not Weird");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 1; i <= 10; i++) {
            int result = N*i;
            System.out.println(N + " x " + i + " = "+ result);
        }
    }
}
