package fhtw.timetracker.network;

import fhtw.timetracker.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface TimetrackerServerApi {
    @GET("users")
    Call<List<User>> findAllUsers();
}
