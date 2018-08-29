package zzp.util.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.util.http.HttpRequest;

public class TestHttpRequest {

	public static void main(String[] args) {
		
		//�ٶȵ�ͼ��ַ�����ӿ�
		System.out.println(HttpRequest.sendGet("http://api.map.baidu.com/geocoder/v2/", "address=�㶫ʡ�������������ӿ���վ&output=json&ak=KaqwkA7ozlPx4KgYVXbTfbGOGUXiIp3R"));
		
		SimpleDateFormat smft = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//		//���������ӿ�4.15 ��ȡ��ǰ�˻��µĳ�����Ϣ 
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("method", "GetVehicleNoList");
//		params.put("appkey", "30914b89-262a-4832-b8b9-fe33770b4b4d");
//		params.put("timestamp", "2018-08-29 10:13:36");
//		params.put("format", "json");
//		String result = HttpRequest.sendGet("http://api.e6gps.com/public/v3/StatisticsReport/Call", "69411cff-d7be-4458-952f-2fca0267b408", params);
//		System.out.println(result);
//		
//		Map map1 = JSON.parseObject(result);
//		System.out.println(map1);
//		Map<String, Object> map2 = (Map<String, Object>)map1;
//		for (Map.Entry<String,Object> entry : map2.entrySet()) {
//			System.out.println(entry.getKey()+"--->"+entry.getValue());
//		}
		
//		//4.2 ��ȡ����������λ����Ϣ 
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("method", "GetVehcileInfo");
//		params.put("appkey", "30914b89-262a-4832-b8b9-fe33770b4b4d");
//		params.put("timestamp", smft.format(new Date()));
//		params.put("format", "json");
//		params.put("isoffsetlonlat", "2");
//		params.put("sessionid", "");
//		params.put("vehicle", "��BAD548,��BAN575");
//		String result = HttpRequest.sendGet("http://api.e6gps.com/public/v3/Inface/Call", "69411cff-d7be-4458-952f-2fca0267b408", params);
//		System.out.println(result);
		
		//4.14��ѯ�����ص���Ԥ��ʱ������
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "GetDriveInfoByPlaceName");
		params.put("appkey", "30914b89-262a-4832-b8b9-fe33770b4b4d");
		params.put("timestamp", smft.format(new Date()));
		params.put("format", "json");
		params.put("startplacename", "�㶫ʡ�������������ӿ���վ");
		params.put("endplacename", "�㶫ʡ����������������³Ƕ�����������������98��405");
		String result = HttpRequest.sendGet("http://api.e6gps.com/public/v3/StatisticsReport/Call", "69411cff-d7be-4458-952f-2fca0267b408", params);
		System.out.println(result);
	
	}

}
