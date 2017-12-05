package json.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONDecodingTestApp {

	public static void main(String[] args) {
		
		String data = "{\"address\":\"����\",\"age\":1000,\"name\":\"ȫ�浿\"}";
		JSONObject jsonObj = (JSONObject)JSONValue.parse(data);
		
		System.out.println("JSON �Է°� Ȯ�� :: "+jsonObj);
		
		System.out.println("<== Data ���� ==>");
		System.out.println(jsonObj.get("address"));
		System.out.println();
		
		String arrayData = "[\"����\",1000,\"ȫ�浿\"]";
		JSONArray jsonArray = (JSONArray)JSONValue.parse(arrayData);
		
		System.out.println("JSON �Է°� Ȯ�� :: "+jsonArray);
		
		System.out.println("<== Data ���� ==>");
		System.out.println(jsonArray.get(0));
		System.out.println();
	}

}
