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
    public static String string2MD5(String str) {
		String result = "";
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			//��str�ı�������Ϊutf-8���������ĳ���MD5��һ�µ����
			md5.update(str.getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte b[] = md5.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		result = buf.toString();
 
		return result;
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
        String s = new String("��BAD548");  
        System.out.println("ԭʼ��" + s);  
        System.out.println("MD5��" + string2MD5(s));  
        System.out.println("���ܵģ�" + convertMD5(s));  
        System.out.println("���ܵģ�" + convertMD5(convertMD5(s)));  
    }  
	
}
