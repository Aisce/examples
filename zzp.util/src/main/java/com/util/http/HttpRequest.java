package com.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.mapsort.MapSort;

/**
 * http���󹤾���
 * 
 * @author karyzeng
 * @since 2018.08.28
 *
 */
public class HttpRequest {

	public static void main(String[] args) {
//		System.out.println(HttpRequest.sendGet("http://api.map.baidu.com/geocoder/v2/", "address=�㶫ʡ����������������³Ƕ�����������������98��405&output=json&ak=KaqwkA7ozlPx4KgYVXbTfbGOGUXiIp3R"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "GetVehicleNoList");
		params.put("appkey", "30914b89-262a-4832-b8b9-fe33770b4b4d");
		params.put("timestamp", "2018-08-29 10:13:36");
		params.put("format", "json");
		System.out.println(HttpRequest.sendGet("http://api.e6gps.com/public/v3/StatisticsReport/Call", "69411cff-d7be-4458-952f-2fca0267b408", params));
	}
	
	/**
     * ��ָ��URL����GET����������
     * 
     * @param url ���������URL
     * @param param ����������������Ӧ���� name1=value1&name2=value2 ����ʽ
     * 
     * @return String ��Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * ��ָ��URL����GET����������
     * 
     * @param url ���������URL
     * @param appsecret ˽Կ
     * @param params ǩ�������Զ����ɣ�����Ҫ����
     * 
     * @return String ��Ӧ���
     */
    public static String sendGet(String url, String appsecret, Map<String, String> params){
    	String sign = getSign(appsecret, params);
    	StringBuffer sBuffer = new StringBuffer();
    	for (Map.Entry<String, String> entry : params.entrySet()) {
    		if (entry.getKey().equals("timestamp")) {
    			//timestampʱ���ַ�����ʽ��2018-08-29 10:13:36�������пո���Ҫ�ȶԺ��пո�Ĳ������д���
				String value = "";
    			try {
					value = URLEncoder.encode(entry.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    			sBuffer.append(entry.getKey() + "=" + value + "&");
			} else {
				sBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
    	}
    	sBuffer.append("sign=" +sign);
    	return sendGet(url, sBuffer.toString());
    }
    
    /**
     * ���ǩ��sign
     * 
     * @param appsecret ˽Կ
     * @param params ǩ������sign�Զ����ɣ�����Ҫ����
     * 
     * @return String ǩ��sign
     */
    public static String getSign(String appsecret, Map<String, String> params) {
    	Map<String, String> sortMap = MapSort.sortMapByKey(params);
    	StringBuffer sBuffer = new StringBuffer();
    	sBuffer.append(appsecret);
    	for (Map.Entry<String, String> entry : sortMap.entrySet()) {
    		sBuffer.append(entry.getKey() + entry.getValue());
    	}
    	sBuffer.append(appsecret);
    	return MD5Util.string2MD5(sBuffer.toString()).toUpperCase();
    }
    
    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url ��������� URL
     * @param param ����������������Ӧ���� name1=value1&name2=value2 ����ʽ
     * 
     * @return String ��Ӧ���
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

}
