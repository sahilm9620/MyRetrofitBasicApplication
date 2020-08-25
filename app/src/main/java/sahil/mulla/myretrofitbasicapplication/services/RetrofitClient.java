package sahil.mulla.myretrofitbasicapplication.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public  static Retrofit getApiCient(String baseurl)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
