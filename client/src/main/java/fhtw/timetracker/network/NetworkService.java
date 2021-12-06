package fhtw.timetracker.network;

import fhtw.timetracker.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class NetworkService {

    private final TimetrackerServerApi serverApi;

    public NetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new AuthenticationInterceptor()).build())
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi = retrofit.create(TimetrackerServerApi.class);
    }

    public void loadAllUsers() {
        serverApi.findAllUsers().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println("onResponse, " + (response.body() != null ? response.body().size() : 0));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("onFailure");
            }
        });
    }
}
