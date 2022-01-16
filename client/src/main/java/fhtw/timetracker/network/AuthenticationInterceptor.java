package fhtw.timetracker.network;

import fhtw.timetracker.service.StateService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("Authorization", StateService.getInstance().getAuthHeader())
                .build();
        return chain.proceed(newRequest);
    }
}
