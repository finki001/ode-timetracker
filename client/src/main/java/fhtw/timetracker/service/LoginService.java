package fhtw.timetracker.service;

import fhtw.timetracker.model.Credentials;
import fhtw.timetracker.model.Role;
import fhtw.timetracker.model.SignUpUser;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.network.AuthenticationInterceptor;
import fhtw.timetracker.network.NetworkCallback;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.network.TimetrackerServerApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {

    private final TimetrackerServerApi backendApi;

    private final NetworkService networkService;


    public LoginService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new AuthenticationInterceptor()).build())
                .baseUrl(TimetrackerServerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.backendApi = retrofit.create(TimetrackerServerApi.class);
        networkService = new NetworkService();
    }

    public void login(String username, String password, NetworkCallback<UserDTO> callback) {
        backendApi.login(new Credentials(username, password)).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    StateService.getInstance().setAuthHeader(response.headers().get("Authorization"));
                    networkService.loadUserByLogin(username, callback);
                } else {
                    callback.onResponse(false, null, new Exception("Error logging in: HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void createUser(String login, String firstname, String lastname, String password, NetworkCallback<Boolean> networkCallback) {
        backendApi.createUser(new SignUpUser(login, firstname, lastname, Role.USER, password)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    networkCallback.onResponse(true, true, null);
                } else {
                    networkCallback.onResponse(false, null, new Exception("Error creating user: HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                networkCallback.onResponse(false, null, t);
            }
        });
    }
}
