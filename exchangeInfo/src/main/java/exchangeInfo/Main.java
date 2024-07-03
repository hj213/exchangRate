package exchangeInfo;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import exchangeInfo.biz.ChatbotApiService;
import exchangeInfo.biz.ExchangeApiService;
import exchangeInfo.model.SearchExchange;
import exchangeInfo.model.UpdateResponse;
import exchangeInfo.model.UpdateResponse.Update;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {
	public static void main(String[] args) {
		//open api & chat bot info
		final String APIKEY = "u5aZStkmyqhNCE3COvjw7UbEPXUHcfFI";
		final String BOTTOKEN = "7091194819:AAE06jKpOc5O11c1YZg97eurqLKSYv00M-s";
		String data = "AP01";
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.telegram.org/bot" + BOTTOKEN + "/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		Retrofit retrofit2 = new Retrofit.Builder()
				.baseUrl("https://www.koreaexim.go.kr/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		ChatbotApiService chatbotService = retrofit.create(ChatbotApiService.class);
		ExchangeApiService exchangeService = retrofit2.create(ExchangeApiService.class);
		
		try {
			UpdateResponse response = chatbotService.getUpdates(0).execute().body();
			SearchExchange exchange = exchangeService.getExchange(APIKEY, data).execute().body();
			//초기 값 업데이트
			long lastId = 0;
			if (response != null && response.result != null && !response.result.isEmpty()) {
                lastId = response.result.get(response.result.size() - 1).updateId;
            }
			
			Scanner sc = new Scanner(System.in);
			
			//업데이트들 확인해서 응답해주기
			while(true) {
				TimeUnit.SECONDS.sleep(1); //매초마다 업데이트 확인(중지시켜서)
				
				response = chatbotService.getUpdates(lastId + 1).execute().body();
				
				if(response != null && response.result != null && !response.result.isEmpty()) {
					
					for(Update update : response.result) {
						long id = update.message.from.id;
						String text = update.message.text;
						
						if(text.equals("/start")){
							
							String greet = "안녕하세요! 오늘의 환율 정보를 알려드리는 챗봇입니다. \n 어느 나라의 환률 정보를 원하시나요?";
							chatbotService.sendMessage(String.valueOf(id),greet).execute().body();
							
						} else {
							
							//open api result
							String reply = "안";
							
							chatbotService.sendMessage(String.valueOf(id),reply).execute().body();
							
							lastId = update.updateId;
							
						}
					}
				}
				
			}
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		while(true) {
			
		}
		
	}
}
