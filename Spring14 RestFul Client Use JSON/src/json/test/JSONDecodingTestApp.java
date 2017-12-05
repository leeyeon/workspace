package json.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONDecodingTestApp {

	public static void main(String[] args) {
		
		String data = "{\"address\":\"서울\",\"age\":1000,\"name\":\"홍길동\"}";
		JSONObject jsonObj = (JSONObject)JSONValue.parse(data);
		
		System.out.println("JSON 입력값 확인 :: "+jsonObj);
		
		System.out.println("<== Data 추출 ==>");
		System.out.println(jsonObj.get("address"));
		System.out.println();
		
		String arrayData = "[\"서울\",1000,\"홍길동\"]";
		JSONArray jsonArray = (JSONArray)JSONValue.parse(arrayData);
		
		System.out.println("JSON 입력값 확인 :: "+jsonArray);
		
		System.out.println("<== Data 추출 ==>");
		System.out.println(jsonArray.get(0));
		System.out.println();
	}

}
