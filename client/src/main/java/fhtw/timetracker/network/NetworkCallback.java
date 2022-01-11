package fhtw.timetracker.network;

public interface NetworkCallback<T> {
    void onResponse(boolean success, T response, Throwable error);
}
