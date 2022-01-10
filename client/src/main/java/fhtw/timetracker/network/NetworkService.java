package fhtw.timetracker.network;

import fhtw.timetracker.model.UserDTO;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class NetworkService {

    private final static String BASE_URL = "http://localhost:8080/";

    private final TimetrackerServerApi serverApi;

    public NetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new AuthenticationInterceptor()).build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(TimetrackerServerApi.class);
    }

    public void loadAllUsers() {
        serverApi.findAllUsers().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                System.out.println("onResponse, " + (response.body() != null ? response.body().size() : 0));
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                System.out.println("onFailure");
            }
        });
    }
}
