package fhtw.timetracker.network;

import fhtw.timetracker.model.UserDTO;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.HttpURLConnection;
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

    /**
     * TODO: this is only an example code to see, how to make a retrofit call
     */
    public void loadAllUsers() {
        serverApi.findAllUsers().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                // check if the status code is ok
                System.out.println("Response Code was: HTTP - " + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    List<UserDTO> responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.isEmpty()) {
                            System.out.println("no users in database");
                        } else {
                            // there are users in the responseBody (type of the response body is defined by the generic implementation of Callback
                            // (IntelliJ IDEA does this for you, so no worries

                            responseBody.forEach(System.out::println);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                // TODO: error handling
                System.out.println("onFailure");
            }
        });
    }
}
