package com.itcast.utils;

import java.util.Random;

public class CheckCodeUtils {
    
    public static String getCheckCode(int number){
        String[] sentCode =  {"1","2","3","4","5","6","7","8","9","0","q","w","e","r","t","y","u","i","p","a","s","d","f","g","h","z","j","k","l","x","c","v","b","n","m"};
        int length = sentCode.length;
        String code ="";
        if (number==6){
        for (int i = 0; i < 6; i++) {
            Random num = new Random();
            int nextInt = num.nextInt(length);
            code +=  sentCode[nextInt];
        }
        }
        if (number==4){
            for (int i = 0; i < 4; i++) {
                Random num = new Random();
                int nextInt = num.nextInt(length);
                code +=  sentCode[nextInt];
            }
        }
        return code;
    }
    public static void main(String[] args) {
        String checkCode = getCheckCode(4);
        System.out.println("checkCode = " + checkCode);
    }
}
