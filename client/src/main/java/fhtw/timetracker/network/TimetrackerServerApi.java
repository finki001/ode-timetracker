package fhtw.timetracker.network;

import fhtw.timetracker.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TimetrackerServerApi {

    String BASE_URL = "http://localhost:8080/";

    @GET("users")
    Call<List<UserDTO>> findAllUsers();

    @GET("users/{login}")
    Call<UserDTO> findUserByLogin(@Path("login") String login);

    @POST("users")
    Call<Void> createUser(SignUpUser signUpUser);

    @DELETE("tasks/{userId}")
    Call<Boolean> deleteUser(@Path("userId") int userId);

    @POST("login")
    Call<Void> login(@Body Credentials credentials);

    @GET("tasks")
    Call<List<TaskDTO>> findAllTasks();

    @POST("tasks")
    Call<TaskDTO> createTask(TaskDTO taskDTO);

    @DELETE("tasks/{taskId}")
    Call<Boolean> deleteTask(@Path("taskId") int taskId);

    @GET("records")
    Call<List<RecordDTO>> findAllRecords();

    @GET("records/{userId}")
    Call<List<RecordDTO>> findAllRecordsForUserId(@Path("userId") int userId);

    @POST("records")
    Call<RecordDTO> createRecord(@Body RecordDTO recordDTO);

    @DELETE("records/{recordId}")
    Call<Boolean> deleteRecord(@Path("recordId") int recordId);
}
