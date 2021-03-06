package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Page;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.Search;

public class PurchaseRestHttpClientApp {

	public static void main(String[] args) throws Exception {
		//PurchaseRestHttpClientApp.getPurchaseTest();
		//PurchaseRestHttpClientApp.addPurchaseTest();
		//PurchaseRestHttpClientApp.updatePurchaseTest();
		//PurchaseRestHttpClientApp.listPurchaseTest();
		//PurchaseRestHttpClientApp.listSaleTest();
		//PurchaseRestHttpClientApp.updateTranCodeTest();
		PurchaseRestHttpClientApp.updateTranCodeTest();
	}
	
	public static void getPurchaseTest() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		// prodNo = 1002 / prodName = 마이구미
		String url= "http://127.0.0.1:8080/purchase/json/getPurchase/tranNo/10060";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println(httpResponse);
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase purchase = objectMapper.readValue(jsonobj.toString(), Purchase.class);
		
		System.out.println("Purchase :: "+purchase);
		
	}
	
	public static void addPurchaseTest() throws Exception {
		  
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/addPurchase/10011/testUserId";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("paymentOption", "0");
		json.put("receiverName", "테스트");
		json.put("receiverPhone", "010-1234-5678");
		json.put("divyAddr", "테스트");
		json.put("divyRequest", "테스트");
		json.put("divyDate", "20171204");
		json.put("amount", 1);
		
		//System.out.println(json);
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		if(serverData.equals("true")) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	
	public static void updatePurchaseTest() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/updatePurchase";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("tranNo", 10042);
		json.put("paymentOption", "0");
		json.put("receiverName", "테스트");
		json.put("receiverPhone", "010-1234-5678");
		json.put("divyAddr", "테스트");
		json.put("divyRequest", "테스트");
		json.put("divyDate", "20171204");
		json.put("amount", 1);
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		System.out.println("JSON :: "+json);

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("httpEntity :: "+httpEntity);
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		if(serverData.equals("true")) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		
	}
	
	public static void listPurchaseTest() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/listPurchase/user02";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		System.out.println("JSON :: "+json);

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("httpEntity :: "+httpEntity);
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
		// CodeHaus...
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Purchase> list = objectMapper.readValue(jsonobj.get("list").toString(), 
				new TypeReference<List<Purchase>>() {});
		Page resultPage = objectMapper.readValue(jsonobj.get("resultPage").toString(), Page.class);
		Search search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		System.out.println("resultPage :: "+resultPage);
		System.out.println("search :: "+search);
		
		for (Purchase purchase : list) {
			System.out.println(purchase);
		}
		
	}
	
	public static void listSaleTest() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/listSale";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		System.out.println("JSON :: "+json);

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("httpEntity :: "+httpEntity);
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
		
		// CodeHaus...
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Purchase> list = objectMapper.readValue(jsonobj.get("list").toString(), 
				new TypeReference<List<Purchase>>() {});
		Page resultPage = objectMapper.readValue(jsonobj.get("resultPage").toString(), Page.class);
		Search search = objectMapper.readValue(jsonobj.get("search").toString(), Search.class);
		System.out.println("resultPage :: "+resultPage);
		System.out.println("search :: "+search);
		
		for (Purchase purchase : list) {
			System.out.println(purchase);
		}
	}

	public static void updateTranCodeTest() throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/purchase/json/updateTranCode";
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("tranNo", 10101);
		json.put("tranCode", 2);
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		System.out.println("JSON :: "+json);

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		System.out.println("httpEntity :: "+httpEntity);
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		if(serverData.equals("true")) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
}
