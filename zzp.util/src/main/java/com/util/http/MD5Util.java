package com.util.http;

import java.security.MessageDigest;

/**
 * MD5������
 * @author karyzeng
 * @since 2018.08.29
 *
 */
public class MD5Util {

	/** 
     * MD5���� ����32λmd5�� 
     * 
     * @param str Ҫ����MD5���ַ���
     * 
     * @return String
     */  
    public static String string2MD5(String str){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = str.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /** 
     * ���ܽ����㷨 ִ��һ�μ��ܣ����ν��� 
     * 
     * @param str��ʾҪ���ܻ��߽��ܵ��ַ���
     * 
     * @return String
     */   
    public static String convertMD5(String str){  
  
        char[] a = str.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
  
    public static void main(String args[]) {  
        String s = new String("tangfuqiang");  
        System.out.println("ԭʼ��" + s);  
        System.out.println("MD5��" + string2MD5(s));  
        System.out.println("���ܵģ�" + convertMD5(s));  
        System.out.println("���ܵģ�" + convertMD5(convertMD5(s)));  
    }  
	
}
