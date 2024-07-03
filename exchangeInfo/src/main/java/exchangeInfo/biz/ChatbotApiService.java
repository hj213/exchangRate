package exchangeInfo.biz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import exchangeInfo.model.UpdateResponse;
import exchangeInfo.model.SendResponse;

public interface ChatbotApiService {
	@GET("getUpdates")
	Call<UpdateResponse> getUpdates(@Query("offset") long offset); 
	
	@GET("sendMessage")
	Call<SendResponse> sendMessage(@Query("chat_id") String chatId, @Query("text") String text);
}
