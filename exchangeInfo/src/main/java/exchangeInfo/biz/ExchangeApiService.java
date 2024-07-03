package exchangeInfo.biz;

import exchangeInfo.model.SearchExchange;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExchangeApiService {
	@GET("site/program/financial/exchangeJSON")
	Call<SearchExchange> getExchange(@Query("authkey") String authkey, @Query("data") String data);
	
}
