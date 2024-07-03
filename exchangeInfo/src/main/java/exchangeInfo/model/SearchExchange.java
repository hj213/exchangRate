package exchangeInfo.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchExchange {
    // 받아온 환율 리스트를 저장하는 필드
    @SerializedName("searchList")
    public List<Search> searchList;
    
    public static class Search{
    	@SerializedName("RESULT")
        public int result;
    	
    	@SerializedName("CUR_NAME")
        public String curName;
    	
    	@SerializedName("CUR_UNIT")
        public String curUnit;
    	
    	@SerializedName("TTB")
        public String ttb;
    	
    	@SerializedName("TTS")
        public String tts;
    	
    }
}
