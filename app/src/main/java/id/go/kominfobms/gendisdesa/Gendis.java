package id.go.kominfobms.gendisdesa;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Gendis {
    @GET("api")
    Call<BannerModel> getBanner();

}
