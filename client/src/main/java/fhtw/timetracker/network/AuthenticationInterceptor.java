package fhtw.timetracker.network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest  = chain.request().newBuilder()
                // TODO: remove this, just hardcoded for testing
                .addHeader("Authorization", "Bearer " + "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWthcyIsInJvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwibmFtZSI6Ikx1a2FzIEZpbmsiLCJleHAiOjE2Mzk2NzQ4NDV9.Dh7fdh_-Nw3vvm2VB06oUPdCJGqmmmK2m5MEa-ACgsBps_WrXyo1M4FoGcG82yJmVzG1hXXOdp5ZJoD09QEz3A")
                .build();
        return chain.proceed(newRequest);
    }
}
