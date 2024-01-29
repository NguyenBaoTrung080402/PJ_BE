package com.DSTA.PJ_BE.utils;

import java.util.Random;
import java.util.stream.Stream;

public class Characters {
	private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
			'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ', 'à', 'á', 'â',
			'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'ỳ', 'ỷ', 'ỹ', 'ỵ',
			'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
			'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
			'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
			'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
			'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
			'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
			'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
			'ữ', 'Ự', 'ự',};
			private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
			'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'Y', 'Y', 'Y', 'Y', 'a', 'a',
			'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
			'y', 'y', 'y', 'y', 'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
			'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', 'U', 'u',};

	private static final String SOURCE_STR = Stream.of(SOURCE_CHARACTERS).map(String::valueOf).reduce("", String::concat);
			
    private static char removeAccent(char ch) {
    	int index = SOURCE_STR.indexOf(ch);
    	if (index >= 0) {
    	ch = DESTINATION_CHARACTERS[index];
    	}
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
    
    public static String abbreviation(String str){
        str = Characters.conver(str);
        String abb = "" + str.charAt(0);
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ') abb = abb + str.charAt(i+1);
        }
        abb = str.substring(str.lastIndexOf(" ") + 1, str.length()) + abb.substring(0, abb.length()-1);
        return removeAccent(abb.toLowerCase());
    }
    
    public static String conver(String str){
        str = str.toLowerCase().trim();
        while (str.contains("  ")) {
            str = str.replaceAll("  ", " ");
        }
        str = String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1, str.length());
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' ') {
                str = str.substring(0, i+1) + String.valueOf(str.charAt(i+1)).toUpperCase() + str.substring(i+2, str.length());
            }
        }
        return str;
    }
    
    public static String getStringRamdom() {
        char[] pass = new char[10];
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            pass[i] = (char) ((char) rd.nextInt(75)+48);
            while ((pass[i] > 57 && pass[i] < 65) || (pass[i] > 90 && pass[i] < 97)) {
                pass[i] = (char) ((char) rd.nextInt(75)+48);
            }
        }
        return String.copyValueOf(pass);
    }
}
