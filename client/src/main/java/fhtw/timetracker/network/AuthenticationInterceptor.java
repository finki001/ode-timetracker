package fhtw.timetracker.network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    // TODO: this is disgusting but works for now
    public static String authHeader = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("Authorization", authHeader)
                .build();
        return chain.proceed(newRequest);
    }
}
