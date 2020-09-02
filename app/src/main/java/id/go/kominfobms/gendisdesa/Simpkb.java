package id.go.kominfobms.gendisdesa;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Simpkb {
    @GET("apiandroid/index")
    Call<BannerModel> getBanner();

    @GET("apiandroid/antrian1")
    Call<NoAntrianModel> getLoket1();

    @GET("apiandroid/antrian2")
    Call<NoAntrianModel> getLoket2();

    @GET("apiandroid/antrian3")
    Call<NoAntrianModel> getLoket3();

    @GET("apiandroid/antrian4")
    Call<NoAntrianModel> getLoket4();
}
