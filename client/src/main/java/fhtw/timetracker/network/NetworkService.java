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

    public void loadUserByLogin(final String login, NetworkCallback<UserDTO> callback) {
        serverApi.findUserByLogin(login).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    UserDTO user = response.body();
                    if (user != null) {
                        callback.onResponse(true, user, null);
                    } else {
                        callback.onResponse(false, null, new Exception("loadUserByLogin was not successful: user is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("loadUserByLogin was not successful: HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    /**
     * TODO: this is only an example code to see, how to make a retrofit call
     */
    public void loadAllUsers(NetworkCallback<List<UserDTO>> callback) {
        serverApi.findAllUsers().enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if (response.isSuccessful()) {
                    List<UserDTO> responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to load all users, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    // TODO: implement methods from TimetrackerServerApi…
}
