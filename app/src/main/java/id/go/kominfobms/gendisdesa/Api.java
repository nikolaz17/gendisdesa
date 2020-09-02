package id.go.kominfobms.gendisdesa;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Api {
    private static final String BASE_URL = "http://simpkb.banyumaskab.go.id/";
    private static final String DEFAULT_USERNAME_PASSWORD = "default";

    private static final OkHttpClient client = new OkHttpClient.Builder()

            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // TODO : https://stackoverflow.com/questions/50981413/how-to-fix-ssl-handshake-timed-out-in-retrofit
            .addConverterFactory(ScalarsConverterFactory.create())      //TODO : BIAR KALO MULTIPART GA ADA PETIKNYA
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit retrofit = builder.build();

    //TODO : PAKE CONTEXT
    public static <S> S createService(Context context, Class<S> serviceClass) {
        return createService(context, serviceClass, DEFAULT_USERNAME_PASSWORD, DEFAULT_USERNAME_PASSWORD);
    }

    private static <S> S createService(Context context, Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(context, serviceClass, authToken);
        }
        return createService(context, serviceClass, null);
    }

    private static <S> S createService(Context context, Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(context, authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }

    //TODO : GA PAKE CONTEXT
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, DEFAULT_USERNAME_PASSWORD, DEFAULT_USERNAME_PASSWORD);
    }

    private static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }
        return createService(serviceClass, null);
    }

    private static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
}

class AuthenticationInterceptor implements Interceptor {
    private Context context;
    private String authToken;

    AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    AuthenticationInterceptor(Context context, String authToken) {
        this.context = context;
        this.authToken = authToken;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
//                .header(App.NAME_API_KEY, App.DEFAULT_API_KEY);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
