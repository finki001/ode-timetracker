package fhtw.timetracker.network;

import com.google.gson.GsonBuilder;
import fhtw.timetracker.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDateTime;
import java.util.List;

public class NetworkService {

    private final static String BASE_URL = "http://localhost:8080/";

    private final TimetrackerServerApi serverApi;

    public NetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new AuthenticationInterceptor()).build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create()))
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

    public void createUser(String login, String firstname, String lastname, Role role, String passwordPlain, NetworkCallback<Void> callback) {
        serverApi.createUser(new SignUpUser(login, firstname, lastname, role, passwordPlain)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void responseBody = response.body();
                if (response.isSuccessful()) {
                    callback.onResponse(true, responseBody, null);
                } else {
                    callback.onResponse(false, null, new Exception("Failed to create User HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void deleteUser(int userId, NetworkCallback<Boolean> callback) {
        serverApi.deleteUser(userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean responseBody = response.body();
                if (response.isSuccessful()) {
                    callback.onResponse(true, responseBody, null);
                } else {
                    callback.onResponse(false, null, new Exception("Failed to delete User HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void findAllTasks(NetworkCallback<List<TaskDTO>> callback) {
        serverApi.findAllTasks().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TaskDTO>> call, Response<List<TaskDTO>> response) {
                if (response.isSuccessful()) {
                    List<TaskDTO> responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to load all tasks, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<TaskDTO>> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void createTask(TaskDTO taskDTO, NetworkCallback<TaskDTO> callback) {
        serverApi.createTask(taskDTO).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TaskDTO> call, Response<TaskDTO> response) {
                if (response.isSuccessful()) {
                    TaskDTO responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to create Task, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<TaskDTO> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void deleteTask(int taskId, NetworkCallback<Boolean> callback) {
        serverApi.deleteTask(taskId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(true, null, null);
                } else {
                    callback.onResponse(false, null, new Exception("Failed to delete Task, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void findAllRecords(NetworkCallback<List<RecordDTO>> callback) {
        serverApi.findAllRecords().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<RecordDTO>> call, Response<List<RecordDTO>> response) {
                if (response.isSuccessful()) {
                    List<RecordDTO> responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to load all records, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<RecordDTO>> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void findAllRecordsForUserId(int userId, NetworkCallback<List<RecordDTO>> callback) {
        serverApi.findAllRecordsForUserId(userId).enqueue(new Callback<List<RecordDTO>>() {
            @Override
            public void onResponse(Call<List<RecordDTO>> call, Response<List<RecordDTO>> response) {
                if (response.isSuccessful()) {
                    List<RecordDTO> responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to load add records for user id, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<RecordDTO>> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void createRecord(RecordDTO recordDTO, NetworkCallback<RecordDTO> callback) {
        serverApi.createRecord(recordDTO).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<RecordDTO> call, Response<RecordDTO> response) {
                if (response.isSuccessful()) {
                    RecordDTO responseBody = response.body();

                    if (responseBody != null) {
                        callback.onResponse(true, responseBody, null);
                    } else {
                        callback.onResponse(false, null, new Exception("Response Body is null"));
                    }
                } else {
                    callback.onResponse(false, null, new Exception("Failed to create Record, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<RecordDTO> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }

    public void deleteRecord(int recordId, NetworkCallback<Boolean> callback) {
        serverApi.deleteRecord(recordId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(true, null, null);
                } else {
                    callback.onResponse(false, null, new Exception("Failed to delete Record, HTTP - " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onResponse(false, null, t);
            }
        });
    }
}
